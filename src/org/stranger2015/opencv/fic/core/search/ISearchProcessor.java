package org.stranger2015.opencv.fic.core.search;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.transform.ITransform;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public
interface ISearchProcessor<M extends IImage<A>, A extends Address <A>, G extends BitBuffer> extends Runnable {
    /**
     *
     */
    ITransform <M, A, G> searchForBestTransform ();

    /**
     * @return
     */
    int evaluate ();

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
    void setBestTransform ( ITransform <M, A, G> bestTransform );

    /**
     * @return
     */
    ITransform <M, A, G> getBestTransform ();
}
