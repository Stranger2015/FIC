package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.Task;
import org.stranger2015.opencv.fic.core.ValueError;

import java.util.List;

/**
 * @param <N>
 * @param
 */
public
interface ICodec
        extends Runnable {

    /**
     * @return
     */
    @Contract(pure = true)
    static @Unmodifiable
    List <IListener <?>> getListeners () {
        return List.of();
    }

    /**
     * @return
     */
    IEncoder getEncoder ();

    /**
     * @return
     */
    IDecoder getDecoder ();

    /**
     * @return
     */
    int getImageSizeBase ();

    /**
     * @param address
     * @return
     * @throws ValueError
     */
    IAddress createAddress ( int address ) throws ValueError;

    /**
     * @param task
     */
    void addTask ( Task task );
}
