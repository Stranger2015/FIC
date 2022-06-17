package org.stranger2015.opencv.fic.core;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 
 * @param <G>
 */
public
class HvTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */, G extends BitBuffer>

        extends RectangularTiler <N, A, G> {

    private final RectangularTiler <N, A, G> rectangularTiler;

    public
    HvTiler ( IImage <A> image, int width, int height ) {
        super(image, width, height);
    }


    /**
     * @param image
     * @return
     */
    @Override
    public
    List <IImageBlock <A>> tile ( GrayScaleImage <A> image ) {
        return rectangularTiler.tile(image);//TODO;
    }

    /**
     * @param rows
     * @param cols
     */
    public
    HvTiler ( GrayScaleImage <A> image, Size rangeSize, Size domainSize, int rows, int cols ) {
        super(image, rangeSize, domainSize, rows, cols);
        rectangularTiler = new RectangularTiler <>(image, rangeSize, domainSize);
    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    HvTiler ( GrayScaleImage <A> image, Size rangeSize, Size domainSize ) {
        this(image, rangeSize, domainSize, 0, 0);
    }
}
