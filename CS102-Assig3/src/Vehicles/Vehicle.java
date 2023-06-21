package Vehicles;

public abstract class Vehicle {
    protected String name;
    protected float speed;
    protected float position;
    protected int fuel;
    protected int minSpeed;
    protected int maxSpeed;
    protected int minFuel;
    protected int maxFuel;
    protected float virtualSpeed;

    public void move(int roadType) {
        if (this.fuel < 0) {
            System.out.println(this.name + "out of Fuel!!!");
            return;
        }

        this.fuel--;
    }

    public abstract double getSpeedFactor(int roadType);

    public boolean isOutOfFuel() {
        return this.fuel <= 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getPosition() {
        return position;
    }

    public void setPosition(float position) {
        this.position = position;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public float getVirtualSpeed() {
        return virtualSpeed;
    }

    public void setVirtualSpeed(float displaySpeed) {
        this.virtualSpeed = displaySpeed;
    }
}
