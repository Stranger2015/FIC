package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 */
public
interface ITreeNodeBuilder<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image> {
    Tree <N, A, M> buildTree () throws ValueError;
}
