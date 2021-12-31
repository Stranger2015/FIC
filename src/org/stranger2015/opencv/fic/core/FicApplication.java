package org.stranger2015.opencv.fic.core;

import org.opencv.osgi.OpenCVNativeLoader;

import java.util.Collections;
import java.util.function.Consumer;

import static org.stranger2015.opencv.fic.core.EPartitionScheme.FIXED_SIZE;
import static org.stranger2015.opencv.fic.core.EPartitionScheme.valueOf;

/**
 *
 */
public
class FicApplication implements Runnable, Consumer <String> {
    //    private final Tree <?, Mat> tree;
    private final Consumer <String> action = this;
    private final String filename;

    /**
     * @param args
     */
    public
    FicApplication (  String[] args){
        switch (args[0]) {
            case "-e":
            case "-d":
                filename = args[1];
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
    public void run () {
        init();
        action.accept(filename);//fixme
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
//        ImageProcessor.create(filename);
//
    }

    /**
     * @return
     */
    public
    String getFilename () {
        return filename;
    }
}
