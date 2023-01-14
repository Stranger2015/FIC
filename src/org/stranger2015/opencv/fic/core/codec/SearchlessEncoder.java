package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.codec.tilers.Pool;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;

import java.util.List;
import java.util.Set;

public
class SearchlessEncoder extends Encoder {

    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    public
    SearchlessEncoder (
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
                fractalModel,
                encoders);

        outputImage = new CompressedImage(inputImage);
        this.imageBlockGenerator = imageBlockGenerator.create(
                partitionProcessor,
                scheme,
                this,
                inputImage,
                rangeSize,
                domainSize
        );

        tilerClass = partitionProcessor.getTiler().getClass();
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
    ImageBlockGenerator <?> createBlockGenerator ( IEncoder encoder,
                                                   IImage image,
                                                   IIntSize rangeSize,
                                                   IIntSize domainSize ) {
        return new SquareImageBlockGenerator <>(
//                this.imageBlockGenerator = imageBlockGenerator.create(
//                        partitionProcessor,
//                        scheme,
//                        this,
//                        inputImage,
//                        rangeSize,
//                        domainSize)
        );
    }

    @Override
    public
    IImageBlock randomTransform ( IImageBlock image, ImageTransform transform ) {
        return null;//todo
    }

    @Override
    public
    IImageBlock applyTransform ( IImageBlock image, ImageTransform transform ) {
        return null;//todo
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
     * @param filename
     * @return
     */
    @Override
    public
    FCImageModel loadModel ( String filename ) throws Exception {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    IImageBlock selectDomainBlock ( IImageBlock rangeBlock, Pool <IImageBlock> domainBlocks ) {
        return null;
    }

    @Override
    public
    IImageBlock applyAffineTransform ( IImageBlock image, AffineTransform transform ) {
        return null;//todo
    }
//
//    @Override
//    public
//    List<IImageBlock> generateAllTransformedBlocks ( IImage image, int sourceSize, int destinationSize, int step ) {
//        return null;//todo
//    }

    /**
     * @param image
     * @param bounds
     * @return
     * @throws ValueError
     */
    @Override
    public
    List <IImageBlock> segmentImage ( IImage image, List <Rectangle> bounds ) throws ValueError {
        return null;
    }

    /**
     * @param node
     */
    @Override
    public
    void add ( TreeNode <?> node ) {

    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( TreeNode.LeafNode <?> node ) {

    }

    //    @Override
    public
    void addLeafNode ( TreeNode <?> node ) {

    }
}