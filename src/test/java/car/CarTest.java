package car;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Random;

class CarTest {

    @Test
    void carShouldMoveLeftByOneUnit() {
        Car car = new Car("Test", 1, 2, 'W', 4, 4, new char[]{'L'});
        car.moveCarForward();
        assertEquals(0, car.getxCoordinate());
        assertEquals(2, car.getyCoordinate());
    }

    @Test
    void carShouldMoveRightByOneUnit() {
        Car car = new Car("Test", 1, 2, 'E', 4, 4, new char[]{'L'});
        car.moveCarForward();
        assertEquals(2, car.getxCoordinate());
        assertEquals(2, car.getyCoordinate());
    }

    @Test
    void carShouldNotCrossLeftMostBounds() {
        Car car = new Car("Test", 0, 2, 'W', 1, 2, new char[]{'L'});
        car.moveCarForward();
        assertEquals(0, car.getxCoordinate());
        assertEquals(2, car.getyCoordinate());
    }

    @Test
    void carShouldNotCrossRightMostBounds() {
        Car car = new Car("Test", 1, 2, 'E', 1, 2, new char[]{'L'});
        car.moveCarForward();
        assertEquals(1, car.getxCoordinate());
        assertEquals(2, car.getyCoordinate());
    }

    @Test
    void carShouldNotCrossUpperMostBounds() {
        Car car = new Car("Test", 1, 2, 'N', 1, 2, new char[]{'L'});
        car.moveCarForward();
        assertEquals(1, car.getxCoordinate());
        assertEquals(2, car.getyCoordinate());
    }

    @Test
    void carShouldNotCrossLowerMostBounds() {
        Car car = new Car("Test", 1, 0, 'S', 1, 1, new char[]{'L'});
        car.moveCarForward();
        assertEquals(1, car.getxCoordinate());
        assertEquals(0, car.getyCoordinate());
    }

    @Test
    void carShouldRotateRightByOne() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4, new char[]{'R'});
        car.rotateCar();
        assertEquals('E', car.getDirection());
    }

    @Test
    void carShouldRotateRightByTwo() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4, new char[]{'R'});
        car.rotateCar();
        car.rotateCar();
        assertEquals('S', car.getDirection());
    }

    @Test
    void carShouldRotateRightByThree() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4, new char[]{'R'});
        car.rotateCar();
        car.rotateCar();
        car.rotateCar();
        assertEquals('W', car.getDirection());
    }

    @Test
    void carShouldRotateRightByFour() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4, new char[]{'R'});
        car.rotateCar();
        car.rotateCar();
        car.rotateCar();
        car.rotateCar();
        assertEquals('N', car.getDirection());
    }

    @Test
    void carShouldRotateLeftByOne() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4, new char[]{'L'});
        car.rotateCar();
        assertEquals('W', car.getDirection());
    }

    @Test
    void carShouldRotateLeftByTwo() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4, new char[]{'L'});
        car.rotateCar();
        car.rotateCar();
        assertEquals('S', car.getDirection());
    }

    @Test
    void carShouldRotateLeftByThree() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4, new char[]{'L'});
        car.rotateCar();
        car.rotateCar();
        car.rotateCar();
        assertEquals('E', car.getDirection());
    }

    @Test
    void carShouldRotateLeftByFour() {
        Car car = new Car("Test", 1, 2, 'N', 4, 4, new char[]{'L'});
        car.rotateCar();
        car.rotateCar();
        car.rotateCar();
        car.rotateCar();
        assertEquals('N', car.getDirection());
    }

    @Test
    void carReachesRightLocationAfterOneCommand() {
        Car car = new Car("Test", 1, 2, 'N', 10, 10, new char[]{'F'});
        car.move();
        assertEquals(1, car.getxCoordinate());
        assertEquals(3, car.getyCoordinate());
        assertEquals('N', car.getDirection());

    }

    @Test
    void carReachesRightLocationAfterManyCommands() {
        Car car = new Car("Test", 1, 2, 'N', 10, 10, new char[]{'F', 'F', 'R', 'F', 'F', 'F', 'R', 'R', 'L', 'F'});
        while (!car.isCommandComplete()) {
            car.move();
        }
        assertEquals(4, car.getxCoordinate());
        assertEquals(3, car.getyCoordinate());
        assertEquals('S', car.getDirection());

    }

    @Test
    void carShouldNotMoveAfterCommandListIsComplete() {
        Car car = new Car("Test", 1, 2, 'N', 10, 10, new char[]{'F'});
        while (!car.isCommandComplete()) {
            car.move();
        }
        Random rand = new Random();
        for (int i = 0; i < rand.nextInt(100); i += 1) {
            car.move();
        }
        assertEquals(1, car.getxCoordinate());
        assertEquals(3, car.getyCoordinate());
        assertEquals('N', car.getDirection());

    }

    @Test
    void testEmptyConstructorInit() {
        Car car = new Car();
        assertTrue(car.isCommandComplete());
    }
}