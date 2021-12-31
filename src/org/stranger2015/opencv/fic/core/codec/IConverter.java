package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.ValueError;

/**
 * @param <I>
 * @param <O>
 */
public
interface IConverter<I, O> {
    /**
     * @param input
     * @return
     */
    O map ( I input ) throws ValueError;

    /**
     * @param output
     * @return
     */
    I unmap ( O output );
}
