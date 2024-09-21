package parser;

import car.Car;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

public class Parser {
    String filePath;

    public Parser(String filePath) {
        this.filePath = filePath;
    }

    public Car[] parseFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        return new Car[]{};

    }
}
