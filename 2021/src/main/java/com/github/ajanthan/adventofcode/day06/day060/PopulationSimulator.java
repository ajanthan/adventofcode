package com.github.ajanthan.adventofcode.day06.day060;

import com.github.ajanthan.adventofcode.utils.InputFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PopulationSimulator {
    public long projectGrowth(int[] fish, int days) {
        long[] fishPopulation = new long[9];
        for (int x : fish) fishPopulation[x]++;
        System.out.println(Arrays.toString(fishPopulation));
        for (int i = 0; i < days; i++) {
            long temp = fishPopulation[0];
            for (int j = 0; j < 8; j++) {
                fishPopulation[j] = fishPopulation[j + 1];
            }
            fishPopulation[6] += temp;
            fishPopulation[8] = temp;
        }

        System.out.println(Arrays.toString(fishPopulation));
        return Arrays.stream(fishPopulation).sum();
    }

    public static void main(String[] args) {
        int[] population = {3, 4, 3, 1, 2};
        try {
            List<String> lines = InputFile.readInputFile("input-day06.txt");
            population = Arrays.stream(lines.get(0).split(",")).map(Integer::parseInt).mapToInt(number -> number).toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PopulationSimulator populationSimulator = new PopulationSimulator();
        System.out.println(populationSimulator.projectGrowth(population, 256));
    }
}
