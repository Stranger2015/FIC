package org.stranger2015.opencv.fic.core;

import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.osgi.OpenCVNativeLoader;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.fic.core.io.FractalReader;
import org.stranger2015.opencv.fic.core.io.FractalWriter;
import org.stranger2015.opencv.fic.core.search.ImageComparator;
import org.stranger2015.opencv.fic.transform.*;
import org.stranger2015.opencv.fic.utils.Config;
import org.stranger2015.opencv.utils.BitBuffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.logging.Logger;

import static java.util.List.of;

/**
 * Command line utility to compress an image using
 * fractal image compression methods.
 *
 * Configuration:
 * TODO: add configuration of filters and transforms
 *
 * Comparators:
 * FIXME: the current compressor cannot handle scale transforms due to
 * ImageComparator's limitation to same-size image comparison only
 *
 * Metric:
 * NOTE: currently implemented: AE MSE RMSE
 * NOTE: currently only AE is affected by fuzz
 *
 * Compression:
 * TODO: FUTURE: multi-threading
 *
 * Tilers:
 * TODO: FUTURE: intelligent tilers - HV/Quadtree partitioning
 */
public
class FicApplication<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>, G extends BitBuffer>

        implements Runnable, Consumer <String> {

    protected final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    private final Consumer <String> action = this;
    private final EPartitionScheme scheme;
    private final String filename;
    private final EtvColorSpace colorSpace;
    private final Config config;

    /**
     * @param config
     */
    public
    FicApplication (Config config ) {

        this.config = config;
        colorSpace = EtvColorSpace.valueOf(config.);
    }

    private
   M readImage() {
        M image = null;

        try {
            image = (M) Imgcodecs.imread("");
        } catch (IOException ioe) {
            System.err.println(EError.FILE_READ.description(configuration.input().getName()));
            System.exit(EError.FILE_READ.errcode());
        }

        return image;
    }
    public FractalModel<N,A,M,G> compress(M image) {
        Compressor<N,A,M,G> compressor = new Compressor<N, A, M, G>(
                config.domainScale(),
                config.tiler(),
                new ImageComparator<>(config.metrics(), config.fuzz()),
                new HashSet <ImageTransform<M,A,G>>(6) {{
                    add(new NoneTransform<>(image,image, ));
                    add(new FlipTransform<>());
                    add(new FlopTransform<>());
                    add(new AffineRotateQuadrantsTransform<>(1));
                    add(new AffineRotateQuadrantsTransform<>(2));
                    add(new AffineRotateQuadrantsTransform<>(3));
                }},
                new HashSet<BufferedImageOp>(1) {{
                    add(new GrayscaleFilter());
                }},
                this);

        return compressor.compress(image);
    }

    public void writeModel(FractalModel<N,A,M,G> fmodel) {
        try {
            FractalWriter fwriter = new FractalWriter(new BufferedOutputStream(getOutStream()));
            fwriter.write(fmodel);
            fwriter.close();
        } catch (IOException ioe) {
            System.err.println(EError.STREAM_WRITE.description());
            System.exit(EError.STREAM_WRITE.errcode());
        }
    }

    private

    OutputStream getOutStream () {
        return null;
    }

    public FractalModel<N,A,M,G> readModel() {
        FractalModel<N,A,M,G> fmodel = null;

        try {
            FractalReader freader = new FractalReader(config.input());
            fmodel = freader.read();
            freader.close();
        } catch (ClassNotFoundException | IOException e) {
            System.err.println(EError.FILE_READ.description(config.input().getName()));
            System.exit(EError.FILE_READ.errcode());
        }

        return fmodel;
    }

    public M decompress(FractalModel<N,A,M,G> fmodel) {
        Decompressor<N,A,M,G> decompressor = new Decompressor<>(this);

        return decompressor.decompress(fmodel);
    }

    public void writeImage(M image) {
        try {
            Imgcodecs.imwrite("");
        } catch (IOException ioe) {
            System.err.println(EError.STREAM_WRITE.description());
            System.exit(EError.STREAM_WRITE.errcode());
        }
    }

    /**
     * read the args, start the app
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Config configuration = new Config(args);
        Runnable fic = new FicApplication<>(configuration);

        fic.run();
    }

//     switch (args[0]) {
//        case "-e":
//        case "-d":
//            scheme = EPartitionScheme.valueOf(args[1]);
//            filename = args[2];
//            colorSpace = EtvColorSpace.valueOf(args[3]);
//             break;
//        default:
//            throw new IllegalStateException("Unexpected value: " + args[0]);
//    }

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
        action.accept(filename);
    }

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

        ICompressor <N, A, M, G> compressor = new Compressor <>(
                scaleTransform,
                imageBlockGenerator,
                comparator,
                transforms,
                fractalModel);
        IDecompressor <N, A, M, G> decompressor= new Decompressor <>();

        IEncoder <N, A, M, G> encoder = Encoder.create(getScheme().getEncoderClassName());
        IDecoder <M, A> decoder = Decoder.create(getScheme().getDecoderClassName());

        EncodeTask <N, A, M, G> encodeTask = new EncodeTask <>(filename, getScheme(), List.of(), encoder);
        ICodec <N, A, M, G> codec = Codec.create(getScheme());

        ImageProcessor <N, A, M, G> processor = ImageProcessor.create(
                filename,
                compressor,
                decompressor,
                scheme,
                of(),
                codec,
                colorSpace);
        try {
            processor.execute(filename);
        } catch (ValueError e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * @return
     */
    public
    String getFilename () {
        return filename;
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

    public
    Config getConfig () {
        return config;
    }
}
