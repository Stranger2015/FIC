package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.io.FractalReader;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.io.File;
import java.lang.reflect.Constructor;
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
        extends ImageProcessor <N, A, G>
        implements IEncoder <N, A, G> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final List <Class <ITiler <N, A, G>>> allowableSubtilers = new ArrayList <>();
    protected Class <?> tilerClass;

    protected String fn;
    protected List <Task <N, A, G>> tasks;

    protected EtvColorSpace colorSpace;
    protected ICodec <N, A, G> codec;

    protected EPartitionScheme scheme;
    protected ScaleTransform <A, G> scaleTransform;
    protected ImageBlockGenerator <N, A, G> imageBlockGenerator;
    protected IDistanceator <A> comparator;
    protected Set <ImageTransform <A, G>> transforms;
    protected Set <IImageFilter <A>> filters;

    protected FCImageModel <N, A, G> fractalModel;
    protected IPartitionProcessor <N, A, G> partitionProcessor;

    protected ISearchProcessor <N, A, G> searchProcessor;
    protected ITreeNodeBuilder <N, A, G> nodeBuilder;

    protected ITreeNodeBuilderFactory <N, A, G> nodeBuilderFactory;

    protected IImage <A> inputImage;
    protected CompressedImage <A> outputImage;

    List <IImageBlock <A>> rangeBlocks;
    List <IImageBlock <A>> domainBlocks;

    protected IIntSize rangeSize;
    protected IIntSize domainSize;

    /**
     *
     */
    protected
    Encoder (
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
        super(fn, scheme, codec, tasks, colorSpace);

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

    /**
     * @param fn
     * @param scheme
     * @param codec
     * @param tasks
     * @param colorSpace
     * @param nodeBuilder
     * @param partitionProcessor
     * @param searchProcessor
     * @param scaleTransform
     * @param imageBlockGenerator
     * @param comparator
     * @param imageTransforms
     * @param imageFilters
     * @param fractalModel
     * @param <N>
     * @param <A>
     * @param <G>
     * @return
     * @throws ReflectiveOperationException
     */
    @SuppressWarnings("unchecked")
    public static
    <N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
    @NotNull IEncoder <N, A, G> create (
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
            FCImageModel <N, A, G> fractalModel )

            throws ReflectiveOperationException {

        Class <?> clazz = Class.forName(scheme.getEncoderClassName());
        Constructor <?> ctor = clazz.getConstructor(
                String.class,
                EPartitionScheme.class,
                ICodec.class,
                List.class,
                EtvColorSpace.class,
                ITreeNodeBuilder.class,
                IPartitionProcessor.class,
                ISearchProcessor.class,
                ScaleTransform.class,
                ImageBlockGenerator.class,
                IDistanceator.class,
                Set.class,
                Set.class,
                FCImageModel.class
        );

        return (IEncoder <N, A, G>) ctor.newInstance(
                fn,
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
    Set <IImageFilter <A>> getFilters () {
        return filters;
    }

    /**
     * @return
     */
    @Override
    public
    List <Class <ITiler <N, A, G>>> getAllowableSubtilers () {
        return allowableSubtilers;
    }

    /**
     * @param tiler
     */
    @Override
    public
    void addAllowableSubtiler ( Class <ITiler <N, A, G>> tilerClass ) {
        allowableSubtilers.add(tilerClass);
    }

    /**
     * @return
     */
    @Override
    public
    Class <?> getTilerClass () {
        return tilerClass;
    }

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
    @Override
    public
    EPartitionScheme getScheme () {
        return scheme;
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
    IPartitionProcessor <N, A, G> doCreatePartitionProcessor ( ITiler <N, A, G> tiler );

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
    FCImageModel <N, A, G> getModel () {
        return fractalModel;
    }

    /**
     * @param filename
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    FCImageModel <N, A, G> loadModel ( String filename ) throws ValueError {
        return (FCImageModel <N, A, G>) new FractalReader <>(new File(filename)).readModel();
    }

    /**
     * @param imageBlock
     * @return
     */
    @Override
    @Contract(pure = true)
    public
    EnumSet <ESplitKind> chooseDirection ( IImageBlock <A> imageBlock ) {
        return EnumSet.of(VERTICAL);
    }

    /**
     * @return
     */
    @Override
    public
    ICompressedImage <A> getOutputImage () {
        return outputImage;
    }

    /**
     * @return
     */
    @Override
    public
    EtvColorSpace getColorSpace () {
        return colorSpace;
    }

    /**
     *
     */
    @Override
    public
    void segmentImage ( IImageBlock <A> roi, int blockWidth, int blockHeight )
            throws ValueError {

        rangeBlocks = imageBlockGenerator.generateRangeBlocks(
                roi,
                blockWidth,
                blockHeight
        );

        domainBlocks = imageBlockGenerator.generateDomainBlocks(
                roi,
                blockWidth,
                blockHeight);

//        List <IImageBlock <A>> codebookBlocks = imageBlockGenerator.createCodebookBlocks(
//                roi,
//                domainBlocks
//        );
    }

    /**
     * @return
     */
    @Override
    public
    List <IImageBlock <A>> getRangeBlocks () {
        return rangeBlocks;
    }

    /**
     * @return
     */
    @Override
    public
    List <IImageBlock <A>> getDomainBlocks () {
        return domainBlocks;
    }

    /**
     * @param image
     * @return
     * @throws Exception
     */
    @Override
    @Contract("null -> fail")
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
    IImage <A> doEncode ( IImage <A> image ) throws Exception {
        IImageBlock <A> imageBlock = image.getSubImage();
        segmentImage(
                imageBlock,
                rangeSize.getWidth(),
                rangeSize.getHeight());

        iterateRangeBlocks(
                imageBlock,
                rangeBlocks
        );

        return searchProcessor.search();
    }

    /**
     * @throws Exception
     */
    @Override
    public
    void initialize () throws Exception {
        logger.info("Initializing encoder... ");

        Library <A> library = new Library <>();
        EncoderFactory <N, A, G> encoderFactory = new EncoderFactory <>();

        nodeBuilder = nodeBuilderFactory.createBuilder(
                inputImage.getSubImage(),
                getScheme(),
                rangeSize,
                domainSize,
                this,
                library
        );

        Tree <N, A, G> tree = nodeBuilder.buildTree(inputImage.getSubImage());
        library.setTree(tree);
        ITiler <N, A, G> tiler = encoderFactory.createTiler(
                inputImage,
                rangeSize,
                domainSize,
                this,
                nodeBuilder
        );
        IPartitionProcessor <N, A, G> partitionProcessor = this.createPartitionProcessor(tiler);

        imageBlockGenerator = createBlockGenerator(
                partitionProcessor,
                getScheme(),
                this,
                inputImage,
                rangeSize,
                domainSize
        );
    }

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

        return new Image <>(
                dest,
                image.getSize(),
                image.getOriginalImageWidth(),
                image.getOriginalImageHeight());
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
                                                          int step ) throws ValueError {

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
        return inputImage;
    }
}