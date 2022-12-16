package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
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
class CompositeEncoder
        extends Encoder{


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
    CompositeEncoder ( EPartitionScheme scheme,
                       ITreeNodeBuilder <?> nodeBuilder,
                       IPartitionProcessor partitionProcessor,
                       ISearchProcessor searchProcessor,
                       ScaleTransform  scaleTransform,
                       ImageBlockGenerator<?> imageBlockGenerator,
                       IDistanceator  comparator,
                       Set <ImageTransform> imageTransforms,
                       Set <IImageFilter > imageFilters,
                       FCImageModel fractalModel,
                       IEncoder ... encoders ) {

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
                fractalModel
        );

        pipeline.addAll(Arrays.asList(encoders));
    }

    /**
     *
     */
    @Override
    public
    void initialize () throws ReflectiveOperationException, Exception {

    }

    /**
     *
     * @param tiler
     * @return
     */
    @Override
    public
    IPartitionProcessor <N> createPartitionProcessor0 ( ITiler <N> tiler ) {
        return getPartitionProcessor().instance(tiler);
    }

    /**
     * @param image
     */
    @Override
    public
    void doEncode ( IImage image ) {
        IImage image1 = image;
        for (int i = 0; i < pipeline.size(); i++) {
            image1 = pipeline.get(0).doEncode(image1);
        }

        return image1;
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
     * @param tiler
     * @return
     */
    @Override
    public
    IPartitionProcessor <N> doCreatePartitionProcessor ( ITiler <N> tiler ) {
        return createPartitionProcessor0(tiler);
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
    void addLeafNode ( TreeNode.LeafNode <N> node ) {

    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( TreeNodeBase <N> node ) {

    }
}
