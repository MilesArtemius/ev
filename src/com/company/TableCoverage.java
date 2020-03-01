package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class TableCoverage {
    TableCoverage parent;
    ArrayList<TableCoverage> children;

    char [][] table;
    Square lastAdded;
    int size;

    public TableCoverage(int sz) {
        parent = null;
        children = new ArrayList<>();

        table = new char [sz][sz];
        for (char[] chars : table) {
            Arrays.fill(chars, ' ');
        }
        table[sz-1][sz-1] = '0';
        size = sz / 2 + 1;
    }

    public TableCoverage(TableCoverage parental, Square additional) {
        parent = parental;
        children = new ArrayList<>();

        table = new char [parental.table.length][parental.table.length];
        for (int i = 0; i < parental.table.length; i++) {
            table[i] = parental.table[i].clone();
        }
        lastAdded = additional;
        size = parental.size;

        for (int i = additional.getY(); i < additional.getY() + additional.getSize(); i++) {
            for (int j = additional.getX(); j < additional.getX() + additional.getSize(); j++) {
                table[i][j] = '1';
            }
        }
    }

    LinkedList<Square> variateSquares() {
        return addSquare();
    }

    private LinkedList<Square> addSquare() {
        LinkedList<Square> tp = new LinkedList<>();

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] == ' ') {
                    int maxY = Math.min(table.length, i + size + 1);
                    int maxX = Math.min(table[i].length, j + size + 1);
                    int occupiedX = maxX, occupiedY = maxY, topSize;

                    for (int k = i + 1; k < maxY; k++) {
                        if (table[k][j] != ' ') {
                            occupiedY = k;
                            break;
                        }
                    }
                    for (int l = j + 1; l < maxX; l++) {
                        if (table[i][l] != ' ') {
                            occupiedX = l;
                            break;
                        }
                    }
                    topSize = Math.min(occupiedX - j, occupiedY - i);
                    if (table[i + topSize - 1][j + topSize - 1] != ' ') topSize--;

                    for (int k = 1; k <= topSize; k++) tp.push(new Square(j, i, k));

                    return tp;
                }
            }
        }

        return tp;
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
