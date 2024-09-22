package car;

import java.lang.reflect.Array;

public class Car {
    private final String carName;
    private int xCoordinate;
    private int yCoordinate;
    private char direction;

    private final int xGridBounds;
    private final int yGridBounds;
    private final char[] commands;
    private char nextCommand;


    public Car(String carName, int xCoordinate, int yCoordinate, char direction, int xGridBounds, int yGridBounds, char[] commands) {
        this.carName = carName;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.direction = direction;
        this.xGridBounds = xGridBounds;
        this.yGridBounds = yGridBounds;
        this.commands = commands;
        this.nextCommand = commands[0];
    }


    public String getCarName() {
        return carName;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public char getDirection() {
        return direction;
    }

    public void moveCarForward() {
        // You need to move the car, ie change the position somehow
        switch (direction) {
            case 'N':
                if (this.yCoordinate < this.yGridBounds) this.yCoordinate++;
                break;
            case 'E':
                if (this.xCoordinate < this.xGridBounds) this.xCoordinate++;
                break;
            case 'S':
                if (this.yCoordinate > 0) this.yCoordinate--;
                break;
            case 'W':
                if (this.xCoordinate > 0) this.xCoordinate--;
                break;
        }
    }

    public void rotateCar() {
        String directions = "NESW";
        int idx = directions.indexOf(this.direction);
        if (this.nextCommand == 'L') {
            idx = (idx + 3) % 4;
        } else {
            idx = (idx + 1) % 4;
        }
        this.direction = directions.charAt(idx);
    }

    public void move() {

    }
}
