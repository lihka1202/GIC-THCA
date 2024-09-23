package grid;

import car.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {
    private Car[] listOfCarsToMove;

    public Grid(Car[] listOfCarsToMove) {
        this.listOfCarsToMove = listOfCarsToMove;
    }

    private boolean areAllCommandsComplete() {
        for (Car car : this.listOfCarsToMove) {
            if (!car.isCommandComplete()) {
                return false;
            }
        }
        return true;
    }

    public void simulate() {
        if (this.listOfCarsToMove.length == 0) {
            System.out.print("Due to an error, this simulation will not run");
        } else if (this.listOfCarsToMove.length == 1) {
            // Move the car
            while (!this.listOfCarsToMove[0].isCommandComplete()) {
                this.listOfCarsToMove[0].move();
            }

            // Print the result
            System.out.print(this.listOfCarsToMove[0].getxCoordinate() +
                    " " + this.listOfCarsToMove[0].getyCoordinate() +
                    " " + this.listOfCarsToMove[0].getDirection());
        } else {
            boolean simulationStop = false;
            int stepCount = 0;
            while (!simulationStop && !areAllCommandsComplete()) {
                stepCount += 1;
                HashMap<String, List<Car>> positionMap = new HashMap<>();

                // Simulate moving each car
                for (Car car : this.listOfCarsToMove) {
                    car.move();
                    String positionKey = car.getxCoordinate() + " " + car.getyCoordinate();
                    positionMap.computeIfAbsent(positionKey, k -> new ArrayList<>()).add(car);
                }

                for (Map.Entry<String, List<Car>> entry : positionMap.entrySet()) {
                    List<Car> carsAtPosition = entry.getValue();
                    if (carsAtPosition.size() > 1) {
                        // Collision taken place
                        simulationStop = true;

                        StringBuilder carLocationOP = new StringBuilder();
                        for (Car crashedCar : carsAtPosition) {
                            carLocationOP.append(crashedCar.getCarName()).append(" ");
                        }
                        // Output the Point of contact
                        System.out.println(carLocationOP);
                        System.out.println(entry.getKey());
                    }
                }
                if (simulationStop) {
                    System.out.print(stepCount);
                }

            }
            if (!simulationStop && areAllCommandsComplete()) {
                System.out.print("no collision");
            }
        }

    }
}
