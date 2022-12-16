package org.stranger2015.opencv.fic.core.search;

import org.stranger2015.opencv.fic.core.IImageBlock;
import org.stranger2015.opencv.fic.transform.ITransform;

import java.util.List;

/**
 
 * @param
 * @param <G>
 */
public abstract
class SearchProcessor implements ISearchProcessor {

    protected ITransform bestTransform;

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
    byte[] search ( IImageBlock imageBlock, List <IImageBlock> rangeBlocks );

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
}
