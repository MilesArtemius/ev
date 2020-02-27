package com.company;

import java.util.Arrays;
import java.util.LinkedList;

public class TableCoverage {
    char [][] table;
    Square lastAdded;
    int onesNumber = -1;
    int size;

    public TableCoverage(int sz) {
        table = new char [sz][sz];
        for (char[] chars : table) {
            Arrays.fill(chars, ' ');
        }
        table[0][0] = '0';
        size = sz - 1;
    }

    public TableCoverage(TableCoverage parental, Square additional) {
        table = new char [parental.table.length][parental.table.length];
        for (int i = 0; i < parental.table.length; i++) {
            table[i] = parental.table[i].clone();
        }
        lastAdded = additional;
        size = additional.getSize();

        for (int i = additional.getY(); i < additional.getY() + additional.getSize(); i++) {
            for (int j = additional.getX(); j < additional.getX() + additional.getSize(); j++) {
                table[i][j] = '1';
            }
        }
    }

    LinkedList<Square> variateSquares() {
        LinkedList<Square> tp = new LinkedList<>();
        for (int i = size; i > 1; i--) {
            tp.addAll(addSquare(i));
        }
        onesNumber = countOnes();
        return tp;
    }

    private LinkedList<Square> addSquare(int size) {
        LinkedList<Square> tp = new LinkedList<>();

        for (int i = 0; i < table.length - size + 1; i++) {
            for (int j = 0; j < table[i].length - size + 1; j++) {
                if (table[i][j] == ' ') {
                    boolean isFree = true;

                    for (int k = i; k < i + size; k++) {
                        for (int l = j; l < j + size; l++) {
                            isFree &= table[k][l] == ' ';
                        }
                    }

                    boolean isSquared = false;
                    for (Square square : tp) {
                        isSquared |= ((i - square.getY()) % size == 0) && ((j - square.getX()) % size == 0);
                    }

                    if (isFree && !isSquared) tp.push(new Square(j, i, size));
                }
            }
        }

        return tp;
    }

    LinkedList<Square> fillWithOnes() {
        return new LinkedList<>();
    }

    private int countOnes() {
        int ones = 0;
        for (char[] chars : table) {
            for (char aChar : chars) {
                if (aChar == ' ') ones++;
            }
        }
        return ones;
    }

    boolean isSame(TableCoverage tc) {
        boolean same = true;

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                same &= table[i][j] == tc.table[i][j];
            }
        }

        return same;
    }



    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (char[] chars : table) {
            output.append(Arrays.toString(chars));
            output.append('\n');
        }
        return output.toString();
    }
}
