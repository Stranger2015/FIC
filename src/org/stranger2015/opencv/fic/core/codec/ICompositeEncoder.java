package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;

import java.util.List;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
interface ICompositeEncoder {

    /**
     * @return
     */
    default
    List <Class <ITiler>> getAllowableSubtilers ( List <Class <ITiler>> classes ) {
        return classes;
    }

    /**
     * @param tiler
     */
    void addAllowableSubtiler ( Class<ITiler> tiler );
}
