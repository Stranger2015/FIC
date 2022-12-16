package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.utils.BitBuffer;

import java.io.IOException;
import java.util.List;

/**
 * a fractal model represents the compressed form of the image.<br />
 * <br />
 * {@code from <Range, <Domain, Transform>>      }<br />
 * {@code ..to <Domain, <Transform, {Range}>>  }<br />
 * <br />
 * or from:
 * <br />
 * {@code Point1 - Domain1 - Transform1}<br />
 * {@code Point2 - Domain2 - Transform1}<br />
 * {@code Point3 - Domain1 - Transform2}<br />
 * {@code Point4 - Domain1 - Transform2}<br />
 * {@code Point5 - Domain2 - Transform1}<br />
 * <br />
 * to:
 * <br />
 * {@code Domain1 -[ Transform1 -[ Point1 ]] }<br />
 * {@code ....... .[ Transform2 -[ Point3 ]  }<br />
 * {@code ....... ............. .[ Point4 ]] }<br />
 * {@code Domain2 -[ Transform1 -[ Point2 ]  }<br />
 * {@code ....... ............. .[ Point5 ]] }<br />
 * <br />
 * in other words, instead of storing for each range the transform <br />
 * and the domain, we store the domain once, along with a set of   <br />
 * transforms, with each transform, we store a set of points that  <br />
 * represent the position of the ranges in the original image.
 *
 * @see Compressor
 */
public
class FCImageModel extends IntSize  {
    public static final String LABEL = "FCIF\r\n";
    private ImageInfo imageInfo;
    private byte[] ifsRecords;
    transient private List <IImageBlock> rangeBlocks;
    transient private List <IImageBlock> domainBlocks;
    private IIntSize size;
    private BitBuffer bitBuffer;

    /**
     * @param simpleModel
     * @param bb
     */
    public
    FCImageModel (byte[] ifsRecords ) throws ValueError, IOException {
        super(null);

        setIfsRecords(ifsRecords);
    }

    /**
     * @return
     */
    public
    ImageInfo getImageInfo () {
        return imageInfo;
    }

    /**
     * @param imageInfo
     */
    public
    void setImageInfo ( ImageInfo imageInfo ) {
        this.imageInfo = imageInfo;
    }

    /**
     * @return
     */
    public
    String dump () {
        return "";
    }

    /**
     *
     */
    public
    void release () {

    }

    /**
     * @return
     */
//    @Override
    public
    List <IImageBlock> getRangeBlocks () {
        return rangeBlocks;
    }

    /**
     * @return
     */
//    @Override
//    public
    List <IImageBlock> getDomainBlocks () {
        return domainBlocks;
    }

    /**
     * @return
     */
//    @Override
    public
    IIntSize getOriginalSize () {
        return null;
    }

//    /**
//     * @return
//     */
//    @Override
//    public
//    List <ImageTransform> getTransforms () throws ValueError {
//        return null;
//    }
//
//    /**
//     * @param transforms
//     */
//    @Override
//    public
//    void setTransforms ( List <ImageTransform> transforms ) {
//
//    }

//    /**
//     * @return
//     */
//    @Override
//    public
//    IImage toImage () {
//        return null;
//    }

    /**
     * @param rangeBlocks
     */
    public
    void setRangeBlocks ( List <IImageBlock> rangeBlocks ) {
        this.rangeBlocks = rangeBlocks;
    }

    /**
     * @param domainBlocks
     */
    public
    void setDomainBlocks ( List <IImageBlock> domainBlocks ) {
        this.domainBlocks = domainBlocks;
    }

    /**
     * @return
     */
//    @Override
    public
    IIntSize getSize () {
        return size;
    }

//    /**
//     * @param w
//     * @param h
//     * @param originalImageWidth
//     * @param originalImageHeight
//     * @return
//     */
//    @Override
//    public
//    IIntSize restoreSize ( int w, int h, int o//{
//        return null;
//    }

    /**
     * @return
     */
    public
    byte[] getIfsRecords () {
        return ifsRecords;
    }

    /**
     * @param ifsRecords
     */
    public
    void setIfsRecords ( byte[] ifsRecords ) {
        this.ifsRecords = ifsRecords;
    }

    /**
     * @return
     */
    public
    BitBuffer getBitBuffer () {
        return bitBuffer;
    }

    /**
     *
     */
    @Override
    public
    int getOriginalImageWidth () {
        return originalImageWidth;
    }

    /**
     *
     */
    @Override
    public
    int getOriginalImageHeight () {
        return originalImageHeight;
    }

    /**
     * @return
     */
    @Override
    public
    int getWidth () {
        return width;
    }

    /**
     * @return
     */
    @Override
    public
    int getHeight () {
        return height;
    }

    /**
     * @param o
     * @return
     */
    @Override
    public
    int compareTo ( IIntSize o ) {
        return 0;
    }

    /**
     * @param bitBuffer
     */
    public
    void setBitBuffer ( BitBuffer bitBuffer ) {
        this.bitBuffer = bitBuffer;
    }

    /**
     * @param size
     */
    public
    void setSize ( IIntSize size ) {
        this.size = size;
    }
}
