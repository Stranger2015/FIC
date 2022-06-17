package org.stranger2015.opencv.fic.core;

import org.opencv.core.MatOfInt;
import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 
 */
public
interface ILeaf<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>  {

    /**
     * @return
     */
    int getX ();

    /**
     * @return
     */
    int getY ();

    /**
     * @return
     */
    IAddress <A> getAddress ();

    /**
     * @return
     */
    IImageBlock <A> getImageBlock ();

    /**
     * @return
     */
    MatOfInt getMat ();

    /**
     * @return
     */
    IImage<A> getImage ();

    /**
     * @return
     */
    IIntSize getBoundingBox ();
}
