package org.stranger2015.opencv.fic.core.search;

import org.stranger2015.opencv.fic.core.IImageBlock;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.core.codec.tilers.Pool;
import org.stranger2015.opencv.fic.transform.ITransform;

/**
 *
 */
public
interface ISearchProcessor extends Runnable {
    /**
     *
     */
    ITransform searchForBestTransform ();

    /**
     * @return
     */
    byte[] search ( IImageBlock imageBlock, Pool <IImageBlock> rangeBlocks ) throws ValueError;

    /**
     * @return
     */
    double evaluate ();

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
    default
    void run () {
        setBestTransform(searchForBestTransform());
    }

    /**
     * @param bestTransform
     */
    void setBestTransform ( ITransform bestTransform );

    /**
     * @return
     */
    ITransform getBestTransform ();

    /**
     * @return
     */
    EMetrics getMetrics ();
}
