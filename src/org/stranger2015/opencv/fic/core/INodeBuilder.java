package org.stranger2015.opencv.fic.core;

/**
 * @param <N>
 * @param <A>
 */
public
interface INodeBuilder<N extends TreeNodeBase <N, A>, A extends Address <A, ?>> {
    TreeNodeBase <N, A> build ();
}
