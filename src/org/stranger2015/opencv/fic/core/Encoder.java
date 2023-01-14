package org.stranger2015.opencv.fic.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.codec.tilers.Pool;
import org.stranger2015.opencv.fic.core.io.FractalWriter;
import org.stranger2015.opencv.fic.core.search.EMetrics;
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

import static org.stranger2015.opencv.fic.core.codec.ESplitKind.VERTICAL;
import static org.stranger2015.opencv.fic.core.codec.tilers.Pool.incUsageCount;

/**
 *
 */
public abstract
class Encoder extends ImageProcessor implements IEncoder {
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

    protected Pool <IImageBlock> rangeBlocks;
    protected Pool <IImageBlock> domainBlocks;

    protected IIntSize rangeSize;

    protected IIntSize domainSize;

    protected double threshold = 0.2;

    protected int currRangeIdx;
    protected int currDomainIdx;
    /**
     *
     */
    private EMetrics eMetrics;

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
            FCImageModel fractalModel ) {

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
                domainSize
        );

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

    public abstract
    IImageBlock randomTransform ( IImageBlock image, ImageTransform transform );

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public abstract
    IImageBlock applyAffineTransform ( IImageBlock image, AffineTransform transform );

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
    EMetrics getMetrics () {
        return eMetrics;
    }

    public abstract
    void addLeafNode ( TreeNodeBase <?> node );

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
    void saveModel ( String filename, FCImageModel model ) throws Exception {
        new FractalWriter(filename, model).writeModel();
    }

    public abstract
    IPartitionProcessor createPartitionProcessor0 ( ITiler tiler );

    public abstract
    IImageBlock flipAxis ( IImageBlock image, int axis ) throws ValueError;

    public abstract
    List <IImageBlock> segmentImage ( IImage image, List <Rectangle> bounds ) throws ValueError;

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
    void segmentImage ( IImageBlock roi, int blockWidth, int blockHeight ) throws ValueError, IOException {

        rangeBlocks = imageBlockGenerator.generateRangeBlocks(
                roi,
                blockWidth,
                blockHeight
        );

        domainBlocks = imageBlockGenerator.generateDomainBlocks(
                roi,
                blockWidth * 2,
                blockHeight * 2
        );

        partitionProcessor.classify(rangeBlocks, domainBlocks);
    }

    /**
     * @param tiler
     * @param imageBlockGenerator
     * @param nodeBuilder
     * @return
     */
    public abstract
    IPartitionProcessor doCreatePartitionProcessor ( ITiler tiler,
                                                     ImageBlockGenerator <?> imageBlockGenerator,
                                                     ITreeNodeBuilder <?> nodeBuilder );

    /**
     * @param rangeBlock
     * @param domainBlocks
     * @return
     */
    @Override
    public
    IImageBlock selectDomainBlock ( IImageBlock rangeBlock, Pool <IImageBlock> domainBlocks ) {
        List <IImageBlock> fp = domainBlocks.getFilteredPool(rangeBlock);
        IImageBlock block = fp.get(currDomainIdx++);
        incUsageCount(fp, block);

        return block;
    }

    /**
     * @return
     */
    @Override
    public
    Pool <IImageBlock> getRangeBlocks () {
        return rangeBlocks;
    }

    /**
     * @return
     */
    @Override
    public
    Pool <IImageBlock> getDomainBlocks () {
        return domainBlocks;
    }

    /**
     * @return
     */
    @Override
    public
    IImage doEncode ( IImage image ) throws Exception {
        IImageBlock imageBlock = image.getSubImage();
        segmentImage(imageBlock, rangeSize.getWidth(), rangeSize.getHeight());
        byte[] bytes = searchProcessor.search(imageBlock, rangeBlocks);
        fractalModel = new FCImageModel(bytes);

        return image;
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
     * @param image
     * @param bounds
     * @return
     * @throws ValueError
     */
    public abstract
    List <IImageBlock> segmentImage ( IImageBlock image, List <Rectangle> bounds )
            throws ValueError;

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImageBlock applyTransform ( IImageBlock image, ImageTransform transform ) {
        return image;
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
    }

    /**
     * @return
     */
    @Override
    public
    IImage getImage () {
        return inputImage;
    }

    /**
     * @param fractalModel
     */
    public
    void setFractalModel ( FCImageModel fractalModel ) {
        this.fractalModel = fractalModel;
    }

    /**
     * @param eMetrics
     */
    @Override
    public
    void setEMetrics ( EMetrics eMetrics ) {
        this.eMetrics = eMetrics;
    }

    /**
     * @return
     */
    public abstract
    IImage getInput ();

    /**
     * @return
     */
    public abstract
    IImage getOutput ();
}