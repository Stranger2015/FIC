package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 *
 */
public
class CompressedSipImage<A extends Address <A>> extends SipImage <A> {

    /**
     * @param input
     * @param pixels
     */
    public
    <M extends IImage <A>>
    CompressedSipImage ( M input, Pixel[] pixels ) {
        super(input, pixels);
    }

    /**
     * @return
     */
//    @Override
    public
    List <ImageTransform <?, A, ?>> getTransforms () {
        return image.getTransforms();
    }

    /**
     * @param transforms
     */
//    @Override
    public
    void setTransforms ( List <ImageTransform <?, A, ?>> transforms ) {
        image.setTransforms(transforms);
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
