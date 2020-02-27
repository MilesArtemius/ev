package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

public class PseudoTreeLeaf {
    TableCoverage content;
    PseudoTreeLeaf parent;
    ArrayList<PseudoTreeLeaf> children;

    public PseudoTreeLeaf(TableCoverage content, PseudoTreeLeaf parent) {
        this.content = content;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public LinkedList<Square> respectParents() {
        LinkedList<Square> family = new LinkedList<>();
        PseudoTreeLeaf heir = this;
        while (heir.parent != null) {
            family.add(heir.content.lastAdded);
            heir = heir.parent;
        }
        return family;
    }
}
