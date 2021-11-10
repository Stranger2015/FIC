package org.stranger2015.opencv.fic.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public
class NodeList {
    private final List <TreeNode <?>> list = new ArrayList <>();

    /**
     * @param node
     */
    public
    void add ( TreeNode <?> node ) {
        addi(node.index, node);
    }

    /**
     * @param node
     */
    public
    void set ( TreeNode <?> node ) {
        seti(node.index, node);
    }

    /**
     * @param index
     * @param node
     */
    private
    void seti ( int index, TreeNode <?> node ) {
        list.set(index, node);
    }

    /**
     * @param direction
     * @param node
     */
    public
    void add ( SideDirection direction, TreeNode <?> node ) {
        int index = direction.ordinal();
        addi(index, node);
    }

    /**
     * @param index
     * @param node
     */
    public
    void add ( int index, TreeNode <?> node ) {
        addi(index, node);
    }

    /**
     * @param index
     * @param node
     */
    public
    void set ( int index, TreeNode <?> node ) {
        list.set(index, node);
    }

    /**
     * @param index
     * @param node
     */
    private
    void addi ( int index, TreeNode <?> node ) {
        if (index < list.size()) {
            list.set(index, node);
        }
        else {
            list.add(index, node);
        }
    }

    /**
     * @param direction
     * @return
     */
    public
    TreeNode <?> get ( SideDirection direction ) {
        return list.get(direction.ordinal());
    }
}
