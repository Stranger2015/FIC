package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;

import java.util.List;
import java.util.Set;

/**
 * @param <N>
 * @param
 */
public
class QuadTreeEncoder extends BinTreeEncoder {

    /**
     * @param image
     * @return
     * @throws ValueError
     */
    @Override
    public
    IImage doEncode ( IImage image ) throws Exception {
        super.doEncode(image);
        return image;
    }

    /**
     * @param inputImage
     */
    public
    QuadTreeEncoder (
            String fileName,
            EPartitionScheme scheme,
            ICodec codec,
            List <Task> tasks,
            EtvColorSpace colorSpace,
            ITreeNodeBuilder <?> nodeBuilder,
            IPartitionProcessor partitionProcessor,
            ISearchProcessor searchProcessor,
            ScaleTransform  scaleTransform,
            ImageBlockGenerator <?> imageBlockGenerator,
            IDistanceator  comparator,
            Set <ImageTransform> imageTransforms,
            Set <IImageFilter > imageFilters,
            FCImageModel fractalModel

    ) {
        super(
                fileName,
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
    IImageBlock randomTransform ( IImageBlock image, ImageTransform transform ) {
        return image;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImageBlock applyTransform ( IImageBlock image, ImageTransform transform ) {
        return image;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImageBlock applyAffineTransform ( IImageBlock image, AffineTransform  transform ) {
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
    List <IImageBlock > generateAllTransformedBlocks ( IImageBlock image,
                                                       int sourceSize,
                                                       int destinationSize,
                                                       int step )
            throws ValueError {

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
    ImageBlockGenerator <?> createBlockGenerator (
            IPartitionProcessor partitionProcessor,
            EPartitionScheme scheme,
            IEncoder encoder,
            IImage image,
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
}
