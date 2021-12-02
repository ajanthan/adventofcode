package com.github.ajanthan.adventofcode.day01;

import com.github.ajanthan.adventofcode.utils.InputFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

class SonarSweepTest {

    @Test
    void getCountOfLargerThanPrevious() {
        SonarSweep sonarSweep = new SonarSweep();
        int result = sonarSweep.getCountOfLargerThanPrevious(
                new int[]{
                        199,
                        200,
                        208,
                        210,
                        200,
                        207,
                        240,
                        269,
                        260,
                        263});
        Assertions.assertEquals(7, result);
        Assertions.assertEquals(1759, sonarSweep.getCountOfLargerThanPrevious(getInputs()));
    }

    @Test
    void getCountOfLargerThanPreviousWithSlidingWindow() {
        SonarSweep sonarSweep = new SonarSweep();
        int result = sonarSweep.getCountOfLargerThanPreviousWithSlidingWindow(
                new int[]{
                        199,
                        200,
                        208,
                        210,
                        200,
                        207,
                        240,
                        269,
                        260,
                        263}
        );
        Assertions.assertEquals(5, result);
        Assertions.assertEquals(1805, sonarSweep.getCountOfLargerThanPreviousWithSlidingWindow(getInputs()));
    }

    private static int[] getInputs() {
        int[] realInput = null;
        try {
            List<String> lines = InputFile.readInputFile("input-day01-1.txt");
            realInput = lines.stream().mapToInt(i -> Integer.parseInt(i)).toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return realInput;
    }
}