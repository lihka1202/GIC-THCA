package main;

import grid.Grid;
import parser.Parser;

public class Main {
    public static void main(String[] args) {
        String filePath = "input.txt";
        Parser parser = new Parser(filePath);
        try {
            Grid grid = new Grid(parser.parseFile());
            grid.simulate();
        } catch (Exception e) {
            System.err.println("Error occurred while running the simulation");
            e.printStackTrace();
        }
    }
}
