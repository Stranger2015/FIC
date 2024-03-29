package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param 
 * @param <G>
 */
public
class SaCodec<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends Codec <N> {

    /**
     * @param scheme
     * @param encodeTask
     * @param decodeTask
     */
    protected
    SaCodec ( EPartitionScheme scheme, EncodeTask <N> encodeTask, DecodeTask <N> decodeTask ) {
        super(scheme, encodeTask.getEncoder(), decodeTask.getDecoder());
    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
//    @Override
    public
    IEncoder <N> getEncoder ( IImage image, Size rangeSize, Size domainSize ) {
        return getEncoder();
    }

    /**
     * @return
     */
    @Override
    public
    IEncoder <N> getEncoder () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    IDecoder <N> getDecoder () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    int getImageSizeBase () {
        return 7;
    }

    /**
     * @param address
     * @return
     * @throws ValueError
     */
    @Override
    public
    IAddress  createAddress ( int address ) throws ValueError {
        return new SaAddress <>(address);
    }
}
