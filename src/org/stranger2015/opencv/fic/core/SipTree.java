package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.SipAddress;
import org.stranger2015.opencv.fic.core.codec.SaTree;
import org.stranger2015.opencv.fic.utils.Point;

import java.util.Map;

/**
 * @param <N>
 * @param <M>
 * @param <A>
 */
public
class SipTree<N extends TreeNode <N, A, M>, A extends SipAddress <A>, M extends IImage>
        extends SaTree <N, A, M> {

    public
    SipTree ( SipTreeNode <N, A, M> parent,
              Map<Point, SipImageBlock> blocks,
              TreeNodeAction <N, A, M> namTreeNodeAction ) {
        super(parent, (M) blocks, namTreeNodeAction);
    }

    /**
     * @return
     */
    @Override
    public
    NodeList <N, A, M> getNodes () {
        return super.getNodes();
    }

    /**
     * @param root
     * @param image
     * @param action
     */
    public
    SipTree ( TreeNode <N, A, M> root, M image, TreeNodeAction <N, A, M> action ) {
        super(root, image, action);
    }

    /**
     * @param shift
     * @return
     */
    public
    N findNode ( Point shift ) {
        for (N n = this.getRoot().getChild(0); n != null; n = n.getChild(0)) {
           if(n.isLeaf()){
               if (((ILeaf) n).getX() == shift.getX() && ((ILeaf) n).getY() == shift.getY()) {
                 return n;
               }
           }
        }

        return null;
    }
}
