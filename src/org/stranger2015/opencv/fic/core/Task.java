package org.stranger2015.opencv.fic.core;

import org.opencv.imgcodecs.Imgcodecs;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.fic.utils.ColorImage;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.fic.utils.SipLibrary;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.util.stream.IntStream.range;
import static org.stranger2015.opencv.fic.core.EPartitionScheme.SIP;

/**
 *
 */
public abstract
class Task<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        implements Function <String, IImage<A>>,
                   IImageProcessorListener <N, A, G>,
                   ICodecListener <N, A, G> {

    protected final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

    protected final List <Task <N, A, G>> tasks = new ArrayList <>();

    protected final String filename;
    protected IImage<A> inputImage;
    protected List <GrayScaleImage <A>> layers = new ArrayList <>(4);
    protected EPartitionScheme scheme;
    protected IImageProcessor <N, A, G> processor;
    protected ICodec <N, A, G> codec;
    protected CompressedImage <A> outputImage;

    /**
     * @param filename
     * @param scheme
     * @param tasks
     */
    public
    Task ( String filename,
           EPartitionScheme scheme,
           ICodec <N, A, G> codec,
           List <Task <N, A, G>> tasks ) {

        this.filename = filename;
        this.scheme = scheme;
        this.codec = codec;
        this.tasks.addAll(tasks);
    }

    /**
     * @return
     */
    public
    List <IImageProcessorListener <N, A, G>> getImageProcessorListeners () {
        return processorListeners;
    }

    private final List <IImageProcessorListener <N, A, G>> processorListeners = new ArrayList <>();

    /**
     * @return
     */
    public
    List <ICodecListener <N, A, G>> getCodecListeners () {
        return codecListeners;
    }

    private final List <ICodecListener <N, A, G>> codecListeners = new ArrayList <>();

    /**
     * @return
     */
    public
    String getFilename () {
        return filename;
    }

    /**
     * @param task
     */
    protected
    void addTask ( Task <N, A, G> task ) {
        tasks.add(task);
    }

    /**
     * @param fn
     * @return
     */
    @SuppressWarnings("unchecked")
    final public
    IImage<A> loadImage ( String fn ) {
        if (inputImage == null) {
            return new ColorImage <A>(Imgcodecs.imread(fn));
        }

        return inputImage;
    }

    /**
     * @param fn
     * @return
     * @throws ValueError
     */
    @SuppressWarnings("unchecked")
    final public
    IImage<A> loadSipImage ( String fn ) throws ValueError {
        IImage<A> image = loadImage(fn);
        SipLibrary <A> sipLib = new SipLibrary <>();
        SipTreeNodeBuilder <N, A, G> builder = new SipTreeNodeBuilder <>(image);

        return sipLib.convertImageToSipImage(builder.buildTree(new SipImageBlock<A>(image.getMat())));
    }

    /**
     * @param fn
     * @param image
     */
    public final
    CompressedImage <A> saveImage ( String fn, CompressedImage <A> image ) {
        Imgcodecs.imwrite(fn, image.getMat());

        return image;
    }

    /**
     * Performs this operation on the given argument.
     *
     * @param filename the input argument
     * @return
     */
    @Override
    public final
    IImage<A> apply ( String filename ) {
        logger.info("Executing task " + getClass());
        try {
            inputImage = ((scheme == SIP) ? loadSipImage(filename) : loadImage(filename));

            return execute(filename);

        } catch (ValueError valueError) {
            valueError.printStackTrace();
            System.exit(-1);
        }

        return null;
    }

    /**
     * @param filename
     * @return
     */
    protected
    IImage<A> execute ( String filename ) throws ValueError {
        IImage<A> image = null;
        for (Task <N, A, G> task : tasks) {
            image = task.execute(filename);
        }
        //todo pre-, post-??
        int bound = getImageProcessorListeners().size();
        for (int i = 0; i < bound; i++) {
            getImageProcessorListeners().get(i).onPreprocess(processor, filename, null);
            getImageProcessorListeners().get(i).onProcess(processor, inputImage);
            getImageProcessorListeners().get(i).onPostprocess(processor, outputImage);
        }

        range(0, getCodecListeners().size())
                .forEach(j -> getCodecListeners().get(j).onCreated(codec));

        return image;
    }

    /**
     *
     */
    @Override
    public
    void onPreprocess ( IImageProcessor <N, A, G> processor, String filename, IImage<A> image) throws ValueError {
        logger.info("On preprocessing \n");

    }

    /**
     *
     */
    @Override
    public
    void onProcess ( IImageProcessor <N, A, G> processor, IImage<A> inputImage ) {
        logger.info("On processing \n");

    }

    /**
     *
     */
    @Override
    public
    void onPostprocess ( IImageProcessor <N, A, G> processor, CompressedImage <A> outputImage ) {
        logger.info("On postprocessing \n");

    }

    /**
     * @param codec
     */
    @Override
    public
    void onCreated ( ICodec <N, A, G> codec ) {
        logger.info(format("On codec created '%s'\n", codec));
    }
}
