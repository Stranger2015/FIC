package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.stranger2015.opencv.fic.core.codec.RegionOfInterest;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.utils.ColorImage;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.stranger2015.opencv.fic.core.IAddress.valueOf;

/**
 *
 */
public abstract
class Image<A extends IAddress <A>>
        extends Shape <A>
        implements IImage <A> {

//    protected final IIntSize size;
//    protected final PointAddress <A> pointAddress;

    protected MatOfInt actualImage;
//    protected final IIntSize blockSize;

    protected int[] pixels;// take pixel from mat  (actual image)
    protected IAddress <A> address;

    protected int type;

    protected List <ImageTransform <A, ?>> transforms = new ArrayList <>();
    protected final List <RegionOfInterest <A>> regions = new ArrayList <>();

    protected int width;
    protected int height;

    protected int originalImageWidth;
    protected int originalImageHeight;

    /**
     * @param image
     * @param rows
     * @param cols
     * @param pixels
     */
    public
    Image ( MatOfInt image, Point[] vertices, int rows, int cols, int[] pixels ) throws ValueError {
        super(image, vertices, blockSize);
//        super(cols, rows);
        actualImage = image;
        valueOf((long) rows + (long) cols * getWidth());
        this.pixels = pixels;
    }

    /**
     * @param imread
     */
    public
    Image ( MatOfInt imread, Point[] vertices, IIntSize size ) {
        super(image, vertices, blockSize);
        imread.create(size.toSize(), -1);
    }

    /**
     * @param input
     * @param pixels
     */
    public
    Image ( IImage <A> input, Point[] vertices, int[] pixels ) {
        super(image, vertices, blockSize);
        actualImage = (MatOfInt) input;
        this.pixels = pixels;
    }

    public
    Image ( MatOfInt image, Point[] vertices, IAddress <A> address, int[] pixels ) throws ValueError {
        this(image, vertices, address, -1, pixels);
    }

    public
    Image ( IImage <A> subImage, int rows, int cols ) throws ValueError {
        super(image, blockSize);
        actualImage = subImage.getMat();
        address = valueOf((long) rows * cols);
    }

    /**
     * @param dest
     */
    public
    Image ( Mat dest ) {
        super(image, new Point[0], blockSize);
        actualImage = (MatOfInt) dest;
    }

    /**
     * @param width
     * @param height
     */
    public
    Image ( Point[] vertices, int width, int height ) {
        super(image, vertices, blockSize);
        this.width = width;
        this.height = height;
    }

    public
    Image ( IImage <A> image, Point[] vertices ) {
        super(image, vertices, blockSize);
        actualImage = image.getMat();
    }

    /**
     * @param mat
     * @param vertices
     */
    public
    Image ( MatOfInt mat, Point[] vertices ) {
        super(image, vertices, blockSize);
        actualImage = mat;
    }

    public
    Image ( MatOfInt image, Point[] p, IAddress <A> address, int cols, int[] pixels ) {
        super(image, p, blockSize);
    }

    /**
     * @param image
     * @param vertices
     * @param address
     */
    public
    Image ( IImage <A> image, Point[] vertices, IAddress <A> address ) {
        super(image, vertices, blockSize);

        actualImage = image.getMat();
        this.address = address;
    }

    /**
     * @param image
     * @param vertices
     * @param blockSize
     * @param address
     */
    public
    Image ( IImage <A> image, Point[] vertices, IIntSize blockSize, IAddress <A> address ) {
super(image, vertices, blockSize, address);
    }

    public
    Image ( IImage <A> image, IIntSize blockSize, IAddress <A> address ) {
        this(image, new Point[0], blockSize, address);
    }

    public
    Image ( IImage <A> roi, int i, int j, int blockWidth, int blockHeight ) {


        super(image, blockSize);
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    public
    IImageBlock <A> subImage ( int rowStart, int rowEnd, int colStart, int colEnd ) {
        return (IImageBlock <A>) submat(rowStart, rowEnd, colStart, colEnd);
    }

    /**
     * @return
     */
    @Override
    public
    IAddress <A> getAddress () {
        return address;
    }

    /**
     * @return
     */
//    @Override
    public
    int cols () {
        return 0;
    }//fixme

    /**
     * @return
     */
//    @Override
    public
    int rows () {
        return 0;
    }//fixme

    /**
     * @return
     */
    @Override
    public
    int getWidth () {
        return cols();
    }

    /**
     * @return
     */
    @Override
    public
    int getHeight () {
        return rows();
    }

    /**
     * @return
     */
    @Override
    public
    IIntSize getSize () {
        return new IntSize(rows(), cols(), originalImageWidth, originalImageHeight);
    }

    /**
     * @return
     */
    @Override
    public
    int get ( IAddress <A> address, int[] data ) {
        return -1;//.(row, col, data);
    }

    /**
     * @param addr
     * @param data
     * @return
     */
    public
    int put ( int addr, int[] data ) throws ValueError {
        return data[addr] = -1;//fixme
    }

    /**
     * @param inputImage
     * @return
     */
    public
    IImage <A> reduce ( IImage <A> inputImage ) {
        IImage <A> image = new GrayScaleImage <>(inputImage);
        Core.reduce(inputImage.getMat(), image.getMat(), -1, -1, -2);//fixme - dim, rtype, dtype

        return image;
    }

    /**
     * @param m
     * @param rtype
     * @param alpha
     * @param beta
     */
    public
    void convertTo ( Mat m, int rtype, double alpha, double beta ) {
//        super.convertTo(m, rtype, alpha, beta);
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
     * @param addr
     * @param data
     * @return
     */
    public
    int putPixels ( int addr, int[] data ) throws ValueError {
        return put(addr, data);
    }

    /**
     * @return
     */
    public
    IImage <A> createInputImage ( IImage <A> image ) throws ValueError {
        return new GrayScaleImage <>(image);
    }

    /**
     * @param image
     * @return
     */
    public
    IImage <A> createOutputImage ( GrayScaleImage <A> image ) {
        return new CompressedImage <>(image);
    }

    /**
     * @param contractivity
     * @return
     */
    @Override
    public
    IImage <A> contract ( int contractivity ) {
        IImage <A> image = new GrayScaleImage <>(this);

        int newWidth = image.getWidth() / contractivity;
        int newHeight = image.getHeight() / contractivity;

        Imgproc.resize(this.getMat(), image.getMat(), new Size(newWidth, newHeight));

        return image;
    }

    /**
     * @param rowStart
     * @param rowEnd
     * @param colStart
     * @param colEnd
     * @return
     */
    @Override
    public
    Mat submat ( int rowStart, int rowEnd, int colStart, int colEnd ) {
        return new MatOfInt();
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
//    @Override
    public
    void setOriginalImageWidth ( int originalImageWidth ) {
        this.originalImageWidth = originalImageWidth;
    }

    /**
     * @param address
     * @param pixels
     */
    @Override
    public
    void putPixels ( IAddress <A> address, int[][] pixels ) throws ValueError {
        IImage.super.putPixels(address, pixels);
    }

    /**
     * @param address
     */
    public
    int putPixel ( IAddress <A> address, int[] pixel ) throws ValueError {
        return put((int) address.getIndex(), pixel);
    }

    /**
     * @param address
     * @return
     */
    public
    int getPixel ( IAddress <A> address, int[] pixels ) {
        return pixels[(int) address.getIndex()];
    }

    /**
     * @return
     */
    public
    MatOfInt getActualImage () {
        return actualImage;
    }

    /**
     * @return
     */
    @Override
    public
    boolean isGrayScale () {
        return this instanceof GrayScaleImage <?>;
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
            address = new SaAddress <>(x, y);
        }
        else {
            address = new DecAddress <>(x, y);
        }
    }

    /**
     * @return
     */
    @Override
    public
    List <IImage <A>> split () {
        List <Mat> list = new ArrayList <>();
        List <IImage <A>> layers = new ArrayList <>();
        Core.split(getMat(), list);
        for (Mat mat : list) {
            layers.add(new GrayScaleImage <>(mat));
        }

        return layers;
    }

    /**
     * @param layers
     * @return
     */
    @Override
    public final
    IImage <A> merge ( List <IImage <A>> layers, IImage <A> inputImage ) {
        List <Mat> l = copyOf(layers);
        IImage <A> img = new ColorImage <>(inputImage.getMat());
        Core.merge(l, img.getMat());

        return img;
    }

    /**
     * @param layers
     * @return
     */
    private
    List <Mat> copyOf ( List <IImage <A>> layers ) {
        return layers.stream().map(IImage::getMat)
                .collect(Collectors.toCollection(() -> new ArrayList <>(layers.size())));
    }

    /**
     *
     */
    @Override
    public
    void release () {

    }

    /**
     * @return
     */
    @Override
    public
    String dump () {
        return null;
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
    IImageBlock <A> getSubImage ( int x, int y, int width, int height ) {
        return new ImageBlock <A>(submat(x, y, width, height));
    }

    /**
     * @param i
     * @param i1
     * @param width
     * @param height
     * @param img1pixels
     * @param i2
     * @param i3
     */
//    @Override
    public
    void getRGB ( int i, int i1, int width, int height, int[] img1pixels, int i2, int i3 ) {

    }

    /**
     * @return
     */
    @Override
    public
    List <ImageTransform <A, ?>> getTransforms () throws ValueError {
        return transforms;
    }

    /**
     * @param imageTransforms
     */
    @Override
    public
    void setTransforms ( List <ImageTransform <A, ?>> imageTransforms ) throws ValueError {
        this.getTransforms().addAll(imageTransforms);
    }

    /**
     * @return
     */
    @Override
    public
    double[] getPixels () throws ValueError {
        return getPixels(IAddress.valueOf(0));
    }

    private
    int[] get ( int cols, int rows ) {
        return new int[0];
    }

    /**
     * @param pixels
     */
    public
    void setPixels ( int[] pixels ) {
        this.pixels = pixels;
    }

    /**
     * @return
     */
    @Override
    public final
    List <RegionOfInterest <A>> getRegions () {
        return regions;
    }

    /**
     * @param regions
     */
    public
    void setRegions ( List <RegionOfInterest <A>> regions ) {
        this.regions.addAll(regions);
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

    @Override
    public
    IShape getShape () {
        return shape;
    }

    protected IShape shape;
}
