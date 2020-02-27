package com.company;

import java.util.LinkedList;

public class Main {
    //private static TableCoverage backtrace() {}

    public static void main(String[] args) {
        PseudoTree tree = new PseudoTree(13);
        PseudoTreeLeaf last = tree.iterateRowsUntilSuccess();
        System.out.println(last.content);
        LinkedList<Square> result = new LinkedList<>();
        result.addAll(tree.head);
        result.addAll(last.respectParents());
    }
}
