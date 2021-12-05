package com.github.ajanthan.adventofcode.day05;

import com.github.ajanthan.adventofcode.utils.InputFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HydroThermalVenture {
    public int calculateDangerousPoints(Line[] lines) {
        Map<String, Integer> points = new HashMap<>();
        int dangerousPointCount = 0;
        for (Line line : lines) {
            if (line.start.x == line.end.x) {
                if (line.start.y < line.end.y) {
                    generateYPoints(points, line.start.x, line.start.y, line.end.y);
                } else {
                    generateYPoints(points, line.start.x, line.end.y, line.start.y);
                }
            }
            if (line.start.y == line.end.y) {
                if (line.start.x < line.end.x) {
                    generateXPoints(points, line.start.y, line.start.x, line.end.x);
                } else {
                    generateXPoints(points, line.start.y, line.end.x, line.start.x);
                }
            }
            if(Math.abs(line.end.x - line.start.x) == Math.abs(line.end.y -line.start.y)){
                generateXYPoints(points,line.start.x,line.end.x,line.start.y,line.end.y);
            }
        }
        for (int interSectionCount : points.values()) {
            if (interSectionCount >= 2) dangerousPointCount++;
        }

        return dangerousPointCount;
    }

    private void generateXYPoints(Map<String, Integer> points, int xStart, int xEnd, int yStart, int yEnd) {
        for( int x = xStart, y = yStart; x<=xEnd && y <= yEnd;x++,y++){
            markPoint(points,x,y);
        }
        for( int x = xStart, y = yStart; x>=xEnd && y >= yEnd;x--,y--){
            markPoint(points,x,y);
        }
        for( int x = xStart, y = yStart; x<=xEnd && y >= yEnd;x++,y--){
            markPoint(points,x,y);
        }
        for( int x = xStart, y = yStart; x >=xEnd && y <= yEnd;x--,y++){
            markPoint(points,x,y);
        }

    }
//    private void generateXYPointsNegativeAngle(Map<String, Integer> points, int xStart, int xEnd, int yStart, int yEnd) {
//        for( int x = xStart, y = yStart; x>= xEnd && y <= yEnd;x--,y++){
//            markPoint(points,x,y);
//        }
//
//    }

    private void generateYPoints(Map<String, Integer> points, int x, int startY, int endY) {
        for (int y = startY; y <= endY; y++) {
            markPoint(points, x, y);
        }
    }

    private void generateXPoints(Map<String, Integer> points, int y, int startX, int endX) {
        for (int x = startX; x <= endX; x++) {
            markPoint(points, x, y);
        }
    }

    private void markPoint(Map<String, Integer> points, int x, int y) {
        String key = x + "#" + y;
        Integer count = 0;
        if (points.containsKey(key)) {
            count = points.get(key);
            count++;
        } else {
            count = 1;
        }
        points.put(key, count);
    }

    public static void main(String[] args) {
        Line[] lines1 = {
                new Line(new Point(0, 9), new Point(5, 9)),
                new Line(new Point(8, 0), new Point(0, 8)),
                new Line(new Point(9, 4), new Point(3, 4)),
                new Line(new Point(2, 2), new Point(2, 1)),
                new Line(new Point(7, 0), new Point(7, 4)),
                new Line(new Point(6, 4), new Point(2, 0)),
                new Line(new Point(0, 9), new Point(2, 9)),
                new Line(new Point(3, 4), new Point(1, 4)),
                new Line(new Point(0, 0), new Point(8, 8)),
                new Line(new Point(5, 5), new Point(8, 2)),
        };
        HydroThermalVenture hydroThermalVenture = new HydroThermalVenture();
        int dangerousPoints1 = hydroThermalVenture.calculateDangerousPoints(lines1);
        System.out.println("Answer 1:" + dangerousPoints1);
        List<Line> lineList = new ArrayList<>();
        try {
            List<String> lines = InputFile.readInputFile("input-day05.txt");
            lineList = lines.stream().map(s -> {
                String[] points = s.split("->");
                String[] xy = points[0].split(",");
                Point start = new Point(Integer.parseInt(xy[0].trim()), Integer.parseInt(xy[1].trim()));
                xy = points[1].split(",");
                Point end = new Point(Integer.parseInt(xy[0].trim()), Integer.parseInt(xy[1].trim()));
                return new Line(start, end);
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Line[] lines2 = lineList.toArray(new Line[0]);
        int dangerousPoints2 = hydroThermalVenture.calculateDangerousPoints(lines2);
        System.out.println("Answer 2:" + dangerousPoints2);
    }
}
