package com.company;

public class Main {
    public static void main(String[] args) {
        PseudoTree tree = new PseudoTree(5);
        TableCoverage last = tree.iterateRowsUntilSuccess();
        int sqNum = 0;
        while (last != null) {
            sqNum++;
            System.out.println(last);
            last = last.parent;
        }
        System.out.println("TOTAL: " + (sqNum + 2));
    }
}
