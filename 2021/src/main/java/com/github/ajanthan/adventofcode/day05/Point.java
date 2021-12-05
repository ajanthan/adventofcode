package com.github.ajanthan.adventofcode.day05;

public class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Point point = (Point) obj;
        return this.x == point.x && this.y == point.y;
    }
}
