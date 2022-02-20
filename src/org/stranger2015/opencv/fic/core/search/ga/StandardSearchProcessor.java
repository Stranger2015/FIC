package org.stranger2015.opencv.fic.core.search.ga;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.ITransform;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <M>
 * @param <A>
 * @param <G>
 */
public
class StandardSearchProcessor<M extends IImage<A>, A extends Address <A>, G extends BitBuffer>
        implements ISearchProcessor <M, A, G> {

    protected ITransform <M, A, G> bestTransform;

    /**
     *
     */
    @Override
    public
    ITransform <M, A, G> searchForBestTransform () {
        return bestTransform;
    }

    /**
     * @return
     */
    @Override
    public
    int evaluate () {
        return 0;
    }

    /**
     * @param bestTransform
     */
    @Override
    public
    void setBestTransform ( ITransform <M, A, G> bestTransform ) {
        this.bestTransform = bestTransform;
    }

    /**
     * @return
     */
    @Override
    public
    ITransform <M, A, G> getBestTransform () {
        return bestTransform;
    }
}
