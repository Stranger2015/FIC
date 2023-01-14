package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.codec.tilers.Pool;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class CompositeEncoder extends Encoder {

    /**
     *
     */
    protected final List <IEncoder> pipeline = new ArrayList <>();

    /**
     * @param scheme
     * @param nodeBuilder
     * @param searchProcessor
     * @param scaleTransform
     * @param imageBlockGenerator
     * @param comparator
     * @param imageTransforms
     * @param imageFilters
     * @param fractalModel
     */
    @SafeVarargs
    protected
    CompositeEncoder (
            String fileName,
            EPartitionScheme scheme,
            ICodec codec,
            List <Task> tasks,
            EtvColorSpace colorSpace,
            ITreeNodeBuilder <?> nodeBuilder,
            IPartitionProcessor partitionProcessor,
            ISearchProcessor searchProcessor,
            ScaleTransform scaleTransform,
            ImageBlockGenerator <?> imageBlockGenerator,
            IDistanceator comparator,
            Set <ImageTransform> imageTransforms,
            Set <IImageFilter> imageFilters,
            FCImageModel fractalModel,
            IEncoder... encoders ) {

        super(fileName,
                scheme,
                codec,
                tasks,
                colorSpace,
                nodeBuilder,
                partitionProcessor,
                searchProcessor,
                scaleTransform,
                imageBlockGenerator,
                comparator,
                imageTransforms,
                imageFilters,
                fractalModel,
                encoders
        );

        pipeline.addAll(Arrays.asList(encoders));
    }

    /**
     *
     */
    @Override
    public
    void initialize () throws Exception {

    }

    /**
     * @param tiler
     * @return
     */
    @Override
    public
    IPartitionProcessor createPartitionProcessor0 ( ITiler tiler,
                                                    ImageBlockGenerator <?> imageBlockGenerator,
                                                    ITreeNodeBuilder <?> nodeBuilder ) {

        return getPartitionProcessor().instance(tiler, imageBlockGenerator, nodeBuilder);
    }

    /**
     * @param image
     * @return
     */
    @Override
    public
    IImage doEncode ( IImage image ) throws Exception {
        for (IEncoder encoder : pipeline) {
            image = encoder.doEncode(image);
        }

        return image;
    }
//
//    /**
//     * @param image
//     * @param transform
//     * @return
//     */
//    @Override
//    public
//    IImageBlock randomTransform ( IImageBlock image, ImageTransform transform ) {
//
//        return null;
//    }
//
    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImageBlock applyTransform ( IImageBlock image, ImageTransform transform ) {
        return null;
    }

    /**
     * @param image
     * @param bounds
     * @return
     * @throws ValueError
     */
    @Override
    public
    List <IImageBlock> segmentImage ( IImage image, List <Rectangle> bounds ) throws ValueError {
        return null;
    }

    /**
     * @param tiler
     * @return
     */
    @Override
    public
    IPartitionProcessor doCreatePartitionProcessor ( ITiler tiler,
                                                     ImageBlockGenerator <?> imageBlockGenerator,
                                                     ITreeNodeBuilder <?> nodeBuilder ) {
        return createPartitionProcessor0(tiler, imageBlockGenerator, nodeBuilder);
    }

    /**
     * @param filename
     * @return
     */
    @Override
    public
    FCImageModel loadModel ( String filename ) throws Exception {
        return fractalModel;
    }

    /**
     * @return
     */
    @Override
    public
    IImageBlock selectDomainBlock ( IImageBlock rangeBlock, Pool <IImageBlock> domainBlocks, IIntSize size) {
        return null;
    }

    /**
     * @param node
     */
    @Override
    public
    void add ( TreeNode <?> node ) {

    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( LeafNode <?> node ) {

    }

    /**
     * @param node
     */
//    @Override
    public
    void addLeafNode ( TreeNodeBase <?> node ) {

    }
}
