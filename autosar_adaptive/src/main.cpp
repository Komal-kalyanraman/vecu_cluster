#include "mockup_com.hpp"

int main() {
    // Initialize AUTOSAR Adaptive platform (mockup)
    mockup_com::init();
    // Start vehicle speed service
    run_vehicle_speed_service();
    return 0;
}