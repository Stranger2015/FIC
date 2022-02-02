package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.EPartitionScheme;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.ImageBlock;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 */
public
class DefaultCodec<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
        extends Codec <N, A, M>
        implements IConstants {

    /**
     *
     */
    protected final IEncoder <N, A, M> encoder;
    protected final IDecoder <M> decoder = new Decoder <>();

    /**
     * @param scheme
     */
    public
    DefaultCodec ( EPartitionScheme scheme, EncodeAction action/*, String filename*/ ) {
        super(scheme, action);
        encoder = getEncoder();
    }

    /**
     * @param scheme
     * @param action
     * @return
     */
    @Override
    public
    Codec <N, A, M> create ( EPartitionScheme scheme, EncodeAction action ) {
        return new DefaultCodec <>(scheme, action/*, action.getFilename()*/);
    }
//
//    /**
//     * @param listener
//     */
//    @Override
//    public
//    void addListener ( IEncoderListener listener ) {
//
//    }
//
//    /**
//     * @param listener
//     */
//    @Override
//    public
//    void removeListener ( IEncoderListener listener ) {
//
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public
//    M encode ( M image ) {
//        return encoder.encode(image);
//    }
//
//    /**
//     * @param x
//     * @param axis
//     * @return
//     */
//    @Override
//    public
//    M flipAxis ( M x, int axis ) {
//        return encoder.flipAxis(x, axis);
//    }
//
//    /**
//     * @param image
//     * @param transform
//     * @return
//     */
//    @Override
//    public
//    M randomTransform ( M image, ImageTransform <M> transform ) {
//        return encoder.randomTransform(image, transform);
//    }
//
//    /**
//     * @param image
//     * @param transform
//     * @return
//     */
//    @Override
//    public
//    M applyTransform ( M image, ImageTransform <M> transform ) {
//        return encoder.applyTransform(image, transform);
//    }
//
//    /**
//     * @param image
//     * @param transform
//     * @return
//     */
//    @Override
//    public
//    M applyAffineTransform ( M image, AffineTransform <M> transform ) {
//        return encoder.applyAffineTransform(image, transform);
//    }
//
//    /**
//     * @param image
//     * @param sourceSize
//     * @param destinationSize
//     * @param step
//     * @return
//     */
//    @Override
//    public
//    List <ImageTransform <M>> compress ( M image, int sourceSize, int destinationSize, int step ) {
//        return encoder.compress(image, sourceSize, destinationSize, step);
//    }

//    /**
//     * @param image
//     * @param sourceSize
//     * @param destinationSize
//     * @param step
//     * @return
//     */
//    @Override
//    public
//    List <ImageBlock> generateAllTransformedBlocks ( M image, int sourceSize, int destinationSize, int step ) {
//        return encoder.generateAllTransformedBlocks(image, sourceSize, destinationSize, step);
//    }

//    /**
//     * @return
//     */
//    @Override
//    public
//    List <ImageBlock> segmentImage ( M image ) {
//        return encoder.segmentImage(image);
//    }

//    /**
//     *
//     */
//    @Override
//    public
//    void onPreprocess () {
//
//    }
//
//    /**
//     *
//     */
//    @Override
//    public
//    void onProcess () {
//
//    }
//
//    /**
//     *
//     */
//    @Override
//    public
//    void onPostprocess () {
//
//    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
    @Override
    public
    IEncoder <N, A, M> getEncoder ( M image, Size rangeSize, Size domainSize ) {
        return encoder;
    }

    /**
     * @return
     */
    @Override
    public
    int getImageSizeBase () {
        return encoder.getImageSizeBase();
    }

    /**
     * @return
     */
    @Override
    public
    IEncoder <N, A, M> getEncoder () {
        return encoder;
    }

    /**
     * @return
     */
    @Override
    public
    IDecoder <M> getDecoder () {
        return decoder;
    }
}
