package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param
 
 */
public
class DefaultCodec<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends Codec <N>

        implements IConstants {

    private EPartitionScheme scheme;

    private EncodeTask <N> task;
    private DecodeTask <N> inverseTask;

    /**
     * @param scheme
     */
    public
    DefaultCodec ( EPartitionScheme scheme, Class <?>[] paramTypes, Object... params  ) {
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
    IEncoder <N> getEncoder ( IImage image, IIntSize rangeSize, IIntSize domainSize ) {
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
    IEncoder <N> getEncoder () {
        return encoder;
    }

    /**
     * @return
     */
    @Override
    public
    IDecoder <N> getDecoder () {
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
    void setTask ( EncodeTask <N> task ) {
        this.task = task;
    }

    /**
     * @return
     */
    public
    DecodeTask <N> getInverseTask () {
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
    EncodeTask <N> getTask () {
        return task;
    }

    /**
     * @param inverseTask
     */
    public
    void setInverseTask ( DecodeTask <N> inverseTask ) {
        this.inverseTask = inverseTask;
    }
}
