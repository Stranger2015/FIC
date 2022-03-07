package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 */
public
interface ICodec<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>, G extends BitBuffer> {

    /**
     * @return
     */
    IEncoder <N, A, M, G> getEncoder ();

    /**
     * @return
     */
    IDecoder <M,A> getDecoder ();

    /**
     * @return
     */
    int getImageSizeBase ();

    /**
     * @param address
     * @return
     * @throws ValueError
     */
    Address<A> createAddress( int address ) throws ValueError;
}
