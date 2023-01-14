package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.stranger2015.opencv.fic.core.geom.Coordinate;
import org.stranger2015.opencv.fic.core.geom.Geometry;
import org.stranger2015.opencv.fic.core.geom.GeometryFactory;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.stranger2015.opencv.fic.core.EAddressKind.ORDINARY;
/**
 *
 */
public
class Image extends Mat implements IImage {

    public static final EAddressKind ADDRESS_KIND = ORDINARY;

    protected /*final*/ IIntSize blockSize;
    private GeometryFactory factory;
    protected double[] meanPixelValue;
    private EColorType colorType;

    private final List <Channel> imageLayers=new ArrayList <>(4);

    protected double[] pixelSum;//todo is this needed h
    // ere

//    /**
//     * @param roi
//     * @param regions
//     * @param actualImage
//     * @param blockSize1
//     * @param i
//     * @param j
//     */
//    public
//        this(actualImage, roi, i, j);
//        this.actualImage = actualImage;
//        this.blockSize = blockSize1;
//        this.roi = roi;
//        x = i;
//        y = j;
    //      this.regions = regions;
//        actualImage.getWidth();
//        actualImage.getHeight();
//    }

    /**
     * @param dest
     * @param size
     * @param actualImage
     * @param blockSize
     * @param colorType
     */
    public
    Image ( MatOfInt actualImage, IIntSize blockSize, EColorType colorType ) {
//        this.actualImage = actualImage;
//        this.blockSize = blockSize;
//        this.roi = roi;
//        x = 0;
//        y = 0;
//        originalImageWidth = actualImage.width();
//        originalImageHeight = actualImage.height();
        this.colorType = colorType;
    }

    /**
     * @param roi
     * @param x
     * @param y
     * @param blockWidth
     * @param colorType
     * @throws ValueError
     */
    public
    Image ( MatOfInt roi, int x, int y, int blockWidth, EColorType colorType ) throws ValueError {
//        actualImage = roi.getMat();
//        this.x = x;
//        this.y = y;
        blockSize = new IntSize(blockWidth, blockWidth);
        //originalImageWidth = actualImage.width();
        //originalImageHeight = actualImage.height();
        this.colorType = colorType;
    }

    /**
     * @param roi
     * @param image
     * @param address
     * @param blockSize
     * @param colorType
     */
    public
    Image ( MatOfInt image, IAddress address, IIntSize blockSize, EColorType colorType ) {
//        actualImage = image;
//        x = address.getX();
//        y = address.getY();
        this.blockSize = blockSize;
//        originalImageWidth = actualImage.width();
//        originalImageHeight = actualImage.height();
        this.colorType = colorType;
    }

//    /**
//     * @param dest
//     * @param roi
//     * @param blockSize
//     */
//    public
//    Image ( Mat dest, IIntSize blockSize ) {
//        actualImage = dest;
//        this.blockSize = blockSize2321;
//        this.roi = roi;
//        originalImageWidth = actualImage.width();
//        originalImageHeight = actualImage.height();
//        x = 0;
//        y = 0;
//    }
//

    /**
     * @param roi
     * @param image
     * @param address
     * @param blockSize
     * @param factory
     * @param colorType
     */
    public
    Image ( IImage image, IAddress address, IIntSize blockSize, GeometryFactory factory, EColorType colorType ) {
//        actualImage = image.getMat();
//        x = address.getX();
//        y = address.getY();
//        this.blockSize = blockSize;

        this.factory = factory;
//        originalImageWidth = actualImage.width();
//        originalImageHeight = actualImage.height();
        this.colorType = colorType;
    }

    /**
     * @param originalImageWidth
     * @param originalImageHeight
     * @param submat
     * @param blockSize
     * @param colorType
     */
//    @Contract(pure = true)
    public
    Image ( Mat submat, IIntSize blockSize, /*,int originalImageWidth, int originalImageHeight*/ EColorType colorType ) {
//        actualImage = submat;
        this.blockSize = blockSize;
//        this.originalImageWidth = originalImageWidth;
//        this.originalImageHeight = originalImageHeight;
//        x = 0;
//        y = 0;
        this.colorType = colorType;
    }

