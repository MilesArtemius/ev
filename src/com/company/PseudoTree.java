package com.company;

import java.util.Collections;
import java.util.LinkedList;

public class PseudoTree {
    static final int [] smp = new int [] {97, 89, 83, 79, 73, 71, 67, 61, 59, 53, 47, 43, 41, 37, 31, 29, 23, 19, 17, 13, 11, 7, 5, 3, 2};

    int size, multiplier = 1;
    LinkedList<Square> head;
    TableCoverage root;
    boolean hasTail;
    int tailOffset;

    public PseudoTree(int sz) {
        for (int i = 0; i < smp.length; i++) {
            if ((sz % smp[i] == 0) && (sz / smp[i] != 1)) {
                sz /= smp[i];
                multiplier *= smp[i];
                i--;
            }
        }

        head = new LinkedList<>();

        int halfSize = sz / 2;
        size = halfSize + 1;

        head.add(new Square(0, 0, halfSize + 1));
        head.add(new Square(halfSize + 1, 0, halfSize));
        head.add(new Square(0, halfSize + 1, halfSize));

        if (size == 2) {
            head.add(new Square(halfSize, halfSize, halfSize));
            hasTail = false;
        } else {
            tailOffset = halfSize;
            root = new TableCoverage(halfSize + 1);
            hasTail = true;
        }
    }

    public boolean hasTail() {
        return hasTail;
    }



    public TableCoverage iterateRowsUntilSuccess() {
        LinkedList<TableCoverage> currentRow = new LinkedList<>(Collections.singletonList(root));
        LinkedList<TableCoverage> newRow = new LinkedList<>();
        TableCoverage finalContainer = null;

        int counter = 0;
        while (finalContainer == null) {
            for (int i = 0; i < currentRow.size(); i++) {
                TableCoverage leaf = currentRow.get(i);
                LinkedList<Square> children = leaf.variateSquares();
                if (children.isEmpty()) {
                    finalContainer = leaf;
                    break;
                }
                for (Square square: children) {
                    TableCoverage newLeaf = new TableCoverage(leaf, square);
                    leaf.children.add(newLeaf);
                    newRow.add(newLeaf);
                }
            }
            currentRow = newRow;
            System.out.println("New level " + counter++ + " reached; elements: " + newRow.size());
            newRow = new LinkedList<>();
        }

        return finalContainer;
    }
}
