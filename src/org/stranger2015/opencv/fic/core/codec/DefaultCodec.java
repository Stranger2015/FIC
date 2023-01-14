package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;

/**
 * @param <N>
 * @param
 
 */
public
class DefaultCodec extends Codec

        implements IConstants {

    private EPartitionScheme scheme;

    private EncodeTask task;
    private DecodeTask inverseTask;

    /**
     * @param scheme
     */
    public
    DefaultCodec ( EPartitionScheme scheme, IEncoder encoder, IDecoder decoder) {
        super(scheme, encoder, decoder);
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
        return encoder;
    }

    /**
     * @return
     */
    @Override
    public
    int getImageSizeBase () {
        return encoder.getImageSizeBase();
    }

    /**
     * @param address
     * @return
     * @throws ValueError
     */
    @Override
    public
    IAddress  createAddress ( int address ) throws ValueError {
        return null;
    }
//
//    @Override
//    public
//    IAddress  createAddress ( int address ) throws ValueError {
//        switch (getScheme()) {
//            case FIXED_SIZE:                        //FALLING
//            case BIN_TREE:
//            case DCT:
//            case CONST_SIZE_DOMAIN_POOL:
//            case ABP:
//            case HV:
//            case IRREGULAR:
//            case QUADRILATERAL:
//            case QUAD_TREE:
//            case TRIANGULAR:
//            case SPLIT_AND_MERGE_0:
//            case SPLIT_AND_MERGE_1:
//            case SEARCHLESS:
//            case UNIFORM_SQUARE:
//            case TWO_LEVEL_SQUARE:
//                return new DecAddress <>(address);
//            case SA_0:
//            case SA_BVR:
//                return new SaAddress <>(address);
//            case SIP:
//            case SIP_BVR:
//                return new SipAddress <>(address);
//        }
//
//        return null;
//    }

    /**
     * @return
     */
    @Override
    public
    IEncoder getEncoder () {
        return encoder;
    }

    /**
     * @return
     */
    @Override
    public
    IDecoder getDecoder () {
        return decoder;
    }

    /**
     * @return
     */
    @Override
    public
    EPartitionScheme getScheme () {
        return scheme;
    }

    /**
     * @param task
     */
    public
    void setTask ( EncodeTask task ) {
        this.task = task;
    }

    /**
     * @return
     */
    public
    DecodeTask getInverseTask () {
        return inverseTask;
    }

    /**
     * @param scheme
     */
    public
    void setScheme ( EPartitionScheme scheme ) {
        this.scheme = scheme;
    }

    /**
     * @return
     */
    public
    EncodeTask getTask () {
        return task;
    }

    /**
     * @param inverseTask
     */
    public
    void setInverseTask ( DecodeTask inverseTask ) {
        this.inverseTask = inverseTask;
    }
}
