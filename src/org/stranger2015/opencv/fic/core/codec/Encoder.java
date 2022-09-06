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

    /**
     * @return
     */
    public
    Logger getLogger () {
        return logger;
    }

    protected ISearchProcessor <N, A, G> searchProcessor;
    protected ITreeNodeBuilder <N, A, G> nodeBuilder;

    protected ITreeNodeBuilderFactory <N, A, G> nodeBuilderFactory;

    protected IImage <A> inputImage;
    protected CompressedImage <A> outputImage;

    protected IImage <A> image;

    protected IIntSize rangeSize;
    protected IIntSize domainSize;

//    /**
//     * @param scheme1
//     * @param scaleTransform
//     * @param comparator
//     * @param transforms
//     * @param filters
//     * @param fractalModel
//     * @param scheme
//     * @param params
//     */
//    @SuppressWarnings("unchecked")
//    public
//    Encoder ( EPartitionScheme scheme/**/, Object... params ) {
//        this(scheme,
//                (ITreeNodeBuilder <N, A, G>) params[0],
//                (ISearchProcessor <N, A, G>) params[1],
//                (ScaleTransform <A, G>) params[2],
//                (ImageBlockGenerator <N, A, G>) params[3],
//                (IDistanceator <A>) params[4],
//                (Set <ImageTransform <A, G>>) params[5],
//                (Set <IImageFilter <A>>) params[6],
//                (FractalModel <N, A, G>) params[7]
//        );
//    }

