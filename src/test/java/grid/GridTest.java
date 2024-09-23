package grid;

import car.Car;
import org.junit.jupiter.api.Test;
import parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    // Helper method to capture System.out and return the output
    private String simulateAndCaptureOutput(String filePath) throws IOException {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStreamCaptor));

        try {
            Parser parser = new Parser(filePath);
            Grid grid = new Grid(parser.parseFile());
            grid.simulate();
        } finally {
            System.setOut(originalOut); // Always restore System.out
        }

        return outputStreamCaptor.toString().trim();
    }

    @Test
    void noSimulationShouldBeRun() throws IOException {
        String output = simulateAndCaptureOutput("src/test/java/grid/test_empty.txt");
        String systemLineSeparator = System.getProperty("line.separator");
        String expectedOutput = "The file is empty, please ensure that it contains the relevant fields" + systemLineSeparator + "Due to an error, this simulation will not run";
        assertEquals(expectedOutput, output);
    }

    @Test
    void singleCarShouldReachRightDestination() throws IOException {
        String output = simulateAndCaptureOutput("src/test/java/grid/test_single_car.txt");
        String expectedOutput = "4 3 S";
        assertEquals(expectedOutput, output);
    }

    @Test
    void twoCarsShouldCollide() throws IOException {
        String output = simulateAndCaptureOutput("src/test/java/grid/test_collision_two_cars.txt");
        String systemLineSeparator = System.getProperty("line.separator");
        String expectedOutput = "A B " + systemLineSeparator + "5 4" + systemLineSeparator + "7";
        assertEquals(expectedOutput, output);
    }

    @Test
    void twoCarsShouldNotCollide() throws IOException {
        String output = simulateAndCaptureOutput("src/test/java/grid/test_no_collision_two_cars.txt");
        String expectedOutput = "no collision";
        assertEquals(expectedOutput, output);
    }

    @Test
    void fiveCarsShouldNotCollide() throws IOException {
        String output = simulateAndCaptureOutput("src/test/java/grid/test_no_collision_five_cars.txt");
        String expectedOutput = "no collision";
        assertEquals(expectedOutput, output);
    }

    @Test
    void fiveCarsShouldCollide() throws IOException {
        String output = simulateAndCaptureOutput("src/test/java/grid/test_collision_five_cars.txt");
        String systemLineSeparator = System.getProperty("line.separator");
        String expectedOutput = "A D " +
                systemLineSeparator + "3 1" + systemLineSeparator +
                "C E " + systemLineSeparator + "3 3" + systemLineSeparator +
                "4";
        assertEquals(expectedOutput, output);
    }
}
