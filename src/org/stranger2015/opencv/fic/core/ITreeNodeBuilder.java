package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
interface ITreeNodeBuilder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer> {

    ITreeNodeBuilder<N,A,G> newInstance();

    /**
     * @return
     * @throws ValueError
     */
    Tree <N, A, G> buildTree ( IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @return
     */
    List <TreeNodeBase <N, A, G>> getSuccessors ();

    /**
     * @param node
     */
    void add ( TreeNodeBase <N, A, G> node );

    /**
     * @param node
     */
    void addLeafNode ( LeafNode <N, A, G> node );

    /**
     * @return
     */
    TreeNodeBase <N, A, G> getLastNode ();

    /**
     * @return
     */
    LeafNode <N, A, G> getLastLeafNode ();
}