package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;

public
class ZoomTransform<M extends Image, C extends CompressedImage> extends ImageTransform<M,C>{

    public
    ZoomTransform (M image) {
        super(image);
    }

    /**
     * @param inputImage
     * @param transformMatrix
     * @param interpolationType
     * @return
     */
    @Override
    public
    M transform ( M inputImage, M transformMatrix, EInterpolationType interpolationType ) {
        return null;
    }
}
