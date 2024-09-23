# Introduction

This README.md contains general ideas on how my implementation is going to work
alongside with some insight on why I made certain design choices.

I will do my best to be as precise and exhaustive as possible in explaining my design choices while ensuring that these
are equally concise.

# Table of Contents

# Problem statement

The problem statement can be broken down into 2 main parts (in the simplest way):

- For a given starting position of a car and a grid, compute the end position after some instructions
- For a bunch of cars, using the code above determine if any of them will collide.

For the time being, I think the first task is fine (conceptually), however there are many assumptions I would need to
make
in the case of the second task.

# Basic Observations

Each car has a few attributes associated to it and can perform some actions.

## Attributes

The car has 2 main attributes associated to it:

- `Position`: (x,y) are positional attributes that indicate where the car is in the grid.
- `Direction`: The direction (`N` or `S` or `E` or `W`) indicate the heading of the car at any point.

## Actions

The car can perform 2 actions which would affect its `Position` or `Direction`:

- `Move`
    - In this case `F` denotes that he car needs to move forward.
- `Rotate`
    - The car can either rotate to the left or the right:
        - `L` is rotate the left.
        - `R` is rotate to the right.

## List of Assumptions made

This section just contains a list of assumptions I make for the second task (some of them could also be related to the
first task).

### `Rotate` and `Move` are commands on the same level and take the same amount of time.

Say if the command is something like this:

```text
"FFFFFLRFLRLF"
```

> Regardless of whether the command is `Move` or `Rotate`, each action will take one unit of time.

### Inaccurate inputs

If the program were to receive any inputs of the following kind:

- Negative number inputs.
- Out of bound coordinates for the cars
- Usage of a direction outside of `N`, `S`, `E`, `W`.
- Usage of an action outside `F`, `L`, `R`.
- Badly formatted inputs (missing lines etc.)

