package org.stranger2015.opencv.fic.core;

import org.opencv.osgi.OpenCVNativeLoader;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.DefaultCodec;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 *
 */
public
class FicApplication implements Runnable, Consumer <String> {
    Logger logger = Logger.getLogger(String.valueOf(getClass()));

    private final Consumer <String> action = this;
    private EPartitionScheme scheme;
    private String filename;

    /**
     * @param args
     */
    public
    FicApplication ( String[] args ) {
        switch (args[0]) {
            case "-e":
                scheme = EPartitionScheme.valueOf(args[1]);
                filename = args[2];
                break;
            case "-d":
//                filename = args[1];
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

        ImageProcessor<?,?,?> processor = ImageProcessor.create(filename, scheme, new ArrayList <>(2));
        processor.preprocess();
        processor.process();
        processor.postprocess();
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
}
