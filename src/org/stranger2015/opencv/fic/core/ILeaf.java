package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 */
public
interface ILeaf<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image> {
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
