package parser;

import car.Car;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    String filePath;

    public Parser(String filePath) {
        this.filePath = filePath;
    }

    private Car[] parseSingleCar(BufferedReader reader, String positionLine, int xGridBounds, int yGridBounds) {
        try {
            String[] firstLineInfo = positionLine.split(" ");
            int xCoord = Integer.parseInt(firstLineInfo[0]);
            int yCoord = Integer.parseInt(firstLineInfo[1]);
            char direction = firstLineInfo[2].charAt(0);

            // Check if the coordinates are negative or if the direction is outside NESW
            if (xCoord < 0 || yCoord < 0) {
                System.out.println("Coordinates of the car are negative, this is not accepted");
                return new Car[]{};
            } else if (xCoord > xGridBounds || yCoord > yGridBounds) {
                System.out.println("Coordinates of the car are outside bounds, this is not accepted");
                return new Car[]{};
            } else if ("NESW".indexOf(direction) == -1) {
                // Direction is something else, not NESW
                System.out.println("Direction is outside NESW, this is not accepted");
                return new Car[]{};
            }

            char[] commandLine = reader.readLine().toCharArray();

            // Check if the list of commands are valid
            for (char command : commandLine) {
                if (command != 'F' && command != 'R' && command != 'L') {
                    System.out.println("Movement commands contain an unknown character, not accepted");
                    return new Car[]{};
                }
            }

            // All the information is right, return a new car array
            return new Car[]{new Car("SingleCar", xCoord, yCoord, direction, xGridBounds, yGridBounds, commandLine)};
        } catch (Exception e) {
            System.out.println("File structure is wrong, this is not accepted");
            return new Car[]{};
        }

    }

    private Car[] parseMultiCar(BufferedReader reader, String carLabelLine, int xGridBounds, int yGridBounds) {
        try {
            List<Car> cars = new ArrayList<>();
            while (carLabelLine != null) {
                // Move on from empty buffer in between
                carLabelLine = reader.readLine();
                // First, we read the car label (e.g., A, B, C, etc.)
                String carLabel = carLabelLine.trim();

                // Now we expect the next line to be the position and direction of the car
                String positionLine = reader.readLine();
                String[] positionInfo = positionLine.split(" ");
                int xCoord = Integer.parseInt(positionInfo[0]);
                int yCoord = Integer.parseInt(positionInfo[1]);
                char direction = positionInfo[2].charAt(0);

                // Check if the coordinates are negative or if the direction is outside NESW
                if (xCoord < 0 || yCoord < 0) {
                    System.out.println("Coordinates of the car are negative, this is not accepted");
                    return new Car[]{};
                } else if (xCoord > xGridBounds || yCoord > yGridBounds) {
                    System.out.println("Coordinates of the car are outside bounds, this is not accepted");
                    return new Car[]{};
                } else if ("NESW".indexOf(direction) == -1) {
                    // Direction is something else, not NESW
                    System.out.println("Direction is outside NESW, this is not accepted");
                    return new Car[]{};
                }

                // Now read the movement commands
                char[] commandLine = reader.readLine().toCharArray();

                // Check if the list of commands is valid
                for (char command : commandLine) {
                    if (command != 'F' && command != 'R' && command != 'L') {
                        System.out.println("Movement commands contain an unknown character, not accepted");
                        return new Car[]{}; // returning empty array if invalid
                    }
                }

                // If everything is valid, create a new car and add it to the list
                cars.add(new Car(carLabel, xCoord, yCoord, direction, xGridBounds, yGridBounds, commandLine));

                // Read the next car label (if any)
                carLabelLine = reader.readLine();
            }

            // Convert the list of cars to an array and return it
            return cars.toArray(new Car[0]);

        } catch (Exception e) {
            System.out.println("File structure is wrong, this is not accepted");
            return new Car[]{}; // returning empty array in case of an error
        }
    }


    public Car[] parseFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();
        // Return empty car if the file is empty
        if (line == null || line.isEmpty()) {
            System.out.println("The file is empty, please ensure that it contains the relevant fields");
            return new Car[]{};
        }

        String[] gridSize = line.split(" ");
        int gridWidth = Integer.parseInt(gridSize[0]);
        int gridHeight = Integer.parseInt(gridSize[1]);
        // Return Empty list of cars if grid height is wrong
        if (gridWidth < 0 || gridHeight < 0) {
            System.out.println("GridWidth or GridHeight is less than 0, please modify this and try again");
            return new Car[]{};
        }
        line = reader.readLine();
        if (!line.isEmpty()) {
            // If the first number is a digit
            return this.parseSingleCar(reader, line, gridWidth, gridHeight);
        } else {
            return this.parseMultiCar(reader, line, gridWidth, gridHeight);
        }

    }
}