Then the program will just ignore that file all together and inform the user.
The error codes can be seen when tests are run for [`Parser`](#parser).

### At a single point, multiple collisions can happen

This is another assumption/inference that I have made from the problem statement
and implemented code bearing this mind.

# Design Choices

Since TDD was the developmental model used, tests were written before the actual logic
of the classes. The docum

I intend to model the problem using the following classes in java.

## Class `Car`

The `Car` class represents a car that moves on a grid. Below are details about the class and its methods:

### Attributes:

- `carName`: The name of the car (e.g., "A", "B", etc.).
- `xCoordinate` & `yCoordinate`: The current position of the car on the grid.
- `direction`: The current direction of the car (`N`, `E`, `S`, `W`).
- `xGridBounds` & `yGridBounds`: The boundaries of the grid (i.e., the maximum x and y values the car can reach).
- `commands`: A list of commands (`F`, `L`, `R`) that dictate how the car should move.
- `nextCommandIdx`: Tracks the next command to be executed.
- `commandComplete`: A boolean indicating whether the car has finished executing all commands.

### Methods:

- `Car(String carName, int xCoordinate, int yCoordinate, char direction, int xGridBounds, int yGridBounds, char[] commands)`:
  Constructor for initializing a new car with a name, position, direction, grid bounds, and a sequence of commands.
- `getCarName()`: Returns the name of the car.
- `getxCoordinate()`: Returns the current x-coordinate of the car.
- `getyCoordinate()`: Returns the current y-coordinate of the car.
- `getDirection()`: Returns the current direction of the car.
- `moveCarForward()`: Moves the car forward in its current direction, respecting grid boundaries.
- `rotateCar()`: Rotates the car either left or right based on the current command.
- `isCommandComplete()`: Returns whether the car has completed all its commands.
- `move()`: Executes the current command (either move forward or rotate) and advances to the next command in the
  sequence.

## Example

Here's an example of how to instantiate and use a `Car` object:

```java
public class Main {
    public static void main(String[] args) {
        char[] commands = {'F', 'F', 'R', 'F', 'L', 'F'};
        Car car = new Car("A", 1, 1, 'N', 10, 10, commands);

        // Execute commands for the car
        while (!car.isCommandComplete()) {
            car.move();
            System.out.println(car.getCarName() + " is at position (" + car.getxCoordinate() + ", " + car.getyCoordinate() + ") facing " + car.getDirection());
        }
    }
}
```

## Class `Parser`

The `Parser` class is responsible for reading car configuration data from a file and creating `Car` objects based on the
parsed input. It supports parsing both single-car and multi-car input formats, validating car positions, directions, and
movement commands, and returning the list of `Car` objects for further simulation.

### Attributes:

- **`filePath`**: A `String` representing the path to the input file containing the grid and car data.

### Constructor:

- **`Parser(String filePath)`**: Initializes the parser with the given path to the input file containing car data (
  positions, directions, and commands).

### Methods:

#### `Car[] parseFile()`

- **Description**: This is the main method that reads the input file, processes the grid size, and returns an array
  of `Car` objects. It checks for empty files and validates all data before creating the cars.
- **Returns**: A `Car[]` array of parsed cars. If the file is empty or invalid, it returns an empty array.
- **Throws**: `IOException` if there is an issue reading the file.

#### `Car[] parseSingleCar(BufferedReader reader, String positionLine, int xGridBounds, int yGridBounds)`

- **Description**: Parses the data for a single car. It reads the car's position, direction, and movement commands, and
  ensures that the car's position and commands are valid.
- **Parameters**:
    - `BufferedReader reader`: The reader to read the file.
    - `String positionLine`: The line from the file containing the car's initial position and direction (e.g., `1 2 N`).
    - `int xGridBounds`: The maximum x-coordinate value for the grid.
    - `int yGridBounds`: The maximum y-coordinate value for the grid.
- **Returns**: A `Car[]` array containing a single car, or an empty array if the data is invalid.

#### `Car[] parseMultiCar(BufferedReader reader, String carLabelLine, int xGridBounds, int yGridBounds)`

- **Description**: Parses data for multiple cars. It reads the position, direction, and commands for each car and
  ensures that the data is valid.
- **Parameters**:
    - `BufferedReader reader`: The reader to read the file.
    - `String carLabelLine`: The line containing the car label (e.g., `A`, `B`, etc.).
    - `int xGridBounds`: The maximum x-coordinate value for the grid.
    - `int yGridBounds`: The maximum y-coordinate value for the grid.
- **Returns**: A `Car[]` array containing the parsed cars, or an empty array if any of the cars' data is invalid.

#### `boolean isValidPositionAndDirection(int xCoord, int yCoord, char direction, int xGridBounds, int yGridBounds)`

- **Description**: Helper method to validate whether the car's position and direction are within the grid bounds and
  follow correct conventions (i.e., direction must be one of `N`, `E`, `S`, `W`).
- **Parameters**:
    - `int xCoord`: The car's x-coordinate.
    - `int yCoord`: The car's y-coordinate.
    - `char direction`: The direction the car is facing (`N`, `E`, `S`, `W`).
    - `int xGridBounds`: The grid's maximum x-boundary.
    - `int yGridBounds`: The grid's maximum y-boundary.
- **Returns**: `true` if the position and direction are valid, `false` otherwise.

#### `boolean isValidCommandLine(char[] commandLine)`

- **Description**: Helper method to validate that the movement commands only contain valid characters (`F`, `L`, `R`).
- **Parameters**:
    - `char[] commandLine`: The array of movement commands (e.g., `['F', 'L', 'R']`).
- **Returns**: `true` if all commands are valid, `false` otherwise.

### Input File Structure

The input file can contain one or more cars, with grid boundaries specified at the top. Each car is defined by its
starting position, direction, and movement commands.

- **Single Car Example**:
    ```
    10 10             # Grid size (width height)
    1 2 N             # Car position and direction (x y direction)
    FFRFLFRF          # Car movement commands (F = Forward, L = Left, R = Right)
    ```

- **Multi-Car Example**:
    ```
    10 10             # Grid size (width height)
    
    A                 # Car label
    1 2 N             # Car position and direction (x y direction)
    FFRFLFRF          # Car movement commands
    
    B                 # Another car label
    3 4 E             # Another car's position
    LFRFFLFFR         # Another car's movement commands
    ```

### Example Usage

```java
public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser("path/to/input.txt");
        try {
            Car[] cars = parser.parseFile();
            for (Car car : cars) {
                System.out.println("Car " + car.getCarName() + " is at position (" + car.getxCoordinate() + ", " + car.getyCoordinate() + ")");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
```

## Class `Grid`

The `Grid` class manages the simulation of multiple cars moving on a grid. It handles the movement of each car according
to their commands and checks for collisions when multiple cars occupy the same position on the grid. The class supports
both single-car and multi-car simulations.

### Attributes:

- **`listOfCarsToMove`**: An array of `Car` objects that will be simulated within the grid.

### Constructor:

- **`Grid(Car[] listOfCarsToMove)`**: Initializes the grid with the provided list of cars to simulate. Each car has
  predefined commands for movement.

### Methods:

#### `void simulate()`

- **Description**: The main method that runs the simulation for all cars on the grid. It moves the cars step by step,
  checking for collisions. If a collision occurs, the simulation stops, and the cars involved in the collision are
  printed along with their position and the step at which the collision occurred. If no collision occurs, the final
  position of each car is printed.
- **Steps in Simulation**:
    1. If there are no cars to move, prints an error message and halts the simulation.
    2. If only one car is present, moves the car based on its commands and prints the final position.
    3. If multiple cars are present, moves each car step by step and tracks their positions.
    4. If a collision is detected (two or more cars share the same position), the simulation stops and the cars involved
       in the collision, the position, and the step number are printed.
    5. If no collision occurs by the end of the simulation, prints "no collision".
- **Output**:
    - In the event of a collision:
        - The car names involved in the collision (e.g., `A B`).
        - The position of the collision (e.g., `5 4`).
        - The step at which the collision occurred (e.g., `7`).
    - If there are no collisions:
        - Prints `no collision`.

#### `boolean areAllCommandsComplete()`

- **Description**: Helper method that checks if all cars have completed their commands.
- **Returns**: `true` if all cars have completed their commands, `false` otherwise.

### Example Usage:

```java
public class Main {
    public static void main(String[] args) {
        // Create cars from the parser
        Car[] cars = {
                new Car("A", 1, 2, 'N', 10, 10, new char[]{'F', 'F', 'R', 'F'}),
                new Car("B", 5, 5, 'E', 10, 10, new char[]{'L', 'F', 'F', 'R'}),
        };

        // Create a Grid and simulate
        Grid grid = new Grid(cars);
        grid.simulate();
    }
}
```

## Unit Testing: `CarTest`

The `CarTest` class contains unit tests that validate the behavior of the `Car` class. These tests ensure that the car
moves correctly according to its commands (`F`, `L`, `R`), stays within grid boundaries, and properly handles command
completion.

### Test Methods:

#### `void carShouldMoveLeftByOneUnit()`

- **Description**: Verifies that a car facing west (`W`) moves left by one unit on the x-axis when
  the `moveCarForward()` method is called.

#### `void carShouldMoveRightByOneUnit()`

- **Description**: Verifies that a car facing east (`E`) moves right by one unit on the x-axis when
  the `moveCarForward()` method is called.

#### `void carShouldNotCrossLeftMostBounds()`

- **Description**: Ensures that a car facing west at the leftmost boundary (x = 0) does not move beyond the boundary
  when the `moveCarForward()` method is called.

#### `void carShouldNotCrossRightMostBounds()`

- **Description**: Ensures that a car facing east at the rightmost boundary does not move beyond the grid boundary when
  the `moveCarForward()` method is called.

#### `void carShouldNotCrossUpperMostBounds()`

- **Description**: Ensures that a car facing north at the top boundary does not move beyond the grid boundary when
  the `moveCarForward()` method is called.

#### `void carShouldNotCrossLowerMostBounds()`

- **Description**: Ensures that a car facing south at the bottom boundary does not move beyond the grid boundary when
  the `moveCarForward()` method is called.

#### `void carShouldRotateRightByOne()`

- **Description**: Verifies that a car facing north (`N`) rotates to east (`E`) after a right turn (`R`).

#### `void carShouldRotateRightByTwo()`

- **Description**: Verifies that a car facing north rotates twice to face south (`S`) after two right turns (`R`).

#### `void carShouldRotateRightByThree()`

- **Description**: Verifies that a car facing north rotates three times to face west (`W`) after three right turns.

#### `void carShouldRotateRightByFour()`

- **Description**: Verifies that a car facing north rotates four times to face north again, completing a full circle
  after four right turns.

#### `void carShouldRotateLeftByOne()`

- **Description**: Verifies that a car facing north rotates to west (`W`) after a left turn (`L`).

#### `void carShouldRotateLeftByTwo()`

- **Description**: Verifies that a car facing north rotates twice to face south after two left turns.

#### `void carShouldRotateLeftByThree()`

- **Description**: Verifies that a car facing north rotates three times to face east after three left turns.

#### `void carShouldRotateLeftByFour()`

- **Description**: Verifies that a car facing north rotates four times to face north again after four left turns.

#### `void carReachesRightLocationAfterOneCommand()`

- **Description**: Verifies that a car facing north moves forward by one unit when the command is `F`.

#### `void carReachesRightLocationAfterManyCommands()`

- **Description**: Simulates a series of commands (`F`, `F`, `R`, `F`, `F`, `F`, `R`, `R`, `L`, `F`) and verifies that
  the car reaches the correct final position and direction after all commands have been executed.

#### `void carShouldNotMoveAfterCommandListIsComplete()`

- **Description**: Ensures that the car stops moving after it has completed its list of commands, even if the `move()`
  method is called additional times.

## Unit Testing: `ParserTest`

The `ParserTest` class contains unit tests to validate the functionality of the `Parser` class, ensuring that it
correctly parses the input files for car data, handles errors gracefully, and verifies correct file structures. The
tests also check that invalid configurations (such as out-of-bound coordinates, wrong commands, or improper file
structures) are properly caught and handled.

### Helper Methods

#### `String captureOutputDuringParsing(Parser parser)`

- **Description**: Captures and returns the output printed to the console during parsing.
- **Parameters**:
    - `parser`: An instance of the `Parser` class.
- **Returns**: A `String` representing the captured console output.

#### `void assertCarCountFromFile(String filePath, int expectedCount)`

- **Description**: Parses the file at the given path and asserts that the number of cars parsed matches the expected
  count.
- **Parameters**:
    - `filePath`: Path to the input file.
    - `expectedCount`: The expected number of cars in the parsed result.

#### `void assertCarParseWithErrorMessage(String filePath, String expectedMessage)`

- **Description**: Captures console output during parsing and verifies that the expected error message appears in the
  output. Also asserts that no cars were parsed.
- **Parameters**:
    - `filePath`: Path to the input file.
    - `expectedMessage`: The error message expected to appear in the console output.

### Test Methods

#### `void shouldCollectOneCarFromFile()`

- **Description**: Tests if the `Parser` correctly parses a file containing one car.
- **File Used**: `test_single_car.txt`
- **Expected Behavior**: The parser should return 1 car.

#### `void shouldCollectMultipleCarsFromFile()`

- **Description**: Tests if the `Parser` correctly parses a file containing multiple cars.
- **File Used**: `test_multi_car.txt`
- **Expected Behavior**: The parser should return 2 cars.

#### `void shouldAvoidOneCarOutsideRightmostBoundFromSingleCarParse()`

- **Description**: Tests if the `Parser` properly detects and handles a car with coordinates that are outside the
  rightmost boundary.
- **File Used**: `test_single_car_right_out_of_bounds.txt`
- **Expected Behavior**: The parser should output an error about the coordinates being outside bounds and return 0 cars.

#### `void shouldAvoidOneCarOutsideRightmostBoundFromMultiCarParse()`

- **Description**: Similar to the previous test but for a multi-car file.
- **File Used**: `test_multi_car_right_out_of_bounds.txt`
- **Expected Behavior**: The parser should output an error about the coordinates being outside bounds and return 0 cars.

#### `void shouldAvoidOneCarOutsideUppermostBoundFromSingleCarParse()`

- **Description**: Tests if the `Parser` correctly identifies a car outside the uppermost boundary in a single-car file.
- **File Used**: `test_single_car_upper_out_of_bounds.txt`
- **Expected Behavior**: The parser should output an error about the coordinates being outside bounds and return 0 cars.

#### `void shouldAvoidOneCarOutsideUppermostBoundFromMultiCarParse()`

- **Description**: Similar to the previous test but for a multi-car file.
- **File Used**: `test_multi_car_upper_out_of_bounds.txt`
- **Expected Behavior**: The parser should output an error about the coordinates being outside bounds and return 0 cars.

#### `void shouldAvoidCarWithNegativeCarCoordinates()`

- **Description**: Tests if the `Parser` correctly identifies a car with negative coordinates and outputs an appropriate
  error.
- **File Used**: `test_negative_coordinates.txt`
- **Expected Behavior**: The parser should output an error about negative coordinates and return 0 cars.

#### `void avoidParsingEmptyFile()`

- **Description**: Tests if the `Parser` handles an empty file gracefully and outputs an appropriate message.
- **File Used**: `test_empty.txt`
- **Expected Behavior**: The parser should output an error about the file being empty and return 0 cars.

#### `void avoidParsingFileWithNegativeGridHeight()`

- **Description**: Tests if the `Parser` detects a file with a negative grid height.
- **File Used**: `test_negative_grid_coordinates.txt`
- **Expected Behavior**: The parser should output an error about the grid height being invalid and return 0 cars.

#### `void avoidParsingFileWithNegativeGridWidth()`

- **Description**: Similar to the previous test but for negative grid width.
- **File Used**: `test_negative_grid_coordinates.txt`
- **Expected Behavior**: The parser should output an error about the grid width being invalid and return 0 cars.

#### `void shouldAvoidSingleCarWithWrongCommands()`

- **Description**: Tests if the `Parser` handles invalid commands (i.e., commands other than `F`, `L`, `R`) in a
  single-car file.
- **File Used**: `test_single_car_wrong_commands.txt`
- **Expected Behavior**: The parser should output an error about unknown movement commands and return 0 cars.

#### `void shouldAvoidMultiCarWithWrongCommands()`

- **Description**: Similar to the previous test but for a multi-car file.
- **File Used**: `test_multi_car_wrong_commands.txt`
- **Expected Behavior**: The parser should output an error about unknown movement commands and return 0 cars.

#### `void shouldAvoidMultiCarWithWrongDirection()`

- **Description**: Tests if the `Parser` handles invalid directions (i.e., directions other than `N`, `E`, `S`, `W`) in
  a multi-car file.
- **File Used**: `test_multi_car_wrong_direction.txt`
- **Expected Behavior**: The parser should output an error about the direction being invalid and return 0 cars.

#### `void shouldAvoidSingleCarWithWrongDirection()`

- **Description**: Similar to the previous test but for a single-car file.
- **File Used**: `test_single_car_wrong_direction.txt`
- **Expected Behavior**: The parser should output an error about the direction being invalid and return 0 cars.

#### `void shouldAvoidSingleCarWithWrongFileStructure()`

- **Description**: Tests if the `Parser` handles files with incorrect structure in a single-car file.
- **File Used**: `test_single_car_wrong_file_structure.txt`
- **Expected Behavior**: The parser should output an error about the file structure being wrong and return 0 cars.

#### `void shouldAvoidMultiCarWithWrongFileStructure()`

- **Description**: Similar to the previous test but for a multi-car file.
- **File Used**: `test_multi_car_wrong_file_structure.txt`
- **Expected Behavior**: The parser should output an error about the file structure being wrong and return 0 cars.

### Integration Test for Car Final Position

#### `void shouldReachRelevantPosition()`

- **Description**: This integration test checks if the parser correctly processes a file and simulates car movement to
  reach the expected final position.
- **File Used**: `test_single_car.txt`
- **Expected Behavior**: The car should reach the final position `(4, 3)` facing south (`S`).

## Unit Testing: `GridTest`

The `GridTest` class contains unit tests that validate the behavior of the `Grid` class. These tests ensure that the car
movement simulation, as well as collision detection, is handled correctly.

### Helper Methods

#### `String simulateAndCaptureOutput(String filePath)`

- **Description**: Runs the simulation for a given input file, captures the output from `System.out`, and returns it as
  a string.
- **Parameters**:
    - `filePath`: The path to the input file that contains the grid and car data.
- **Returns**: The captured simulation output as a string.

---

### Test Methods

#### `void noSimulationShouldBeRun()`

- **Description**: Tests the behavior of the `Grid` class when the input file is empty. The simulation should not run,
  and an error message should be printed.
- **File Used**: `test_empty.txt`
- **Expected Output**: The file is empty, please ensure that it contains the relevant fields Due to an error, this
  simulation will not run

#### `void singleCarShouldReachRightDestination()`

- **Description**: Tests if a single car reaches the correct final position and direction after executing its movement
  commands.
- **File Used**: `test_single_car.txt`
- **Expected Output**:

```text
4 3 S
```

#### `void twoCarsShouldCollide()`

- **Description**: Tests if two cars collide during the simulation. The output should include the names of the cars
  involved in the collision, the position of the collision, and the step at which the collision occurred.
- **File Used**: `test_collision_two_cars.txt`
- **Expected Output**:

```text
A B
5 4
7
```

#### `void twoCarsShouldNotCollide()`

- **Description**: Tests if two cars do not collide during the simulation. The simulation should complete without
  detecting any collisions.
- **File Used**: `test_no_collision_two_cars.txt`
- **Expected Output**:

```text
no collision
```

#### `void fiveCarsShouldNotCollide()`

- **Description**: Tests if five cars do not collide during the simulation. The simulation should complete without
  detecting any collisions.
- **File Used**: `test_no_collision_five_cars.txt`
- **Expected Output**:

```text
no collision
```

#### `void fiveCarsShouldCollide()`

- **Description**: Tests if multiple collisions occur during the simulation. Two groups of cars collide separately, and
  the output includes the names of the cars involved, the positions of the collisions, and the step at which the
  collisions occurred.
- **File Used**: `test_collision_five_cars.txt`
- **Expected Output**:

```text
A D
3 1
C E
3 3
4
```

# How to run

## Prerequisites

- Java 11 or higher
- Maven (to build and compile the project)

## Steps to run

- Enter relevant coordinates and information of the car(s) in `input.txt`.
    - **Follow the format in [Input File Structure](#input-file-structure), any deviations will prevent the simulation
      from running**.
- Compile the project using Maven
    ```shell
    mvn compile
    ```
- Run the compiled classes using java.
    ```shell
    java -cp target/classes main.Main
    ```
- If you wish to run tests, just run the following commands:
  ```shell
  mvn test
  ```


