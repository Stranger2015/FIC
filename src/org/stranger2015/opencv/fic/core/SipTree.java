package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.SaTree;
import org.stranger2015.opencv.fic.core.codec.SipAddress;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Map;

/**
 * @param <N>
 * @param <M>
 * @param <A>
 */
public
class SipTree<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>, G extends BitBuffer>
        extends SaTree <N, A, M, G> {

    public
    SipTree ( SipTreeNode <N, A, M, G> parent,
              Map<Point, SipImageBlock<A>> blocks,
              TreeNodeAction <N, A, M, G> namTreeNodeAction ) {

        super(parent, (M) blocks, namTreeNodeAction);
    }

    /**
     * @return
     */
    @Override
    public
    NodeList <N, A, M, G> getNodes () {
        return super.getNodes();
    }

    /**
     * @param root
     * @param image
     * @param action
     */
    public
    SipTree ( TreeNode <N, A, M, G> root, M image, TreeNodeAction <N, A, M, G> action ) {
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
               if (((ILeaf<N, A, M, G>) n).getX() == shift.getX() && ((ILeaf<N, A, M, G>) n).getY() == shift.getY()) {
                 return n;
               }
           }
        }

        return null;
    }
}
