package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public abstract
class RoiBasedEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Encoder <N, A, G>
        implements IRoiBasedEncoder <N, A, G> {
    /**
     *
     */
    private final List <IImageBlock <A>> roiBlocks = new ArrayList <>();

    /**
     * @param scheme
     * @param nodeBuilder
     * @param partitionProcessor
     * @param searchProcessor
     * @param scaleTransform
     * @param imageBlockGenerator
     * @param comparator
     * @param imageTransforms
     * @param iImageFilters
     * @param fractalModel
     */
    protected
    RoiBasedEncoder ( EPartitionScheme scheme,
                      ITreeNodeBuilder <N, A, G> nodeBuilder,
                      IPartitionProcessor <N, A, G> partitionProcessor,
                      ISearchProcessor <N, A, G> searchProcessor,
                      ScaleTransform <A, G> scaleTransform,
                      ImageBlockGenerator <N, A, G> imageBlockGenerator,
                      IDistanceator <A> comparator,
                      Set <ImageTransform <A, G>> imageTransforms,
                      Set <IImageFilter <A>> imageFilters,
                      FCImageModel <N, A, G> fractalModel ) {
        super(
                scheme,
                nodeBuilder,
                partitionProcessor,
                searchProcessor,
                scaleTransform,
                imageBlockGenerator,
                comparator,
                imageTransforms,
                imageFilters,
                fractalModel);
    }

    /**
     * @return
     */
    @Override
    public
    IImage <A> doEncode ( IImage <A> image ) throws ValueError, ReflectiveOperationException {
        List <Rectangle> bounds = List.of(new Rectangle(image.getWidth(), image.getHeight()));
        final List <IImageBlock <A>> regions = segmentImage(image, bounds);
        List <List <IImageBlock <A>>> list = handleRegionList(regions);
        for (List <IImageBlock <A>> region : list) {
            image = iterateRegions(regions);
//            for (int j = 0, regionsSize = regions1.size(); j < regionsSize; j++) {
//                IImageBlock <A> region = regions1.get(j);

//            }
        }

        return searchProcessor.search();
    }

    /**
     * @return
     */
    public final
    List <IImageBlock <A>> getRegionBlocks () {
        return roiBlocks;
    }

//    /**
//     * @param tiler
//     * @return
//     */
//    @Override
//    public
//    IPartitionProcessor <N, A, G> createPartitionProcessor0 ( ITiler <N, A, G> tiler ) {
//        return super.createPartitionProcessor0(tiler);
//    }

    /**
     * @return
     */
    @Override
    public
    int getImageSizeBase () {
        return super.getImageSizeBase();
    }

//    /**
//     * @param tiler
//     * @return
//     */
//    @Override
//    public
//    IPartitionProcessor <N, A, G> createPartitionProcessor ( ITiler <N, A, G> tiler ) {
//        return super.createPartitionProcessor(tiler);
//    }

    /**
     * @param tiler
     * @return
     */
    @Override
    public
    IPartitionProcessor <N, A, G> doCreatePartitionProcessor ( ITiler <N, A, G> tiler ) {
        return null;
    }

    /**
     * @param node
     */
    @Override
    public
    void add ( TreeNode <N, A, G> node ) {

    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( LeafNode <N, A, G> node ) {

    }

    /**
     * @param roi
     * @param rangeBlocks
     * @return
     */
    @Override
    public
    IImageBlock <A> iterateRangeBlocks ( IImageBlock <A> roi, List <IImageBlock <A>> rangeBlocks ) throws ValueError {
        return super.iterateRangeBlocks(roi, rangeBlocks);
    }

//
//    /**
//     * @param rangeBlock
//     * @param domainBlocks
//     * @param bestTransform
//     */
//    @Override
//    public
//    void iterateDomainBlocks ( IImageBlock <A> rangeBlock,
//                               List <IImageBlock <A>> domainBlocks,
//                               ImageTransform <A, G> bestTransform )
//            throws ValueError {
//
//        super.iterateDomainBlocks(rangeBlock, domainBlocks, bestTransform);
//    }

    /**
     * @param range
     * @param domain
     * @return
     */
    @Override
    public
    int[] getDistance ( IImageBlock <A> range, IImageBlock <A> domain ) {
        return super.getDistance(range, domain);
    }

    /**
     * @param image
     * @param bounds
     * @return
     * @throws ValueError
     */
    @Override
    public
    List <IImageBlock <A>> segmentImage ( IImage <A> image, List <Rectangle> bounds ) throws ValueError {
        return null;
    }

    /**
     * @param image
     * @return
     * @throws ValueError
     */
    @Override
    public
    List <IImageBlock <A>> segmentImage ( IImage <A> image ) throws ValueError {
        return IRoiBasedEncoder.super.segmentImage(image);
    }

    /**
     * @param codec
     */
    @Override
    public
    void onCodecCreated ( ICodec <N, A, G> codec ) {
        super.onCodecCreated(codec);
    }

    /**
     * @param imageProcessor
     * @param filename
     * @param image
     */
    @Override
    public
    void onPreprocess ( IImageProcessor <N, A, G> imageProcessor, String filename, IImage <A> image ) throws ValueError {
        super.onPreprocess(imageProcessor, filename, image);
    }

    /**
     * @param imageProcessor
     * @param inputImage
     */
    @Override
    public
    void onProcess ( IImageProcessor <N, A, G> imageProcessor, IImage <A> inputImage ) {
        super.onProcess(imageProcessor, inputImage);
    }

    /**
     * @param imageProcessor
     * @param outputImage
     */
    @Override
    public
    void onPostprocess ( IImageProcessor <N, A, G> imageProcessor, CompressedImage <A> outputImage ) {
        super.onPostprocess(imageProcessor, outputImage);
    }

    /**
     * @param instance
     */
    @Override
    public
    void onCreated ( ICodec <N, A, G> instance ) {
        super.onCreated(instance);
    }
}
