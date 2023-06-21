package Vehicles;

import java.util.Random;

public class Quadruped extends Vehicle{

    private Random random = new Random();

    private static int count;
    private static double[] speedFactors = {0.50, 1.00, 0.75};

    public Quadruped() {
        this.name = "Q" + ++count;
        this.minSpeed = 20;
        this.maxSpeed = 40;
        this.minFuel = 10;
        this.maxFuel = 20;
        this.speed = random.nextInt(minSpeed, maxSpeed + 1);
        this.virtualSpeed = speed;
        this.fuel = random.nextInt(minFuel, maxFuel + 1);
    }

    @Override
    public void move(int roadType) {
        super.move(roadType);

        System.out.println("FUCKEDD");
        if (roadType == 0)
            this.virtualSpeed *= speedFactors[0];
        else if (roadType == 1)
            this.virtualSpeed *= speedFactors[1];
        else if (roadType == 2)
            this.virtualSpeed *= speedFactors[2];

        this.position += this.virtualSpeed;
        this.virtualSpeed = this.speed;
    }

    public double getSpeedFactor(int roadType) {
        return speedFactors[roadType];
    }

    public static void setCount(int count) {
        Quadruped.count = count;
    }
}
