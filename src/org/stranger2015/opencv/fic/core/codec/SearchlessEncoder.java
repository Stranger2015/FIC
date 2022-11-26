package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 
 */
public
class SearchlessEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, M extends IImage<A>,
        G extends BitBuffer>

        extends Encoder <N, A, G> {

    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    public
    SearchlessEncoder ( M inputImage,
                        ISearchProcessor <N, A, G> rangeSize,
                        ImageBlockGenerator <N, A, G> domainSize ) {
        super(inputImage,
                rangeSize,
                rangeSize,
                domainSize,
                transforms,
                comparator,
                filters,
                fractalModel);
    }

    /**
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
//    @Override
    protected
    ImageBlockGenerator <N, A, G> createBlockGenerator ( IEncoder <N, A, G> encoder,
                                                            M image,
                                                            Size rangeSize,
                                                            Size domainSize ) {
        return new SquareImageBlockGenerator <>(
                new RectangularTiler <N, A, G>(8),
                encoder,
                image,
                rangeSize,
                domainSize);
    }

    @Override
    public
    M randomTransform ( M image, ImageTransform <A, G> transform ) {
        return null;//todo
    }

    @Override
    public
    M applyTransform ( M image, ImageTransform <A, G> transform ) {
        return null;//todo
    }

    @Override
    public
    M applyAffineTransform ( M image, AffineTransform <A, G> transform ) {
        return null;//todo
    }
//
//    @Override
//    public
//    List <ImageTransform <M, A, G>> compress ( M image, int sourceSize, int destinationSize, int step ) {
//        return null;//todo
//    }

    @Override
    public
    List<IImageBlock<A>> generateAllTransformedBlocks ( M image, int sourceSize, int destinationSize, int step ) {
        return null;//todo//todo
    }

    /**
     *
     */
    @Override
    public
    void onPreprocess () {

    }

    /**
     *
     */
    @Override
    public
    void onProcess () {

    }

    /**
     *
     */
    @Override
    public
    void onPostprocess () {

    }

    /**
     *
     */
    @Override
    public
    void onCompress () {

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

    @Override
    public
    void addLeafNode ( TreeNode <N, A, G> node ) {

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
