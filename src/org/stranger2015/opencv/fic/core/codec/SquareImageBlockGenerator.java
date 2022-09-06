package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 *
 */
public
class SquareImageBlockGenerator<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>

        extends ImageBlockGenerator <N, A, G> {

//    @Override
//    public
//    ImageBlockGenerator <N, A, G> newInstance ( IPartitionProcessor <N, A, G> partitionProcessor,
//                                                EPartitionScheme scheme,
//                                                IEncoder <N, A, G> encoder,
//                                                IImage <A> image,
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
     * @param inputImage
     * @return
     */
    @Override
    public
    List <IImageBlock <A>> generateRangeBlocks ( RegionOfInterest <A> roi,
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
    SquareImageBlockGenerator ( IPartitionProcessor <N, A, G> partitionProcessor,
                                EPartitionScheme scheme,
                                IEncoder <N, A, G> encoder,
                                IImage <A> image,
                                IIntSize rangeSize,
                                IIntSize domainSize
    ) {

        super(partitionProcessor, scheme, encoder, image, rangeSize, domainSize);
    }

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
    HvBlockGenerator <N, A, G> newInstance ( IPartitionProcessor <N, A, G> partitionProcessor,
                                                      EPartitionScheme scheme,
                                                      IEncoder <N, A, G> encoder,
                                                      IImage <A> image,
                                                      IIntSize rangeSize,
                                                      IIntSize domainSize ) {
        return this;
    }
}
