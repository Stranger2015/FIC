package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 *
 */
public
interface ICompressedImage<A extends Address<A>> extends IImage<A> {
    /**
     * @return
     */
    List <ImageTransform <?,A,?>> getTransforms();

    /**
     * @param transforms
     */
    void setTransforms(List <ImageTransform <?,A,?>> transforms);
}
