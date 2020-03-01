package com.company;

public class MonoBitArray {
    private int [] data;

    public MonoBitArray(int size) {
        data = new int [size];
    }

    public Coords findFirstEmpty() {
        int full = Integer.MAX_VALUE % data.length;

        for (int i = 0; i < data.length; i++) {
            int partial = data[i] % data.length;
            if (partial < full) {
                int counter = 0, diff = full - partial;
                while (diff > 1) {
                    diff >>= 1;
                    counter++;
                }
                return new Coords(counter, i);
            }
        }

        return null;
    }

    public boolean isEmpty(Coords coords) {
        return (data[coords.getY()] >> (data.length - coords.getX())) % 2 == 0;
    }

    public int getLine(int num) {
        return data[num];
    }

    public void addLine(int line, int num) {
        data[num] += line;
    }



    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int line : data) {
            output.append(Integer.toBinaryString(line % data.length));
            output.append('\n');
        }
        return output.toString();
    }
}
