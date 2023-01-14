package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.*;

/**
 * @param <N>
 * @param
 
 */
public
class SipCodec    extends Codec {

    /**
     * @param scheme
     * @param encoder
     * @param decoder
     */
    public
    SipCodec ( EPartitionScheme scheme, IEncoder encoder, IDecoder <N> decoder ) {
        super(scheme, encoder, decoder);
    }

    public
    SipCodec ( EPartitionScheme scheme, EncodeTask action ) {
        super(scheme, action);
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
        return new SipEncoder(image, rangeSize, domainSize);
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
        return null ;
    }

    /**
     * @return
     */
    @Override
    public
    int getImageSizeBase () {
        return 3;
    }//9 ???

    /**
     * @param address
     * @return
     * @throws ValueError
     */
    @Override
    public
    IAddress  createAddress ( int address ) throws ValueError {
        return new SipAddress <>(address);
    }
}