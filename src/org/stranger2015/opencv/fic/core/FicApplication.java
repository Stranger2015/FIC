package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.logging.Logger;

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
class FicApplication<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        implements Runnable,
                   Consumer <String> {

    protected final Logger logger = Logger.getLogger(String.valueOf(getClass()));
    private final Consumer <String> action = this;
    private final FicConfig <N, A, G> config;

    private EtvColorSpace colorSpace;

    protected
    enum ECommands {
        COMPRESS,
        DECOMPRESS,
    }

    /**
     * @param config
     * @param nodeBuilder
     */
    public
    FicApplication ( FicConfig <N, A, G> config ) {
        this.config = config;
    }

    @SuppressWarnings({"unchecked"})
    public
    IImage <A> readImage ( File input ) {
        return (IImage <A>) Imgcodecs.imread(input.getAbsolutePath());
    }

    /**
     * @param image
     * @return
     */
    public
    FCImageModel <N, A, G> compress ( IImage <A> image, String encoderClassName ) throws Exception {

        return (FCImageModel <N, A, G>) readImage(config.getInput());
    }

    /**
     * @param fractalModel
     */
    void decompress ( FCImageModel <N, A, G> fractalModel ) {

        fractalModel.getModel();
    }

    //
////    /**
////     * @param tiler
////     * @param encoder
////     * @param image
////     * @param rangeSize
////     * @param domainSize
////     * @return
////     */
////    @SuppressWarnings("unchecked")
////    @Contract(value = "_, _, _, _, _ -> new", pure = true)
////    private @NotNull
////    ImageBlockGenerator <N, A, G> createBlockGenerator (
////            IPartitionProcessor <N, A, G> partitionProcessor,
////            EPartitionScheme scheme,
////            IEncoder <N, A, G> encoder,
////            IImage <A> image,
////            IIntSize rangeSize,
////            IIntSize domainSize
////    ) {
////        return new ImageBlockGenerator <>(
////                partitionProcessor,
////                scheme,
////                encoder,
////                image,
////                rangeSize,
////                domainSize
////        ) {
////            /**
////             * @param partitionProcessor
////             * @param scheme
////             * @param encoder
////             * @param image
////             * @param rangeSize
////             * @param domainSize
////             * @return
////             */
////            @Override
////            public
////            ImageBlockGenerator <N, A, G> newInstance ( IPartitionProcessor <N, A, G> partitionProcessor, EPartitionScheme scheme, IEncoder <N, A, G> encoder, IImage <A> image, IIntSize rangeSize, IIntSize domainSize ) {
////                return null;
////            }
////        };
////    }
//
////    /**
////     * @return
////     */
////    public
////    IImageProcessor <N, A, G> getProcessor () {
////        return processor;
////    }
////
////    final IDistanceator <M, A> comparator=
////        final Set <ImageTransform <M, A, G>> transforms,
////        final Set <IImageFilter <M, A>> filters,
////        FCImageModel <N, A, G> fractalModel ) throws NullPointerException {
//
////            ICompressor <N, A, G> compressor = new Compressor <N, A, G>(
////                config.domainScale(),
////                new SquareImageBlockGenerator <N, A, G>(
////                        config.tiler(),
////                        image, image.getAddress(). new IntSize(0, 0), new IntSize(1, 1)
////                ),
////                new ImageComparator <>(config.metrics(), config.fuzz()),
////                new HashSet <ImageTransform <M, A, G>>(6) {{
////                    add(new NoneTransform <>(
////                            image,
////                            BILINEAR,
////                            new DecAddress <>(0),
////                            0,
////                            0,
////                            -1));
////                    add(new FlipTransform <>(image, false, new DecAddress <>(0)));
////                    add(new FlopTransform <>(image, BILINEAR, new DecAddress <>(0)));
////                    add(new AffineRotateQuadrantsTransform <A, G>(image, 1, new DecAddress <>(0)));
////                    add(new AffineRotateQuadrantsTransform <A, G>(image, 2, new DecAddress <>(0)));
////                    add(new AffineRotateQuadrantsTransform <A, G>(image, 3, new DecAddress <>(0)));
////                }},
////                new HashSet <IImageFilter <M, A>>(1) {{
////                    add(new NoneFilter <>());
////                }},
////                this);
//
////        return compressor.compress(image, 0, 0, 0);//fixme
//    /**
//     * @return
//     */
////    public
////    FCImageModel <N, A, G> readModel () {
////
////        FCImageModel <N, A, G> model = null;
////
////        FractalReader <N, A, G> fractalReader = new FractalReader <>(config.command().getInput());
////        model = fractalReader.readModel();
////        fractalReader.close();
////
////        return model;
////    }
//
//
// //   /**
//   //  * @param model
//    // */
////    public
////    void writeModel ( FCImageModel <N, A, G> model, boolean allowOverwrite ) {
////        FractalWriter <N, A, G> writer = new FractalWriter <>(output, model.getImageInfo(), allowOverwrite);
////        writer.writeModel(model);
////        writer.close();
////    }
//
////    /**
////     * @param image
////     */
////    public
////    void writeImage ( IImage<A> image ) {
////        Imgcodecs.imwrite(output.getAbsolutePath(), image.getMat());
////    }
////
////    /**
////     * read the args, start the app
////     *
////     * @param args the command line arguments
////     */
////
    public static
    void main ( String[] args ) throws ClassNotFoundException, NoSuchMethodException {
        FicConfig <?, ?, ?> configuration = new FicConfig <>(args);
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
            Options <N, A, G> options = new Options <>();

            ICodec <N, A, G> codec = (ICodec <N, A, G>) Utils.invoke(
                    config.partitionScheme(),
                    Class.forName(config.partitionScheme().getCodecClassName()).getConstructor());

            IEncoder <N, A, G> encoder = null;

            encoder = Encoder.create(
                    config.partitionScheme(),
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
            //IEncoder<N,A,G> encoder =   config.getCommand()== ECommands.COMPRESS
            //config.getCommand()== ECommands.COMPRESS
//            IDecoder <N, A, G> decoder = Utils.invoke();
//            if (config.getCommand()== ECommands.COMPRESS){
            // config.getCommand()== ECommands.DECOMPRESS Utils.invoke();
//            }
            //todo invoke encoder before codec
            EncodeTask <N, A, G> encodeTask = new EncodeTask <>(
                    filename,
                    config.partitionScheme(),
                    codec,
                    List.of()
            );
            DecodeTask <N, A, G> decodeTask = new DecodeTask <>(
                    filename,
                    config.partitionScheme(),
                    codec,
                    List.of()
            );

            codec.addTask(encodeTask);
            codec.addTask(decodeTask);

            ImageProcessor <N, A, G> processor = new ImageProcessor <>(
                    filename,
                    config.partitionScheme(),
                    codec,
                    List.of(encodeTask, decodeTask),
                    config.colorSpace()
            );

            processor.execute(filename);

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
    Set <IImageFilter> imageFilter () {
        return new HashSet <>();
    }

    /**
     * @return
     */
    @Contract(value = " -> new", pure = true)
    private @NotNull
    Set <ImageTransform <?, ?>> imageTransform () {
        return new HashSet <>();
    }

    private
    IDistanceator <A> distanceator () {
        return config.distanceator;
    }

    private
    ImageBlockGenerator <N, A, G> imageBlockGenerator () {
        return null;
    }

    /**
     * @return
     */
    @Contract(pure = true)
    private @Nullable
    ScaleTransform <A, G> scaleTransform () {
        return null;
    }

//    private
//    ISearchProcessor <N, A, G> searchProcessor () {
//        return Options.searchProcessor;
//    }

    private
    IPartitionProcessor <N, A, G> partitionProcessor () {
        return null;
    }

    private
    ITreeNodeBuilder nodeBuilder () {
        return nodeBuilder;
    }

    private
    FCImageModel <N, A, G> fractalModel () {
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
    FicConfig <?, ?, ?> getConfig () {
        return config;
    }
//
//    /**
//     * @param fractalModel
//     */
//    public
//    void setFractalModel ( FCImageModel <N, A, G> fractalModel ) {
//        this.fractalModel = fractalModel;
//    }
//
//    /**
//     * @param transforms
//     */
//    public
//    void setTransforms ( Set <ImageTransform <A, G>> transforms ) {
//        this.transforms = transforms;
//    }
//
//    /**
//     * @return
//     */
//    public
//    IIntSize getRangeSize () {
//        return rangeSize;
//    }
//
//    /**
//     * @param rangeSize
//     */
//    public
//    void setRangeSize ( IIntSize rangeSize ) {
//        this.rangeSize = rangeSize;
//    }
//
//    /**
//     * @return
//     */
//    public
//    IIntSize getDomainSize () {
//        return domainSize;
//    }
//
//    /**
//     * @param domainSize
//     */
//    public
//    void setDomainSize ( IIntSize domainSize ) {
//        this.domainSize = domainSize;
//    }
//
}
