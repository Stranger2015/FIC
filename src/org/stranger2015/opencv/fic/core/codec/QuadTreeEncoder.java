package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.io.FractalReader;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * @param <N>
 * @param <A>
 */
public
class QuadTreeEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends BinTreeEncoder <N, A, G> {

    /**
     * @param image
     * @return
     * @throws ValueError
     */
    @Override
    public
    IImage <A> doEncode ( IImage <A> image ) throws ValueError, ReflectiveOperationException {
        return super.doEncode(image);
    }

    /**
     * @param inputImage
     */
    public
    QuadTreeEncoder (
            String fn,
            EPartitionScheme scheme,
            ICodec <N, A, G> codec,
            List <Task <N, A, G>> tasks,
            EtvColorSpace colorSpace,
            ITreeNodeBuilder <N, A, G> nodeBuilder,
            IPartitionProcessor <N, A, G> partitionProcessor,
            ISearchProcessor <N, A, G> searchProcessor,
            ScaleTransform <A, G> scaleTransform,
            ImageBlockGenerator <N, A, G> imageBlockGenerator,
            IDistanceator <A> comparator,
            Set <ImageTransform <A, G>> imageTransforms,
            Set <IImageFilter <A>> imageFilters,
            FCImageModel <N, A, G> fractalModel

    ) {
        super(fn,
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
                fractalModel
        );
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage <A> randomTransform ( IImage <A> image, ImageTransform <A, G> transform ) {
        return image;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage <A> applyTransform ( IImage <A> image, ImageTransform <A, G> transform ) {
        return image;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage <A> applyAffineTransform ( IImage <A> image, AffineTransform <A, G> transform ) {
        return image;//todo
    }

    /**
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
    @Override
    public
    List <IImageBlock <A>> generateAllTransformedBlocks ( IImage <A> image,
                                                          int sourceSize,
                                                          int destinationSize,
                                                          int step ) throws ValueError {
        return List.of(image.getSubImage());
    }

    /**
     * /**
     *
     * @param tiler
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
    @Override
    public
    ImageBlockGenerator <N, A, G> createBlockGenerator (
            IPartitionProcessor <N, A, G> partitionProcessor,
            EPartitionScheme scheme,
            IEncoder <N, A, G> encoder,
            IImage <A> image,
            IIntSize rangeSize,
            IIntSize domainSize ) {

        return new QuadTreeImageBlockGenerator <>(
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
     * @param axis
     * @return
     */
    @Override
    public
    IImage <A> flipAxis ( IImage <A> image, int axis ) {
        return image;
    }

    /**
     * @return
     */
//    @Override
    public
    ISearchProcessor <N, A, G> createSearchProcessor () {
        return null;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    public
    FCImageModel <N, A, G> loadModel ( String filename ) throws ValueError {
        FractalReader <N, A, G> reader = new FractalReader <>(new File(filename));

        return new FCImageModel <>(null);
    }
}
