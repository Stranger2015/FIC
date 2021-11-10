package org.stranger2015.opencv.fic.core;

import java.util.ArrayList;
import java.util.List;

public
class NodeList {
    private final List <TreeNode <?>> list = new ArrayList <>();

    public
    void add ( TreeNode <?> node ) {
        addi(node.index, node);
    }

    public
    void set ( TreeNode <?> node ) {
    seti(node.index, node);
    }

    private
    void seti ( int index, TreeNode <?> node ) {


    }

    public

    void add ( SideDirection direction, TreeNode <?> node ) {
        int index = direction.ordinal();
        addi(index, node);
    }


    public
    void add ( int i, TreeNode <?> node ) {
        addi(i, node);
    }

    public
    void set ( int i, TreeNode <?> node ) {
        list.set(index, node);
    }

    private
    void addi ( int index, TreeNode <?> node ) {
        if (index < list.size()) {
            list.set(index, node);
        }
        else {
            list.add(index, node);
        }

    }

    public
    TreeNode <?> get ( SideDirection direction ) {
        return list.get(direction.ordinal());
    }
}
