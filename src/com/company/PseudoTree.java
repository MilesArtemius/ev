package com.company;

import java.util.Collections;
import java.util.LinkedList;

public class PseudoTree {
    static final int [] smp = new int [] {97, 89, 83, 79, 73, 71, 67, 61, 59, 53, 47, 43, 41, 37, 31, 29, 23, 19, 17, 13, 11, 7, 5, 3, 2};

    int size, multiplier = 1;
    LinkedList<Square> head;
    PseudoTreeLeaf root;
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

        size = sz;
        head = new LinkedList<>();

        int halfSize = size / 2;

        head.add(new Square(0, 0, halfSize + 1));
        head.add(new Square(halfSize + 1, 0, halfSize));
        head.add(new Square(0, halfSize + 1, halfSize));

        if (size == 2) {
            head.add(new Square(halfSize, halfSize, halfSize));
            hasTail = false;
        } else {
            tailOffset = halfSize;
            root = new PseudoTreeLeaf(new TableCoverage(halfSize + 1), null);
            hasTail = true;
        }
    }

    public boolean hasTail() {
        return hasTail;
    }



    public PseudoTreeLeaf iterateRowsUntilSuccess() {
        LinkedList<PseudoTreeLeaf> currentRow = new LinkedList<>(Collections.singletonList(root));
        LinkedList<PseudoTreeLeaf> newRow = new LinkedList<>();
        PseudoTreeLeaf finalContainer = null;

        int counter = 0;
        while (finalContainer == null) {
            for (int i = 0; i < currentRow.size(); i++) {
                PseudoTreeLeaf leaf = currentRow.get(i);
                if (i == 0) {
                    //System.out.println(leaf.content);
                    System.out.println("onesnum " + leaf.content.onesNumber);
                }
                if (leaf.content.onesNumber != -1) {
                    if (leaf.content.onesNumber == 0) {
                        finalContainer = leaf;
                        break;
                    }
                    leaf.content.onesNumber--;
                    newRow.add(leaf);
                    continue;
                }
                LinkedList<Square> children = leaf.content.variateSquares();
                if (children.isEmpty()) {
                    if (leaf.content.onesNumber != 0) {
                        newRow.add(leaf);
                        continue;
                    }
                    finalContainer = leaf;
                    break;
                }
                for (Square square: children) {
                    PseudoTreeLeaf newLeaf = new PseudoTreeLeaf(new TableCoverage(leaf.content, square), leaf);
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
