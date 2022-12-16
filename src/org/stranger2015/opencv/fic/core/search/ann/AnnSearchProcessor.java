package org.stranger2015.opencv.fic.core.search.ann;

import org.stranger2015.opencv.fic.core.search.SearchProcessor;
import org.stranger2015.opencv.fic.transform.ITransform;

/**
 
 * @param
 * @param <G>
 */
public abstract
class AnnSearchProcessor
        extends SearchProcessor {
    /**
     *
     */
    @Override
    public
    ITransform searchForBestTransform () {
        return null;
    }
//    /**
//     *
//     */
//    @Override
//    public
//    ITransform  searchForBestTransform () {
//        return null;
//    }

//    /**
//     * @return
//     */
//    @Override
//    public
//    IImage search () {
//        return null;
//    }

//    /**
//     * @return
//     */
//    @Override
//    public
//    double evaluate () {
//        return 0;
//    }

}
