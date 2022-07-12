package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.io.FractalReader;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.utils.BitBuffer;

import java.io.File;
import java.util.*;

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

    static {
        @SuppressWarnings("raw") final Map <Class <?>, Class <?>> encoderToTilerMap = new HashMap <>();

        encoderToTilerMap.put(BinTreeEncoder.class, BinTreeTiler.class);
        encoderToTilerMap.put(QuadTreeEncoder.class, QuadTreeTiler.class);
    }

    protected ISearchProcessor <N, A, G> searchProcessor;
    protected ITreeNodeBuilder <N, A, G> nodeBuilder;

    protected ITreeNodeBuilderFactory <N, A, G> nodeBuilderFactory;

    protected IImage <A> inputImage;
    protected CompressedImage <A> outputImage;

    protected IImage <A> image;

    protected IIntSize rangeSize;
    protected IIntSize domainSize;

    /**
     * @param scheme
     * @param paramTypes
     * @param scheme1
     * @param scaleTransform
     * @param comparator
     * @param transforms
     * @param filters
     * @param fractalModel
     * @param params
     */
    public
    Encoder ( EPartitionScheme scheme, Class <?>[] paramTypes, Object ... params ) {
        this.scheme = scheme;
        this.scaleTransform = scaleTransform;
        this.comparator = comparator;
        this.transforms = transforms;
        this.filters = filters;
        this.fractalModel = fractalModel;
    }

    public
    Encoder ( IImage <A> image, IIntSize rangeSize, IIntSize domainSize ) {
        this.image = image;
        this.rangeSize = rangeSize;
        this.domainSize = domainSize;
    }

    public
    EPartitionScheme getScheme () {
        return scheme;
    }

    protected EPartitionScheme scheme;
    protected ScaleTransform <A, G> scaleTransform;
    protected ImageBlockGenerator <N, A, G> imageBlockGenerator;
    protected IDistanceator <A> comparator;
    protected Set<ImageTransform <A, G>> transforms;
    protected Set <IImageFilter <A>> filters;

    protected FractalModel <N, A, G> fractalModel;

    protected ITiler <N, A, G> tiler;

    protected final List <RegionOfInterest <A>> roiBlocks = new ArrayList <>();//FIXME HERE??

    /**
     *
     */
    @SuppressWarnings("unchecked")
    protected
    Encoder (
            EPartitionScheme scheme,
            ITreeNodeBuilder <N, A, G> nodeBuilder,
            ISearchProcessor <N, A, G> searchProcessor,
            ScaleTransform <A, G> scaleTransform,
            ImageBlockGenerator <N, A, G> imageBlockGenerator,
            IDistanceator <A> comparator,
            Set <ImageTransform <A, G>> transforms,
            Set <IImageFilter <A>> filters,
            FractalModel <N, A, G> fractalModel
    ) {
        this.scheme = scheme;
        this.nodeBuilder = nodeBuilder;
        this.searchProcessor = searchProcessor;
        this.scaleTransform = scaleTransform;
        this.transforms = transforms;
        this.comparator = comparator;
        this.filters = filters;
        this.fractalModel = fractalModel;

        outputImage = new CompressedImage <>(inputImage);
        this.imageBlockGenerator = imageBlockGenerator;
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
    ImageBlockGenerator <N, A, G> createBlockGenerator ( ITiler <N, A, G> tiler,
                                                         EPartitionScheme scheme,
                                                         IEncoder <N, A, G> encoder,
                                                         IImage <A> image,
                                                         IIntSize rangeSize,
                                                         IIntSize domainSize
    ) {
        return imageBlockGenerator.newInstance();//TODO
    }

    /**
     * @return
     */
    @Override
    public
    ITiler <N, A, G> createTiler0 () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    ITiler <N, A, G> getTiler () {
        return tiler;
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
    void segmentRegion ( RegionOfInterest <A> roi )
            throws ValueError {

        imageBlockGenerator.generateRangeBlocks(roi);
        imageBlockGenerator.generateDomainBlocks(roi);
        imageBlockGenerator.createCodebookBlocks(roi);
    }

    /**
     * @return
     */
    public
    IImage <A> encode ( IImage <A> image ) throws ValueError {
        assert image != null : "Cannot compress null image";
        final List <RegionOfInterest <A>> regions = segmentImage(image);
        List <List <IImageBlock <A>>> list = handleRegionList(regions);

        return searchProcessor.search();
    }

    private
    List <List <IImageBlock <A>>> handleRegionList ( List <RegionOfInterest <A>> regions ) {
        List <List <IImageBlock <A>>> list = new ArrayList <>();
        ImageBlockGenerator <N, A, G> imageBlockGenerator =
                createBlockGenerator(
                        getTiler(),
                        getScheme(),
                        this,
                        getImage(),
                        getTiler().getRangeSize(),
                        getTiler().getDomainSize()
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
            list.add(imageBlockGenerator.generateRangeBlocks(roi));
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
//    @SuppressWarnings("unchecked")
    public
    IImage <A> flipAxis ( IImage <A> image, int axis ) {
        Mat dest = new Mat(image.getMat().nativeObj);
        flip(image.getMat(), dest, axis);

        return new GrayScaleImage <>(dest) {
            /**
             * Sets a sample in the specified band for the pixel located at (x,y)
             * in the DataBuffer using an int for input.
             * ArrayIndexOutOfBoundsException may be thrown if the coordinates are
             * not in bounds.
             *
             * @param address
             * @param b       The band to set.
             * @param s       The input sample as an int.
             * @throws NullPointerException           if data is null.
             * @throws ArrayIndexOutOfBoundsException if the coordinates or
             *                                        the band index are not in bounds.
             */
            @Override
            public
            void setSample ( IAddress <A> address, int b, int s ) {

            }

            /**
             * Returns the sample in a specified band for the pixel located
             * at (x,y) as an int.
             * ArrayIndexOutOfBoundsException may be thrown if the coordinates are
             * not in bounds.
             *
             * @param address
             * @param b       The band to return.
             * @return the sample in a specified band for the specified pixel.
             * @throws NullPointerException           if data is null.
             * @throws ArrayIndexOutOfBoundsException if the coordinates or
             *                                        the band index are not in bounds.
             */
            @Override
            public
            int getSample ( IAddress <A> address, int b ) {
                return 0;
            }

            /**
             * @return
             */
            @Override
            public
            MatOfInt getMat () {
                return null;
            }
        };
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
        double factor = sourceSize; // destination_size
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