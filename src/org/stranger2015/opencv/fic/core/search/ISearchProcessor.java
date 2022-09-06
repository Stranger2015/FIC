package org.stranger2015.opencv.fic.core.search;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.transform.ITransform;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public
interface ISearchProcessor<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Runnable {

    /**
     *
     */
    ITransform <A, G> searchForBestTransform ();

    /**
     * @return
     */
    IImage<A> search ();

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
    void setBestTransform ( ITransform <A, G> bestTransform );

    /**
     * @return
     */
    ITransform <A, G> getBestTransform ();
}
