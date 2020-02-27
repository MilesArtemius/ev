package com.company;

public class Square {
    private int x, y, size;
    private boolean isFinal;

    public Square(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "TablePiece{" +
                "x=" + x +
                ", y=" + y +
                ", size=" + size +
                '}';
    }
}
