package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.codec.SipAddress;

/**
 * @param <N>
 * @param <A>
 */
public
class SipTreeNode<N extends SipTreeNode <N, A>, A extends SipAddress <A>>
        extends VsaTreeNode <N, A> {
    /**
     * @param parent
     * @param hexant
     * @param rect
     */
    public
    SipTreeNode ( TreeNode <N, A> parent, Direction hexant, Rect rect ) throws ValueError {
        super(parent, hexant, rect);
    }

    /**
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    protected
    A newAddress ( int index ) throws ValueError {
        return (A) new SipAddress <A>(index);
    }

    /**
     * @param hexant
     * @param boundingBox
     * @return
     */
    @Override
    public
    TreeNode <N, A> createChild ( Direction hexant, Rect boundingBox ) throws ValueError {
        return new SipTreeNode <>(this, hexant, boundingBox);
    }

    /**
     * @param parent
     * @param hexant
     * @param boundingBox
     * @return
     */
    @Override
    public
    TreeNode <N, A> createNode ( TreeNode <N, A> parent, Direction hexant, Rect boundingBox )
            throws ValueError {
        return new SipTreeNode <>(parent, hexant, boundingBox);
    }
}
