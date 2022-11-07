package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * AssistedFreehand    | Assisted freehand region of interest
 * Circle	           | Circular region of interest
 * Crosshair	       | Crosshair region of interest
 * Cuboid	           | Cuboidal region of interest
 * Ellipse	           | Elliptical region of interest
 * Freehand            | Freehand region of interest
 * Line	               | Line region of interest
 * Point	           | Point region of interest
 * Polygon	           | Polygonal region of interest
 * Polyline	           | Polyline region of interest
 * Rectangle	       | Rectangular region of interest
 *
 * A region of interest (ROI) is a portion of an image that you want to filter or operate on in some way.
 * You can represent an ROI as a binary mask image.
 * In the mask image, pixels that belong to the ROI are set to 1 and pixels outside the ROI are set to 0.
 *
 * @param <A>
 */
public
class RegionOfInterest<A extends IAddress <A>>
        extends ImageBlock <A> {

    protected final List <IImageBlock <A>> rangeBlocks = new ArrayList <>();
    protected final List <IImageBlock <A>> domainBlocks = new ArrayList <>();
    protected final List <IImageBlock <A>> codebookBlocks = new ArrayList <>();

    /**
     * @param image
     * @param blockSize
     * @param address
     */
    public
    RegionOfInterest ( IImage <A> image, IAddress <A> address, IIntSize blockSize) {
        super(image, address, blockSize, geometry);
    }

    /**
     * @param inputImage
     */
    public
    RegionOfInterest ( IImage <A> inputImage ) {
        super(inputImage.getMat());
    }

    /**
     * @return
     */
    public
    List <IImageBlock <A>> getRangeBlocks () {
        return rangeBlocks;
    }

    /**
     * @return
     */
    public
    List <IImageBlock <A>> getDomainBlocks () {
        return domainBlocks;
    }

    /**
     * @return
     */
    public
    List <IImageBlock <A>> getCodebookBlocks () {
        return codebookBlocks;
    }

    /**
     * Sets a pixel in  the DataBuffer using an int array of samples for input.
     * ArrayIndexOutOfBoundsException may be thrown if the coordinates are
     * not in bounds.
     *
     * @param address
     * @param iArray  The input samples in an int array.
     * @throws NullPointerException           if iArray or data is null.
     * @throws ArrayIndexOutOfBoundsException if the coordinates are
     *                                        not in bounds, or if iArray is too small to hold the input.
     */
    @Override
    public
    void put( IAddress <A> address, double[] data ) {
        put(address.getX(), address.getY(), data);
    }

    @Override
    public
    double[] get ( int x, int y ) {
        return super.getPixel(x, y, data);
    }

    /**
     * @return
     */
    @Override
    public
    boolean isSquare () {
        return super.isSquare();
    }

    public
    IImageBlock<A> merge ( List <IImageBlock <A>> blocks, List <IImageBlock <A>> blocksToMerge ) {
        return null;
    }
}
