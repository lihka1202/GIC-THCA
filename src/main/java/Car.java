public class Car {
    private String carName;
    private int xCoordinate;
    private int yCoordinate;
    private char direction;

    public Car(String carName, int xCoordinate, int yCoordinate, char direction) {
        this.carName = carName;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.direction = direction;
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

    public void moveCar() {
        // You need to move the car, ie change the position somehow
        this.xCoordinate += 1;
        this.yCoordinate += 1;
    }

    public void rotateCar() {
        this.direction = 'S';
    }
}
