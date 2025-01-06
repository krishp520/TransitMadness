# Transit Madness

**Transit Madness** is a Java program designed to simulate the boarding and unloading process of passengers on buses with one or two doors. It uses a command-based input to manage bus operations and outputs the order in which passengers reach their destination.

## Features

1. **Simulates Boarding and Unloading:**
    - Supports buses with single and double doors.
    - Handles boarding at the front or back depending on the bus type.

2. **Manifest Instructions:**
    - `loop`: Moves the current bus to the end of the queue.
    - `depart`: Unloads all passengers from the current bus in order.
    - Passenger name: Adds a passenger to the current bus.

3. **Dynamic Bus Management:**
    - Adjusts queue positions and ensures proper unloading sequence.

## Input Format
1. Number of buses.
2. Number of doors for each bus (1 or 2).
3. Number of instructions in the manifest.
4. Instructions including `loop`, `depart`, or passenger names.

## Output
- Prints the names of passengers in the order they are unloaded at the destination.

## Example Usage
**Input:**
```
3
2
1
2
9
Michael
Rob
depart
Michael
Gertrude
loop
Millie
depart
Ethel
depart
```

**Output:**
```
Rob
Michael
Gertrude
Millie
Ethel
```

