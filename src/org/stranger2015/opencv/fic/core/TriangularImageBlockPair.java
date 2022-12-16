package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.stranger2015.opencv.fic.core.geom.Polygon;

import java.util.List;

/**
 * @param 
 */
public
class TriangularImageBlockPair<A extends IAddress>
        extends ImageBlock
        implements ITriangularImageBlockPair  {

    private IImageBlock  block1;
    private IImageBlock  block2;

    /**
     * @param image
     * @param x
     * @param sideSize
     * @param block1
     * @param block2
     * @throws ValueError
     */
    public
    TriangularImageBlockPair ( IImage image,
                               int x,
                               int y,
                               int sideSize,
                               Point[] vertices,
                               IImageBlock  block1,
                               IImageBlock  block2
    ) throws ValueError {

        super(image, x, y, sideSize, vertices, kind);
        this.block1 = block1;
        this.block2 = block2;
    }


    /**
     * @param inputImage
     * @param i
     * @param block1
     * @param block2
     */
    public
    TriangularImageBlockPair ( IImage inputImage, int i, Point[] vertices, IImageBlock  block1, IImageBlock  block2 ) {
        this(inputImage.getMat(),vertices, block1);
    }

    /**
     * @param submat
     * @param block1
     */
    public
    TriangularImageBlockPair ( MatOfInt submat, Point[] vertices, IImageBlock  block1 ) {
        super(submat, vertices, geometry);
        this.block1 = block1;
    }

    /**
     * @param image
     * @param blockSize
     * @param address
     * @param block1
     */
    public
    TriangularImageBlockPair ( IImage image,
                               IIntSize blockSize,
                               IAddress  address,
                               Point[] vertices,
                               IImageBlock  block1 ) {

        super(image, blockSize, address, vertices, geometry);

        this.block1 = block1;
    }

    /**
     * @param image
     * @param rows
     * @param cols
     * @param pixels
     * @param block1
     */
    public
    TriangularImageBlockPair ( MatOfInt image, Point[] vertices, int rows, int cols, int[] pixels, IImageBlock  block1 ) throws ValueError {
        super(image, vertices, rows, cols, pixels);
        this.block1 = block1;
    }

    /**
     * @param bb
     * @param polygonList
     * @return
     */
    @Override
    public
    Mat createMask ( IIntSize bb, List <Polygon <?>> polygonList ) {
        return super.createMask(bb, polygonList);
    }

    /**
     * @return
     */
    @Override
    public
    int getNumBands () {
        return super.getNumBands();
    }

    /**
     * Returns the samples for a specified band for the specified rectangle
     * of pixels in an int array, one sample per array element.
     * ArrayIndexOutOfBoundsException may be thrown if the coordinates are
     * not in bounds.
     *
     * @param address
     * @param sideSize The width of the pixel square.
     * @param dummy
     * @param b        The band to return.
     * @param iArray   If non-null, returns the samples in this array.
     * @return the samples for the specified band for the specified region of pixels.
     * @throws NullPointerException           if data is null.
     * @throws ArrayIndexOutOfBoundsException if the coordinates or
     *                                        the band index are not in bounds, or if iArray is too small to
     *                                        hold the output.
     */
    @Override
    public
    int[] getSamples ( IAddress  address, int sideSize, int dummy, int b, int[] iArray ) throws ValueError {
        return super.getSamples(address, sideSize, dummy, b, iArray);
    }

    /**
     * @return
     */
    @Override
    public
    IImageBlock  getBlock1 () {
        return block1;
    }

    /**
     * @return
     */
    @Override
    public
    IImageBlock  getBlock2 () {
        return null;
    }

    @Override
    public
    boolean isBlockHomogenous ( IImageBlock  block ) {
        return false;
    }
}
