package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImageBlock;
import org.stranger2015.opencv.fic.core.IIntSize;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 *
 */
public
interface ICompressedImage<A extends IAddress <A>>{

    /**
     * @return
     */
    List<IImageBlock<A>> getRangeBlocks();

    /**
     * @return
     */
    List<IImageBlock<A>> getDomainBlocks();
    /**
     * @return
     */
    IIntSize getOriginalSize();

    /**
     * @return
     */
    List <ImageTransform <A, ?>> getTransforms ();

    /**
     * @param transforms
     */
    void setTransforms ( List <ImageTransform <A, ?>> transforms );
}
