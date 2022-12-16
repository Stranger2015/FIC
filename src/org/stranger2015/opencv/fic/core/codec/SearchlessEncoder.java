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
 * @param
 
 */
public
class SearchlessEncoder<N extends TreeNode <N>, A extends IAddress , IImage extends IImage,
        G extends BitBuffer>

        extends Encoder <N> {

    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    public
    SearchlessEncoder ( IImage inputImage,
                        ISearchProcessor <N> rangeSize,
                        ImageBlockGenerator <N> domainSize ) {
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
    ImageBlockGenerator <N> createBlockGenerator ( IEncoder <N> encoder,
                                                            IImage image,
                                                            Size rangeSize,
                                                            Size domainSize ) {
        return new SquareImageBlockGenerator <>(
                new RectangularTiler <N>(8),
                encoder,
                image,
                rangeSize,
                domainSize);
    }

    @Override
    public
    IImage randomTransform ( IImage image, ImageTransform transform ) {
        return null;//todo
    }

    @Override
    public
    IImage applyTransform ( IImage image, ImageTransform transform ) {
        return null;//todo
    }

    @Override
    public
    IImage applyAffineTransform ( IImage image, AffineTransform  transform ) {
        return null;//todo
    }
//
//    @Override
//    public
//    List <ImageTransform <M, A, G>> compress ( IImage image, int sourceSize, int destinationSize, int step ) {
//        return null;//todo
//    }

    @Override
    public
    List<IImageBlock> generateAllTransformedBlocks ( IImage image, int sourceSize, int destinationSize, int step ) {
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
    List <IImageBlock > segmentImage ( IImage image, List <Rectangle> bounds ) throws ValueError {
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
    void addLeafNode ( TreeNode.LeafNode <N> node ) {

    }

    @Override
    public
    void addLeafNode ( TreeNode <N> node ) {

    }

    /**
     * @return
     */
    @Override
    public
    IPipeline <IImage , IImage> getLinkedObject () {
        return null;
    }

    /**
     * @param link
     */
    @Override
    public
    void setNext ( ISingleLinked <IPipeline <IImage , IImage>> link ) {

    }

    @Override
    public
    ISingleLinked <IPipeline <IImage , IImage>> getNext () {
        return null;
    }

    @Override
    public
    IImage getInput () {
        return null;
    }

    @Override
    public
    IImage getOutput () {
        return null;
    }
}
