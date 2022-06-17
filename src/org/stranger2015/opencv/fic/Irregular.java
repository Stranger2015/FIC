package org.stranger2015.opencv.fic;

import org.stranger2015.opencv.fic.core.Shape;
import org.stranger2015.opencv.fic.core.IAddress;

public
class Irregular<A extends IAddress<A>> extends Shape{
    /**
     * @param address
     */
    public
    Irregular ( IAddress <A> address ) {
        super(address, vertices, image, blockSize);
    }

    /**
     * @return
     */
    @Override
    public
    EShape getShapeKind () {
        return EShape.RECTANGLE;
    }

    /**
     * @return
     */
    @Override
    public
    double area () {
        return 0;
    }

    @Override
    public
    IAddress <A> getAddress () {
        return null;
    }
}
