package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.ICodecListener;
import org.stranger2015.opencv.fic.core.codec.IImageProcessorListener;
import org.stranger2015.opencv.fic.utils.SipLibrary;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

import static org.stranger2015.opencv.fic.core.EPartitionScheme.SIP;

/**
 *
 */
public abstract
class Task<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>, G extends BitBuffer>
        implements Function <String, M>,
                   IImageProcessorListener,
                   ICodecListener {

    protected Logger logger = Logger.getLogger(String.valueOf(getClass()));

    protected final List <Task <N, A, M, G>> tasks = new ArrayList <>();

    protected final String filename;
    protected M image;
    protected EPartitionScheme scheme;

    public
    Task ( String filename, EPartitionScheme scheme, List <Task <N, A, M, G>> tasks ) {
        this.filename = filename;
        this.scheme = scheme;
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
    List <IImageProcessorListener> getCodecListeners () {
        return codecListeners;
    }

    private final List <IImageProcessorListener> codecListeners = new ArrayList <>();

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
    void addTask ( Task <N, A, M, G> task ) {
        tasks.add(task);
    }

    /**
     * @param fn
     * @return
     */
    @SuppressWarnings("unchecked")
    final public
    M loadImage ( String fn ) {
        return (M) new Image <A>(Imgcodecs.imread(fn));
    }

    /**
     * @param fn
     * @return
     * @throws ValueError
     */
    @SuppressWarnings("unchecked")
    final public
    M loadSipImage ( String fn ) throws ValueError {
        M image = loadImage(fn);
        SipLibrary <A> sipLib = new SipLibrary <>();
        SipTreeNodeBuilder <N, A, M, G> builder = new SipTreeNodeBuilder <>(image);

        return (M) sipLib.convertImageToSipImage(builder.buildTree(), image);
    }

    /**
     * @param fn
     * @param image
     */
    public
    final
    void saveImage ( String fn, M image ) {
        Imgcodecs.imwrite(fn, (Mat) image);
    }

    /**
     * Performs this operation on the given argument.
     *
     * @param filename the input argument
     * @return
     */
    @Override
    public final
    M apply ( String filename ) {
        logger.info("Executing task " + getClass());

        try {
            image = ((scheme == SIP) ? loadSipImage(filename) : loadImage(filename));

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
    M execute ( String filename ) throws ValueError {
        M image = null;
        for (int i = 0; i < tasks.size(); i++) {
            image = tasks.get(0).execute(filename);
        }

        return image;
    }

    /**
     *
     */
    @Override
    public
    void onPreprocess () {

    }

    /**
     *
     */
    @Override
    public
    void onProcess () {

    }

    /**
     *
     */
    @Override
    public
    void onPostprocess () {

    }

    /**
     *
     */
    @Override
    public
    void onCreateCodec () {

    }

    /**
     *
     */
    @Override
    public
    void onEncode () {

    }

    /**
     *
     */
    @Override
    public
    void onDecode () {

    }
}
