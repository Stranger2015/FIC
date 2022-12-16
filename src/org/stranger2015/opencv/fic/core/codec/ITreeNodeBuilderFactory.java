package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
interface ITreeNodeBuilderFactory<N extends TreeNode <N>> {

    /**
     * @param image
     * @param scheme
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param library
     * @return
     */
    default
    ITreeNodeBuilder <N> createBuilder (
            IImageBlock image,
            EPartitionScheme scheme,
            IIntSize rangeSize,
            IIntSize domainSize,
            IEncoder encoder,
            Library library
    ) {

        ITreeNodeBuilder <N> result = null;
        switch (scheme) {
            case FIXED_SIZE:
                break;
            case BIN_TREE:
                result = new BinTreeNodeBuilder <>(
                        image,
                        rangeSize,
                        domainSize,
                        encoder,
                        library);
            break;
            case DCT:
                break;
            case CONST_SIZE_DOMAIN_POOL:
                break;
            case ABP:
                break;
            case HV:
                break;
            case IRREGULAR:
                break;
            case QUADRILATERAL:
                break;
            case QUAD_TREE:
                result = new QuadTreeNodeBuilder <>(
                        image,
                        rangeSize,
                        domainSize,
                        encoder,
                        library);
                break;
            case TRIANGULAR:
                break;
            case SPLIT_AND_MERGE_0:
//                result = new BushTreeNodeBuilder <>(
//                        image,
//                        rangeSize,
//                        domainSize,
//                        encoder,
//                        library);
                break;
            case SPLIT_AND_MERGE_1:
                break;
            case SEARCHLESS:
                break;
            case UNIFORM_SQUARE:
                break;
            case TWO_LEVEL_SQUARE:
                break;
            case SA_0:
                break;
            case SA_BVR:
                break;
            case SIP_BVR:
                break;
            case SIP:
                result = new SipTreeNodeBuilder <>(
                        image,
                        rangeSize,
                        domainSize,
                        encoder,
                        library);
                break;
            case FA_FE_EV:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + scheme);
        }

        return result;
    }
}
