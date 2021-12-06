package com.github.ajanthan.adventofcode.day06;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class LanternfishPopulation {
    public int populationInit(List<Fish> population, int start, int end, boolean expandList) throws IOException {
        for (int i = start; i <= end; i++) {
            List<Fish> newPop = new ArrayList<>();
            int size = population.size();
            for (int j = 0; j < size; j++) {
                Fish fish = population.get(j);
                if (fish.clock == 0) {
                    fish.clock = 6;
                } else if (fish.clock == 1) {
                    if (expandList) population.add(new Fish(9));
                    else newPop.add(new Fish(9));
                    fish.clock--;
                } else {
                    fish.clock--;
                }

            }
            if (!expandList && newPop.size() > 0) {
                Path loc = Path.of("/Users/ajanthan/IdeaProjects/adventofcode/2021/day06/" + (i + 1));
                if (!Files.exists(loc)) loc = Files.createDirectory(loc);
                DirectoryStream<Path> stream = Files.newDirectoryStream(loc);
                Iterator<Path> files = stream.iterator();
                int fileNum = -1;
                while (files.hasNext()) {
                    fileNum++;
                    files.next();
                }
                if (fileNum == -1) fileNum = 0;
                Path file = Path.of(loc.toString() + "/fish." + fileNum + ".txt");

                if (!Files.exists(file)) file = Files.createFile(file);
                if (Files.size(file) > 100 * 1024 * 1024) {
                    fileNum++;
                    file = Files.createFile(Path.of(loc.toString() + "/fish." + fileNum + ".txt"));
                }

                StringBuilder builder = new StringBuilder();
                newPop.stream().forEach(n -> {
                    builder.append(n);
                    builder.append(",");
                });
                //builder.deleteCharAt(builder.length() - 1);
                Path toClose = null;
                try {
                    toClose = Files.writeString(file, builder.toString(), StandardOpenOption.APPEND);
                } finally {
                    stream.close();
                    //System.out.println(toClose.getFileSystem().isOpen());
                }
            }
        }
        return population.size();
    }

    public static void main(String[] args) throws IOException {
        List<Fish> population = Arrays.stream(new Fish[]{
                new Fish(3),
                new Fish(4),
                new Fish(3),
                new Fish(1),
                new Fish(2),
        }).collect(Collectors.toList());

        LanternfishPopulation lanternfishPopulation = new LanternfishPopulation();
        List<Fish> population2 = lanternfishPopulation.getFish("/Users/ajanthan/IdeaProjects/adventofcode/2021/src/main/resources/input-day06.txt");
//        System.out.println("Population after 18 days: " +
//                lanternfishPopulation.projectTotalPopulationAfterDays(population, 18));

        Path path = Path.of("/Users/ajanthan/IdeaProjects/adventofcode/2021/day06/");
        Files.createDirectory(path);
        int days = 256;
        long count = lanternfishPopulation.populationInit(population, 1, days, false);
        int count1 = 0;
        for (int i = 1; i <= days; i++) {
            Path dirPath = Path.of(path.toString() + "/" + i);
            if (Files.exists(dirPath)) {

                DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath);
                Iterator<Path> files = stream.iterator();
                try {
                    while (files.hasNext()) {
                        Path file = files.next();
                        if (Files.exists(file)) {
                            try {
                                count1 = lanternfishPopulation.populationInit(lanternfishPopulation.getFish(file.toString()), i, days, false);
                                count += count1;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } finally {
                    stream.close();
                }
                System.out.println(i + ":" + count1);
            }
        }
//        Iterator<Path> paths = Files.newDirectoryStream(path).iterator();
//        List<Integer> dirs = new ArrayList<>();
//        while (paths.hasNext()) {
//            Path d = paths.next();
//            int day = Integer.parseInt(d.getFileName().toString());
//            dirs.add(day);
//        }
//        Collections.sort(dirs);
//        int count1;
//        for (int dir : dirs) {
//            try {
//                if (dir <= days) {
//                    count1 = lanternfishPopulation.populationInit(lanternfishPopulation.getFish("/Users/ajanthan/IdeaProjects/adventofcode/2021/day06/" + dir + "/fish.txt"), dir, days, false);
//                    System.out.println(dir + ":" + count1);
//                    count += count1;
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            count += lanternfishPopulation.getFish("/Users/ajanthan/IdeaProjects/adventofcode/2021/day06/" + days + "/fish.txt").size();
//        }catch (Exception e){
//            //no action
//        }
        System.out.println(count);

        // lanternfishPopulation.projectTotalPopulationAfterDays(population, 100);
//        System.out.println("Sample input: Population after 80 days: " +
//                lanternfishPopulation.projectTotalPopulationAfterDays(population, 56));

//        System.out.println("Final input: Population after 80 days: " +
//                lanternfishPopulation.projectTotalPopulationAfterDays(population2, 80));

    }

    public List<Fish> getFish(String file) {
        List<String> lines = null;
        try {
            lines = readInputFileFromLocation(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Fish> population2 = new ArrayList<>();
        for (String line : lines) {
            String[] nums = line.split(",");
            population2.addAll(Arrays.stream(nums).map(Integer::parseInt).map(Fish::new).collect(Collectors.toList()));
        }

        return population2;
    }

    public static List<String> readInputFileFromLocation(String fileName) throws IOException {
        File input = new File(fileName);
        return Files.readAllLines(input.toPath());
    }
}

class Fish {
    int clock;

    public Fish(int clock) {
        this.clock = clock;
    }

    @Override
    public String toString() {
        return String.valueOf(clock);
    }
}