package org.stranger2015.opencv.fic.core.codec;

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
    O map ( I input );

    /**
     * @param output
     * @return
     */
    I unmap ( O output );
}
