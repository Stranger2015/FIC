package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.IImageBlock;
import org.stranger2015.opencv.fic.core.IIntSize;
import org.stranger2015.opencv.fic.core.ValueError;
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
    List<IImageBlock> getRangeBlocks();

    /**
     * @return
     */
    List<IImageBlock> getDomainBlocks();
    /**
     * @return
     */
    IIntSize getOriginalSize();

    /**
     * @return
     */
    List <ImageTransform> getTransforms () throws ValueError;

    /**
     * @param transforms
     */
    void setTransforms ( List <ImageTransform> transforms );

//    IImage toImage ();

    String dump ();

    void release ();
}
