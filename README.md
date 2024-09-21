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

# Design Choices

I intend to model the problem using the following classes in java

## Car

This class contains information about the car.

## Grid

This is the class responsible for simulating the movement of the car.