package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 *
 */
public
class CompressedSipImage<A extends IAddress <A>> extends Image <A> {

    /**
     * @param input
     *
     */
    public
    CompressedSipImage ( IImage <A> input ) {
        super(actualImage, input, pixels);
    }

    /**
     * @return
     */
    @Override
    public
    List <ImageTransform <A, ?>> getTransforms () throws ValueError {
        return actualImage.getTransforms();
    }

    /**
     * @param transforms
     */
    @Override
    public
    void setTransforms ( List <ImageTransform <A, ?>> transforms ) throws ValueError {
        actualImage.setTransforms(transforms);
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
