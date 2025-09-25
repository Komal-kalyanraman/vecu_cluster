cmake_minimum_required(VERSION 3.10)
project(autosar_adaptive_demo)

set(CMAKE_CXX_STANDARD 17)

include_directories(
    include
    ara/com
    tasks
)

add_executable(autosar_adaptive_demo
    src/main.cpp
    ara/com/mockup_com.cpp
    tasks/vehicle_speed_service.cpp
)