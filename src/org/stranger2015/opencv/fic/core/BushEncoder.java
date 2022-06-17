package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.*;
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
@Deprecated
public
class BushEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends CompositeEncoder <N, A, G> {

    /**
     *
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
    protected
    BushEncoder ( EPartitionScheme scheme,
                  ITreeNodeBuilder <N, A, G> nodeBuilder,
                  ISearchProcessor <N, A, G> searchProcessor,
                  ScaleTransform <A, G> scaleTransform,
                  ImageBlockGenerator <N, A, G> imageBlockGenerator,
                  IDistanceator <A> comparator,
                  Set <ImageTransform <A, G>> imageTransforms,
                  Set <IImageFilter <A>> imageFilters,
                  FractalModel <N, A, G> fractalModel ) {

        super(scheme,
                nodeBuilder,
                searchProcessor,
                scaleTransform,
                imageBlockGenerator,
                comparator,
                imageTransforms,
                imageFilters,
                fractalModel
        );
    }

    /**
     * @return
     */
    @Override
    public
    IPipeline <IImage <A>, IImage <A>> getLinkedObject () {
        return null;
    }

    /**
     * @param link
     */
    @Override
    public
    void setNext ( ISingleLinked <IPipeline <IImage <A>, IImage <A>>> link ) {

    }

    /**
     * @param image
     * @param bounds
     * @return
     * @throws ValueError
     */
    @Override
    public
    List <RegionOfInterest <A>> segmentImage ( IImage <A> image, List <Rectangle> bounds )  {
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
     * @param node
     */
    @Override
    public
    void addLeafNode ( TreeNode <N, A, G> node ) {

    }

    @Override
    public
    ISingleLinked <IPipeline <IImage <A>, IImage <A>>> getNext () {
        return null;
    }

    @Override
    public
    IImage <A> getInput () {
        return null;
    }

    @Override
    public
    IImage <A> getOutput () {
        return null;
    }
}
