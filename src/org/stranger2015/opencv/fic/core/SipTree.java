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
 
 * @param <A>
 */
public
class SipTree<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends SaTree <N, A, G> {

    /**
     * @param parent
     * @param blocks
     * @param action
     */
    @SuppressWarnings("unchecked")
    public
    SipTree ( TreeNode <N, A, G> parent,
              Map<Point, SipImageBlock<A>> blocks,
              TreeNodeTask <N, A, G> action ) {

        super(parent, of(), action);
    }

    public
    SipTree ( TreeNode <N, A, G> root, IImageBlock <A> imageBlock ) {
        super(root,imageBlock);
    }

    /**
     * @return
     */
    @Override
    public
    List <TreeNode<N, A, G>> getNodes () {
        return super.getNodes();
    }

    /**
     * @param root
     * @param image
     * @param action
     */
    public
    SipTree ( TreeNode <N, A, G> root, IImage<A> image, TreeNodeTask <N, A, G> action ) {
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
               if (((ILeaf<N, A, G>) n).getX() == shift.getX() && ((ILeaf<N, A, G>) n).getY() == shift.getY()) {
                 return n;
               }
           }
        }

        return null;
    }
}
