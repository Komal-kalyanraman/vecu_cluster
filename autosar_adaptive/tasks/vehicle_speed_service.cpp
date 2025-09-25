#include <iostream>
#include <thread>
#include <chrono>
#include <random>

void run_vehicle_speed_service() {
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_int_distribution<> speed_dist(0, 200);

    std::cout << "[vehicle_speed_service] Starting vehicle speed service (mockup)" << std::endl;
    for (int i = 0; i < 10; ++i) {
        int speed = speed_dist(gen);
        std::cout << "[vehicle_speed_service] Vehicle speed: " << speed << " km/h" << std::endl;
        std::this_thread::sleep_for(std::chrono::seconds(1));
    }
    std::cout << "[vehicle_speed_service] Service finished" << std::endl;
}