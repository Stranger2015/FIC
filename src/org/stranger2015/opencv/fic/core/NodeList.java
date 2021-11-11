package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public
class NodeList<N extends TreeNode <N>> implements Iterable<N>{

    private final List <TreeNode <N>> list = new ArrayList <>();

    public
    NodeList () {
    }

    public
    NodeList ( TreeNode <N> node ) {
        list.add(node);
    }

    public
    boolean isEmpty () {
        return list.isEmpty();
    }

    public
    int size () {
        return list.size();
    }

    /**
     * @param node
     */
    public
    void add ( TreeNode <N> node ) {
        addi(node.index, node);
    }

    /**
     * @param node
     */
    public
    void set ( TreeNode <N> node ) {
        seti(node.index, node);
    }

    /**
     * @param index
     * @param node
     */
    private
    void seti ( int index, TreeNode <N> node ) {
        list.set(index, node);
    }

    /**
     * @param direction
     * @param node
     */
    public
    void add ( Direction direction, TreeNode <N> node ) {
        int index = direction.getOrd();
        addi(index, node);
    }

    /**
     * @param index
     * @param node
     */
    public
    void add ( int index, TreeNode <N> node ) {
        addi(index, node);
    }

    /**
     * @param index
     * @param node
     */
    public
    void set ( int index, TreeNode <N> node ) {
        list.set(index, node);
    }

    /**
     * @param index
     * @param node
     */
    private
    void addi ( int index, TreeNode <N> node ) {
        if (index < list.size()) {
            list.set(index, node);
        }
        else {
            list.add(index, node);
        }
    }

    /**
     * @param dir
     * @return
     */
    public
    TreeNode <N> get ( Direction dir ) {
        return list.get(dir.getOrd());
    }

    public
    List <TreeNode <N>> getList () {
        return list;
    }

    NodeList <N> singleton ( TreeNode <N> node ) {
        return new NodeList <>(node);

    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @NotNull
    @Override
    public
    Iterator <N> iterator () {
        return null;//todo
    }
}
