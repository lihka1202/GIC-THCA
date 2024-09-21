import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    void carShouldMoveLeftByOneUnit() {
        Car car = new Car("Test", 1, 2, 'W', 4, 4);
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 0);
        assertEquals(car.getyCoordinate(), 2);
    }

    @Test
    void carShouldMoveRightByOneUnit() {
        Car car = new Car("Test", 1, 2, 'E', 4, 4);
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 2);
        assertEquals(car.getyCoordinate(), 2);
    }

    @Test
    void carShouldNotCrossLeftMostBounds() {
        Car car = new Car("Test", 0, 2, 'W', 1, 2);
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 0);
        assertEquals(car.getyCoordinate(), 2);
    }

    @Test
    void carShouldNotCrossRightMostBounds() {
        Car car = new Car("Test", 1, 2, 'E', 1, 2);
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 1);
        assertEquals(car.getyCoordinate(), 2);
    }

    @Test
    void carShouldNotCrossUpperMostBounds() {
        Car car = new Car("Test", 1, 2, 'N', 1, 2);
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 1);
        assertEquals(car.getyCoordinate(), 2);
    }

    @Test
    void carShouldNotCrossLowerMostBounds() {
        Car car = new Car("Test", 1, 0, 'S', 1, 1);
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 1);
        assertEquals(car.getyCoordinate(), 0);
    }

    @Test
    void carShouldRotateRightByOne() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4);
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }

    @Test
    void carShouldRotateRightByTwo() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4);
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }

    @Test
    void carShouldRotateRightByThree() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4);
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }

    @Test
    void carShouldRotateRightByFour() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4);
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }
    @Test
    void carShouldRotateLefttByOne() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4);
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }

    @Test
    void carShouldRotateLeftByTwo() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4);
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }

    @Test
    void carShouldRotateLeftByThree() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4);
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }

    @Test
    void carShouldRotateLeftByFour() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4);
        car.moveCarForward();
        assertEquals(car.getxCoordinate(), 4);
        assertEquals(car.getyCoordinate(), 4);
    }
}