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
The error codes can be seen in [`Parser`](#parser).

# Design Choices

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

## Parser

Since there can be many inputs for many cars, I feel that the inputs should be read from a file
instead of being typed onto the terminal. For this reason a `Parser` class is a good solution.

## Grid

This is the class responsible for simulating the movement of the car.

