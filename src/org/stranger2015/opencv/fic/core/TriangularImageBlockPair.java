package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;

import java.util.List;

/**
 * @param <A>
 */
public
class TriangularImageBlockPair<A extends IAddress<A>>
        extends ImageBlock<A>
        implements ITriangularImageBlockPair <A> {

    private IImageBlock <A> block1;
    private IImageBlock <A> block2;

    /**
     * @param image
     * @param x
     * @param sideSize
     * @param block1
     * @param block2
     * @throws ValueError
     */
    public
    TriangularImageBlockPair ( IImage <A> image,
                               int x,
                               int y,
                               int sideSize,
                               Point[] vertices,
                               IImageBlock <A> block1,
                               IImageBlock <A> block2
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
    TriangularImageBlockPair ( IImage <A> inputImage, int i, Point[] vertices, IImageBlock <A> block1, IImageBlock <A> block2 ) {
        this(inputImage.getMat(),vertices, block1);
    }

    /**
     * @param submat
     * @param block1
     */
    public
    TriangularImageBlockPair ( MatOfInt submat, Point[] vertices, IImageBlock <A> block1 ) {
        super(submat, vertices);
        this.block1 = block1;
    }

    /**
     * @param image
     * @param blockSize
     * @param address
     * @param block1
     */
    public
    TriangularImageBlockPair ( IImage <A> image,
                               IIntSize blockSize,
                               IAddress <A> address,
                               Point[] vertices,
                               IImageBlock <A> block1 ) {

        super(image, blockSize, address, vertices);

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
    TriangularImageBlockPair ( MatOfInt image, Point[] vertices, int rows, int cols, int[] pixels, IImageBlock <A> block1 ) throws ValueError {
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
    int[] getSamples ( IAddress <A> address, int sideSize, int dummy, int b, int[] iArray ) throws ValueError {
        return super.getSamples(address, sideSize, dummy, b, iArray);
    }

    /**
     * @return
     */
    @Override
    public
    IImageBlock <A> getBlock1 () {
        return block1;
    }

    /**
     * @return
     */
    @Override
    public
    IImageBlock <A> getBlock2 () {
        return null;
    }
}
