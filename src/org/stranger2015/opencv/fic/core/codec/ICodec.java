package org.stranger2015.opencv.fic.core.codec;


import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 */
public
interface ICodec<N extends TreeNode <N, A, M>, A extends Address <A>, M extends IImage> {
    /**
     * @return
     */
    IEncoder <N, A, M> getEncoder ();

    /**
     * @return
     */
    IDecoder <M> getDecoder ();

    /**
     * @return
     */
    int getImageSizeBase ();
}
