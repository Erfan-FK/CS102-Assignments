package Vehicles;

import java.util.Random;

public class Wheeled extends Vehicle{

    private Random random = new Random();

    private static int count;
    private static double[] speedFactors = {1.00, 0.75, 0.75};


    public Wheeled() {
        this.name = "W" + ++count;
        this.minSpeed = 15;
        this.maxSpeed = 25;
        this.minFuel = 30;
        this.maxFuel = 40;
        this.speed = random.nextInt(minSpeed, maxSpeed + 1);
        this.virtualSpeed = speed;
        this.fuel = random.nextInt(minFuel, maxFuel + 1);
    }

    @Override
    public void move(int roadType) {
        super.move(roadType);

        if (roadType == 0)
            this.virtualSpeed *= speedFactors[0];
        else if (roadType == 1 || roadType == 2)
            this.virtualSpeed *= speedFactors[1];

        this.position += this.virtualSpeed;
        this.virtualSpeed = this.speed;
    }

    public double getSpeedFactor(int roadType) {
        return speedFactors[roadType];
    }

    public static void setCount(int count) {
        Wheeled.count = count;
    }
}
