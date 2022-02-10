package org.stranger2015.opencv.fic.core.codec;


import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 *
 */
public
interface ICompressedImage extends IImage {
    /**
     * @return
     */
    List <ImageTransform <IImage>> getTransforms();

    /**
     * @param transforms
     */
    void setTransforms(List <ImageTransform <IImage>> transforms);
}
