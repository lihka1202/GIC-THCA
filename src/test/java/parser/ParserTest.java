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
        Parser parser = new Parser("src/test/java/parser/test-one.txt");
        try {
            Car[] listOfCars = parser.parseFile();
            assertEquals(1, listOfCars.length);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }


    @Test
    void shouldAvoidOneCarOutsideLeftmostBound() {
        Parser parser = new Parser("src/test/java/parser/test-one.txt");
        try {
            Car[] listOfCars = parser.parseFile();
            assertEquals(0, listOfCars.length);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }

    @Test
    void shouldAvoidOneCarOutsideRightmostBound() {
        Parser parser = new Parser("src/test/java/parser/test-one.txt");
        try {
            Car[] listOfCars = parser.parseFile();
            assertEquals(1, listOfCars.length);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }

    @Test
    void shouldAvoidOneCarOutsideUppermostBound() {
        Parser parser = new Parser("src/test/java/parser/test-one.txt");
        try {
            Car[] listOfCars = parser.parseFile();
            assertEquals(0, listOfCars.length);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }

    @Test
    void shouldAvoidOneCarOutsideLowermostBound() {
        Parser parser = new Parser("src/test/java/parser/test-one.txt");
        try {
            Car[] listOfCars = parser.parseFile();
            assertEquals(0, listOfCars.length);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }

    @Test
    void shouldAvoidCarWithNegativeXCoordinate() {
        Parser parser = new Parser("src/test/java/parser/test-negativecoords.txt");
        try {
            Car[] listOfCars = parser.parseFile();
            assertEquals(0, listOfCars.length);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }

    @Test
    void shouldAvoidCarWithNegativeYCoordinate() {
        Parser parser = new Parser("src/test/java/parser/test-negativecoords.txt");
        try {
            Car[] listOfCars = parser.parseFile();
            assertEquals(0, listOfCars.length);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }

    @Test
    void avoidParsingEmptyFile() {
        try {
            Parser parser = new Parser("src/test/java/parser/test-empty.txt");

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
            Parser parser = new Parser("src/test/java/parser/test-negativegrid.txt");

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
            Parser parser = new Parser("src/test/java/parser/test-negativegrid.txt");

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
    void shouldCollectMultipleCarsFromFile() {
        Parser parser = new Parser("src/test/java/parser/test-one.txt");
        try {
            Car[] listOfCars = parser.parseFile();
            assertEquals(1, listOfCars.length);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }

}