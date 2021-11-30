package org.stranger2015.opencv.fic.core;

import org.opencv.osgi.OpenCVNativeLoader;

import java.util.function.Consumer;

import static org.stranger2015.opencv.fic.core.EPartitionScheme.FIXED_SIZE;

public
class FicApplication implements Runnable, Consumer <String> {

    //    private final Tree <?, Mat> tree;
    private final Consumer <String> action = this;
//    private final ImageProcessor <?, Image, CompressedImage> processor;
    private final String filename;

    public
    FicApplication (  String[] args
//                     Mat image,
//                     PartitionScheme scheme,
//                     Tree<?, Mat> tree,
            /* Consumer <String[]> action*/ ) {
//        this.processor = new ImageProcessor <>();
//        this.image = image;
//        this.scheme = scheme;
//        this.tree = tree;
        switch (args[0]) {
            case "-e":
                filename = args[1];
                break;
            case "-d":
                filename = args[1];
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + args[0]);
        }
    }
//        this.processor = new ImageProcessor();
//        encoder = new Encoder();
//        decoder = new Decoder();
//        init();
//        action.accept(args);

//    public
//    Tree <?, Mat> getTree () {
//        return tree;
//    }

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
    public void run () {
        init();
        action.accept(toString());//fixme
    }

    public
    Consumer <String> getAction () {
        return action;
    }

//    public
//    ImageProcessor <?, ?, ?> getProcessor () {
//        return processor;
//    }

    /**
     * Performs this operation on the given argument.
     *
     * @param s the input argument
     */
    @Override
    public
    void accept ( String s ) {
        /*processor = new ImageProcessor <N, M, C>(s);*/

    }
}
