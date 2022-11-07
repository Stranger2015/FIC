package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class CompositeEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Encoder <N, A, G> {


    /**
     *
     */
    protected final List <IEncoder <N, A, G>> pipeline = new ArrayList <>();

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
                       ITreeNodeBuilder <N, A, G> nodeBuilder,
                       IPartitionProcessor <N, A, G> partitionProcessor,
                       ISearchProcessor <N, A, G> searchProcessor,
                       ScaleTransform <A, G> scaleTransform,
                       ImageBlockGenerator <N, A, G> imageBlockGenerator,
                       IDistanceator <A> comparator,
                       Set <ImageTransform <A, G>> imageTransforms,
                       Set <IImageFilter <A>> imageFilters,
                       FicFileModel <N, A, G> fractalModel,
                       IEncoder <N, A, G>... encoders ) {

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
    IPartitionProcessor <N, A, G> createPartitionProcessor0 ( ITiler <N, A, G> tiler ) {
        return getPartitionProcessor().instance(tiler);
    }

    /**
     * @param image
     * @return
     */
    @Override
    public
    IImage <A> doEncode ( IImage <A> image ) {
        IImage <A> image1 = image;
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
    List <RegionOfInterest <A>> segmentImage ( IImage <A> image, List <Rectangle> bounds ) throws ValueError {
        return null;
    }

    /**
     * @param tiler
     * @return
     */
    @Override
    public
    IPartitionProcessor <N, A, G> doCreatePartitionProcessor ( ITiler <N, A, G> tiler ) {
        return createPartitionProcessor0(tiler);
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
    void addLeafNode ( TreeNode.LeafNode <N, A, G> node ) {

    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( TreeNodeBase <N, A, G> node ) {

    }
}
