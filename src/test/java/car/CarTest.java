package car;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    void carShouldMoveLeftByOneUnit() {
        Car car = new Car("Test", 1, 2, 'W', 4, 4);
        car.moveCarForward();
        assertEquals(0, car.getxCoordinate());
        assertEquals(2, car.getyCoordinate());
    }

    @Test
    void carShouldMoveRightByOneUnit() {
        Car car = new Car("Test", 1, 2, 'E', 4, 4);
        car.moveCarForward();
        assertEquals(2, car.getxCoordinate());
        assertEquals(2, car.getyCoordinate());
    }

    @Test
    void carShouldNotCrossLeftMostBounds() {
        Car car = new Car("Test", 0, 2, 'W', 1, 2);
        car.moveCarForward();
        assertEquals(0, car.getxCoordinate());
        assertEquals(2, car.getyCoordinate());
    }

    @Test
    void carShouldNotCrossRightMostBounds() {
        Car car = new Car("Test", 1, 2, 'E', 1, 2);
        car.moveCarForward();
        assertEquals(1, car.getxCoordinate());
        assertEquals(2, car.getyCoordinate());
    }

    @Test
    void carShouldNotCrossUpperMostBounds() {
        Car car = new Car("Test", 1, 2, 'N', 1, 2);
        car.moveCarForward();
        assertEquals(1, car.getxCoordinate());
        assertEquals(2, car.getyCoordinate());
    }

    @Test
    void carShouldNotCrossLowerMostBounds() {
        Car car = new Car("Test", 1, 0, 'S', 1, 1);
        car.moveCarForward();
        assertEquals(1, car.getxCoordinate());
        assertEquals(0, car.getyCoordinate());
    }

    @Test
    void carShouldRotateRightByOne() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4);
        car.rotateCar('R');
        assertEquals('E', car.getDirection());
    }

    @Test
    void carShouldRotateRightByTwo() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4);
        car.rotateCar('R');
        car.rotateCar('R');
        assertEquals('S', car.getDirection());
    }

    @Test
    void carShouldRotateRightByThree() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4);
        car.rotateCar('R');
        car.rotateCar('R');
        car.rotateCar('R');
        assertEquals('W', car.getDirection());
    }

    @Test
    void carShouldRotateRightByFour() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4);
        car.rotateCar('R');
        car.rotateCar('R');
        car.rotateCar('R');
        car.rotateCar('R');
        assertEquals('N', car.getDirection());
    }
    @Test
    void carShouldRotateLeftByOne() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4);
        car.rotateCar('L');
        assertEquals('W', car.getDirection());
    }

    @Test
    void carShouldRotateLeftByTwo() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4);
        car.rotateCar('L');
        car.rotateCar('L');
        assertEquals('S', car.getDirection());
    }

    @Test
    void carShouldRotateLeftByThree() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4);
        car.rotateCar('L');
        car.rotateCar('L');
        car.rotateCar('L');
        assertEquals('E', car.getDirection());
    }

    @Test
    void carShouldRotateLeftByFour() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4);
        car.rotateCar('L');
        car.rotateCar('L');
        car.rotateCar('L');
        car.rotateCar('L');
        assertEquals('N', car.getDirection());
    }
}