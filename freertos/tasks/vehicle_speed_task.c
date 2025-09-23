#include "FreeRTOS.h"
#include "task.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void vVehicleSpeedTask(void *pvParameters) {
    srand((unsigned int)time(NULL));
    while (1) {
        int speed = rand() % 121; // 0 to 120 km/h
        printf("[VehicleSpeedTask] Vehicle speed: %d km/h\n", speed);
        fflush(stdout);
        vTaskDelay(pdMS_TO_TICKS(1000)); // 1 second
    }
}