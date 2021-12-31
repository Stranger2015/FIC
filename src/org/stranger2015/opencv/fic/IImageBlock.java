package org.stranger2015.opencv.fic;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Image;

/**
 *
 */
public
interface IImageBlock<M extends Image>{
    /**
     * @return
     */
    Size getSize ();
}
