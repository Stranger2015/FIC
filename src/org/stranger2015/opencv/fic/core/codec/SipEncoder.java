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
class SipEncoder
        extends SaEncoder{

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
            ITreeNodeBuilder <N> nodeBuilder,
            IPartitionProcessor<N,A,G> partitionProcessor,
            ISearchProcessor <N> searchProcessor,
            ScaleTransform  scaleTransform,
            ImageBlockGenerator <N> imageBlockGenerator,
            IDistanceator  comparator,
            Set <ImageTransform> transforms,
            Set <IImageFilter > filters,
            FCImageModel <N> fractalModel
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
    SipEncoder ( IImage image, IIntSize rangeSize, IIntSize domainSize ) {
        super(
                image,
                rangeSize,
                domainSize
        );
    }

//    public
//    ImageBlockGenerator <N> createBlockGenerator ( ITiler<N> tiler,
//                                                            EPartitionScheme scheme,
//                                                            IEncoder <N> encoder,
//                                                            IImage image,
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
    IImage randomTransform ( IImage image, ImageTransform transform ) {
        return image;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
//    @Override
    public
    IImage applyTransform ( IImage image, ImageTransform transform ) {
        return image;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
//    @Override
    public
    IImage applyAffineTransform ( IImage image, AffineTransform  transform ) {
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
    List <ImageTransform> compress ( IImage image, int sourceSize, int destinationSize, int step ) {
        return new ArrayList <>();
    }

    /**
     * @param image
     * @return
     */
//    @Override
    public
    IImage encode ( IImage image ) throws ValueError {
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
    ImageBlockGenerator <?> createBlockGenerator (
            IPartitionProcessor <N> partitionProcessor,
            EPartitionScheme scheme,
            IEncoder <N> encoder,
            IImage image,
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
    List <IImageBlock > generateAllTransformedBlocks ( IImageBlock image,
                                                         int sourceSize,
                                                         int destinationSize,
                                                         int step ) {
        return List.of(image); //fixme
    }
}