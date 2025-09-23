cmake_minimum_required(VERSION 3.10)
project(freertos_sim)

set(CMAKE_C_STANDARD 99)

include_directories(
    ${CMAKE_SOURCE_DIR}/freertos/portable/ThirdParty/GCC/Posix
    ${CMAKE_SOURCE_DIR}/freertos/include
    ${CMAKE_SOURCE_DIR}
    ${CMAKE_SOURCE_DIR}/src
    ${CMAKE_SOURCE_DIR}/tasks
)

add_definitions(-DpvPortMalloc=malloc -DvPortFree=free)

file(GLOB KERNEL_SRC
    "freertos/*.c"
)

file(GLOB APP_SRC
    "src/*.c"
    "tasks/*.c"
)

# Add the POSIX port source file explicitly
set(POSIX_PORT_SRC
    freertos/portable/ThirdParty/GCC/Posix/port.c
    freertos/portable/ThirdParty/GCC/Posix/utils/wait_for_event.c
)

add_executable(freertos_sim ${KERNEL_SRC} ${APP_SRC} ${POSIX_PORT_SRC})
target_link_libraries(freertos_sim pthread)