package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 *
 */
public
interface ICompressedImage {
    /**
     * @return
     */
    List <ImageTransform <Image>> getTransforms();

    /**
     * @param transforms
     */
    void setTransforms(List <ImageTransform <Image>> transforms);
}
