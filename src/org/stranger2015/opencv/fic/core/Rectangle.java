package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.PointAddress;

import static org.stranger2015.opencv.fic.core.IShape.EShape.RECTANGLE;

/**
 *
 */
public
class Rectangle extends IntSize implements IShape , IRectangle{
    protected final IAddress <?> address;

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public
    Rectangle ( int x, int y, int width, int height ) throws ValueError {
        super(width, height);

        address = new PointAddress<>(x, y);//;;valueOf(x + (long) height * y);
    }

    /**
     * @param width
     * @param height
     */
    public
    Rectangle ( int width, int height ) throws ValueError {
        this(0, 0, width, height);
    }

    public boolean isSquare(){
        return width == height;
    }

    /**
     * @param size
     */
    public
    Rectangle ( IIntSize size ) throws ValueError {
        this(0, 0, size.getWidth(), size.getHeight());
    }

    /**
     * @return
     */
    @Override
    public
    EShape getShapeKind () {
        return RECTANGLE;
    }

    /**
     * @return
     */
    @Override
    public
    int area () {
        return width * height;
    }

    /**
     * @return
     */
    @Override
    public
    IAddress <?> getAddress () {
        return address;
    }
}