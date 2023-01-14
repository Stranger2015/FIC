package org.stranger2015.opencv.fic.core.test;

import org.opencv.osgi.OpenCVNativeLoader;
import org.stranger2015.opencv.fic.Config;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public
class DtApplication<N extends TreeNode <N>>
        implements Runnable, Consumer <String> {

    protected final Logger logger = LoggerFactory.getLogger(String.valueOf(getClass()));

    private final FicConfig config;
    private final EtvColorSpace colorSpace;
    private final EPartitionScheme scheme;
    private final Consumer <String> action = this;

    private final File input;
    private final File output;

    private final FCImageModel fractalModel;
    private final Set <ImageTransform> transforms = new HashSet <>();

    private final IIntSize rangeSize;
    private final IIntSize domainSize;

    private ImageProcessor processor;


    public
    DtApplication ( Config config, FCImageModel fractalModel, IIntSize rangeSize, IIntSize domainSize ) {
        this.config = config;
        colorSpace = config.colorSpace();
        scheme = config.partitionScheme();
        input = config.command().getInput();
        output = config.command().getOutput();
        this.fractalModel = fractalModel;
        this.rangeSize = rangeSize;
        this.domainSize = domainSize;
    }

    @Override
    public
    void accept ( String filename ) {
        try {
            Class <?>[] pt = new Class <?>[]{};
            Object[] p = new Object[]{};

            IEncoder <N> encoder = Encoder.create(getScheme(), pt, p);//todo invoke encoder before codec
            IDecoder <N> decoder = Decoder.create(getScheme(), pt, p);

            ICodec <N> codec = Codec.create(
                    getScheme(),
                    new Class <?>[]{},
                    new Object[]{});

            EncodeTask <N> encodeTask = new EncodeTask <>(
                    filename,
                    getScheme(),
                    codec,
                    List.of()
            );
            DecodeTask <N> decodeTask = new DecodeTask <>(
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
    FicConfig getConfig () {
        return config;
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
    EPartitionScheme getScheme () {
        return scheme;
    }

    /**
     * @return
     */
    public
    File getOutput () {
        return output;
    }

    /**
     * @return
     */
    public
    FCImageModel <N> getFractalModel () {
        return fractalModel;
    }

    /**
     * @return
     */
    public
    IIntSize getRangeSize () {
        return rangeSize;
    }

    /**
     * @return
     */
    public
    IIntSize getDomainSize () {
        return domainSize;
    }

    public
    Set <ImageTransform> getTransforms () {
        return transforms;
    }
}
