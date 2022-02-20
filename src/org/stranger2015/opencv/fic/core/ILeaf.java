package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 */
public
interface ILeaf<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage, G extends BitBuffer> {

    /**
     * @return
     */
    int getX();

    /**
     * @return
     */
    int getY();

    /**
     * @return
     */
    Address <A> getAddress();

    /**
     * @return
     */
    ImageBlock getImageBlock ();

    /**
     * @return
     */
    M getMat ();

    /**
     * @return
     */
    M getImage ();

    /**
     * @return
     */
    Rect getBoundingBox();
}
