package com.github.ajanthan.adventofcode.day02;

import com.github.ajanthan.adventofcode.utils.InputFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DiveTest {
    private Dive dive;
    private Command[] cmds;

    @BeforeEach
    public void setUpDive() {
        this.dive = new Dive();
        cmds = new Command[]{
                new Command(Command.CommandType.FORWARD, 5),
                new Command(Command.CommandType.DOWN, 5),
                new Command(Command.CommandType.FORWARD, 8),
                new Command(Command.CommandType.UP, 3),
                new Command(Command.CommandType.DOWN, 8),
                new Command(Command.CommandType.FORWARD, 2),
        };
    }

    @Test
    void getPosition() {
        Position currentPosition = dive.getPosition(cmds);
        assertEquals(15, currentPosition.getX());
        assertEquals(10, currentPosition.getD());
        currentPosition = dive.getPosition(getInput());
        System.out.println("Horizontal position: " + currentPosition.getX());
        System.out.println("Depth: " + currentPosition.getD());
        System.out.println("Answer: " + currentPosition.getD() * currentPosition.getX());
    }

    @Test
    void getPositionWithAim() {
        Position currentPosition = dive.getPositionWithAim(cmds);
        assertEquals(15, currentPosition.getX());
        assertEquals(60, currentPosition.getD());
        currentPosition = dive.getPositionWithAim(getInput());
        System.out.println("Horizontal position: " + currentPosition.getX());
        System.out.println("Depth: " + currentPosition.getD());
        System.out.println("Answer: " + currentPosition.getD() * currentPosition.getX());
    }

    private Command[] getInput() {
        try {
            List<String> lines = InputFile.readInputFile("input-day02.txt");
            List<Command> cmds = lines.stream().map(line -> {
                String[] parts = line.split("\\s");
                String type = parts[0];
                int units = Integer.parseInt(parts[1]);
                Command cmd = null;
                switch (type) {
                    case "forward":
                        cmd = new Command(Command.CommandType.FORWARD, units);
                        break;
                    case "up":
                        cmd = new Command(Command.CommandType.UP, units);
                        break;
                    case "down":
                        cmd = new Command(Command.CommandType.DOWN, units);
                }
                return cmd;
            }).collect(Collectors.toList());
            return cmds.toArray(new Command[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}