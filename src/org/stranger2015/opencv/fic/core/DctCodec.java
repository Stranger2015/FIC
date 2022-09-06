package org.stranger2015.opencv.fic.core;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.Codec;
import org.stranger2015.opencv.fic.core.codec.IDecoder;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 */
public
class DctCodec<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Codec <N, A, G> {

    /**
     * @param scheme
     * @param paramTypes
     * @param params
     */
    protected
    DctCodec ( EPartitionScheme scheme,
               Class <?>[] paramTypes,
               Object... params ) {

        super(scheme, paramTypes, params);
    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
//    @Override
    public
    IEncoder <N, A, G> getEncoder ( IImage <A> image, Size rangeSize, Size domainSize ) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    IEncoder <N, A, G> getEncoder () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    IDecoder <N, A, G> getDecoder () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    int getImageSizeBase () {
        return 2;
    }

    /**
     * @param address
     * @return
     * @throws ValueError
     */
    @Override
    public
    IAddress <A> createAddress ( int address ) throws ValueError {
        return null;
    }
}
