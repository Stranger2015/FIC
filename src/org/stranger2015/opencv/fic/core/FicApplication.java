package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.osgi.OpenCVNativeLoader;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.fic.core.io.FractalReader;
import org.stranger2015.opencv.fic.core.io.FractalWriter;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.fic.utils.Config;
import org.stranger2015.opencv.utils.BitBuffer;

import java.io.File;
import java.util.HashMap;
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
 * TODO: add configuration of filters and transforms
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
        implements Runnable, Consumer <String> {

    protected final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    private final Consumer <String> action = this;
    private final EPartitionScheme scheme;

    private final EtvColorSpace colorSpace;
    private final Config <N, A, G> config;
    private final File input;
    private final File output;

    private FractalModel <N, A, G> fractalModel;
    private Set <ImageTransform <A, G>> transforms = new HashSet <>();

    private IIntSize rangeSize;
    private IIntSize domainSize;

    private ImageProcessor <N, A, G> processor;

    /**
     * @param config
     */
    public
    FicApplication ( Config <N, A, G> config ) {
        this.config = config;
        colorSpace = config.colorSpace();
        scheme = config.partitionScheme();
        input = config.command().getInput();
        output = config.command().getOutput();
    }

    @SuppressWarnings({"unchecked"})
    public
    IImage<A> readImage ( File input ) {
        return (IImage <A>) Imgcodecs.imread(input.getAbsolutePath());
    }

    /**
     * @param image
     * @return
     */
    public
    FractalModel <N, A, G> compress (IImage <A> image ) throws ValueError, ReflectiveOperationException {
        final ImageBlockGenerator <N, A, G> imageBlockGenerator =
                createBlockGenerator(
                        config.tiler(),
                        config.partitionScheme(),
                        Encoder.create(
                                config.partitionScheme(),
                                new Class[]{
                                        IImage.class,//  IImage<A> inputImage,
                                        IIntSize.class,// rangeSize,
                                        IIntSize.class, //domainSize,
                                        Set.class,// <ImageTransform <A, G>> //transforms,
                                        ScaleTransform.class,// <A, G>, //scaleTransform,
                                        IDistanceator.class,// <A> ,//comparator,
                                        Set.class,// <IImageFilter <A>> //filters,
                                        FractalModel.class// <N, A, G> //fractalModel
                                },
                                new Object[]{
                                        image,
                                        getConfig().tiler().getRangeSize(),
                                        getConfig().tiler().getDomainSize(),
                                        new HashSet <>(),
                                        getConfig().domainScale(),
                                        new ImageComparator <A>(
                                                getConfig().metrics(),
                                                getConfig().fuzz()
                                        ),
                                        new HashSet <IImageFilter <A>>(1).
                                            add(new NoneFilter <A>())
                                        },
                                        new FractalModel <>(new HashMap <>())));
        return null;
    }

    private
    ImageBlockGenerator <N, A, G> createBlockGenerator ( ITiler <N, A, G> tiler,
                                                         EPartitionScheme partitionScheme,
                                                         IEncoder <N, A, G> nagiEncoder ) {
        return null;
    }

    /**
     * @param fractalModel
     */
    void decompress ( FractalModel <N, A, G> fractalModel ) {
        fractalModel.getModel();
    }

    /**
     * @param tiler
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
    @SuppressWarnings("unchecked")
    @Contract(value = "_, _, _, _, _ -> new", pure = true)
    private @NotNull
    ImageBlockGenerator <N, A, G> createBlockGenerator (
            ITiler <N,A,G> tiler,
            EPartitionScheme scheme,
            IEncoder <N, A, G> encoder,
            IImage <A> image,
            IIntSize rangeSize,
            IIntSize domainSize
    ) {
        return new ImageBlockGenerator <>(
                tiler,
                topDownTiler, bottomUpTiler, scheme,
                encoder,
                image,
                rangeSize,
                domainSize
        );
    }

    /**
     * @return
     */
    public
    IImageProcessor <N, A, G> getProcessor () {
        return processor;
    }

//    final IDistanceator <M, A> comparator=
//        final Set <ImageTransform <M, A, G>> transforms,
//        final Set <IImageFilter <M, A>> filters,
//        FractalModel <N, A, G> fractalModel ) throws NullPointerException {

//            ICompressor <N, A, G> compressor = new Compressor <N, A, G>(
//                config.domainScale(),
//                new SquareImageBlockGenerator <N, A, G>(
//                        config.tiler(),
//                        image, image.getAddress(). new IntSize(0, 0), new IntSize(1, 1)
//                ),
//                new ImageComparator <>(config.metrics(), config.fuzz()),
//                new HashSet <ImageTransform <M, A, G>>(6) {{
//                    add(new NoneTransform <>(
//                            image,
//                            BILINEAR,
//                            new DecAddress <>(0),
//                            0,
//                            0,
//                            -1));
//                    add(new FlipTransform <>(image, false, new DecAddress <>(0)));
//                    add(new FlopTransform <>(image, BILINEAR, new DecAddress <>(0)));
//                    add(new AffineRotateQuadrantsTransform <A, G>(image, 1, new DecAddress <>(0)));
//                    add(new AffineRotateQuadrantsTransform <A, G>(image, 2, new DecAddress <>(0)));
//                    add(new AffineRotateQuadrantsTransform <A, G>(image, 3, new DecAddress <>(0)));
//                }},
//                new HashSet <IImageFilter <M, A>>(1) {{
//                    add(new NoneFilter <>());
//                }},
//                this);

//        return compressor.compress(image, 0, 0, 0);//fixme
    /**
     * @return
     */
    public
    FractalModel <N, A, G> readModel () {

        FractalModel <N, A, G> model = null;

        FractalReader <N, A, G> fractalReader = new FractalReader <>(config.command().getInput());
        model = fractalReader.readModel();
        fractalReader.close();

        return model;
    }


    /**
     * @param model
     */
    public
    void writeModel ( FractalModel <N, A, G> model, boolean allowOverwrite ) {
        FractalWriter <N, A, G> writer = new FractalWriter <>(output, model.getImageInfo(), allowOverwrite);
        writer.writeModel(model);
        writer.close();
    }

    /**
     * @param image
     */
    public
    void writeImage ( IImage<A> image ) {
        Imgcodecs.imwrite(output.getAbsolutePath(), image.getMat());
    }

    /**
     * read the args, start the app
     *
     * @param args the command line arguments
     */

    public static
    <N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
    void main ( String[] args ) throws ClassNotFoundException, NoSuchMethodException {
        Config <N, A, G> configuration = new Config <>(args);
        //Runnable fic = new FicApplication <>(configuration);
        Runnable fic = new FicApplication<>(configuration);

        fic.run();
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
        action.accept(input.getAbsolutePath());
    }

    /**
     * @return
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
    @Override
    public
    void accept ( String filename ) {
        try {
            Class <?>[] pt = new Class <?>[]{};
            Object[] p = new Object[]{};

            IEncoder <N, A, G> encoder = Encoder.create(getScheme(), pt, p);//todo invoke encoder before codec
            IDecoder <N, A, G> decoder = Decoder.create(getScheme(), pt, p);

            ICodec <N, A, G> codec = Codec.create(
                    getScheme(),
                    new Class <?>[]{},
                    new Object[]{});

            EncodeTask <N, A, G> encodeTask = new EncodeTask <>(
                    filename,
                    getScheme(),
                    codec,
                    List.of()
            );
            DecodeTask <N, A, G> decodeTask = new DecodeTask <>(
                    filename,
                    getScheme(),
                    codec,
                    List.of()
            );

            codec.addTask(encodeTask);
            codec.addTask(decodeTask);

            processor = new ImageProcessor <>(
                    filename,
                    getScheme(),
                    codec,
                    List.of(encodeTask, decodeTask),
                    colorSpace
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
    @Contract(pure = true)
    private
    Set <ImageTransform <A, G>> createTransforms () {
        return transforms;
    }

    /**
     * @return
     */
    public
    String getFilename () {
        return getConfig().command().getInput().getAbsolutePath();
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
    public
    EtvColorSpace getColorSpace () {
        return colorSpace;
    }

    /**
     * @return
     */
    public
    Config <N, A, G> getConfig () {
        return config;
    }

    /**
     * @param fractalModel
     */
    public
    void setFractalModel ( FractalModel <N, A, G> fractalModel ) {
        this.fractalModel = fractalModel;
    }

    /**
     * @param transforms
     */
    public
    void setTransforms ( Set <ImageTransform <A, G>> transforms ) {
        this.transforms = transforms;
    }

    /**
     * @return
     */
    public
    IIntSize getRangeSize () {
        return rangeSize;
    }

    /**
     * @param rangeSize
     */
    public
    void setRangeSize ( IIntSize rangeSize ) {
        this.rangeSize = rangeSize;
    }

    /**
     * @return
     */
    public
    IIntSize getDomainSize () {
        return domainSize;
    }

    /**
     * @param domainSize
     */
    public
    void setDomainSize ( IIntSize domainSize ) {
        this.domainSize = domainSize;
    }

    /**
     * @return
     */
    public
    FractalModel <N, A, G> getFractalModel () {
        return fractalModel;
    }
}
