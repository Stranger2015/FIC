package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IImage;

public
class ZoomTransform<M extends IImage> extends ImageTransform<M>{

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
