package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.SaTree;
import org.stranger2015.opencv.fic.utils.Point;

import java.util.List;
import java.util.Map;

/**
 * @param <N>
 
 * @param
 */
public
class SipTree<T, T1>
        extends SaTree <TreeNode> {

    /**
     * @param parent
     * @param blocks
     * @param action
     */
    @SuppressWarnings("unchecked")
    public
    SipTree ( TreeNode <TreeNode> parent,
              Map<Point, SipImageBlock> blocks,
              TreeNodeTask <TreeNode> action ) {

        super(parent, Map. <Object, Object>of(), action);
    }

    public
    SipTree ( TreeNode <TreeNode> root, IImageBlock  imageBlock ) {
        super(root,imageBlock);
    }

    /**
     * @return
     */
    @Override
    public
    List <TreeNode<TreeNode>> getNodes () {
        return super.getNodes();
    }

    /**
     * @param root
     * @param image
     * @param action
     */
    public
    SipTree ( TreeNode <TreeNode> root, IImage image, TreeNodeTask <TreeNode> action ) {
        super(root, image, action);
    }

    /**
     * @param shift
     * @return
     */
    @SuppressWarnings("unchecked")
    public
    TreeNode findNode ( Point shift ) {
        for (TreeNode n = this.getRoot().getChild(0); n != null; n = n.getChild(0)) {
           if(n.isLeaf()){
               if (((ILeaf<TreeNode>) n).getX() == shift.getX() && ((ILeaf<TreeNode>) n).getY() == shift.getY()) {
                 return n;
               }
           }
        }

        return null;
    }
}
