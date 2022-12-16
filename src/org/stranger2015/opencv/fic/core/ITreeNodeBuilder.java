package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;

import java.util.List;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
interface ITreeNodeBuilder<N extends TreeNode <N>>{

    ITreeNodeBuilder<N> newInstance();

    /**
     * @return
     * @throws ValueError
     */
    Tree <N> buildTree ( IImageBlock  imageBlock ) throws ValueError;

    /**
     * @return
     */
    List <TreeNodeBase <?>> getSuccessors ();

    /**
     * @param node
     */
    void add ( TreeNodeBase <?> node );

    /**
     * @param node
     */
    void addLeafNode ( LeafNode <?> node );

    /**
     * @return
     */
    TreeNodeBase <N> getLastNode ();

    /**
     * @return
     */
    LeafNode <N> getLastLeafNode ();
}