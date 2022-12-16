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
 * @param
 * @param <G>
 */
public abstract
class RoiBasedEncoder<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends Encoder <N>
        implements IRoiBasedEncoder <N> {
    /**
     *
     */
    private final List <IImageBlock > roiBlocks = new ArrayList <>();

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
                      ITreeNodeBuilder <N> nodeBuilder,
                      IPartitionProcessor <N> partitionProcessor,
                      ISearchProcessor <N> searchProcessor,
                      ScaleTransform  scaleTransform,
                      ImageBlockGenerator <N> imageBlockGenerator,
                      IDistanceator  comparator,
                      Set <ImageTransform> imageTransforms,
                      Set <IImageFilter > imageFilters,
                      FCImageModel <N> fractalModel ) {
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
     *
     */
    @Override
    public
    void doEncode ( IImage image ) throws ValueError, ReflectiveOperationException {
        List <Rectangle> bounds = List.of(new Rectangle(image.getWidth(), image.getHeight()));
        final List <IImageBlock > regions = segmentImage(image, bounds);
        List <List <IImageBlock >> list = handleRegionList(regions);
        for (List <IImageBlock > region : list) {
            image = iterateRegions(regions);
//            for (int j = 0, regionsSize = regions1.size(); j < regionsSize; j++) {
//                IImageBlock  region = regions1.get(j);

//            }
        }

        return searchProcessor.search(, );
    }

    /**
     * @return
     */
    public final
    List <IImageBlock > getRegionBlocks () {
        return roiBlocks;
    }

//    /**
//     * @param tiler
//     * @return
//     */
//    @Override
//    public
//    IPartitionProcessor <N> createPartitionProcessor0 ( ITiler <N> tiler ) {
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
//    IPartitionProcessor <N> createPartitionProcessor ( ITiler <N> tiler ) {
//        return super.createPartitionProcessor(tiler);
//    }

    /**
     * @param tiler
     * @return
     */
    @Override
    public
    IPartitionProcessor <N> doCreatePartitionProcessor ( ITiler <N> tiler ) {
        return null;
    }

    /**
     * @param node
     */
    @Override
    public
    void add ( TreeNode <N> node ) {

    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( LeafNode <N> node ) {

    }

    /**
     * @param roi
     * @param rangeBlocks
     * @return
     */
    @Override
    public
    IImageBlock  iterateRangeBlocks ( IImageBlock  roi, List <IImageBlock > rangeBlocks ) throws ValueError {
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
//    void iterateDomainBlocks ( IImageBlock  rangeBlock,
//                               List <IImageBlock > domainBlocks,
//                               ImageTransform bestTransform )
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
    int[] getDistance ( IImageBlock  range, IImageBlock  domain ) {
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
    List <IImageBlock > segmentImage ( IImage image, List <Rectangle> bounds ) throws ValueError {
        return null;
    }

    /**
     * @param image
     * @return
     * @throws ValueError
     */
    @Override
    public
    List <IImageBlock > segmentImage ( IImage image ) throws ValueError {
        return IRoiBasedEncoder.super.segmentImage(image);
    }

    /**
     * @param codec
     */
    @Override
    public
    void onCodecCreated ( ICodec <N> codec ) {
        super.onCodecCreated(codec);
    }

    /**
     * @param imageProcessor
     * @param filename
     * @param image
     */
    @Override
    public
    void onPreprocess ( IImageProcessor <N> imageProcessor, String filename, IImage image ) throws ValueError {
        super.onPreprocess(imageProcessor, filename, image);
    }

    /**
     * @param imageProcessor
     * @param inputImage
     */
    @Override
    public
    void onProcess ( IImageProcessor <N> imageProcessor, IImage inputImage ) {
        super.onProcess(imageProcessor, inputImage);
    }

    /**
     * @param imageProcessor
     * @param outputImage
     */
    @Override
    public
    void onPostprocess ( IImageProcessor <N> imageProcessor, CompressedImage  outputImage ) {
        super.onPostprocess(imageProcessor, outputImage);
    }

    /**
     * @param instance
     */
    @Override
    public
    void onCreated ( ICodec <N> instance ) {
        super.onCreated(instance);
    }
}
