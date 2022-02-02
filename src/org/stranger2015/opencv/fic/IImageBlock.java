package org.stranger2015.opencv.fic;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Image;

/**
 *
 */
public
interface IImageBlock {
    /**
     * @return
     */
    int getX();

    /**
     * @return
     */
    int getY();

    /**
     * @return
     */
    int getWidth();

    /**
     * @return
     */
    int getHeight();
    /**
     * @return
     */
    Size getSize ();
}
