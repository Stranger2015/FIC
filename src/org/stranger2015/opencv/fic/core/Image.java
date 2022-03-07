package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.stranger2015.opencv.fic.core.codec.Pixel;
import org.stranger2015.opencv.fic.core.codec.SipAddress;

/**
 *
 */
public
class Image<A extends Address <A>> extends MatOfDouble implements IImage <A> {

    protected /*final*/ IImage <A> image;
    protected Address <A> address;
//    private final int width;
//    private final int height;
    protected /*final*/ int type;

    /**
     * @param image
     */
    public
    Image ( IImage <A> image ) {
        this.image = image;
    }

    /**
     * @param image
     * @param address
     * @param type
     */
    public
    Image ( IImage <A> image, Address <A> address, int type ) {
        this.image = image;
        this.address = address;
        this.type = type;
    }

    public
    Image ( IImage <A> inputImage, Address <A> address, int width, int height ) {
        this.image = inputImage;
        this.address = address;
        setOriginalImageWidth(width);
        setOriginalImageHeight(height);
    }

    /**
     *
     */
    enum EColorType {
        GRAYSCALE(0),
        TRUE_COLOR(2),
        INDEXED_COLOR(3),
        GRAYSCALE_WITH_ALPHA(4),
        TRUE_COLOR_WITH_ALPHA(6);

        private final int cType;

        /**
         * @param cType
         */
        @Contract(pure = true)
        EColorType ( int cType ) {
            this.cType = cType;
        }

        /**
         * @return
         */
        @Contract(pure = true)
        public
        int getCType () {
            return cType;
        }
    }

    public EColorType cType;

    public int originalImageWidth;
    public int originalImageHeight;

    /**
     * @param rows
     * @param cols
     * @param cType
     * @param pixelData
     * @param image
     */
    public
    Image ( int rows, int cols, EColorType/*int*/ cType, Scalar pixelData, IImage <A> image ) {
        super(rows,
                cols,
                cType.getCType(),//FIXME
                pixelData.val[0],
                pixelData.val[1],
                pixelData.val[2],
                pixelData.val[3]
        );
        this.cType = cType;
        this.image = image;
    }

    /**
     * @param image
     * @param n
     */
    public
    Image ( IImage <A> image, double... n ) {
        super(n);
        this.image = image;
    }

    /**
     * @param image
     * @param w
     * @param h
     * @param cType
     */
    public
    Image ( IImage <A> image, Address <A> address, int w, int h, EColorType cType ) {//fixme
        this.image = image;
        this.address = address;
        this.cType = cType;

    }

    /**
     * @param image
     * @param address
     * @param blockSize
     * @param cType
     */
    public
    Image ( IImage <A> image, Address <A> address, int blockSize, EColorType cType ) {
        this(image, address, blockSize, blockSize, cType);
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    public
    IImage <A> subImage ( int rowStart, int rowEnd, int colStart, int colEnd ) {
        return (IImage <A>) submat(rowStart, rowEnd, colStart, colEnd);
    }

    /**
     * @param imread
     */
    public
    Image ( Mat imread ) {
        imread.create(imread.size(), type());
    }

    /**
     * @param imread
     */
    public
    Image ( Mat imread, Size size ) {
        imread.create(size, type());
    }

    public
    Address <A> getAddress () {
        return address;
    }

    /**
     * @return
     */
    public
    int getWidth () {
        return width();
    }

    /**
     * @return
     */
    public
    int getHeight () {
        return height();
    }

    /**
     * @return
     */
    @Override
    public
    Size getSize () {
        return size();
    }

    /**
     * @param row
     * @param col
     * @return
     */
    @Override
    public
    double[] get ( int row, int col ) {
        return super.get(row, col);
    }

    /**
     * @param row
     * @param col
     * @param data
     * @return
     */
    @Override
    public
    int put ( int row, int col, double... data ) {
        return super.put(row, col, data);
    }

    /**
     * @param inputImage
     * @param outputImage
     * @param factor
     * @return
     */
    public
    IImage <A> reduce ( IImage <A> inputImage, IImage <A> outputImage, int factor ) {
        return outputImage;
    }

    /**
     * @param m
     * @param rtype
     * @param alpha
     * @param beta
     */
    @Override
    public
    void convertTo ( Mat m, int rtype, double alpha, double beta ) {
        super.convertTo(m, rtype, alpha, beta);
    }

    /**
     * @param image
     * @return
     */
    public
    IImage <A> convertTo ( IImage <A> image ) {
        return image;
    }

    /**
     * @param address
     * @param data
     * @return
     */
    public
    int put ( int address, double... data ) {
        return put(address, 0, data);
    }

    /**
     * @return
     */
    public
    IImage <A> createInputImage ( IImage <A> image ) {
        return new Image <>(image);
    }

    /**
     * @param image
     * @return
     */
    public
    IImage <A> createOutputImage ( IImage <A> image ) {
        return new CompressedImage <>(image);
    }

    /**
     * @param row
     * @param col
     * @return
     */
    public
    Pixel getPixel ( int row, int col ) {
        return new Pixel(get(row, col));
    }

    /**
     * @param contractivity
     * @return
     */
    @Override
    public
    IImage <A> contract ( int contractivity ) {
        IImage <A> image = new Image <>((Mat) this);

        int newWidth = image.getWidth() / contractivity;
        int newHeight = image.getHeight() / contractivity;

        Imgproc.resize(this, (Mat) image, new Size(newWidth, newHeight));

        return image;
    }

    /**
     * @return
     */
    @Override
    public
    int getOriginalImageWidth () {
        return originalImageWidth;
    }

    /**
     * @param originalImageWidth
     */
    @Override
    public
    void setOriginalImageWidth ( int originalImageWidth ) {
        this.originalImageWidth = originalImageWidth;
    }

    /**
     * @return
     */
    @Override
    public
    int getOriginalImageHeight () {
        return originalImageHeight;
    }

    /**
     * @param originalImageHeight
     */
    @Override
    public
    void setOriginalImageHeight ( int originalImageHeight ) {
        this.originalImageHeight = originalImageHeight;
    }

    /**
     * @param destX
     * @param destY
     * @param pixel
     */
    @Override
    public
    void put ( int destX, int destY, int pixel ) {

    }

    /**
     * @param pixels
     * @param v
     * @param v1
     * @param v2
     * @param v3
     */
    @Override
    public
    void put ( Pixel[] pixels, double v, double v1, double v2, double v3 ) {

    }

    /**
     * @param x
     * @param y
     */
    @Override
    public
    void setAddress ( int x, int y ) throws ValueError {
        if (this instanceof SipImageBlock) {
            address = new SipAddress <>(x, y);
        }
        else if (this instanceof SaImageBlock) {
            address = new SaAddress <A>(x, y);
        }
    }

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    <M extends IImage <A>> M getSubImage ( int x, int y, int width, int height ) {
        return (M) submat(x, y, width, height);
    }

////        @Override
//    public
//    <M extends IImage <A>, N extends TreeNode <N, A, M, G>, G extends BitBuffer, A extends Address <A>>
//    Image<A> convertImageToSipImage ( SipTree <?, A, Image<A>, ?> buildTree, Image<A> image ) {
//        return null;
//    }
}
