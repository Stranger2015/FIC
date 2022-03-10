package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IAddress;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 */
public
interface ILeaf<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>, G extends BitBuffer> {

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
    IAddress <A> getAddress();

    /**
     * @return
     */
    ImageBlock<A> getImageBlock ();

    /**
     * @return
     */
    Mat getMat ();

    /**
     * @return
     */
    M getImage ();

    /**
     * @return
     */
    Rect getBoundingBox();
}
