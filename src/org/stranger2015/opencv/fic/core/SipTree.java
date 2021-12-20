package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.codec.SipAddress;

/**
 * @param <N>
 * @param <M>
 * @param <A>
 */
public
class SipTree<N extends SipTreeNode <N, ?>, M extends Image, A extends SipAddress <A>>
        extends Tree <N, M, A> {

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
    @Override
    public
    TreeNode <N, A> nodeInstance ( TreeNode <N, A> parent, Direction quadrant, Rect rect ) {
        return new SipTreeNode <>(parent, quadrant, rect);
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    Class <N> getNodeClass () {
        return (Class <N>) SipTreeNode.class;
    }
}