//    /**
//     * @param image
//     * @param rangeSize
//     * @param domainSize
//     */
//    public
//    Encoder ( IImage <A> image, IIntSize rangeSize, IIntSize domainSize ) {
//        this.image = image;
//        this.rangeSize = rangeSize;
//        this.domainSize = domainSize;
//    }

    public
    EPartitionScheme getScheme () {
        return scheme;
    }

    protected EPartitionScheme scheme;
    protected ScaleTransform <A, G> scaleTransform;
    protected ImageBlockGenerator <N, A, G> imageBlockGenerator;
    protected IDistanceator <A> comparator;
    protected Set <ImageTransform <A, G>> transforms;
    protected Set <IImageFilter <A>> filters;

    protected FractalModel <N, A, G> fractalModel;
    protected IPartitionProcessor <N, A, G> partitionProcessor;
    protected final List <RegionOfInterest <A>> roiBlocks = new ArrayList <>();

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
            FractalModel <N, A, G> fractalModel
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
                domainSize);//TODO
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
    FractalModel <N, A, G> getModel () {
        return fractalModel;
    }

    /**
     * @param filename
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    FractalModel <N, A, G> loadModel ( String filename ) {
        return (FractalModel <N, A, G>) new FractalReader <>(new File(filename)).readModel();
    }

    /**
     * @param imageBlock
     * @return
     */
    @Contract(pure = true)
    private
    ESplitKind chooseDirection ( IImageBlock <A> imageBlock ) {
        return VERTICAL;//fixme
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

        List <IImageBlock <A>> rangeBlocks = imageBlockGenerator.generateRangeBlocks(
                roi,
                blockWidth,
                blockHeight);

        List <IImageBlock <A>> domainBlocks = imageBlockGenerator.generateDomainBlocks(
                roi,
                rangeBlocks);

        List <IImageBlock <A>> codeBookBlocks = imageBlockGenerator.createCodebookBlocks(
                roi,
                domainBlocks);
    }

    /**
     * @return
     */
    public
    IImage <A> encode ( IImage <A> image ) throws ValueError {
        assert image != null : "Cannot compress null image";

        final List <RegionOfInterest <A>> regions = segmentImage(image);
        List <List <IImageBlock <A>>> list = handleRegionList(regions, rangeSize, domainSize);

        return searchProcessor.search();
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
        ImageBlockGenerator <N, A, G> imageBlockGenerator =
                createBlockGenerator(
                        getPartitionProcessor(),
                        getScheme(),
                        this,
                        getImage(),
                        rangeSize,
                        domainSize
                );
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
//    IImage <A> iterateRangeBlocks ( IImage <A> image ) {
//        rangeBlocks.forEach(rangeBlock -> {
//            int percent = 100 * (rangeBlocks.indexOf(rangeBlock) + 1) / rangeBlocks.size();
//            System.err.printf("%d%%", percent);
//            ImageTransform <A, G> bestTransform = ImageTransform.create(image, rangeBlock.getAddress());
//            int minDistance = Integer.MAX_VALUE;
//            try {
//                iterateDomainBlocks(rangeBlock, bestTransform, minDistance);
//            } catch (ValueError e) {
//                e.printStackTrace();
//            }
//            getTransforms().add(bestTransform);
//        });
//        outputImage.getTransforms().addAll(transforms);
//
//        return outputImage;
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
//                    int[] data = new int[4];//fixme
//                    alpha += (domainBlock.get(i, j, data) - domainBlock.meanPixelValue);
//                }
//            }
//
//            for (int addr = 0; addr < domainBlock.getSideSize(); addr++) {
//                int[] data = new int[4];//fixme
//                alpha += (domainBlock.get(domainBlock.getAddress().newInstance(addr), data) - domainBlock.meanPixelValue);
//            }

//            double contrast = alpha / domainBlock.beta;//byte
//            int brightness = (int) (rangeBlock.meanPixelValue - contrast * domainBlock.meanPixelValue);
//
//            for (int index = 0; index < 8; index++) {
//                ImageBlock <A> transformedDomainBlock =
//                        new ImageBlock <A>(
//                                inputImage,
//                                domainBlock.getSideSize(),
//                                domainBlock.getAddress()
//                        );
//                int pixelAmount = domainBlock.getSideSize() * domainBlock.getSideSize();
//                for (int addr = 0; addr < pixelAmount; addr++) {
//                    for (int y = 0; y < domainBlock.height; y++) {
//                    int newAddr = addr * -1; // fixme dihedralAffineTransforms[index][0] + y * dihedralAffineTransforms[index][1];
//                        int newY = x * dihedralAffineTransforms[index][2] + y * dihedralAffineTransforms[index][3];
//                    if (newAddr < 0) {
//                        newAddr += domainBlock.getSideSize();
//                    }
//                        if (newY < 0) {
//                            newY += domainBlock.height;
//                        }
//                    int newPixelValue = (short) (contrast * domainBlock.put(addr, new int[1]) + brightness);
//                    transformedDomainBlock.put(
//                            newAddr,
//                            new int[]{
//                                    ((newPixelValue < MAX_PIXEL_VALUE) && (newPixelValue >= 0)) ?
//                                            newPixelValue :
//                                            (MAX_PIXEL_VALUE / 2)
//                            }//Originally was double
//                    );
////                    }
//                }
//                int distance = getDistance(rangeBlock, transformedDomainBlock);
//                if ((distance < minDistance) && (distance != 0)) {
//                    minDistance = distance;
//                    bestTransform.dihedralAffineTransformIndex = index;
//                    bestTransform.setAddress(transformedDomainBlock.getAddress());
//                    bestTransform.brightnessOffset = brightness;
//                    bestTransform.contrastScale = contrast;
//                }
//            }
//        }
//    }

    /**
     * @param range
     * @param domain
     * @return
     */
    public
    int getDistance ( IImageBlock <A> range, IImageBlock <A> domain ) {
        double error;
        int boundOuter = range.getWidth();
        error = range(0, boundOuter)
                .map(i -> range.getHeight())
                .flatMap(bound -> range(0, bound)).mapToDouble(j -> Math
                        .pow(range.getMeanPixelValue() - domain.getMeanPixelValue(), 2)).sum();
        ;
        error = error / (double) (range.getWidth() * range.getHeight());

        return (int) error;
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
        Mat dest = new Mat(image.getMat().nativeObj);
        flip(image.getMat(), dest, axis);

        return new Image <>(dest);
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