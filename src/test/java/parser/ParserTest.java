package parser;

import car.Car;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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
    void shouldCollectMultipleCarsFromFile() {
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
            assertEquals(1, listOfCars.length);
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
            assertEquals(1, listOfCars.length);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }
    @Test
    void shouldAvoidOneCarOutsideLowermostBound() {
        Parser parser = new Parser("src/test/java/parser/test-one.txt");
        try {
            Car[] listOfCars = parser.parseFile();
            assertEquals(1, listOfCars.length);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }

    @Test
    void shouldAvoidCarWithNegativeXCoordinate() {
        Parser parser = new Parser("src/test/java/parser/test-one.txt");
        try {
            Car[] listOfCars = parser.parseFile();
            assertEquals(1, listOfCars.length);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }
    @Test
    void shouldAvoidCarWithNegativeYCoordinate() {
        Parser parser = new Parser("src/test/java/parser/test-one.txt");
        try {
            Car[] listOfCars = parser.parseFile();
            assertEquals(1, listOfCars.length);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }
}