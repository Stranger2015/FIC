package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
interface ICompositeEncoder {
    /**
     * @param tiler
     */
    void addAllowableSubtiler (Class <ITiler> tiler );
}
