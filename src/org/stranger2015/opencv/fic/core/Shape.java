package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.opencv.core.Point;
import org.stranger2015.opencv.fic.core.codec.PointAddress;

/**
 *
 */
public abstract
class Shape implements IShape {

    protected final IImage <?> image;
    protected final Point[] vertices;
    protected final IIntSize blockSize;
    protected final IAddress <?> address;//INSERTION POINT

    /**
     * @param image
     * @param blockSize
     */
    @Contract(pure = true)
    public
    Shape ( IImage <?> image, IIntSize blockSize ) {
        this.image = image;
        vertices = image.getVertices();
        this.blockSize = blockSize;
        address = image.getAddress();
    }

    public
    Shape ( IImage <?> image, Point[] vertices, IIntSize blockSize, IAddress <?> address ) {
        this.image = image;
        this.vertices = vertices;
        this.blockSize = blockSize;
        this.address = address;
    }

    /**
     * @return
     */
    @Override
    public
    Point[] getVertices () {
        return vertices;
    }

    /**
     * @param x
     * @param y
     * @param i
     * @param image
     * @param vertices
     * @param blockSize
     */
    @Contract(pure = true)
    public
    Shape ( IImage <?> image, Point[] vertices, int x, int y, int i, IIntSize blockSize ) {
        this.image = image;
        this.vertices = vertices;
        address = new PointAddress <>(x, y);
        this.blockSize = blockSize;
    }

    /**
     * @return
     */
    @Override
    public abstract
    EShape getShapeKind ();

    /**
     * @return
     */
    @Override
    public abstract
    double area ();
}
