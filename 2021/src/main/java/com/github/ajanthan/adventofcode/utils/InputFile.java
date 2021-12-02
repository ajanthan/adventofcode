package com.github.ajanthan.adventofcode.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class InputFile {
    public static List<String> readInputFile(String fileName) throws IOException {
        File input = new File(InputFile.class.getClassLoader().getResource(fileName).getFile());
        return Files.readAllLines(input.toPath());
    }
}
