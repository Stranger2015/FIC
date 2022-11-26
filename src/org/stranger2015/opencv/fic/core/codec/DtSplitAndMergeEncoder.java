package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;
import java.util.Set;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class DtSplitAndMergeEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends BinTreeEncoder <N, A, G> {

    /**
     * @param scheme
     * @param nodeBuilder
     * @param searchProcessor
     * @param scaleTransform
     * @param imageBlockGenerator
     * @param comparator
     * @param imageTransforms
     * @param filters
     * @param fractalModel
     */
    public
    DtSplitAndMergeEncoder (
            EPartitionScheme scheme,
            TreeNodeBuilder <N, A, G> nodeBuilder,
            IPartitionProcessor <N, A, G> partitionProcessor,
            ISearchProcessor <N, A, G> searchProcessor,
            ScaleTransform <A, G> scaleTransform,
            ImageBlockGenerator <N, A, G> imageBlockGenerator,
            IDistanceator <A> comparator,
            Set <ImageTransform <A, G>> transforms,
            Set <IImageFilter <A>> filters,
            FCImageModel <N, A, G> fractalModel
    ) {
        super(
                scheme,
                nodeBuilder,
                partitionProcessor,
                searchProcessor,
                scaleTransform,
                imageBlockGenerator,
                comparator,
                transforms,
                filters,
                fractalModel
        );
    }

    /**
     * @param image
     * @param bounds
     * @return
     */
    @Override
    public
    List <IImageBlock <A>> segmentImage ( IImage <A> image, List <Rectangle> bounds ) throws ValueError {
        return super.segmentImage(image, bounds);
    }

    /**
     * @param addr
     * @return
     * @throws ValueError
     */
    IAddress <A> createAddress ( int addr ) throws ValueError {
        return IAddress.valueOf(addr, inputImage.getWidth(), 0);
    }

//    /**
//     * @param imageBlock
//     * @return
//     */
//    @Override
//    protected
//    ESplitKind chooseDirection ( IImageBlock <A> imageBlock ) {
//        return DIAGONAL;
//    }

    /**
     * @throws ValueError
     */
    @Override
    public
    void initialize () throws Exception {
        super.initialize();
    }

    /**
     * @param image
     * @return
     * @throws ValueError
     */
    @Override
    public
    IImage <A> doEncode ( IImage <A> image ) throws ValueError {
        List <IImageBlock <A>> regions = partitionProcessor.generateRegions(
                image,
                List.of(new Rectangle(image.getSize())));
        List <IImageBlock <A>> rangeBlocks = partitionProcessor.generateRangeBlocks(
                (IImageBlock <A>) regions,
                image.getWidth(),
                image.getHeight());

        List <IImageBlock <A>> domainBlocks = partitionProcessor.generateDomainBlocks(
                rangeBlocks,
                partitionProcessor.getRangeSize(),
                imageBlockGenerator.domainSize);

        return super.doEncode(image);
    }
}
