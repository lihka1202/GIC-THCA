package parser;

import car.Car;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void shouldAggregateSomeCarsFromFile() {
        Parser parser = new Parser("random.txt");
        try {
            Car[] listOfCars = parser.parseFile();
        } catch (IOException e) {
            fail("File cannot be opened");
        }

    }
}