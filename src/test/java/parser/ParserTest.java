package parser;

import car.Car;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {


    private String captureOutputDuringParsing(Parser parser) throws IOException {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;  // Save the original System.out
        System.setOut(new PrintStream(outputStreamCaptor));

        try {
            parser.parseFile();
        } finally {
            System.setOut(originalOut);  // Always restore System.out, even if there's an exception
        }

        return outputStreamCaptor.toString().trim();  // Return the captured output
    }

    // Helper method for parsing and checking car count
    private void assertCarCountFromFile(String filePath, int expectedCount) {
        Parser parser = new Parser(filePath);
        try {
            Car[] listOfCars = parser.parseFile();
            assertEquals(expectedCount, listOfCars.length);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }

    // Helper method for parsing and checking console output and car list size
    private void assertCarParseWithErrorMessage(String filePath, String expectedMessage) {
        Parser parser = new Parser(filePath);
        try {
            String output = captureOutputDuringParsing(parser);

            // Check if the error message is printed out to console
            assertTrue(output.contains(expectedMessage));

            // Check if no cars were parsed
            assertCarCountFromFile(filePath, 0);
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }

    @Test
    void shouldCollectOneCarFromFile() {
        assertCarCountFromFile("src/test/java/parser/test_single_car.txt", 1);
    }

    @Test
    void shouldCollectMultipleCarsFromFile() {
        assertCarCountFromFile("src/test/java/parser/test_multi_car.txt", 2);
    }

    @Test
    void shouldAvoidOneCarOutsideRightmostBoundFromSingleCarParse() {
        assertCarParseWithErrorMessage("src/test/java/parser/test_single_car_right_out_of_bounds.txt",
                "Coordinates of the car are outside bounds, this is not accepted");
    }

    @Test
    void shouldAvoidOneCarOutsideRightmostBoundFromMultiCarParse() {
        assertCarParseWithErrorMessage("src/test/java/parser/test_multi_car_right_out_of_bounds.txt",
                "Coordinates of the car are outside bounds, this is not accepted");
    }

    @Test
    void shouldAvoidOneCarOutsideUppermostBoundFromSingleCarParse() {
        assertCarParseWithErrorMessage("src/test/java/parser/test_single_car_upper_out_of_bounds.txt",
                "Coordinates of the car are outside bounds, this is not accepted");
    }

    @Test
    void shouldAvoidOneCarOutsideUppermostBoundFromMultiCarParse() {
        assertCarParseWithErrorMessage("src/test/java/parser/test_multi_car_upper_out_of_bounds.txt",
                "Coordinates of the car are outside bounds, this is not accepted");
    }

    @Test
    void shouldAvoidCarWithNegativeCarCoordinates() {
        assertCarParseWithErrorMessage("src/test/java/parser/test_negative_coordinates.txt",
                "Coordinates of the car are negative, this is not accepted");
    }

    @Test
    void avoidParsingEmptyFile() {
        assertCarParseWithErrorMessage("src/test/java/parser/test_empty.txt",
                "The file is empty, please ensure that it contains the relevant fields");
    }

    @Test
    void avoidParsingFileWithNegativeGridHeight() {
        assertCarParseWithErrorMessage("src/test/java/parser/test_negative_grid_coordinates.txt",
                "GridWidth or GridHeight is less than 0, please modify this and try again");
    }

    @Test
    void avoidParsingFileWithNegativeGridWidth() {
        assertCarParseWithErrorMessage("src/test/java/parser/test_negative_grid_coordinates.txt",
                "GridWidth or GridHeight is less than 0, please modify this and try again");
    }

    @Test
    void shouldAvoidSingleCarWithWrongCommands() {
        assertCarParseWithErrorMessage("src/test/java/parser/test_single_car_wrong_commands.txt",
                "Movement commands contain an unknown character, not accepted");
    }

    @Test
    void shouldAvoidMultiCarWithWrongCommands() {
        assertCarParseWithErrorMessage("src/test/java/parser/test_multi_car_wrong_commands.txt",
                "Movement commands contain an unknown character, not accepted");
    }

    @Test
    void shouldAvoidMultiCarWithWrongDirection() {
        assertCarParseWithErrorMessage("src/test/java/parser/test_multi_car_wrong_direction.txt",
                "Direction is outside NESW, this is not accepted");
    }

    @Test
    void shouldAvoidSingleCarWithWrongDirection() {
        assertCarParseWithErrorMessage("src/test/java/parser/test_single_car_wrong_direction.txt",
                "Direction is outside NESW, this is not accepted");
    }

    @Test
    void shouldAvoidSingleCarWithWrongFileStructure() {
        assertCarParseWithErrorMessage("src/test/java/parser/test_single_car_wrong_file_structure.txt",
                "File structure is wrong, this is not accepted");
    }

    @Test
    void shouldAvoidMultiCarWithWrongFileStructure() {
        assertCarParseWithErrorMessage("src/test/java/parser/test_multi_car_wrong_file_structure.txt",
                "File structure is wrong, this is not accepted");
    }

    // Integration Test for checking car's final position
    @Test
    void shouldReachRelevantPosition() {
        Parser parser = new Parser("src/test/java/parser/test_single_car.txt");
        try {
            Car[] listOfCars = parser.parseFile();
            while (!listOfCars[0].isCommandComplete()) {
                listOfCars[0].move();
            }
            assertEquals(4, listOfCars[0].getxCoordinate());
            assertEquals(3, listOfCars[0].getyCoordinate());
            assertEquals('S', listOfCars[0].getDirection());
        } catch (IOException e) {
            fail("File cannot be opened");
        }
    }
}
