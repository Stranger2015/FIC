package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.opencv.core.Scalar;

/**
 * @param <N>
 */
@SuppressWarnings("unchecked")
public
class VsaTreeNode<N extends TreeNode <N, A>, A extends SaAddress <A>> extends TreeNode <N, A> {
    /**
     * @param index
     * @return
     */
    @Override
    protected
    A newAddress ( int index ) throws ValueError {
        return (A) new SaAddress <A>(index);
    }

    /**
     * @param parent
     * @param hexant
     * @param rect
     */
    public
    VsaTreeNode ( TreeNode <N, A> parent, Direction hexant, Rect rect ) throws ValueError {
        super(parent, hexant, rect);
    }

    /**
     * @param image
     * @param rect
     */
    @Override
    public
    void draw ( Image image, Rect rect ) {

    }

    /**
     * @param image
     * @param rect
     * @param color
     */
    @Override
    public
    void rectangle ( Image image, Rect rect, Scalar color ) {
        super.rectangle(image, rect, color);
    }

    /**
     * @param hexant
     * @param boundingBox
     * @return
     */
    @Override
    public
    TreeNode <N, A> createChild ( Direction hexant, Rect boundingBox ) throws ValueError {
        return new VsaTreeNode <>(this, hexant, boundingBox);
    }

    /**
     * @param parent
     * @param hexant
     * @param boundingBox
     * @return
     */
    @Override
    public
    TreeNode <N, A> createNode ( TreeNode <N, A> parent, Direction hexant, Rect boundingBox ) throws ValueError {
        return new VsaTreeNode <>(parent, hexant, boundingBox);
    }
}
