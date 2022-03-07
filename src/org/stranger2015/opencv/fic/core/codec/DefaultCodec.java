package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 */
public
class DefaultCodec<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>, G extends BitBuffer>
        extends Codec <N, A, M, G>
        implements IConstants {

    private EPartitionScheme scheme;
    private EncodeTask <N, A, M, G> task;
    private DecodeTask <N, A, M, G> inverseTask;

    /**
     * @param scheme
     */
    public
    DefaultCodec ( EPartitionScheme scheme, EncodeTask <N, A, M, G> task, DecodeTask <N, A, M, G> decodeTask ) {
        super(scheme, task, decodeTask);
    }

//    public
//    DefaultCodec ( EPartitionScheme scheme, EncodeTask <N, A, M, G> action, String filename ) {
//
//    }

    public
    ICodec <N, A, M, G> create ( EPartitionScheme scheme,
                                 EncodeTask <N, A, M, G> task,
                                 DecodeTask <N, A, M, G> inverseTask ) {

        return new DefaultCodec <>(scheme, task, inverseTask);
    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
    @Override
    public
    IEncoder <N, A, M, G> getEncoder ( M image, Size rangeSize, Size domainSize ) {
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

    @Override
    public
    Address <A> createAddress ( int address ) throws ValueError {
        switch (getScheme()) {
            case FIXED_SIZE:                        //FALLING
            case BIN_TREE:
            case DCT:
            case CONST_SIZE_DOMAIN_POOL:
            case ABP:
            case HV:
            case IRREGULAR:
            case QUADRILATERAL:
            case QUAD_TREE:
            case TRIANGULAR:
            case SPLIT_AND_MERGE_0:
            case SPLIT_AND_MERGE_1:
            case SEARCHLESS:
            case UNIFORM_SQUARE:
            case TWO_LEVEL_SQUARE:
                return new DecAddress <>(address, EAddressKind.ORDINARY);
            case SA_0:
            case SABVR:
                return new SaAddress <>(address, EAddressKind.SPIRAL);
            case SIP:
                return new SipAddress <A>(address, EAddressKind.SQUIRAL);
        }
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    IEncoder <N, A, M, G> getEncoder () {
        return encoder;
    }

    /**
     * @return
     */
    @Override
    public
    IDecoder <M> getDecoder () {
        return decoder;
    }

    @Override
    public
    EPartitionScheme getScheme () {
        return scheme;
    }

    public
    void setTask ( EncodeTask <N, A, M, G> task ) {
        this.task = task;
    }

    public
    DecodeTask <N, A, M, G> getInverseTask () {
        return inverseTask;
    }

    public
    void setScheme ( EPartitionScheme scheme ) {
        this.scheme = scheme;
    }
}
