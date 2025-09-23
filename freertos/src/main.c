#include "FreeRTOS.h"
#include "task.h"

// Forward declaration
void vVehicleSpeedTask(void *pvParameters);

int main(void) {
    xTaskCreate(vVehicleSpeedTask, "SpeedTask", 256, NULL, 1, NULL);
    vTaskStartScheduler();
    for (;;);
    return 0;
}