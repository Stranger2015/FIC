package org.stranger2015.opencv.fic.core;

import org.opencv.imgcodecs.Imgcodecs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.fic.core.codec.ICodecListener;
import org.stranger2015.opencv.fic.core.codec.ICompressedImage;
import org.stranger2015.opencv.fic.core.codec.IImageProcessorListener;
import org.stranger2015.opencv.fic.utils.ColorImage;
import org.stranger2015.opencv.fic.utils.SipLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.stranger2015.opencv.fic.core.EPartitionScheme.SIP;

/**
 *
 */
public abstract
class Task implements Function <String, IImage>,
                      IImageProcessorListener,
                      ICodecListener {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final List <Task> tasks = new ArrayList <>();

    protected final String filename;
    protected IImage inputImage;
    protected List <IImage> layers = new ArrayList <>(4);
    protected EPartitionScheme scheme;
    protected IImageProcessor processor;
    protected ICodec codec;
    protected ICompressedImage outputImage;

    /**
     * @param filename
     * @param scheme
     * @param tasks
     */
    public
    Task ( String filename,
           EPartitionScheme scheme,
           ICodec codec,
           List <Task> tasks ) {

        this.filename = filename;
        this.scheme = scheme;
        this.codec = codec;
        this.tasks.addAll(tasks);
    }

    /**
     * @return
     */
    public
    List <IImageProcessorListener> getImageProcessorListeners () {
        return processorListeners;
    }

    private final List <IImageProcessorListener> processorListeners = new ArrayList <>();

    /**
     * @return
     */
    public
    List <ICodecListener> getCodecListeners () {
        return codecListeners;
    }

    private final List <ICodecListener> codecListeners = new ArrayList <>();

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
    void addTask ( Task task ) {
        tasks.add(task);
    }

    /**
     * @param fn
     * @return
     */
    @SuppressWarnings("unchecked")
    final public
    IImage loadImage ( String fn ) {
        if (inputImage == null) {
            return new ColorImage(Imgcodecs.imread(fn));
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
    IImage loadSipImage ( String fn ) throws ValueError {
        IImage image = loadImage(fn);
        SipLibrary sipLib = new SipLibrary();
        SipTreeNodeBuilder <?> builder = new SipTreeNodeBuilder <>(image);

        return sipLib.convertImageToSipImage((SipTree <?, ?>) builder.buildTree(new SipImageBlock (image.getMat())));
    }

    /**
     * @param fn
     * @param image
     */
    public final
    IImage saveImage ( String fn, IImage image ) {
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
    IImage apply ( String filename ) {
        logger.info("Executing task " + getClass());
        try {
            inputImage = ((scheme == SIP) ? loadSipImage(filename) : loadImage(filename));

            return execute(filename);

        } catch (ValueError valueError) {
            valueError.printStackTrace();
            System.exit(-1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private
    IImage execute ( String filename ) {
        return null;
    }

    /**
     *
     */
    @Override
    public
    void onPreprocess ( IImageProcessor processor, String filename, IImage image ) throws ValueError {
        logger.info("On preprocessing...\n");
    }

    /**
     *
     */
    @Override
    public
    void onProcess ( IImageProcessor processor, IImage inputImage ) {
        logger.info("On processing... \n");
    }

    /**
     *
     */
    @Override
    public
    void onPostprocess ( IImageProcessor processor, IImage image, List <IImage> outputImages ) {
    }

    /**
     * @param codec
     */
    @Override
    public
    void onCreated ( ICodec codec ) {
        logger.info(String.format("On codec created '%s'\n", codec));
    }

    @SuppressWarnings("unchecked")
    public abstract
    void onPreprocess ( IImageProcessor processor, String filename ) throws ValueError;
}
