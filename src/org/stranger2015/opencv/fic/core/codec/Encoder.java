package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.io.FractalReader;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.io.File;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.IntStream.range;
import static org.opencv.core.Core.flip;
import static org.opencv.core.Core.reduce;
import static org.stranger2015.opencv.fic.core.codec.ESplitKind.VERTICAL;

/**
 * @param <N>
 */
public abstract
class Encoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        implements IEncoder <N, A, G> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    // rangeBlocks;

    @Override
    public
    Class <?> getTilerClass () {
        return tilerClass;
    }

    protected Class <?> tilerClass;

    protected EPartitionScheme scheme;
    protected ScaleTransform <A, G> scaleTransform;
    protected ImageBlockGenerator <N, A, G> imageBlockGenerator;
    protected IDistanceator <A> comparator;
    protected Set <ImageTransform <A, G>> transforms;
    protected Set <IImageFilter <A>> filters;

    protected FicFileModel <N, A, G> fractalModel;
    protected IPartitionProcessor <N, A, G> partitionProcessor;

    protected ISearchProcessor <N, A, G> searchProcessor;
    protected ITreeNodeBuilder <N, A, G> nodeBuilder;

    protected ITreeNodeBuilderFactory <N, A, G> nodeBuilderFactory;

    protected final List <RegionOfInterest <A>> roiBlocks = new ArrayList <>();

    protected IImage <A> inputImage;
    protected CompressedImage <A> outputImage;

    protected IImage <A> image;

    protected IIntSize rangeSize;
    protected IIntSize domainSize;

    /**
     * @return
     */
    public
    Logger getLogger () {
        return logger;
    }

    /**
     * @return
     */
    public
    EPartitionScheme getScheme () {
        return scheme;
    }

    /**
     *
     */
    @SuppressWarnings("unchecked")
    protected
    Encoder (
            EPartitionScheme scheme,
            ITreeNodeBuilder <N, A, G> nodeBuilder,
            IPartitionProcessor <N, A, G> partitionProcessor,
            ISearchProcessor <N, A, G> searchProcessor,
            ScaleTransform <A, G> scaleTransform,
            ImageBlockGenerator <N, A, G> imageBlockGenerator,
            IDistanceator <A> comparator,
            Set <ImageTransform <A, G>> imageTransforms,
            Set <IImageFilter <A>> imageFilters,
            FicFileModel <N, A, G> fractalModel
    ) {
        this.scheme = scheme;
        this.nodeBuilder = nodeBuilder;
        this.partitionProcessor = partitionProcessor;
        this.searchProcessor = searchProcessor;
        this.scaleTransform = scaleTransform;
        this.transforms = imageTransforms;
        this.comparator = comparator;
        this.filters = imageFilters;
        this.fractalModel = fractalModel;

        outputImage = new CompressedImage <>(inputImage);
        this.imageBlockGenerator = imageBlockGenerator.create(
                partitionProcessor,
                scheme,
                this,
                inputImage,
                rangeSize,
                domainSize);

        tilerClass = partitionProcessor.getTiler().getClass();
    }

    @SuppressWarnings("unchecked")
    public static
    <N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
    IEncoder <N, A, G> create ( @NotNull EPartitionScheme scheme,
                                Class <?>[] paramTypes,
                                Object... params )
            throws ReflectiveOperationException {

        return (IEncoder <N, A, G>) Utils.create(
                scheme.getEncoderClassName(),
                List.of(),
                paramTypes,
                params
        );
    }

    /**
     * @return
     */
    public final
    List <RegionOfInterest <A>> getRegionBlocks () {
        return roiBlocks;
    }

    /**
     * @return
     */
    @Override
    public
    IImage <A> getInputImage () {
        return inputImage;
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
    ImageBlockGenerator <N, A, G> createBlockGenerator (
            IPartitionProcessor <N, A, G> partitionProcessor,
            EPartitionScheme scheme,
            IEncoder <N, A, G> encoder,
            IImage <A> image,
            IIntSize rangeSize,
            IIntSize domainSize
    ) {
        return imageBlockGenerator.newInstance(
                partitionProcessor,
                scheme,
                encoder,
                image,
                rangeSize,
                domainSize
        );
    }

    /**
     * @return
     */
    public abstract
    IPartitionProcessor <N, A, G> createPartitionProcessor0 ( ITiler <N, A, G> tiler );

    /**
     * @return
     */
    @Override
    public
    IPartitionProcessor <N, A, G> getPartitionProcessor () {
        return partitionProcessor;
    }

    /**
     * @return
     */
    @Override
    public
    FicFileModel <N, A, G> getModel () {
        return fractalModel;
    }

    /**
     * @param filename
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    FicFileModel <N, A, G> loadModel ( String filename ) {
        return (FicFileModel <N, A, G>) new FractalReader <>(new File(filename)).readModel();
    }

    /**
     * @param imageBlock
     * @return
     */
    @Contract(pure = true)
    public
    EnumSet <ESplitKind> chooseDirection ( IImageBlock <A> imageBlock ) {
        return EnumSet.of(VERTICAL);
    }

    /**
     * @return
     */
    public
    CompressedImage <A> getOutputImage () {
        return outputImage;
    }

    /**
     *
     */
    @Override
    public
    void segmentRegion ( RegionOfInterest <A> roi, int blockWidth, int blockHeight )
            throws ValueError {
        List <IImageBlock <A>> rangeBlocks = partitionProcessor.generateRangeBlocks(
                roi,
                blockWidth,
                blockHeight);

        List <IImageBlock <A>> domainBlocks = imageBlockGenerator.generateDomainBlocks(
                roi,
                rangeBlocks);

        List <IImageBlock <A>> codebookBlocks = imageBlockGenerator.createCodebookBlocks(
                roi,
                domainBlocks);
    }

    public final
    IImage <A> encode ( IImage <A> image ) throws Exception {
        assert image != null : "Cannot compress null image";

        initialize();

        return doEncode(image);
    }

    /**
     * @return
     */
    @Override
    public
    IImage <A> doEncode ( IImage <A> image ) throws ValueError {
        List <Rectangle> bounds = List.of(new Rectangle(image.getWidth(), image.getHeight()));
        final List <RegionOfInterest <A>> regions = segmentImage(image, bounds);
        List <List <IImageBlock <A>>> list = handleRegionList(regions, rangeSize, domainSize);

        return searchProcessor.search();
    }

    @Override
    public
    void initialize () throws Exception {
        logger.info("Initializing encoder... ");

        Library <A> library = new Library <>();
        EncoderFactory <N, A, G> encoderFactory = new EncoderFactory <>();

        nodeBuilder = nodeBuilderFactory.createBuilder(
                image.getSubImage(),
                getScheme(),
                rangeSize,
                domainSize,
                this,
                library);

        Tree <N, A, G> tree = nodeBuilder.buildTree(image.getSubImage());
        library.setTree(tree);
        ITiler <N, A, G> tiler = encoderFactory.createTiler(
                image,
                rangeSize,
                domainSize,
                this,
                nodeBuilder
        );
        IPartitionProcessor <N, A, G> partitionProcessor = this.createPartitionProcessor0(tiler);

        imageBlockGenerator = createBlockGenerator(
                partitionProcessor,
                getScheme(),
                this,
                image,
                rangeSize,
                domainSize
        );
    }


    /**
     * @param regions
     * @return
     * @throws ValueError
     */
    private
    List <List <IImageBlock <A>>> handleRegionList ( List <RegionOfInterest <A>> regions,
                                                     IIntSize rangeSize,
                                                     IIntSize domainSize ) throws ValueError {

        List <List <IImageBlock <A>>> list = new ArrayList <>();
        for (RegionOfInterest <A> roi : regions) {
            IImage <A> roiImage = roi;
            /*
             * Normalization. Before tiling the image, pass it throw a set of filters.
             * This might improve results, if used wisely.
             */
            for (IImageFilter <A> filter : filters) {
                roi = (RegionOfInterest <A>) filter.filter(roiImage);
            }
            list.add(imageBlockGenerator.generateRangeBlocks(
                    roi,
                    rangeSize.getWidth(),
                    rangeSize.getHeight()));
        }

        return list;
    }

    /**
     * @param image
     * @return
     */
    @Contract("_ -> param1")
    private
    IImage <A> iterateRegions ( IImage <A> image ) {
        for (int i = 0, size = image.getRegions().size(); i < size; i++) {
            RegionOfInterest <A> region = image.getRegions().get(i);
        }

        return image;
    }

//    /**
//     * @param image
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    protected
//    IImageBlock <A> iterateRangeBlocks ( IImageBlock <A> image, List <IImageBlock <A>> rangeBlocks ) {
//        rangeBlocks.forEach(rangeBlock -> {
//            int percent = 100 * (rangeBlocks.indexOf(rangeBlock) + 1) / rangeBlocks.size();
//            System.err.printf("%d%%", percent);
//            ImageTransform <A, G> bestTransform = ImageTransform.create(image, rangeBlock.getAddress());
//            int minDistance = Integer.MAX_VALUE;
//            iterateDomainBlocks(rangeBlock, bestTransform, minDistance);
//            getTransforms().add(bestTransform);
//        });
//        outputImage.getTransforms().addAll(transforms);

//        return (IImageBlock <A>) outputImage;
//    }

//    /**
//     * @param rangeBlock
//     * @param bestTransform
//     * @param minDistance
//     */
//    protected
//    void iterateDomainBlocks ( ImageBlock <A> rangeBlock,
//                               ImageTransform <A, G> bestTransform,
//                               int minDistance ) throws ValueError {
//        for (ImageBlock <A> domainBlock : domainBlocks) {
//            double alpha = 0;
//            for (int i = 0; i < domainBlock.width; i++) {
//                for (int j = 0; j < domainBlock.height; j++) {
//                    double[] data = new double[4];//fixme
//                    alpha += (domainBlock.get(i, j, data) - domainBlock.meanPixelValue);
//                }
//            }
//ca
//            for (int addr = 0; addr < domainBlock.getWidth(); addr++) {
//                double[] data = new double[4];//fixme
//                alpha += (domainBlock.get(domainBlock.getAddress().newInstance(addr), data) - domainBlock.meanPixelValue);
//            }

//            double contrast = alpha / domainBlock.beta;//byte
//            int brightness = (int) (rangeBlock.meanPixelValue - contrast * domainBlock.meanPixelValue);
//
//            for (int index = 0; index < 8; index++) {
//                IImageBlock <A> transformedDomainBlock =
//                        new ImageBlock <>(
//                                inputImage,
//                                domainBlock.getAddress(),
//                                domainBlock.getSize(),
//                                geometry);
//                int pixelAmount = domainBlock.getWidth() * domainBlock.getWidth();
//                for (int addr = 0; addr < pixelAmount; addr++) {
//                    for (int y = 0; y < domainBlock.height; y++) {
//                        int newAddr = addr * -1; // fixme dihedralAffineTransforms[index][0] + y * dihedralAffineTransforms[index][1];
//                        int newY = x * dihedralAffineTransforms[index][2] + y * dihedralAffineTransforms[index][3];
//                        if (newAddr < 0) {
//                            newAddr += domainBlock.getWidth();
//                        }
//                        if (newY < 0) {
//                            newY += domainBlock.height;
//                        }
//                        double newPixelValue = (short) (contrast * domainBlock.put(addr, new int[1]) + brightness);
//
//                        double[] data = new double[4];
//data[0]=
//                            ((newPixelValue < MAX_PIXEL_VALUE) && (newPixelValue >= 0)) ?
//                                    newPixelValue :
//                                    (MAX_PIXEL_VALUE / 2);
//
//                        transformedDomainBlock.put(
//                                newAddr,
//                                data
//                        );
//}
//                    }
//                    int distance = getDistance(rangeBlock, transformedDomainBlock);
//                    if ((distance < minDistance) && (distance != 0)) {
//                        minDistance = distance;
//                        bestTransform.dihedralAffineTransformIndex = index;
//                        bestTransform.setAddress(transformedDomainBlock.getAddress());
//                        bestTransform.brightnessOffset = brightness;
//                        bestTransform.contrastScale = contrast;
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * @param range
//     * @param domain
//     * @return
//        double error;
//        int boundOuter = range.getWidth();
//        error = range(0, boundOuter)
//                .map(i -> range.getHeight())
///                .flatMap(bound -> range(0, bound)).mapToDouble(j -> Math
//                        .pow(range.getMeanPixelValue() - domain.getMeanPixelValue(), 2)).sum();
//        error = error / (double) (range.getWidth() * range.getHeight());
//
//        return (int) error;
//    }

    /**
     * @return
     */
    @Override
    public
    Set <ImageTransform <A, G>> getTransforms () {
        return transforms;
    }

    /**
     * x = np.asarray(x).swapaxes (axis, 0);
     * x = x[::-1, ...]
     * x = x.swapaxes(0, axis);
     *
     * @param image
     * @param axis
     * @return
     */
    public
    IImage <A> flipAxis ( IImage <A> image, int axis ) {
        Mat dest = new Mat();
        flip(image.getMat(), dest, axis);

        return new Image <>(dest, image.getSize(), originalImageWidth);
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage <A> randomTransform ( IImage <A> image, ImageTransform <A, G> transform ) {
        return image;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage <A> applyTransform ( IImage <A> image, ImageTransform <A, G> transform ) {
        return image;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage <A> applyAffineTransform ( IImage <A> image, AffineTransform <A, G> transform ) {
        return image;
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
                                                          int step ) {

        final List <IImageBlock <A>> transformedBlocks = new ArrayList <>();
        //Extract the source block and reduce it to the shape of a destination block
        range(0, image.getWidth() - sourceSize / step + 1)
                .flatMap(i -> range(0, image.getHeight() - sourceSize / step + 1))
                .mapToObj(j -> new Mat())
                .forEachOrdered(dest -> reduce(image.getMat(), dest, -1, -1, -1));
//        for k in range((img.shape[0] - source_size) // step + 1):
//        for l in range((img.shape[1] - source_size) // step + 1):
//
//                S = reduce(img[k*step:k*step+source_size,l*step:l*step+source_size], factor)
//            # Generate all possible transformed blocks
//        for direction, angle in candidates:

//        transformedBlocks.add(n(k, l, direction, angle, applyTransformation(S, direction, angle));

        return transformedBlocks;
    }

    /**
     * @return
     */
    public
    ImageBlockGenerator <N, A, G> getImageBlockGenerator () {
        return imageBlockGenerator;
    }

    /**
     * @return
     */
    public
    ScaleTransform <A, G> getScaleTransform () {
        return scaleTransform;
    }

    /**
     * @return
     */
    public
    IDistanceator <A> getComparator () {
        return comparator;
    }

    /**
     * @return
     */
    public
    IImage <A> getImage () {
        return image;
    }
}