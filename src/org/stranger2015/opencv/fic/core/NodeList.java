package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.*;

/**
 *
 */
public
class NodeList<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>, G extends BitBuffer>
        implements Iterable <N>{

    private final List <TreeNode <N, A, M, G>> list = new ArrayList <>();

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
    NodeList ( TreeNode <N, A, M, G> node ) {
        list.add(node);
    }

    public
    NodeList ( int i ) {

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
    void add ( TreeNode <N, A, M, G> node ) {
        addi(node.index, node);
    }

    /**
     * @param node
     */
    public
    void set ( TreeNode <N, A, M, G> node ) {
        seti(node.index, node);
    }

    /**
     * @param index
     * @param node
     */
    private
    void seti ( int index, TreeNode <N, A, M, G> node ) {
        list.set(index, node);
    }

    /**
     * @param direction
     * @param node
     */
    public
    void add ( EDirection direction, TreeNode <N, A, M, G> node ) {
        int index = direction.getOrd();
        addi(index, node);
    }

    /**
     * @param direction
     * @param node
     */
    public
    void set ( EDirection direction, TreeNode <N, A, M, G> node ) {
        int index = direction.getOrd();
        seti(index, node);
    }

    /**
     * @param index
     * @param node
     */
    public
    void add ( int index, TreeNode <N, A, M, G> node ) {
        addi(index, node);
    }

    /**
     * @param index
     * @param node
     */
    public
    void set ( int index, TreeNode <N, A, M, G> node ) {
        list.set(index, node);
    }

    /**
     * @param index
     * @param node
     */
    private
    void addi ( int index, TreeNode <N, A, M, G> node ) {
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
    TreeNode <N, A, M, G> get ( EDirection dir ) {
        return list.get(dir.getOrd());
    }

    /**
     * @return
     */
    public
    List <TreeNode <N, A, M, G>> getList () {
        return list;
    }

    /**
     * @param node
     * @return
     */
    NodeList <N, A, M, G> singleton ( TreeNode <N, A, M, G> node ) {
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

    /**
     * @param l
     */
    @SuppressWarnings("unchecked")
    public
    void add ( NodeList <N, A, M, G> l ) {
        getList().addAll((Collection <? extends TreeNode <N, A, M, G>>) l);
    }

    public
    TreeNode <N, A, M, G> get ( int ord ) {
        return list.get(ord);
    }
}
