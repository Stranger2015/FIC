package org.stranger2015.opencv.fic.core.search;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.transform.ITransform;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 
 * @param <A>
 * @param <G>
 */
public abstract
class SearchProcessor<N extends TreeNode <N, A, G>, A extends IAddress <A>,  G extends BitBuffer>
        implements ISearchProcessor <N, A, G> {

    protected ITransform <A, G> bestTransform;

    /**
     *
     */
    @Override
    public abstract
    ITransform <A, G> searchForBestTransform ();

    /**
     * @return
     */
    @Override
    public abstract
    IImage <A> search ();

    /**
     * @return
     */
    @Override
    public abstract
    double evaluate ();

    /**
     * @param bestTransform
     */
    @Override
    public
    void setBestTransform ( ITransform <A, G> bestTransform ) {
        this.bestTransform = bestTransform;
    }

    /**
     * @return
     */
    @Override
    public
    ITransform <A, G> getBestTransform () {
        return bestTransform;
    }
}
