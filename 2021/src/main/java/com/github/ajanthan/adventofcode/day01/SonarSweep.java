package com.github.ajanthan.adventofcode.day01;

public class SonarSweep {
    public int getCountOfLargerThanPrevious(int depths[]) {
        if (depths == null || depths.length == 0) return 0;
        int previousDepth = depths[0], increasedCount = 0;

        for (int i = 1; i < depths.length; i++) {
            if (depths[i] > previousDepth) {
                increasedCount++;
            }
            previousDepth = depths[i];
        }
        return increasedCount;
    }

    public int getCountOfLargerThanPreviousWithSlidingWindow(int depths[]) {
        if (depths == null || depths.length < 2) return 0;
        int previousDepth = 0, increasedCount = 0, currentDepth;
        for (int i = 0; i < 3; i++) {
            previousDepth += depths[i];
        }
        for (int i = 1; i < depths.length - 2; i++) {
            currentDepth = depths[i] + depths[i + 1] + depths[i + 2];
            if (currentDepth > previousDepth) {
                increasedCount++;
            }
            previousDepth = currentDepth;
        }
        return increasedCount;
    }
}
