package org.stranger2015.opencv.fic.core;

import org.opencv.highgui.HighGui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stranger2015.opencv.fic.core.CodecTask.ColorspaceConversionTask;
import org.stranger2015.opencv.fic.core.codec.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.stranger2015.opencv.fic.core.Library.nearestGreaterPowerOf;

/**
 *
 */
public abstract
class ImageProcessor
        extends BidiTask
        implements IImageProcessor {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private EncodeTask task;
    private EtvColorSpace colorSpace;

    public
    ImageProcessor (
            String filename,
            EPartitionScheme scheme,
            ICodec codec
//                     ITreeNodeBuilder<?> nodeBuilder,
//                     IPartitionProcessor partitionProcessor,
//                     ISearchProcessor searchProcessor,
//                     ScaleTransform scaleTransform,
//                     ImageBlockGenerator<?> imageBlockGenerator,
//                     IDistanceator comparator,
//                     Set<ImageTransform> imageTransforms,
//                     Set<IImageFilter> imageFilters,
//                     FCImageModel fractalModel,

            /*IEncoder[] encoders */ ) {

        super(filename,
                scheme,
                codec
        );
    }

    /**
     * @return
     */
    public
    List <IImageProcessorListener> getListeners () {
        return listeners;
    }

    /**
     *
     */
    protected final List <IImageProcessorListener> listeners = new ArrayList <>();

    /**
     * @param filename
     * @param scheme
     * @param codec
     * @param colorSpace
     */
    protected
    ImageProcessor ( String filename,
                     EPartitionScheme scheme,
                     ICodec codec,
                     List <Task> tasks,
                     EtvColorSpace colorSpace ) {

        super(filename, scheme, codec, tasks);

        this.scheme = scheme;
        this.codec = codec;

        BidiTask task1 = new NormalizeRestoreImageTask <>(filename, scheme, codec);
        BidiTask task2 = new ColorspaceConversionTask <>(
                filename,
                scheme,
                codec,
                colorSpace
        );

        final List <Task> preprocTasks = new ArrayList <>(2);

        preprocTasks.add(task1.getTask());
        preprocTasks.add(task2.getTask());

        final List <Task> postprocTasks = new ArrayList <>(2);

        postprocTasks.add(task1.getInverseTask());
        postprocTasks.add(task2.getInverseTask());

    }

    /**
     * 1. Divide the image into range blocks.
     * <p>
     * 2. Divide  the  image  into  non-overlapping  domain  blocks,  Di.
     * <p>
     * The  union  of  the  domain  blocks IImage must  cover  the  entire
     * image, G, but they can be any size or shape [1].
     * <p>
     * 3. Define  a  finite  set  of  contractive  affine  transformations,  wi
     * (which IImage ap from a range block R to a domain block Di).
     * <p>
     * 4.
     * For each domain block {
     * ----->For each range block {
     * ----------> For each transformation {
     * -------------------> Calculate the Hausdorff distance h(wi(R  G), Di  G) (or use another IImage metrics )
     * -------------------> }
     * ----------> }
     * }
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
    IImage process ( IImage image ) throws Exception {
        ICompressedImage cimg;
        IEncoder encoder = codec.getEncoder();
        List <IImage> list = image.split();
        List <IImage> layers = new ArrayList <>();
        for (IImage imageBlock : list) {
            encoder.encode(imageBlock);
            for (IImageProcessorListener listener : listeners) {
                listener.onProcess(this, imageBlock);
            }
            imageBlock = postprocess(imageBlock);
            layers.add(imageBlock);
        }
        image.merge(layers, image);
        cimg = composeImage(image);

        return saveImage(filename, (IImage) cimg);
    }

    private
    ICompressedImage composeImage ( IImage image ) {
        return new CompressedImage(image);
    }

//    /**
//     * @param image
//     * @return
//     * @throws ValueError
//     */
//    @Contract("_ -> new")
//    private
//    FCImageModel  composeImage (IImage image ) throws ValueError, IOException {
//        return new FCImageModel (image);
//    }

    /**
     * @param outputImage
     * @return
     */    @Override
    public
    IImage postprocess ( IImage outputImage ) {
        return outputImage;
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
     * @param image
     */
    protected
    ICompressedImage compressImage ( IImage image ) throws Exception {
        image = process(image);
        ICompressedImage imageOut = new CompressedImage(image);
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
    ICodec getCodec () {
        return codec;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    public
    IImage preprocess ( String filename ) {

        return null;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    public
    IImage preprocess ( String filename, IImage image ) throws ValueError {
        if (filename == null) {
            return preprocess(image);
        }
        if (image != null) {
            image = this.inputImage == null ?
                    task.loadImage(filename) :
                    image;

            for (IImageProcessorListener listener : listeners) {
                listener.onPreprocess(this, filename, image);
            }
        }

        return image;
    }

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
    EncodeTask getAction () {
        return task;
    }

    /**
     * @param filename
     * @return
     */
    @SuppressWarnings("unchecked")
    public
    IImage execute ( String filename ) throws Exception {
        super.execute(filename);
        IImage image = preprocess(filename);

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
    <V> Function <V, IImage> compose ( /*@NotNull*/ Function <? super V, ? extends String> before ) {
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
    void setImage ( IImage image ) {
        this.inputImage = image;
    }

    /**
     * @param task
     */
    public
    void setTask ( EncodeTask task ) {
        this.task = task;
    }

    /**
     * @param codec
     */
    @Override
    public
    void onCreated ( ICodec codec ) {
        super.onCreated(codec);
    }

    public
    void setColorSpace ( EtvColorSpace colorSpace ) {
        this.colorSpace = colorSpace;
    }
}