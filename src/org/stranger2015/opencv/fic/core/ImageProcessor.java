package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.stranger2015.opencv.fic.core.Library.nearestGreaterPowerOf;

/**
 *
 */
public
class
ImageProcessor<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>

        extends BidiTask <N, A, G>
        implements IImageProcessor <N, A, G> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final ICodec <N, A, G> codec;
    private final EPartitionScheme scheme;
    private final EtvColorSpace colorSpace;

    private EncodeTask <N, A, G> task;

    /**
     * @return
     */
    public
    List <IImageProcessorListener <N, A, G>> getListeners () {
        return listeners;
    }

    /**
     *
     */
    private final List <IImageProcessorListener <N, A, G>> listeners = new ArrayList <>();

    /**
     * @param filename
     * @param scheme
     * @param codec
     */
    public
    ImageProcessor ( String filename,
                     EPartitionScheme scheme,
                     ICodec <N, A, G> codec,
                     List <Task <N, A, G>> tasks,
                     EtvColorSpace colorSpace
    ) {

        super(filename, scheme, codec, tasks);

        this.scheme = scheme;
        this.codec = codec;
        this.colorSpace = colorSpace;

        BidiTask <N, A, G> task1 = new NormalizeRestoreImageTask <>(filename, scheme, codec);
        BidiTask <N, A, G> task2 = new ColorspaceConversionTask <>(
                filename,
                scheme,
                codec,
                colorSpace
        );

        final List <Task <N, A, G>> preprocTasks = new ArrayList <>(2);

        preprocTasks.add(task1.getTask());
        preprocTasks.add(task2.getTask());

        final List <Task <N, A, G>> postprocTasks = new ArrayList <>(2);

        postprocTasks.add(task1.getInverseTask());
        postprocTasks.add(task2.getInverseTask());

//        Codec.create()
//        codec = new DefaultCodec<>(scheme, new EncodeAction(filename), getFilename());
    }

    /**
     * @param <N>
     
     * @param <A>
     * @param filename
     * @return
     */
    public static
    <N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */, G extends BitBuffer>

    ImageProcessor <N, A, G> create ( String filename,
                                         EPartitionScheme scheme,
                                         ICodec <N, A, G> codec,
                                         List <Task <N, A, G>> tasks,
                                         EtvColorSpace colorSpace ) {

        return new ImageProcessor <>(
                filename,
                scheme,
                codec,
                tasks,
                colorSpace
        );
    }

    /**
     * 1. Divide the image into range block.
     * <p>
     * 2. Divide  the  image  into  non-overlapping  domain  blocks,  Di.
     * <p>
     * The  union  of  the  domain  blocks  must  cover  the  entire
     * image, G, but they can be any size or shape [1].
     * <p>
     * 3. Define  a  finite  set  of  contractive  affine  transformations,  wi
     * (which map from a range block R to a domain block Di).
     * <p>
     * 4. For each domain block {
     * <p>
     * For each range block {
     * For each transformation {
     * Calculate the Hausdorff distance h(wi(R  G), Di  G) (or use another metric)
     * }
     * }
     * }
     * <p>
     * 5. Record the transformed domain block which is found to be the best approximation for
     * the current range block is assigned to that range block.
     * <p>
     * 6. Next domain block [1].
     *
     * @param image
     * @return
     */
    @SuppressWarnings("unchecked")
    public
    IImage <A> process ( IImage<A> image ) throws ValueError {
        CompressedImage <A> cimg = new CompressedImage <>(image);
        IEncoder <N, A, G> encoder = codec.getEncoder();
        List <RegionOfInterest <A>> regions = image.getRegions();
        for (RegionOfInterest <A> region : regions) {
            List <GrayScaleImage <A>> list = region.split();
            List <GrayScaleImage <A>> layers = new ArrayList <>();

            for (GrayScaleImage <A> grayScaleImage : list) {
                GrayScaleImage <A> layer = encoder.encode(grayScaleImage);
                for (IImageProcessorListener <N, A, G> listener : listeners) {
                    listener.onProcess(this, layer);
                }
                layer = postprocess((CompressedImage <A>) layer);
                layers.add(layer);
            }
            regions = (List <RegionOfInterest <A>>) region.merge(layers, region);
            cimg = composeImage(regions, image);

        }

        return saveImage(filename, cimg);
    }

    private
    CompressedImage <A> composeImage ( List <RegionOfInterest <A>> regions, IImage <A> image ) {
//        adjustROI();
        return new CompressedImage <>(image);
    }

    /**
     * @param outputImage
     * @return
     */
    @Override
    public
    CompressedImage <A> postprocess ( CompressedImage <A> outputImage ) {
//        outputImage=super./postprocess(outputImage);


        return outputImage;
    }

    /**
     * @return
     */
    @Override
    public
    IImage<A> getImage () {
        return inputImage;
    }

    /**
     * @param image
     */
    @SuppressWarnings("unchecked")
    protected
    M compressImage ( M image ) {

        M imageOut = (M) new CompressedImage <>(image);

        HighGui.namedWindow("OpenCV");

        System.out.println(imageOut.dump());

        HighGui.destroyAllWindows();

        image.release();
        imageOut.release();

        return imageOut;
    }

    /**
     * @return
     */
    public
    EPartitionScheme getScheme () {
        return scheme;
    }

    /**
     * @return
     */
    @Override
    public
    ICodec <N, A, G> getCodec () {
        return codec;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    public
    M preprocess ( String filename ) {

        return null;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    public
    IImage<A> preprocess ( String filename, IImage<A> image ) throws ValueError {
        if (filename == null) {
            return preprocess(image);
        }
        if (image != null) {
            image = this.inputImage == null ?
                    task.loadImage(filename) :
                    image;
            for (IImageProcessorListener <N, A, G> listener : listeners) {
                listener.onPreprocess(this, filename, image);
            }
        }

        return image;
    }

//    /**
//     *        IntSize size = image.getSize();
//     *         int w = size.getWidth();
//     *         int h = size.getHeight();
//     *         int radix = image.getAddress().radix();
//     *         size = adjustSize(radix, w, h);
//     *
//     *         return (M) new GrayScaleImage <>(image.getMat(), size);
//     * @param inputImage
//     * @return
//     */
//    @Override
//    @SuppressWarnings("unchecked")
//    public
//    M preprocess ( M inputImage ) throws ValueError {
////        IntSize size = adjustSize(
////                inputImage.getAddress().radix(),
////                inputImage.getWidth(),
////                inputImage.getHeight()
////        );
////        //image or block or roi
////        M destImage = (M) new GrayScaleImage <A>(inputImage.getMat());
////        Imgproc.resize(
////                inputImage.getMat(),
////                destImage.getMat(),
////                new Size(size.getWidth(), size.getHeight()),
////                1.0 * size.getWidth() / size.originalImageWidth,
////                1.0 * size.getHeight() / size.originalImageHeight
////        );
////
////        return destImage;
//    }

    /**
     * @param base
     * @param w
     * @param h
     * @return
     */
    @Override
    public
    IntSize adjustSize ( int base, int w, int h ) {
        int newW = nearestGreaterPowerOf(base, w);
        int newH = nearestGreaterPowerOf(base, h);

        IntSize size;
        if (newW == newH) {
            size = new IntSize(newW, newH, w, h);
        }
        else if (newW > newH) {
            size = new IntSize(newW, newW / 2, w, h);
        }
        else {
            size = new IntSize(newH / 2, newH, w, h);
        }

        return size;
    }

    /**
     * @return
     */
    public
    EncodeTask <N, A, G> getAction () {
        return task;
    }

    /**
     * @param filename
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    protected
    IImage<A> execute ( String filename ) throws ValueError {

        super.execute(filename);

        IImage<A> image;
        image = preprocess(filename);

        return process(image);
    }

    /**
     * Returns a composed function that first applies the {@code before}
     * function to its input, and then applies this function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param before the function to apply before this function is applied
     * @return a composed function that first applies the {@code before}
     * function and then applies this function
     * @throws NullPointerException if before is null
     * @see #andThen(Function)
     */
    @Override
    public
    <V> Function <V, IImage <A>> compose ( @NotNull Function <? super V, ? extends String> before ) {
        return super.compose(before);
    }

    /**
     * @return
     */
    public
    EtvColorSpace getColorSpace () {
        return colorSpace;
    }

    /**
     * @param image
     */
    public
    void setImage ( IImage<A> image ) {
        this.inputImage = image;
    }

    /**
     * @param task
     */
    public
    void setTask ( EncodeTask <N, A, G> task ) {
        this.task = task;
    }

    /**
     * @param codec
     */
    @Override
    public
    void onCreated ( ICodec <N, A, G> codec ) {
        super.onCreated(codec);
    }
}