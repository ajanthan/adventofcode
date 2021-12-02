package com.github.ajanthan.adventofcode.day02;

public class Position {
    private int x;
    private int d;

    public Position(int x, int d) {
        this.x = x;
        this.d = d;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }
}
