# Adaptive AUTOSAR vECU Demo

This container simulates an Adaptive AUTOSAR vECU with a mockup communication stack and a simple vehicle speed service.

## Features

- Mockup Ara::Com communication (replaceable with real stack)
- Simple vehicle speed service (prints random speed values)
- Ready for extension to real AUTOSAR Adaptive stack

## Build & Run

```bash
cmake -S . -B build
cmake --build build
./build/autosar_adaptive_demo
```

## Development

- Edit `tasks/vehicle_speed_service.cpp` to change the service logic.
- Replace `ara/com/mockup_com.cpp` with a real Ara::Com implementation as needed.