package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.tilers.Pool;

import java.util.List;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class BinTreeImageBlockGenerator<N extends TreeNode <N>>
        extends ImageBlockGenerator <N> {

    /**
     * @param tiler
     * @param scheme
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    BinTreeImageBlockGenerator ( IPartitionProcessor partitionProcessor,
                                 EPartitionScheme scheme,
                                 IEncoder encoder,
                                 IImage image,
                                 IIntSize rangeSize,
                                 IIntSize domainSize ) {

        super(
                partitionProcessor,
                scheme,
                encoder,
                image,
                rangeSize,
                domainSize
        );
    }

    /**
     * @param tiler
     * @param scheme
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
    @Override
    public
    ImageBlockGenerator <N> newInstance (
            IPartitionProcessor partitionProcessor,
            EPartitionScheme scheme,
            IEncoder encoder,
            IImage image,
            IIntSize rangeSize,
            IIntSize domainSize ) {

        return new BinTreeImageBlockGenerator <>(
                partitionProcessor,
                scheme,
                encoder,
                image,
                rangeSize,
                domainSize
        );
    }

//    /**
//     * @param inputImage
//     */
//    @Override
//    public
//    List <IImageBlock > generateRegions ( IImage inputImage, List <Rectangle> bounds ) throws ValueError {
//        return super.generateRegions(inputImage, bounds);
//    }

    /**
     * @param imageBlocks
     * @param blocksToMerge
     * @return
     * @throws ValueError
     */
    //    @Override
    protected
    IImageBlock  mergeBlocks ( List <IImageBlock > imageBlocks,
                                  List <IImageBlock > blocksToMerge
    ) throws ValueError {
        for (int i = 0; i < blocksToMerge.size(); i++) {

        }

        return new ImageBlock(image);
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
//    ImageBlockGenerator <?> newInstance ( IPartitionProcessor partitionProcessor, EPartitionScheme scheme, IEncoder encoder, IImage image, IIntSize rangeSize, IIntSize domainSize ) {
//        return null;
//    }

    /**
     * @param roi
     * @return
     */
    @Override
    public
    Pool <IImageBlock> generateRangeBlocks ( IImageBlock  roi,
                                                 int rangeSize,
                                                 int domainSize ) throws ValueError {
        return super.generateRangeBlocks(
                roi,
                rangeSize,
                domainSize);
    }

    /**
     * @param roi
     * @param list
     * @return
     * @throws ValueError
     */
    @Override
    public
    List <IImageBlock > createCodebookBlocks ( IImageBlock  roi, List <IImageBlock > domainBlocks )
            throws ValueError {

        return super.createCodebookBlocks(roi, domainBlocks);
    }
}
