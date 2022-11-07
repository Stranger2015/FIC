package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Size;

/**
 *
 */
public
interface IIntSize {
    /**
     *
     */
    int getOriginalImageWidth ();

    /**
     *
     */
    int getOriginalImageHeight ();

    /**
     * @return
     */
    int getWidth ();

    /**
     * @return
     */
    int getHeight ();

    /**
     * @param o
     * @return
     */
    int compareTo ( @NotNull IIntSize o );

    /**
     * @return
     */
    default
    Size toSize () {
        return new Size(this.getWidth(), this.getHeight());
    }
}
