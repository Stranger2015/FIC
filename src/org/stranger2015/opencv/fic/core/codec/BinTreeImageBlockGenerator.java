package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class BinTreeImageBlockGenerator<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends ImageBlockGenerator <N, A, G> {

    /**
     * @param tiler
     * @param scheme
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    BinTreeImageBlockGenerator ( IPartitionProcessor <N, A, G> partitionProcessor,
                                 EPartitionScheme scheme,
                                 IEncoder <N, A, G> encoder,
                                 IImage <A> image,
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
    ImageBlockGenerator <N, A, G> newInstance (
            IPartitionProcessor <N, A, G> partitionProcessor,
            EPartitionScheme scheme,
            IEncoder <N, A, G> encoder,
            IImage <A> image,
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

    /**
     * @param inputImage
     */
    @Override
    public
    List <IImageBlock <A>> generateRegions ( IImage <A> inputImage, List <Rectangle> bounds ) throws ValueError {
        return super.generateRegions(inputImage, bounds);
    }

    //    @Override
    protected
    IImageBlock <A> mergeBlocks ( List <IImageBlock <A>> imageBlocks,
                                  List <IImageBlock <A>> blocksToMerge
    ) {
        for (int i = 0; i < blocksToMerge.size(); i++) {

        }

        IImageBlock <A> block = new ImageBlock <>(image.getMat(), blockSize, geometry);

        return block;
    }

    /**
     * @param roi
     * @return
     */
    @Override
    public
    List <IImageBlock <A>> generateRangeBlocks ( IImageBlock <A> roi,
                                                 int rangeSize,
                                                 int domainSize ) throws ValueError {
        return super.generateRangeBlocks(
                roi,
                rangeSize,
                domainSize);
    }

    /**
     * @param roi
     * @param rangeBlocks
     * @return
     */
    @Override
    public
    List <IImageBlock <A>> generateDomainBlocks ( IImageBlock <A> roi,
                                                  List <IImageBlock <A>> rangeBlocks )
            throws ValueError {

        return super.generateDomainBlocks(roi, rangeBlocks);
    }

    /**
     * @param roi
     * @param list
     * @return
     * @throws ValueError
     */
    @Override
    public
    List <IImageBlock <A>> createCodebookBlocks ( IImageBlock <A> roi, List <IImageBlock <A>> domainBlocks )
            throws ValueError {

        return super.createCodebookBlocks(roi, domainBlocks);
    }
}