    public
    Image ( IImage image, int x, int y, IIntSize blockSize, EColorType colorType ) {
//        actualImage = image.getMat();
//        this.x = x;
//        this.y = y;
//        this.blockSize = blockSize;
        // this.originalImageWidth = blockSize.getWidth();
        //this.originalImageHeight = blockSize.getHeight();
        this.colorType = colorType;
    }

    public
    Image ( IImage image, int x, int y, int blockWidth, int regions, EColorType colorType ) {
//        actualImage = image.getMat();
//        this.x = x;
//        this.y = y;
//        this.blockSize = new IntSize(blockWidth, blockWidth);
//        th//is.originalImageWidth = blockSize.getWidth();
//        this.originalImageHeight = blockSize.getHeight();
        this.colorType = colorType;
    }

    public
    Image ( Mat mat, int rows, int cols, double[] pixels, EColorType colorType ) {
        super();
        //actualImage=new Mat(cols, rows,-1,new Scalar(pixels));
        this.colorType = colorType;
    }

    public
    Image ( IImage src, IAddress address, int targetSize, EColorType colorType ) {

        this.colorType = colorType;
    }

    public
    Image ( MatOfInt mat, EColorType colorType ) {

        this.colorType = colorType;
    }

    public
    Image () {

    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    public
    Mat subImage ( int rowStart, int rowEnd, int colStart, int colEnd ) {
        return (IImageBlock) submat(rowStart, rowEnd, colStart, colEnd);
    }

    @Override
    public
    Coordinate[] getCoordinates () {
        return new Coordinate[0];
    }

    //    @Override
    public
    int getNumPoints () {
        return 0;
    }

    //    @Override
    public
    boolean isEmpty () {
        return false;
    }

    @Override
    public
    Coordinate getCentroid () {
        return null;
    }

    public
    int getDimension () {
        return 0;
    }

    //    @Override
    public
    Geometry <?> getBoundary () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    int getBoundaryDimension () {
        return 0;
    }

    /**
     *
     */
    public
    void normalize () {

    }

    /**
     * @return
     */
    public
    int pixelAmount () {
        return getWidth() * getHeight();
    }

    protected List <ImageTransform> transforms;

    /**
     * @param n
     * @return
     */
    @Override
    public
    int plus ( int... n ) {
        return 0;
    }

    @Override
    public
    void initialize () {
    }

    /**
     * @return
     */
    @Override
    public
    EColorType getColorType () {
        return colorType;
    }

    /**
     * @return
     */
    @Override
    public
    int cols () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    int rows () {
        return 0;
    }

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
    double getBeta () {
        return 0;
    }

    /**
     * @param beta
     */
    @Override
    public
    void setBeta ( double beta ) {
    }

    /**
     * @return
     */

    @Override
    public
    IIntSize getSize () {
        return new IntSize(getActualImage());
    }

    public
    double[] put ( IAddress address, double[] data ) throws ValueError {
        put(address.getX(), address.getY(), data);

        return data;
    }

    /**
     * @param inputImage
     * @return
     */
    public
    IImage reduce ( IImage inputImage ) {
        IImage image = new Image(
                inputImage.getMat(),
                inputImage.getSize(),
//                ioriginalImageWidth,
//                originalImageHeight
                colorType);

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
    IImage convertTo ( IImage image ) {
        return image;
    }

    /**
     * @param addr
     * @param data
     * @return
     */
    public
    void putPixels ( IAddress addr, double[] data ) throws ValueError {
        put(addr.getX(), addr.getY(), data);
    }

    /**
     * @return
     */
    public
    IImage createInputImage ( IImage image ) throws ValueError {
        return new Image(image.getMat(), image.getSize(), colorType);
    }

    /**
     * @param image
     * @return
     */
    public
    IImage createOutputImage ( IImage image ) {
        return new CompressedImage(image);
    }

    /**
     * @param contractivity
     * @return
     */
    @Override
    public
    IImage contract ( int contractivity ) {
        IImage image = new Image((MatOfInt) this.getMat(), this.getSize(), colorType);

        int newWidth = image.getWidth() / contractivity;
        int newHeight = image.getHeight() / contractivity;

        Imgproc.resize(this.getMat(), image.getMat(), new Size(newWidth, newHeight));

        return image;
    }


    /**
     * @param scale
     * @return
     */
    public
    IImage resize ( double scale ) {
        IImage image = new Image((MatOfInt) this.getMat(), this.getSize(), colorType);

        int newWidth = (int) (image.getWidth() * scale);
        int newHeight = (int) (image.getHeight() * scale);

        Imgproc.resize(this.getMat(), image.getMat(), new Size(newWidth, newHeight));

        return image;
    }

    @Override
    public
    Image resize ( int contractivity ) {
        int newWidth = this.getWidth() / contractivity;
        int newHeight = this.getHeight() / contractivity;
        int channels = this.getChannelsAmount();
        double[] newPixelValues = new double[newWidth * newHeight * channels];
        for (int c = 0; c < channels; c++) {
            for (int i = 0; i < newWidth; i++) {
                for (int j = 0; j < newHeight; j++) {
                    newPixelValues(i, j)[c] =
                            (pixelValues(i * 2, j * 2)[c]
                                    + pixelValues(i * 2, j * 2 + 1)[c]
                                    + pixelValues(i * 2 + 1, j * 2)[c]
                                    + pixelValues(i * 2 + 1, j * 2)[c]
                            ) / contractivity * contractivity;
                }
            }
        }
        IImage image = new Image();
//    image
//        this.cols(newWidth) = newWidth;
//        this.height = newHeight;
//        this.pixelValues = newPixelValues;
        return (Image) image;
    }

    private
    double[] newPixelValues ( int i, int j ) {
        return new double[0];
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
//        return originalImageWidth;
        return getWidth();
    }

    @Override
    public
    void setMeanPixelValue ( double meanPixelValue ) {
        this.meanPixelValue = meanPixelValue;
    }

    /**
     * @param other the object to be compared.
     * @return
     */
    @Override
    public
    int compareTo ( /*@NotNull*/ @NotNull IImage other ) {
        return 0;
    }

    /**
     * @param blocks
     */
    public
    void setRegions ( List <IImageBlock> blocks ) {

    }

    /**
     * @return
     */
    @Override
    public
    List <IImageBlock> getRegions () {
        return null;
    }

    /**
     * @param address
     */
    public
    void putPixel ( IAddress address, double[] pixel ) throws ValueError {
        put(address.getX(), address.getY(), pixel);
    }

    @Override
    public
    void putPixels ( double[] pixelData ) {
        put(0, 0, pixelData);
    }

    /**
     * @param address
     * @return
     */
    public
    double[] getPixel ( IAddress address ) {
        return getPixel(address.getX(), address.getY());
    }

    /**
     * @return
     */
    public
    MatOfInt getActualImage () {
        return (MatOfInt) getMat();
    }

    /**
     * @return
     */
    @Override
    public
    boolean isGrayScale () {
        return this instanceof GrayScaleImage;
    }

    @Override
    public
    List <IImage> getComponents () {
        return List.of();
    }

//    /**
//     * @param x
//     * @param y
//     */
//    @Override
//    public
//    void setAddress ( int x, int y ) throws ValueError {
//        if (this instanceof SipImageBlock) {
//            address = new SipAddress <>(x, y);
//        }
//        else if (this instanceof SaImageBlock) {
//            address = new SaAddress <>(x, y);
//        }
//        else {
//            address = new DecAddress <>(x, y);
//        }
//    }

//    /**
//     * @return
//     */
//    @Override
//    public
//    List <Mat> split () {
//        List <Mat> mv = new ArrayList <>();
//        List <IImage> layers = new ArrayList <>();
//        Core.split(getMat(), mv);
//        for (Mat mat : mv) {
//            layers.add(new Image(mat, getSize(), colorType));
//        }
//
//        return layers;
//    }

    /**
     * @param layers
     * @return
     */
    @Override
    public
    Mat merge ( List <Mat> layers, Mat image) {
//        List <Mat> l = copyOf(layers);
//        IImage img = new Image((MatOfInt) image, colorType);
        Core.merge(layers, image);

        return image;
    }

    /**
     * @param layers
     * @return
     */
    private
    List <Mat> copyOf ( List <IImage> layers ) {
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
        return "null";
    }

    /**
     * @return
     */
    @Override
    public
    Mat getMat () {
        return this.getActualImage();
    }

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    @Override
    public
    IImageBlock getSubImage ( int x, int y, int width, int height ) throws ValueError {
        return new ImageBlock(this, x, y, width, height);
    }

    public abstract
    int compareTo ( /*@NotNull*/ IIntSize o );

    public abstract
    Size toSize ();

    /**
     * @param i
     * @param i1
     * @param width
     * @param height
     * @param img1pixels
     * @param i2
     * @param i3
     */
    public
    void getRGB ( int i, int i1, int width, int height, double[] img1pixels, int i2, int i3 ) {
    }

    /**
     * @return
     */
    @Override
    public
    List <ImageTransform> getTransforms () throws ValueError {
        return transforms;
    }

    /**
     * @param imageTransforms
     */
    @Override
    public
    void setTransforms ( List <ImageTransform> imageTransforms ) throws ValueError {
        this.getTransforms().addAll(imageTransforms);
    }

    /**
     * @param col
     * @param row
     * @return
     */
    public
    double[] getPixel ( int col, int row ) {
        return getMat().get(col, row);
    }

    /**
     * @param row
     * @param col
     * @return
     * @throws ValueError
     */
    @Override
    public
    IAddress getAddress ( int row, int col ) throws ValueError {
        return create(row, getWidth(), col, getAddressKind());
    }

    /**
     * @return
     */
    @Override
    public
    EAddressKind getAddressKind () {
        return ORDINARY;
    }
    /**
     * @return
     */
    public
    int[][] getAddressTable () {
        return new int[0][0];//table;
    }

    /**
     * @return
     */
    @Override
    public
    double[] getPixels () {
        return new double[(int) (getMat().total() * getMat().channels())][];
    }

    @Override
    public
    double getMeanPixelValue () {
        return meanPixelValue;
    }

    @Override
    public
    IImageBlock getSubImage ( Rectangle rectangle ) throws ValueError {
        return this.getSubImage(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    @Override
    public
    IImageBlock getSubImage () throws ValueError {
        return new ImageBlock(getMat(), new Rectangle(0, 0, getWidth(), getHeight()));
    }

    /**
     * @return
     */
    @Override
    public
    int getOriginalImageHeight () {
        return getHeight();
    }

    public
    GeometryFactory getFactory () {
        return factory;
    }

    public
    int get ( int c, int r, double[] data ) {
        data = getMat().get(c, r);
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    List <IChannel> getChannels () {
        return imageLayers;
    }

    public
    int getChannelsAmount () {
        return getMat().channels();
    }

    @Override
    public
    double pixelValues ( int x, int y ) {
        return getPixel(x, y);
    }

    @Override
    public
    double getPixelValuesLayer ( int x, int y, int ch ) {
        double[] data = new double[ch];

        data = get(x, y, data);
        return 0;//todo
    }

    @Override
    public
    void setMeanPixelValuesLayer ( int c, double v ) {

    }

    /**
     * @param originalImageWidth
     */
    @Override
    public
    void setOriginalImageWidth ( int originalImageWidth ) {

    }

    /**
     * @param w
     * @param h
     * @param originalImageWidth
     * @param originalImageHeight
     * @return
     */
    @Override
    public
    IIntSize restoreSize ( int w, int h, int originalImageWidth, int originalImageHeight ) {
        return null;
    }
}