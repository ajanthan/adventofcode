package com.github.ajanthan.adventofcode.day03;

import com.github.ajanthan.adventofcode.utils.InputFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryDiagnostic {
    public int[] getO2GenRate(int[][] report, int bitPosition) {
        if (report.length == 1) return report[0];
        List<int[]> filteredReport = new ArrayList<>();
        int recordSize = report[0].length;

        int bit = 0;
        int oneBitCount = 0;
        for (int i = 0; i < report.length; i++) {
            if (report[i][bitPosition] == 1) {
                oneBitCount++;
            }
        }
        if (oneBitCount >= report.length - oneBitCount) {
            bit = 1;
        }
        for (int[] record : report) {
            if (record[bitPosition] == bit) {
                filteredReport.add(record);
            }
        }

        return getO2GenRate(filteredReport.toArray(new int[0][]), bitPosition + 1);
    }

    public int[] getCO2ScrubberRate(int[][] report, int bitPosition) {
        if (report.length == 1) return report[0];
        List<int[]> filteredReport = new ArrayList<>();
        int recordSize = report[0].length;

        int bit = 0;
        int oneBitCount = 0;
        for (int i = 0; i < report.length; i++) {
            if (report[i][bitPosition] == 1) {
                oneBitCount++;
            }
        }
        if (oneBitCount < report.length - oneBitCount) {
            bit = 1;
        }
        for (int[] record : report) {
            if (record[bitPosition] == bit) {
                filteredReport.add(record);
            }
        }

        return getCO2ScrubberRate(filteredReport.toArray(new int[0][]), bitPosition + 1);
    }

    public int[] getGammaRate(int[][] report) {
        int recordSize = report[0].length;
        int[] result = new int[recordSize];

        for (int j = 0; j < recordSize; j++) {
            int oneBitCount = 0;
            for (int i = 0; i < report.length; i++) {
                if (report[i][j] == 1) {
                    oneBitCount++;
                }
            }
            if (oneBitCount > report.length / 2) {
                result[j] = 1;
            }
        }
        System.out.println(Arrays.toString(result));
        return result;
    }

    public static void main(String[] args) {
        BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic();
        List<String> lines;
        try {
            lines = InputFile.readInputFile("input-day03.txt");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String[] linesArray = lines.toArray(new String[0]);
        int[][] report = new int[linesArray.length][linesArray[0].length()];
        for (int i = 0; i < linesArray.length; i++) {
            for (int j = 0; j < linesArray[0].length(); j++) {
                if (linesArray[i].charAt(j) == '1') {
                    report[i][j] = 1;
                }
            }
        }
        int[][] report2 = {
                {0, 0, 1, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 1, 1, 0},
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {0, 1, 1, 1, 1},
                {0, 0, 1, 1, 1},
                {1, 1, 1, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 1, 0}};
        int[] gammaRate = binaryDiagnostic.getGammaRate(report);
        int[] epsilonRate = binaryDiagnostic.invert(gammaRate);


        System.out.println("Result1: " + binaryDiagnostic.binaryToDecimal(gammaRate) * binaryDiagnostic.binaryToDecimal(epsilonRate));
        int[] o2GenRate1 = binaryDiagnostic.getO2GenRate(report2, 0);
        int[] co2ScrubRate1 = binaryDiagnostic.getCO2ScrubberRate(report2, 0);
        System.out.println("Result 2.1: " + binaryDiagnostic.binaryToDecimal(o2GenRate1) * binaryDiagnostic.binaryToDecimal(co2ScrubRate1));
        int[] o2GenRate2 = binaryDiagnostic.getO2GenRate(report, 0);
        int[] co2ScrubRate2 = binaryDiagnostic.getCO2ScrubberRate(report, 0);
        System.out.println("Result 2.2: " + binaryDiagnostic.binaryToDecimal(o2GenRate2) * binaryDiagnostic.binaryToDecimal(co2ScrubRate2));

    }

    public int binaryToDecimal(int[] bits) {
        int result = 0, base = 1, start = bits.length - 1;
        for (int i = bits.length - 1; i >= 0; i--) {

            if (bits[i] == 1) {
                result += base;
            }
            base *= 2;
        }
        return result;
    }

    public int[] invert(int[] in) {
        int[] result = new int[in.length];
        for (int i = 0; i < in.length; i++) {
            if (in[i] == 0) result[i] = 1;
        }
        return result;
    }
}
