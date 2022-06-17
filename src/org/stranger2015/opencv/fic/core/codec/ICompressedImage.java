package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 *
 */
public
interface ICompressedImage<A extends IAddress <A>> extends IImage <A> {
    /**
     * @return
     */
    @Override
    List <ImageTransform <A, ?>> getTransforms ();

    /**
     * @param transforms
     */
    @Override
    void setTransforms ( List <ImageTransform <A, ?>> transforms );
}
