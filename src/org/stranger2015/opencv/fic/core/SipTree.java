package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.codec.SaTree;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;
import java.util.Map;

import static java.util.Map.*;

/**
 * @param <N>
 
 * @param
 */
public
class SipTree<N extends TreeNode <N>>
        extends SaTree <N> {

    /**
     * @param parent
     * @param blocks
     * @param action
     */
    @SuppressWarnings("unchecked")
    public
    SipTree ( TreeNode <N> parent,
              Map<Point, SipImageBlock> blocks,
              TreeNodeTask <N> action ) {

        super(parent, Map. <Object, Object>of(), action);
    }

    public
    SipTree ( TreeNode <N> root, IImageBlock  imageBlock ) {
        super(root,imageBlock);
    }

    /**
     * @return
     */
    @Override
    public
    List <TreeNode<N>> getNodes () {
        return super.getNodes();
    }

    /**
     * @param root
     * @param image
     * @param action
     */
    public
    SipTree ( TreeNode <N> root, IImage image, TreeNodeTask <N> action ) {
        super(root, image, action);
    }

    /**
     * @param shift
     * @return
     */
    @SuppressWarnings("unchecked")
    public
    N findNode ( Point shift ) {
        for (N n = this.getRoot().getChild(0); n != null; n = n.getChild(0)) {
           if(n.isLeaf()){
               if (((ILeaf<N>) n).getX() == shift.getX() && ((ILeaf<N>) n).getY() == shift.getY()) {
                 return n;
               }
           }
        }

        return null;
    }
}
