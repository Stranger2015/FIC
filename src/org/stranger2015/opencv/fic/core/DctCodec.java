package org.stranger2015.opencv.fic.core;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.codec.Codec;
import org.stranger2015.opencv.fic.core.codec.IDecoder;
import org.stranger2015.opencv.fic.core.codec.IEncoder;

/**
 * @param <N>
 * @param
 */
public
class DctCodec extends Codec {

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
    IEncoder getEncoder ( IImage image, Size rangeSize, Size domainSize ) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    IEncoder getEncoder () {
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
    IAddress createAddress ( int address ) throws ValueError {
        return null;
    }

    /**
     * @param task
     */
    @Override
    public
    void addTask ( Task task ) {

    }
}
