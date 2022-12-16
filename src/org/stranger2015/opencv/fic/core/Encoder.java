package org.stranger2015.opencv.fic.core;

//import org.jetbrains.annotations.Contract;
//import org.jetbrains.annotations.NotNull;

import org.opencv.core.Mat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.io.FractalReader;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;

import java.io.IOException;
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
 *
 */
public abstract
class Encoder extends ImageProcessor
        implements IEncoder {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final List <Class <ITiler>> allowableSubtilers = new ArrayList <>();
    protected Class <? extends ITiler> tilerClass;

    protected String fileName;
    protected List <Task> tasks;

    protected EtvColorSpace colorSpace;
    protected ICodec codec;

    protected EPartitionScheme scheme;
    protected ScaleTransform scaleTransform;
    protected ImageBlockGenerator <?> imageBlockGenerator;
    protected IDistanceator comparator;
    protected Set <ImageTransform> transforms;
    protected Set <IImageFilter> filters;

    protected FCImageModel fractalModel;
    protected IPartitionProcessor partitionProcessor;

    protected ISearchProcessor searchProcessor;
    protected ITreeNodeBuilder <?> nodeBuilder;

    protected ITreeNodeBuilderFactory <?> nodeBuilderFactory;

    protected IImage inputImage;
    protected CompressedImage outputImage;

    List <IImageBlock> rangeBlocks;
    List <IImageBlock> domainBlocks;

    protected IIntSize rangeSize;

    protected IIntSize domainSize;

    /**
     *
     */
    protected
    Encoder (
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
        super(fileName, scheme, codec, tasks, colorSpace);

        this.nodeBuilder = nodeBuilder;
        this.partitionProcessor = partitionProcessor;
        this.searchProcessor = searchProcessor;
        this.scaleTransform = scaleTransform;
        this.transforms = imageTransforms;
        this.comparator = comparator;
        this.filters = imageFilters;
        this.fractalModel = fractalModel;

        outputImage = new CompressedImage(inputImage);
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
     * @param
     * @param <G>
     * @return
     * @throws ReflectiveOperationException
     */
    @SuppressWarnings("unchecked")
    public static

    /*@NotNull*/
    IEncoder create (
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
            FCImageModel fractalModel )

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

        return (IEncoder) ctor.newInstance(
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
    Set <IImageFilter> getFilters () {
        return filters;
    }

    /**
     * @return
     */
    @Override
    public
    List <Class <ITiler>> getAllowableSubtilers () {
        return allowableSubtilers;
    }

    /**
     * @param tilerClass
     */
    @Override
    public
    void addAllowableSubtiler ( Class <ITiler> tilerClass ) {

    }

    /**
     * @return
     */
    @Override
    public
    Class <? extends ITiler> getTilerClass () {
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
    IImage getInputImage () {
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
    ImageBlockGenerator <?> createBlockGenerator (
            IPartitionProcessor partitionProcessor,
            EPartitionScheme scheme,
            IEncoder encoder,
            IImage image,
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
    IPartitionProcessor doCreatePartitionProcessor ( ITiler tiler );

    /**
     * @return
     */
    @Override
    public
    IPartitionProcessor getPartitionProcessor () {
        return partitionProcessor;
    }

    /**
     * @return
     */
    @Override
    public
    FCImageModel getModel () {
        return fractalModel;
    }

    /**
     * @param filename
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    FCImageModel loadModel ( String filename ) throws ValueError, IOException {
        return new FractalReader(filename).readModel();
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
    void addLeafNode ( LeafNode <?> node ) {

    }

    /**
     * @param imageBlock
     * @return
     */
    @Override
    public
    EnumSet <ESplitKind> chooseDirection ( IImageBlock imageBlock ) {
        return EnumSet.of(VERTICAL);
    }

    /**
     * @return
     */
    @Override
    public
    ICompressedImage getOutputImage () {
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
    void segmentImage ( IImageBlock roi, int blockWidth, int blockHeight )
            throws ValueError {

        rangeBlocks = imageBlockGenerator.generateRangeBlocks(
                roi,
                blockWidth,
                blockHeight
        );

        domainBlocks = imageBlockGenerator.generateDomainBlocks(
                roi,
                blockWidth,
                blockHeight
        );
    }

    /**
     * @return
     */
    @Override
    public
    List <IImageBlock> getRangeBlocks () {
        return rangeBlocks;
    }

    /**
     * @return
     */
    @Override
    public
    List <IImageBlock> getDomainBlocks () {
        return domainBlocks;
    }

    /**
     *
     */
    @Override
    public
    void doEncode ( IImage image ) throws Exception {
        IImageBlock imageBlock = image.getSubImage();
        segmentImage(imageBlock, rangeSize.getWidth(), rangeSize.getHeight());
        byte[] bytes = searchProcessor.search(imageBlock, rangeBlocks);
        fractalModel = new FCImageModel(bytes);
    }

    /**
     * @throws Exception
     */
    @Override
    public
    void initialize () throws Exception {
        logger.info("Initializing encoder... ");

        Library library = new Library();
        EncoderFactory encoderFactory = new EncoderFactory();

        nodeBuilder = nodeBuilderFactory.createBuilder(
                inputImage.getSubImage(),
                getScheme(),
                rangeSize,
                domainSize,
                this,
                library
        );

        Tree <?> tree = nodeBuilder.buildTree(inputImage.getSubImage());
        library.setTree(tree);
        ITiler tiler = encoderFactory.createTiler(
                inputImage,
                rangeSize,
                domainSize,
                this,
                nodeBuilder
        );
        IPartitionProcessor partitionProcessor = this.createPartitionProcessor(tiler);

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
    Set <ImageTransform> getTransforms () {
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
    IImage flipAxis ( IImage image, int axis ) {
        Mat dest = new Mat();
        flip(image.getMat(), dest, axis);

        return new Image(
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
    IImage randomTransform ( IImage image, ImageTransform transform ) {
        return image;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage applyTransform ( IImage image, ImageTransform transform ) {
        return image;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage applyAffineTransform ( IImage image, AffineTransform transform ) {
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
    List <IImageBlock> generateAllTransformedBlocks ( IImage image,
                                                      int sourceSize,
                                                      int destinationSize,
                                                      int step ) throws ValueError {

        final List <IImageBlock> transformedBlocks = new ArrayList <>();
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
    ImageBlockGenerator <?> getImageBlockGenerator () {
        return imageBlockGenerator;
    }

    /**
     * @return
     */
    public
    ScaleTransform getScaleTransform () {
        return scaleTransform;
    }

    /**
     * @return
     */
    public
    IDistanceator getComparator () {
        return comparator;
    }

    /**
     * @param outputImage
     * @return
     */
    @Override
    public
    IImage postprocess ( IImage outputImage ) {
        return null;
    }//fixme

    /**
     * @return
     */
    public
    IImage getImage () {
        return inputImage;
    }

    public
    void setFractalModel ( FCImageModel fractalModel ) {
        this.fractalModel = fractalModel;
    }
}