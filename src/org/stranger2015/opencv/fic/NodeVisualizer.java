package org.stranger2015.opencv.fic;

import org.stranger2015.opencv.fic.core.TreeNodeBase;

/**
 *
 */
public
class NodeVisualizer /*implements IDrawable<Image> */ {

    private TreeNodeBase <?> treeNode;

    /**
     * @param treeNode
     */
    public
    void setNode ( TreeNodeBase <?> treeNode ) {
        this.treeNode = treeNode;
    }

    /**
     * @return
     */
    public
    TreeNodeBase <?> getTreeNode () {
        return treeNode;
    }

    /**
     * @param treeNode
     */
    public
    void setTreeNode ( TreeNodeBase <?> treeNode ) {
        this.treeNode = treeNode;
    }
}
