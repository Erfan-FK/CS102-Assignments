import Vehicles.Flying;
import Vehicles.Quadruped;
import Vehicles.Vehicle;
import Vehicles.Wheeled;

import java.util.Random;
import java.util.Scanner;

enum Road {Asphalt, Dirt, Stone};

public class Simulation {
    private static Random random = new Random();
    private static int[] roadTypes;
    private static float[] roadLengths;
    private static Vehicle[] vehicles;
    private static int turn;
    private static int totalLengthGlobal;

    private static void roadGenerator(int totalLength) {
        int typeNo = random.nextInt(3,7);

        totalLengthGlobal = totalLength;

        roadTypes = new int[typeNo];
        roadLengths = new float[typeNo];
        int currentLength = 0;

        for (int i = 0; i < typeNo; i++) {
            int roadType = random.nextInt(3);
            int roadLength = (totalLength - 4) / typeNo;

            currentLength += roadLength;
            roadTypes[i] = roadType;
            roadLengths[i] = roadLength;
        }

        if (currentLength != totalLength)
            roadLengths[typeNo - 1] = roadLengths[typeNo - 1] + totalLength - currentLength;

//        I tried to make each length multiple of 5, but I could not.
        for (int i = typeNo - 1; i > 0; i--) {
//            while ((int)roadLengths[i] % 5 != 0) {
                int j = random.nextInt(typeNo);
                int k = random.nextInt(1,(totalLength / 10));

                float tmp = roadLengths[i] + k;

                if (roadLengths[j] - k > 0) {
                    roadLengths[i] = roadLengths[i] + k;
                    roadLengths[j] = roadLengths[j] - k;
                } else if (roadLengths[i] - k > 0){
                    roadLengths[i] = roadLengths[i] - k;
                    roadLengths[j] = roadLengths[j] + k;
                }
//            }
        }
    }

    private static void printRoad() {
        StringBuilder road = new StringBuilder();

        road.append("|");

        for (int i = 0; i < roadTypes.length; i++) {
            road.append(" -");
            road.append(Road.values()[roadTypes[i]]);
            road.append(" ");
            road.append(roadLengths[i]);
            road.append("- |");
        }

        System.out.println(road.toString());
    }

    private static void vehicleGenerator(int count) {
        vehicles = new Vehicle[count];
        double chance;
        for (int i = 0; i < count; i++){
            chance = random.nextDouble();
            Vehicle newVehicle;

            if (chance <= 0.4)
                newVehicle = new Wheeled();
            else if (chance <= 0.65)
                newVehicle = new Flying();
            else
                newVehicle = new Quadruped();

            vehicles[i] = newVehicle;
        }
    }

    private static void printVehicles() {
        StringBuilder vehiclesText = new StringBuilder();

        for (Vehicle vehicle : vehicles) {
            vehiclesText.append(vehicle.getName());
            vehiclesText.append(" - ");
            vehiclesText.append("Speed: ").append(vehicle.getSpeed());
            vehiclesText.append(" - ");
            vehiclesText.append("Fuel: ").append(vehicle.getFuel());
            vehiclesText.append("\n");
        }

        System.out.println(vehiclesText.toString());
    }

    public static boolean isAllOutOfFuel() {
        for (Vehicle vehicle: vehicles) {
            if (!vehicle.isOutOfFuel())
                return false;
        }
        return true;
    }

    public static int getType(float position) {
        int currentTypeMaX = 0;

        for (int i = 0; i < roadLengths.length; i++) {
            currentTypeMaX += (int)roadLengths[i];

            if ((int)position < currentTypeMaX)
                return roadTypes[i];
        }

        return -1;
    }

    private static void printTurn() {
        StringBuilder turnText = new StringBuilder();

        turnText.append("Turn ").append(++turn).append("\n");

        for (Vehicle vehicle : vehicles) {
            turnText.append(vehicle.getName());
            turnText.append(" - ");
            turnText.append("Position: ").append(vehicle.getPosition());
            turnText.append(" - ");
            turnText.append("Speed: ").append(vehicle.getSpeed());
            turnText.append(" - ");
            turnText.append("Fuel: ").append(vehicle.getFuel());
            turnText.append("\n");
        }

        System.out.println(turnText.toString());
    }

    private static void printMovement() {
        String moveText = "Movements:\n";


        for (Vehicle vehicle : vehicles) {
            if (!vehicle.isOutOfFuel())
                moveText += "" + vehicle.getName() + " moves from " + Road.values()[getType(vehicle.getPosition())] +
                        ", for " + vehicle.getSpeed() + " * " + vehicle.getSpeedFactor(getType(vehicle.getPosition())) +
                        " = " + (vehicle.getSpeed() *  vehicle.getSpeedFactor(getType(vehicle.getPosition()))) + " units.\n";
            else
                moveText += "" + vehicle.getName() + " is out of fuel!\n";
        }

        System.out.println(moveText);
    }

    private static boolean isThereWinner() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPosition() >= totalLengthGlobal)
                return true;
        }

        return false;
    }

    private static Vehicle getWinner() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPosition() >= totalLengthGlobal)
                return vehicle;
        }

        return null;
    }
    private static void printWinner() {
        String winnerText = getWinner().getName() + " finishes the race! " +
                "Position: " + getWinner().getPosition() + " - " +
                "Speed: " + getWinner().getSpeed() +
                " Fuel: " + getWinner().getFuel();

        System.out.println(winnerText);
    }

    private static void reset() {
        turn = 0;

        Wheeled.setCount(0);
        Flying.setCount(0);
        Quadruped.setCount(0);
    }

    public static void stimulate() {
        boolean again = false;

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Well, well. Welcome" + ((again) ? " again" : "") + " Schumacher. Please enter the road length: ");

            int length = scanner.nextInt();

            roadGenerator(length);

            System.out.println("\nThe following road is generated: ");
            printRoad();

            System.out.print("\nPlease enter vehicle count: ");
            int vehicleCount = scanner.nextInt();

            vehicleGenerator(vehicleCount);

            System.out.println("\nThe following vehicles are generated:");
            printVehicles();

            while (!isThereWinner() && !isAllOutOfFuel()) {
                printTurn();
                printMovement();

                for (Vehicle vehicle : vehicles)
                    if (!vehicle.isOutOfFuel())
                        vehicle.move(getType(vehicle.getPosition()));

            }

            if (isAllOutOfFuel())
                System.out.println("All vehicles out of Fuel!");
            else
                printWinner();

            System.out.print("\nEnd of simulation. One more turn Schumacher? Y/N ");
            char response = scanner.next().toUpperCase().charAt(0);

            if (response == 'Y') {
                again = true;
                reset();
            }

            System.out.println("\n");
        } while (again);
    }
}

