package edu.iu.c212.models;

public enum Item {

    GAME_BOY("Gameboy", 50),
    HERSHEY("Hershey's Chocolate", 10),
    PIZZA("Pizza", 15),
    SODA("Soda", 10),
    MAP("World Map", 20),
    CAR("Hot Wheels Car", 10),
    ROBOT("Robot Toy", 30),
    BLANKET("Soft Blanket", 30),
    BOTTLE("Water Bottle", 25),
    WATCH("Watch", 40);

    final String readableName;
    final double value;

    Item(String readableName, double value) {
        this.readableName = readableName;
        this.value = value;
    }

    public String getReadableName() {
        return this.readableName;
    }

    public double getValue(){
        return this.value;
    }

    @Override
    public String toString() {
        return this.readableName + ": $" + this.value;
    }
}
