package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;

import java.util.List;
import java.util.Set;

/**
 *
 */
public
class DtSplitAndMergeEncoder extends BinTreeEncoder {

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
    DtSplitAndMergeEncoder (
            String fileName,
            EPartitionScheme scheme,
            ICodec codec,
            List <Task> tasks,
            EtvColorSpace colorSpace,
            ITreeNodeBuilder <?> nodeBuilder,
            IPartitionProcessor partitionProcessor,
            ISearchProcessor searchProcessor,
            ScaleTransform scaleTransform,
            ImageBlockGenerator <?> imageBlockGenerator,
            IDistanceator comparator,
            Set <ImageTransform> imageTransforms,
            Set <IImageFilter> imageFilters,
            FCImageModel fractalModel ) {

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
     * @return
     */
    @Override
    public
    IImage getInputImage () {
        return super.getInputImage();
    }

    /**
     * @param tiler
     * @param scheme
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

        return new DtImageBlockGenerator <>(
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
     * @param bounds
     * @return
     */
    @Override
    public
    List <IImageBlock> segmentImage ( IImage image, List <Rectangle> bounds ) throws ValueError {
        return super.segmentImage(image, bounds);
    }

    /**
     * @param addr
     * @return
     * @throws ValueError
     */
    IAddress createAddress ( int addr ) throws ValueError {
        return IAddress.valueOf(addr, inputImage.getWidth(), 0);
    }

//    /**
//     * @param imageBlock
//     * @return
//     */
//    @Override
//    protected
//    ESplitKind chooseDirection ( IImageBlock  imageBlock ) {
//        return DIAGONAL;
//    }

    /**
     * @throws ValueError
     */
    @Override
    public
    void initialize () throws Exception {
        super.initialize();
    }

    /**
     * @param tiler
     * @return
     */
    @Override
    public
    IPartitionProcessor doCreatePartitionProcessor ( ITiler tiler ) {
        return null;
    }

    /**
     * @param image
     * @return
     * @throws ValueError
     */
    @Override
    public
    IImage doEncode ( IImage image ) throws Exception {
        List <IImageBlock> rangeBlocks = partitionProcessor.generateRangeBlocks(
                image.getSubImage(),
                image.getWidth(),
                image.getHeight());

        List <IImageBlock> domainBlocks = partitionProcessor.generateDomainBlocks(//fixme
                image.getSubImage(),
                partitionProcessor.getDomainSize().getWidth(),
                partitionProcessor.getDomainSize().getHeight());

        super.doEncode(image);
        return image;
    }

    /**
     * @param tilerClass
     */
    @Override
    public
    void addAllowableSubtiler (Class <ITiler> tilerClass ) {

    }

    /**
     * @param outputImage
     * @return
     */
    @Override
    public
    IImage postprocess ( IImage outputImage ) {
        return null;
    }
}
