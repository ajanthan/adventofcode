package com.github.ajanthan.adventofcode.day02;

public class Command {
    private CommandType type;
    private int units;

    public Command(CommandType name, int units) {
        this.type = name;
        this.units = units;
    }

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public enum CommandType {
        FORWARD, UP, DOWN
    }
}
