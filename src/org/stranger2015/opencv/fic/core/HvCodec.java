package org.stranger2015.opencv.fic.core;

import org.checkerframework.checker.units.qual.A;
import org.stranger2015.opencv.fic.core.codec.*;

/**
 * @param <N>
 * @param
 
 */
public
class HvCodec extends Codec {

    /**
     * @param scheme
     * @param encodeTask
     * @param decodeTask
     */
    public
    HvCodec ( EPartitionScheme scheme,
              EncodeTask  encodeTask,
              DecodeTask <N> decodeTask ) {

        super(scheme, encodeTask, decodeTask);
    }

    /**
     * @param scheme
     * @param encodeTask
     * @param decodeTask
     * @return
     */
    public
    ICodec <N> create ( EPartitionScheme scheme,
                              EncodeTask <N> encodeTask,
                              DecodeTask <N> decodeTask ) {

        return new HvCodec <>(scheme, encodeTask, decodeTask);
    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
//    @Override
    public
    IEncoder <N> getEncoder ( IImage image, IIntSize rangeSize, IIntSize domainSize ) {
        return new HvEncoder <N>(image, rangeSize, domainSize);
    }

    /**
     * @return
     */
    @Override
    public
    IEncoder<N, A,M,G>  getEncoder () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    IDecoder <M, A> getDecoder () {
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
    IAddress  createAddress ( int address ) throws ValueError {
        return new DecAddress <>(address);
    }
}
