package org.stranger2015.opencv.fic.core.search;

import org.stranger2015.opencv.fic.core.IImageBlock;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.core.codec.tilers.Pool;
import org.stranger2015.opencv.fic.transform.ITransform;

/**
 
 * @param
 * @param <G>
 */
public abstract
class SearchProcessor implements ISearchProcessor {

    protected ITransform bestTransform;

    private EMetrics metrics;

    /**
     *
     */
    @Override
    public abstract
    ITransform searchForBestTransform ();

    /**
     * @return
     */
    @Override
    public abstract
    byte[] search ( IImageBlock imageBlock, Pool <IImageBlock> rangeBlocks ) throws ValueError;

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
    void setBestTransform ( ITransform bestTransform ) {
        this.bestTransform = bestTransform;
    }

    /**
     * @return
     */
    @Override
    public
    ITransform getBestTransform () {
        return bestTransform;
    }

    /**
     * @return
     */
    @Override
    public
    EMetrics getMetrics () {
        return metrics;
    }
}
