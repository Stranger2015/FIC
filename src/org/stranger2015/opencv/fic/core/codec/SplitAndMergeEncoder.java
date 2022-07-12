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
class SplitAndMergeEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
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
    SplitAndMergeEncoder ( EPartitionScheme scheme,
                           TreeNodeBuilder <N, A, G> nodeBuilder,
                           ISearchProcessor <N, A, G> searchProcessor,
                           ScaleTransform <A, G> scaleTransform,
                           ImageBlockGenerator <N, A, G> imageBlockGenerator,
                           IDistanceator <A> comparator,
                           Set <ImageTransform <A, G>> imageTransforms,
                           Set <IImageFilter <A>> filters,
                           FractalModel <N, A, G> fractalModel ) {
        super(
                scheme,
                nodeBuilder,
                searchProcessor,
                scaleTransform,
                imageBlockGenerator,
                comparator,
                imageTransforms,
                filters,
                fractalModel
        );
    }

    /**
     * @param scheme
     * @param paramTypes
     * @param params
     */
    protected
    SplitAndMergeEncoder ( EPartitionScheme scheme, Class <?>[] paramTypes, Object... params ) {
        super(scheme, paramTypes, params);
    }

    /**
     * @param scheme
     * @param encoder
     * @param decoder
     */
    public
    SplitAndMergeEncoder ( EPartitionScheme scheme, IEncoder <N, A, G> encoder, IDecoder <N, A, G> decoder ) {
        this(
                scheme,
                new Class <?>[]{encoder.getClass(), decoder.getClass()},
                encoder,
                decoder
        );
    }

    /**
     * @param image
     * @return
     */
    @Override
    public
    IImage <A> encode ( IImage <A> image ) throws ValueError {
        Library<A> library=new Library<>();
        nodeBuilder = nodeBuilderFactory.createBuilder(
                image.getSubImage(),
                getScheme(),
                rangeSize,
                domainSize,
                this,
                library);
        Tree <N, A, G> tree = nodeBuilder.buildTree(image.getSubImage());
        library.setTree(tree);
        tiler = new DelaunayTriangularTiler <>(image, rangeSize, domainSize, this, nodeBuilder);
        imageBlockGenerator = createBlockGenerator(tiler, getScheme(), this, image, rangeSize, domainSize);

        return super.encode(image);
    }


    /**
     * @param image
     * @param bounds
     * @return
     */
    @Override
    public
    List <RegionOfInterest <A>> segmentImage ( IImage <A> image, List <Rectangle> bounds ) throws ValueError {

        return super.segmentImage(image, bounds);
    }

    /**
     * @param addr
     * @return
     * @throws ValueError
     */
//    @Override
    public
    IAddress <A> createAddress ( int addr ) throws ValueError {
        return IAddress.valueOf(addr);
    }
}
