package org.stranger2015.opencv.fic.core;

import  org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.osgi.OpenCVNativeLoader;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.cli.Options;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Command line utility to compress an image using
 * fractal image compression methods.
 * <p>
 * Configuration:
 * // * TODO: add configuration of filters and transforms
 * <p>
 * Comparators:
 * FIXME: the current compressor cannot handle scale transforms due to
 * ImageComparator's limitation to same-size image comparison only
 * <p>
 * Metric:
 * NOTE: currently implemented: AE MSE RMSE
 * NOTE: currently only AE is affected by fuzz
 * <p>
 * Compression:
 * TODO: FUTURE: multi-threading
 * <p>
 * Tilers:
 * TODO: FUTURE: intelligent tilers - HV/Quadtree partitioning
 */
public
class FicApplication<N extends TreeNode <N>>
        implements Runnable,
                   Consumer <String> {

    protected final Logger logger = LoggerFactory.getLogger(String.valueOf(getClass()));

    private final Consumer <String> action = this;
    private final FicConfig config;
    private EtvColorSpace colorSpace;
    private ITreeNodeBuilder <N> nodeBuilder;
    private final Set <ImageTransform> transforms = new HashSet <>();
    private final List <Task> tasks = new ArrayList <>();

    /**
     * @return
     */
    public
    EtvColorSpace getColorSpace () {
        return colorSpace;
    }

    /**
     * @param colorSpace
     */
    public
    void setColorSpace ( EtvColorSpace colorSpace ) {
        this.colorSpace = colorSpace;
    }

    /**
     * @param nodeBuilder
     */
    public
    void setNodeBuilder ( ITreeNodeBuilder <N> nodeBuilder ) {
        this.nodeBuilder = nodeBuilder;
    }

    /**
     * @return
     */
    public
    List <Task <N>> getTasks () {
        return tasks;
    }

    /**
     *
     */
    protected
    enum ECommands {
        COMPRESS,
        DECOMPRESS,
    }

    /**
     * @param nodeBuilder
     * @param config
     * @param colorSpace
     */
    public
    FicApplication ( FicConfig config ) {
        this.config = config;
    }

    /**
     * @param input
     * @return
     */
    @SuppressWarnings({"unchecked"})
    public
    IImage readImage ( File input ) {
        return (IImage ) Imgcodecs.imread(input.getAbsolutePath());
    }

    /**
     * @param image
     * @return
     */
    public
    FCImageModel <N> compress ( IImage image, String encoderClassName ) throws Exception {

        return (FCImageModel <N>) readImage(config.getInput());
    }

    /**
     * @param fractalModel
     */
    void decompress ( FCImageModel <N> fractalModel ) {
        fractalModel.getModel();
    }

    public static
    void main ( String[] args ) throws ClassNotFoundException, NoSuchMethodException {
        FicConfig configuration = new FicConfig(args);
        Runnable fic = new FicApplication <>(configuration);

        CmdLineParser parser = new CmdLineParser(fic);
        try {
            parser.parseArgument(args);
            fic.run();
        } catch (CmdLineException e) {
            // handling of wrong arguments
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }
    }

    /**
     *
     */
    private
    void init () {
        new OpenCVNativeLoader().init();
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public
    void run () {
        init();
        action.accept(config.getInput().getAbsolutePath());
    }

    /**
     * //     * @return
     * //
     */
    public
    Consumer <String> getAction () {
        return action;
    }

    /**
     * Performs this operation on the given argument.
     *
     * @param filename the input argument
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    void accept ( String filename ) {
        try {
            Options <N> options = new Options <>();

            ICodec <N> codec = (ICodec <N>) Utils.invoke(
                    config.partitionScheme(),
                    Class.forName(config.partitionScheme().getCodecClassName()).getConstructor());

            EncodeTask <N> encodeTask = new EncodeTask <>(
                    filename,
                    config.partitionScheme(),
                    codec,
                    List.of()
            );
            DecodeTask <N> decodeTask = new DecodeTask <>(
                    filename,
                    config.partitionScheme(),
                    codec,
                    List.of()
            );

            codec.addTask(encodeTask);
            codec.addTask(decodeTask);

            IEncoder <N> encoder = Encoder.create(
                    filename,
                    config.partitionScheme(),
                    config.tasks(),
                    config.colorSpace(),
                    nodeBuilder(),
                    partitionProcessor(),
                    options.getSearchProcessor(),
                    scaleTransform(),
                    imageBlockGenerator(),
                    distanceator(),
                    createTransforms(),
                    imageFilter(),
                    fractalModel()
            );
            //processor.execute(filename);

        } catch (ReflectiveOperationException | ValueError e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * @return
     */
    @Contract(value = " -> new", pure = true)
    private
    Set <IImageFilter > imageFilter () {
        return new HashSet <>();
    }

    /**
     * @return
     */
    @Contract(value = " -> new", pure = true)
    private @NotNull
    Set <ImageTransform> imageTransform () {
        return new HashSet <>();
    }

    private
    IDistanceator  distanceator () {
        return config.distanceator;
    }

    private
    ImageBlockGenerator <N> imageBlockGenerator () {
        return newInstance();
    }

    /**
     * @return
     */
    @Contract(pure = true)
    private @Nullable
    ScaleTransform  scaleTransform () {
        return null;
    }

//    private
//    ISearchProcessor <N> searchProcessor () {
//        return Options.searchProcessor;
//    }

    private
    IPartitionProcessor <N> partitionProcessor () {
        return null;
    }

    private
    ITreeNodeBuilder <N> nodeBuilder () {
        return nodeBuilder;
    }

    private
    FCImageModel <N> fractalModel () {
        return fractalModel;
    }

    /**
     * @return
     */
    @Contract(pure = true)
    private
    Set <ImageTransform> createTransforms () {
        return transforms;
    }

    public final
    ITreeNodeBuilder <N> getNodeBuilder () {
        return nodeBuilder;
    }


//    /**
//     * @return
//     */
//    public
//    String getFilename () {
//        return getConfig().input().getAbsolutePath();
//    }


    /**
     * @return
     */
    public
    FicConfig getConfig () {
        return config;
    }
}
