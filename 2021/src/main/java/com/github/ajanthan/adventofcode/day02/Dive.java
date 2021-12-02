package com.github.ajanthan.adventofcode.day02;

public class Dive {
    public Position getPosition(Command[] commands) {
        int currentX = 0, currentH = 0;
        for (Command cmd : commands) {
            switch (cmd.getType()) {
                case DOWN:
                    currentH += cmd.getUnits();
                    break;
                case UP:
                    currentH -= cmd.getUnits();
                    break;
                case FORWARD:
                    currentX += cmd.getUnits();
            }
        }
        return new Position(currentX, currentH);
    }

    public Position getPositionWithAim(Command[] commands) {
        int currentX = 0, currentH = 0, currentAim = 0;
        for (Command cmd : commands) {
            switch (cmd.getType()) {
                case DOWN:
                    currentAim += cmd.getUnits();
                    break;
                case UP:
                    currentAim -= cmd.getUnits();
                    break;
                case FORWARD:
                    currentX += cmd.getUnits();
                    currentH += cmd.getUnits() * currentAim;
            }
        }
        return new Position(currentX, currentH);
    }
}
