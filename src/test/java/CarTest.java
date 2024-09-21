import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    void carShouldMoveLeftByOneUnit() {
        Car car = new Car("Test", 1, 2, 'N');
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }

    @Test
    void carShouldMoveRightByOneUnit() {
        Car car = new Car("Test", 1, 2, 'N');
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }

    @Test
    void carShouldNotCrossLeftMostBounds() {
        Car car = new Car("Test", 1, 2, 'N');
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }

    @Test
    void carShouldNotCrossRightMostBounds() {
        Car car = new Car("Test", 1, 2, 'N');
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }

    @Test
    void carShouldNotCrossUpperMostBounds() {
        Car car = new Car("Test", 1, 2, 'N');
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }

    @Test
    void carShouldNotCrossLowerMostBounds() {
        Car car = new Car("Test", 1, 2, 'N');
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }

    @Test
    void carShouldRotateRightByOne() {
        Car car = new Car("Test", 1, 2, 'N');
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }

    @Test
    void carShouldRotateRightByTwo() {
        Car car = new Car("Test", 1, 2, 'N');
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }

    @Test
    void carShouldRotateRightByThree() {
        Car car = new Car("Test", 1, 2, 'N');
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }

    @Test
    void carShouldRotateRightByFour() {
        Car car = new Car("Test", 1, 2, 'N');
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }
    @Test
    void carShouldRotateLefttByOne() {
        Car car = new Car("Test", 1, 2, 'N');
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }

    @Test
    void carShouldRotateLeftByTwo() {
        Car car = new Car("Test", 1, 2, 'N');
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }

    @Test
    void carShouldRotateLeftByThree() {
        Car car = new Car("Test", 1, 2, 'N');
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }

    @Test
    void carShouldRotateLeftByFour() {
        Car car = new Car("Test", 1, 2, 'N');
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }
}