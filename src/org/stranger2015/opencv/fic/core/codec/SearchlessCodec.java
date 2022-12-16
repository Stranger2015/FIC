package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param
 
 */
public
class SearchlessCodec<N extends TreeNode <N>, A extends IAddress , IImage extends IImage, G extends BitBuffer>
        extends Codec <N> {
    /**
     * @param scheme
     * @param encodeTask
     * @param decodeTask
     */
    protected
    SearchlessCodec ( EPartitionScheme scheme,
                      EncodeTask <N> encodeTask,
                      DecodeTask <N> decodeTask ) {

        super(scheme, encodeTask, decodeTask);
    }
//
//     /**
//     * @param scheme
//     * @param encodeTask
//     * @param decodeTask
//      *
//     * @return
//     */
////    @Override
//    public
//    ICodec <N> create ( EPartitionScheme scheme,
//                                EncodeTask <N> encodeTask,
//                                DecodeTask <N> decodeTask  ) {
//        return new SearchlessCodec <>(scheme, encodeTask, decodeTask );
//    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
//    @Override
    public
    IEncoder <N> getEncoder ( IImage image, ISearchProcessor <N> rangeSize, ImageBlockGenerator <N> domainSize ) {
        return new SearchlessEncoder <>(image, rangeSize, domainSize);
    }

    /**
     *
     * @return
     */
    @Override
    public
    IEncoder <N> getEncoder () {
        return getEncoder(ac);
    }

    /**
     * @return
     */
    @Override
    public
    IDecoder <M,A> getDecoder () {
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
