package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.tilers.Pool;

/**
 *
 */
public
class SquareImageBlockGenerator<N extends TreeNode <N>>

        extends ImageBlockGenerator <N> {

//    @Override
//    public
//    ImageBlockGenerator <N> newInstance ( IPartitionProcessor <N> partitionProcessor,
//                                                EPartitionScheme scheme,
//                                                IEncoder <N> encoder,
//                                                IImage image,
//                                                IIntSize rangeSize,
//                                                IIntSize domainSize ) {
//        return new SquareImageBlockGenerator <>(
//                partitionProcessor,
//                scheme,
//                encoder,
//                image,
//                rangeSize,
//                domainSize
//        );
    // }

    /**
     * @param partitionProcessor
     * @param scheme
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
    @Override
    public
    ImageBlockGenerator <N> newInstance ( IPartitionProcessor partitionProcessor,
                                          EPartitionScheme scheme,
                                          IEncoder encoder,
                                          IImage image,
                                          IIntSize rangeSize,
                                          IIntSize domainSize ) {
        return null;
    }

    /**
     * @param inputImage
     * @return
     */
    @Override
    public
    Pool <IImageBlock> generateRangeBlocks ( IImageBlock  roi,
                                                 int blockWidth,
                                                 int blockHeight ) throws ValueError {

        return partitionProcessor.generateRangeBlocks(roi, blockWidth, blockHeight);
    }

    /**
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    SquareImageBlockGenerator ( IPartitionProcessor partitionProcessor,
                                EPartitionScheme scheme,
                                IEncoder encoder,
                                IImage image,
                                IIntSize rangeSize,
                                IIntSize domainSize
    ) {
        super(partitionProcessor, scheme, encoder, image, rangeSize, domainSize);
    }

//    /**
//     * @param partitionProcessor
//     * @param scheme
//     * @param encoder
//     * @param image
//     * @param rangeSize
//     * @param domainSize
//     * @return
//     */
//    @Override
//    public
//    ImageBlockGenerator <N> newInstance (
//            IPartitionProcessor partitionProcessor,
//            EPartitionScheme scheme,
//            IEncoder encoder,
//            IImage image,
//            IIntSize rangeSize,
//            IIntSize domainSize ) {
//
//        return this;
//    }
}
