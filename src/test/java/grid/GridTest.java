package grid;

import car.Car;
import org.junit.jupiter.api.Test;
import parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void noSimulationShouldBeRun() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStreamCaptor));
        Parser parser = new Parser("src/test/java/grid/test_empty.txt");
        try {
            Grid grid = new Grid(parser.parseFile());
            grid.simulate();
            String systemLineSeparator = System.getProperty("line.separator");
            String expectedOutput = "The file is empty, please ensure that it contains the relevant fields" + systemLineSeparator + "Due to an error, this simulation will not run";
            assertEquals(expectedOutput, outputStreamCaptor.toString());
        } catch (IOException e) {
            fail("File cannot be opened");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void singleCarShouldReachRightDestination() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStreamCaptor));
        Parser parser = new Parser("src/test/java/grid/test_single_car.txt");
        try {
            Grid grid = new Grid(parser.parseFile());
            grid.simulate();
            String expectedOutput = "4 3 S";
            assertEquals(expectedOutput, outputStreamCaptor.toString());
        } catch (IOException e) {
            fail("File cannot be opened");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void twoCarsShouldCollide() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStreamCaptor));
        Parser parser = new Parser("src/test/java/grid/test_collision_two_cars.txt");
        try {
            Grid grid = new Grid(parser.parseFile());
            grid.simulate();
            String systemLineSeparator = System.getProperty("line.separator");
            String expectedOutput = "A B " + systemLineSeparator + "5 4" + systemLineSeparator + "7";
            assertEquals(expectedOutput, outputStreamCaptor.toString());
        } catch (IOException e) {
            fail("File cannot be opened");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void twoCarsShouldNotCollide() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStreamCaptor));
        Parser parser = new Parser("src/test/java/grid/test_no_collision_two_cars.txt");
        try {
            Grid grid = new Grid(parser.parseFile());
            grid.simulate();
//            String systemLineSeparator = System.getProperty("line.separator");
            String expectedOutput = "no collision";
            assertEquals(expectedOutput, outputStreamCaptor.toString());
        } catch (IOException e) {
            fail("File cannot be opened");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void fiveCarsShouldNotCollide() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStreamCaptor));
        Parser parser = new Parser("src/test/java/grid/test_no_collision_five_cars.txt");
        try {
            Grid grid = new Grid(parser.parseFile());
            grid.simulate();
//            String systemLineSeparator = System.getProperty("line.separator");
            String expectedOutput = "no collision";
            assertEquals(expectedOutput, outputStreamCaptor.toString());
        } catch (IOException e) {
            fail("File cannot be opened");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void fiveCarsShouldCollide() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStreamCaptor));
        Parser parser = new Parser("src/test/java/grid/test_collision_five_cars.txt");
        try {
            Grid grid = new Grid(parser.parseFile());
            grid.simulate();
            String systemLineSeparator = System.getProperty("line.separator");
            String expectedOutput = "A D " +
                    systemLineSeparator + "3 1" + systemLineSeparator +
                    "C E " + systemLineSeparator + "3 3" + systemLineSeparator +
                    "4";
            assertEquals(expectedOutput, outputStreamCaptor.toString());
        } catch (IOException e) {
            fail("File cannot be opened");
        } finally {
            System.setOut(originalOut);
        }
    }
}