package parser;

import car.Car;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void shouldCollectOneCarFromFile() {
        Parser parser = new Parser("src/test/java/parser/test_single_car.txt");
        try {
            Car[] listOfCars = parser.parseFile();
            assertEquals(1, listOfCars.length);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }

    @Test
    void shouldCollectMultipleCarsFromFile() {
        Parser parser = new Parser("src/test/java/parser/test_multi_car.txt");
        try {
            Car[] listOfCars = parser.parseFile();
            assertEquals(2, listOfCars.length);
            assertEquals("A", listOfCars[0].getCarName());
            assertEquals("B", listOfCars[1].getCarName());
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }


    @Test
    void shouldAvoidOneCarOutsideRightmostBoundFromSingleCarParse() {
        Parser parser = new Parser("src/test/java/parser/test_single_car_right_out_of_bounds.txt");
        try {
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStreamCaptor));
            Car[] listOfCars = parser.parseFile();

            // Check if the error message is printed out to console
            assertTrue(outputStreamCaptor.toString().contains("Coordinates of the car are outside bounds, this is not accepted"));


            assertEquals(0, listOfCars.length);
            System.setOut(originalOut);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }

    @Test
    void shouldAvoidOneCarOutsideRightmostBoundFromMultiCarParse() {
        Parser parser = new Parser("src/test/java/parser/test_multi_car_right_out_of_bounds.txt");
        try {
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStreamCaptor));
            Car[] listOfCars = parser.parseFile();

            // Check if the error message is printed out to console
            assertTrue(outputStreamCaptor.toString().contains("Coordinates of the car are outside bounds, this is not accepted"));


            assertEquals(0, listOfCars.length);
            System.setOut(originalOut);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }

    @Test
    void shouldAvoidOneCarOutsideUppermostBoundFromSingleCarParse() {
        Parser parser = new Parser("src/test/java/parser/test_single_car_upper_out_of_bounds.txt");
        try {
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStreamCaptor));
            Car[] listOfCars = parser.parseFile();

            // Check if the error message is printed out to console
            assertTrue(outputStreamCaptor.toString().contains("Coordinates of the car are outside bounds, this is not accepted"));


            assertEquals(0, listOfCars.length);
            System.setOut(originalOut);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }
    @Test
    void shouldAvoidOneCarOutsideUppermostBoundFromMultiCarParse() {
        Parser parser = new Parser("src/test/java/parser/test_multi_car_upper_out_of_bounds.txt");
        try {
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStreamCaptor));
            Car[] listOfCars = parser.parseFile();

            // Check if the error message is printed out to console
            assertTrue(outputStreamCaptor.toString().contains("Coordinates of the car are outside bounds, this is not accepted"));


            assertEquals(0, listOfCars.length);
            System.setOut(originalOut);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }


    @Test
    void shouldAvoidCarWithNegativeCarCoordinates() {
        Parser parser = new Parser("src/test/java/parser/test_negative_coordinates.txt");
        try {
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStreamCaptor));
            Car[] listOfCars = parser.parseFile();

            // Check if the error message is printed out to console
            assertTrue(outputStreamCaptor.toString().contains("Coordinates of the car are negative, this is not accepted"));


            assertEquals(0, listOfCars.length);
            System.setOut(originalOut);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }


    @Test
    void avoidParsingEmptyFile() {
        try {
            Parser parser = new Parser("src/test/java/parser/test_empty.txt");

            // Check the output of the code
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStreamCaptor));
            Car[] listOfCars = parser.parseFile();

            // Check if the error message is printed out to console
            assertTrue(outputStreamCaptor.toString().contains("The file is empty, please ensure that it contains the relevant fields"));

            // Check if the returned number of cars is 0, as expected
            assertEquals(0, listOfCars.length);
            System.setOut(originalOut);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }

    @Test
    void avoidParsingFileWithNegativeGridHeight() {
        try {
            Parser parser = new Parser("src/test/java/parser/test_negative_grid_coordinates.txt");

            // Check the output of the code
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStreamCaptor));
            Car[] listOfCars = parser.parseFile();

            // Check if the error message is printed out to console
            assertTrue(outputStreamCaptor.toString().contains("GridWidth or GridHeight is less than 0, please modify this and try again"));

            // Check if the returned number of cars is 0, as expected
            assertEquals(0, listOfCars.length);
            System.setOut(originalOut);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }

    @Test
    void avoidParsingFileWithNegativeGridWidth() {
        try {
            Parser parser = new Parser("src/test/java/parser/test_negative_grid_coordinates.txt");

            // Check the output of the code
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStreamCaptor));
            Car[] listOfCars = parser.parseFile();

            // Check if the error message is printed out to console
            assertTrue(outputStreamCaptor.toString().contains("GridWidth or GridHeight is less than 0, please modify this and try again"));

            // Check if the returned number of cars is 0, as expected
            assertEquals(0, listOfCars.length);
            System.setOut(originalOut);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }



}