package org.stranger2015.opencv.fic.core;

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
              DecodeTask  decodeTask ) {

        super(scheme, encodeTask.getEncoder(), decodeTask.getDecoder());
    }

    /**
     * @param scheme
     * @param encodeTask
     * @param decodeTask
     * @return
     */
    public
    ICodec create ( EPartitionScheme scheme,
                              EncodeTask encodeTask,
                              DecodeTask decodeTask ) {

        return new HvCodec(scheme, encodeTask, decodeTask);
    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
//    @Override
    public
    IEncoder getEncoder ( IImage image, IIntSize rangeSize, IIntSize domainSize ) {
        return new HvEncoder(image, rangeSize, domainSize);
    }

    /**
     * @return
     */
    @Override
    public
    IEncoder  getEncoder () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    IDecoder getDecoder () {
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
        return new DecAddress(address);
    }
}
