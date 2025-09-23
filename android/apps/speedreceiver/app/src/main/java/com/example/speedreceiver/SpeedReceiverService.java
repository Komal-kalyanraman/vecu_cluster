package com.example.speedreceiver;

import android.app.Service;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class SpeedReceiverService extends Service {
    private static final String CHANNEL_ID = "SpeedReceiverServiceChannel";
    private static final String TAG = "SpeedReceiverService";
    private static final int PORT = 5005; // UDP port to listen on
    private volatile boolean running = true;
    private Thread udpThread;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        Notification notification = new Notification.Builder(this, CHANNEL_ID)
            .setContentTitle("Speed Receiver Service")
            .setContentText("Listening for vehicle speed...")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .build();
        startForeground(1, notification);

        udpThread = new Thread(() -> {
            try (DatagramSocket socket = new DatagramSocket(PORT)) {
                byte[] buf = new byte[128];
                while (running) {
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    String msg = new String(packet.getData(), 0, packet.getLength()).trim();
                    Log.i(TAG, "Received vehicle speed: " + msg);
                }
            } catch (Exception e) {
                Log.e(TAG, "UDP error", e);
            }
        });
        udpThread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Service logic handled in background thread
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        running = false;
        if (udpThread != null) {
            udpThread.interrupt();
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                CHANNEL_ID,
                "Speed Receiver Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
}