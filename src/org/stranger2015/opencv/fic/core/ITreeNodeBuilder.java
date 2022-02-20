package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 */
public
interface ITreeNodeBuilder<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage, G extends BitBuffer> {
    Tree <N, A, M, G> buildTree () throws ValueError;
}
