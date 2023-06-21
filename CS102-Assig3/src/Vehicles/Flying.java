package Vehicles;

import java.util.Random;

public class Flying extends Vehicle{

    private Random random = new Random();

    private static int count;
    private static double[] speedFactors = {1.00, 1.00, 1.00};

    public Flying() {
        this.name = "F" + ++count;
        this.minSpeed = 20;
        this.maxSpeed = 30;
        this.minFuel = 20;
        this.maxFuel = 30;
        this.speed = random.nextInt(minSpeed, maxSpeed + 1);
        this.virtualSpeed = speed;
        this.fuel = random.nextInt(minFuel, maxFuel + 1);
    }

    @Override
    public void move(int roadType) {
        super.move(roadType);

        this.virtualSpeed *= speedFactors[1];

        this.position += this.virtualSpeed;
        this.virtualSpeed = this.speed;
    }

    public double getSpeedFactor(int roadType) {
        return speedFactors[roadType];
    }

    public static void setCount(int count) {
        Flying.count = count;
    }
}
