package org.stranger2015.opencv.fic.core;

import org.opencv.core.MatOfInt;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * @param <N>
 * @param 
 */
public
interface ILeaf<N extends TreeNode <N>> {

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
    IAddress  getAddress ();

    /**
     * @return
     */
    IImageBlock  getImageBlock ();

    /**
     * @return
     */
    MatOfInt getMat ();

    /**
     * @return
     */
    IImage getImage ();

    /**
     * @return
     */
    IIntSize getBoundingBox ();
}
