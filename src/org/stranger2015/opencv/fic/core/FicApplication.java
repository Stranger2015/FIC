package org.stranger2015.opencv.fic.core;

import org.opencv.osgi.OpenCVNativeLoader;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

import static java.util.List.of;

/**
 *
 */
public
class FicApplication<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>,
        G extends BitBuffer>

        implements Runnable, Consumer <String> {

    protected final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    private final Consumer <String> action = this;
    private final EPartitionScheme scheme;
    private final String filename;
    private final EtvColorSpace colorSpace;

    /**
     * @param args
     */
    public
    FicApplication ( String[] args ) {
        switch (args[0]) {
            case "-e":
            case "-d":
                scheme = EPartitionScheme.valueOf(args[1]);
                filename = args[2];
                colorSpace = EtvColorSpace.valueOf(args[3]);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + args[0]);
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
        ICompressor <N, A, M, G> compressor = new Compressor <N, A, M, G>();
        IDecompressor <N, A, M, G> decompressor=new Decompressor<N, A, M, G>();
        Encoder <N, A, M, G> encoder = Encoder.create(getScheme().getEncoderClassName());
        Decoder <M, A> decoder = Decoder.create(getScheme().getDecoderClassName());
        EncodeTask <N, A, M, G> encodeTask = new EncodeTask <>(filename, getScheme(), List.of(), encoder);
        ICodec <N, A, M, G> codec = Codec.create(getScheme());

        ImageProcessor <N, A, M, G> processor = ImageProcessor.create(
                filename,
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

    public
    EPartitionScheme getScheme () {
        return scheme;
    }

    public
    String getColorSpace () {
        return colorSpace;
    }
}
