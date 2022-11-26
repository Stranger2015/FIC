package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @param <N>
 
 */
public
class SipEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends SaEncoder <N, A, G> {

    /**
     *
     * @param scheme
     * @param nodeBuilder
     * @param searchProcessor
     * @param scaleTransform
     * @param imageBlockGenerator
     * @param comparator
     * @param transforms
     * @param filters
     * @param fractalModel
     */
    public
    SipEncoder (
            EPartitionScheme scheme,
            ITreeNodeBuilder <N, A, G> nodeBuilder,
            IPartitionProcessor<N,A,G> partitionProcessor,
            ISearchProcessor <N, A, G> searchProcessor,
            ScaleTransform <A, G> scaleTransform,
            ImageBlockGenerator <N, A, G> imageBlockGenerator,
            IDistanceator <A> comparator,
            Set <ImageTransform <A, G>> transforms,
            Set <IImageFilter <A>> filters,
            FCImageModel <N, A, G> fractalModel
    ) {
        super(
                scheme,
                nodeBuilder,
                partitionProcessor,
                searchProcessor,
                scaleTransform,
                imageBlockGenerator,
                comparator,
                transforms,
                filters,
                fractalModel
        );
    }

    public
    SipEncoder ( IImage<A> image, IIntSize rangeSize, IIntSize domainSize ) {
        super(
                image,
                rangeSize,
                domainSize
        );
    }

//    public
//    ImageBlockGenerator <N, A, G> createBlockGenerator ( ITiler<N, A, G> tiler,
//                                                            EPartitionScheme scheme,
//                                                            IEncoder <N, A, G> encoder,
//                                                            IImage <A> image,
//                                                            IIntSize rangeSize,
//                                                            IIntSize domainSize
//    ) {
//        return new SipImageBlockGenerator <>(
//                tiler,
//                scheme,
//                encoder,
//                image,
//                rangeSize,
//                domainSize
//        );
//    }

    /**
     * @return
     */
//    @Override
    public
    int getImageSizeBase () {
        return 9;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
//    @Override
    public
    IImage<A> randomTransform ( IImage<A> image, ImageTransform <A, G> transform ) {
        return image;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
//    @Override
    public
    IImage<A> applyTransform ( IImage<A> image, ImageTransform <A, G> transform ) {
        return image;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
//    @Override
    public
    IImage<A> applyAffineTransform ( IImage<A> image, AffineTransform <A, G> transform ) {
        return image;//todo
    }

    /**
     *
     */
//    @Override
    public
    void onPreprocess () {
        super.onPreprocess();
    }

    /**
     *
     */
//    @Override
    public
    void onProcess () {
        super.onProcess();
    }

    /**
     *
     */
//    @Override
    public
    void onPostprocess () {
        super.onPostprocess();
    }

    /**
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
//    @Override
    public
    List <ImageTransform <A, G>> compress ( IImage<A> image, int sourceSize, int destinationSize, int step ) {
        return new ArrayList <>();
    }

    /**
     * @param image
     * @return
     */
//    @Override
    public
    IImage<A> encode ( IImage<A> image ) throws ValueError {
        return super.encode(image);
    }

    /**
     * @param encoder
     * @param image
     * @param rangeSize
     * @return
     */
//    @Override
    public
    ImageBlockGenerator <N, A, G> createBlockGenerator (
            IPartitionProcessor <N, A, G> partitionProcessor,
            EPartitionScheme scheme,
            IEncoder <N, A, G> encoder,
            IImage<A> image,
            IIntSize rangeSize,
            IIntSize domainSize
    ) {
        return new SipImageBlockGenerator <>(
                partitionProcessor,
                scheme,
                encoder,
                image,
                rangeSize,
                domainSize
        );
    }

    /**
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
   @SuppressWarnings("unchecked")
    @Override
    public
    List <IImageBlock <A>> generateAllTransformedBlocks ( IImageBlock<A> image,
                                                         int sourceSize,
                                                         int destinationSize,
                                                         int step ) {
        return List.of(image); //fixme
    }
}