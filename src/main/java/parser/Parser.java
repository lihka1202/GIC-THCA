package parser;

import car.Car;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
            String commandLine = reader.readLine();
            return new Car[]{new Car("SingelCar", xCoord, yCoord, direction, xGridBounds, yGridBounds, commandLine)};
        } catch (IOException e) {
            System.out.println("File structure is wrong");
            return new Car[]{};
        }

    }

    private Car[] parseMultiCar(BufferedReader reader, String positionLine) {
        return new Car[]{};
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

        return new Car[]{};

    }
}
