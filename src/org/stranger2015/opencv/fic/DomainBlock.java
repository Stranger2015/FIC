package org.stranger2015.opencv.fic;

import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.Leaf;

/**
 * 8 X 8
 */
public
class DomainBlock<N extends DomainBlock<N>> extends Leaf<N> implements IImageBlock<Image> {

    public final static int W = 8;
    public final static int H = 8;

    /**
     * @param parent
     * @param image
     * @param rect
     */
    public
    DomainBlock ( DomainBlock <N> parent, Image image, Rect rect ) {
        super(parent, image, rect);
    }

    @Override
    public
    Size getSize () {
        return new Size();
    }//fixme
}
