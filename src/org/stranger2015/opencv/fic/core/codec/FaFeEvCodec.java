package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.EPartitionScheme;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param
 
 * @param <G>
 */
public
class FaFeEvCodec
        extends Codec {
    /**
     * @param scheme
     * @param paramTypes
     * @param params
     */
    protected
    FaFeEvCodec ( EPartitionScheme scheme, Class <?>[] paramTypes, Object... params/*EncodeTask <N> encodeTask, DecodeTask <N> decodeTask*/ ) {
        super(scheme, paramTypes, params/*encodeTask, decodeTask*/);
    }
//
//    /**
//     * @return
//     */
//    @Override
//    public
//    IEncoder<N> getEncoder () {
//        return null;
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public
//    IDecoder<M,A> getDecoder () {
//        return null;
//    }

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
    IAddress  createAddress ( int address ) throws ValueError {
//        return super.createAddress(address);
        return null;
    }
}
