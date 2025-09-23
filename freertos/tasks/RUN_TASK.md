# How to Build and Run the FreeRTOS Vehicle Speed Task (POSIX Simulation)

This guide explains how to build and run the FreeRTOS POSIX simulation for the vehicle speed task using CMake and GCC inside the dev container.

## Why POSIX Simulation?

- **Rapid Development:** Test and debug FreeRTOS tasks on your PC before deploying to hardware.
- **No Hardware Required:** Simulate embedded tasks in a standard Linux environment.
- **Easy Debugging:** Use standard Linux tools (GDB, Valgrind, etc.) for development.

## Prerequisites

- The FreeRTOS dev container (with GCC, CMake, GDB, etc.) is already set up.
- All source files and CMakeLists.txt are present in the `/workspace` directory inside the container.

## Steps to Build and Run

1. **Open a terminal in the dev container:**

   ```bash
   cd /workspace
   ```

2. **Create the build directory (if not present):**

   ```bash
   mkdir -p build
   cd build
   ```

3. **Configure the project with CMake:**

   ```bash
   cmake ..
   ```

4. **Build the FreeRTOS simulation:**

   ```bash
   make
   ```

   This will generate the simulation binary, typically named `freertos_sim`.

5. **Run the simulation:**

   ```bash
   ./freertos_sim
   ```

6. **Expected Output:**

   You should see output similar to:

   ```
   [VehicleSpeedTask] Vehicle speed: 54 km/h
   [VehicleSpeedTask] Vehicle speed: 37 km/h
   [VehicleSpeedTask] Vehicle speed: 62 km/h
   ...
   ```

   The task prints a random speed value every second.

## Notes

- The build directory is ignored by git except for the `.keep` file.
- You can use `gdb` for debugging:
  ```bash
  gdb ./freertos_sim
  ```
- To clean the build:
  ```bash
  make clean
  ```

## Troubleshooting

- If you see CMake errors, check that all source files are present and `CMakeLists.txt` is correct.
- If you see linker errors about missing FreeRTOS symbols, ensure the POSIX port and kernel sources are included in the build.
- For verbose build output, use:
  ```bash
  make VERBOSE=1
  ```