package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 *
 */
public
class CompressedSipImage extends CompressedImage  {

    private ICompressedImage actualImage;

    /**
     * @param input
     *
     */
    public
    CompressedSipImage ( IImage input ) {
        super(null, input);
    }

    /**
     * @return
     */
    @Override
    public
    List <ImageTransform> getTransforms () throws ValueError {
        return actualImage.getTransforms();
    }

    @Override
    public
    void setTransforms ( List <ImageTransform> transforms ) {
        super.setTransforms(transforms);
    }

    /**
     * @return
     */
    @Override
    public
    Mat getMat () {
        return super.getMat();
    }
}
