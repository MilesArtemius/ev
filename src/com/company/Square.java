package com.company;

public class Square extends Coords {
    private int size;

    public Square(int x, int y, int size) {
        super(x, y);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "TablePiece{" +
                "x=" + getX() +
                ", y=" + getY() +
                ", size=" + size +
                '}';
    }
}
