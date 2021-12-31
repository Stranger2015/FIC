package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public
class NodeList<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
        implements Iterable <N> {

    private final List <TreeNode <N, A, M>> list = new ArrayList <>();

    /**
     *
     */
    public
    NodeList () {
    }

    /**
     * @param node
     */
    public
    NodeList ( TreeNode <N, A, M> node ) {
        list.add(node);
    }

    /**
     * @return
     */
    public
    boolean isEmpty () {
        return list.isEmpty();
    }

    /**
     * @return
     */
    public
    int size () {
        return list.size();
    }

    /**
     * @param node
     */
    public
    void add ( TreeNode <N, A, M> node ) {
        addi(node.index, node);
    }

    /**
     * @param node
     */
    public
    void set ( TreeNode <N, A, M> node ) {
        seti(node.index, node);
    }

    /**
     * @param index
     * @param node
     */
    private
    void seti ( int index, TreeNode <N, A, M> node ) {
        list.set(index, node);
    }

    /**
     * @param direction
     * @param node
     */
    public
    void add ( EDirection direction, TreeNode <N, A, M> node ) {
        int index = direction.getOrd();
        addi(index, node);
    }

    /**
     * @param direction
     * @param node
     */
    public
    void set ( EDirection direction, TreeNode <N, A, M> node ) {
        int index = direction.getOrd();
        seti(index, node);
    }

    /**
     * @param index
     * @param node
     */
    public
    void add ( int index, TreeNode <N, A, M> node ) {
        addi(index, node);
    }

    /**
     * @param index
     * @param node
     */
    public
    void set ( int index, TreeNode <N, A, M> node ) {
        list.set(index, node);
    }

    /**
     * @param index
     * @param node
     */
    private
    void addi ( int index, TreeNode <N, A, M> node ) {
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
    TreeNode <N, A, M> get ( EDirection dir ) {
        return list.get(dir.getOrd());
    }

    /**
     * @return
     */
    public
    List <TreeNode <N, A, M>> getList () {
        return list;
    }

    /**
     * @param node
     * @return
     */
    NodeList <N, A, M> singleton ( TreeNode <N, A, M> node ) {
        return new NodeList <>(node);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @SuppressWarnings("unchecked")
    @NotNull
    @Override
    public
    Iterator <N> iterator () {
        return (Iterator <N>) list.iterator();
    }
}
