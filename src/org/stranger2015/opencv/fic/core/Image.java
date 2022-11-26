package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
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

import static org.stranger2015.opencv.fic.core.IAddress.create;

/**
 *
 */
public
class Image<A extends IAddress <A>>
        implements IImage <A> {

    public static final EAddressKind ADDRESS_KIND = EAddressKind.ORDINARY;

    public final Mat actualImage;
    protected final IIntSize blockSize;
    //    rotected final int[,] table;
    protected final int originalImageWidth;
    protected final int originalImageHeight;

    //    protected final MatOfInt roi;
    private GeometryFactory factory;
    protected final int x;
    protected final int y;
    //    protected List <IImageBlock <A>> regions;
    protected double[] meanPixelValue;

    /**
     * @param roi
     * @param actualImage
     * @param blockSize1
     * @param i
     * @param j
     * @param blockSize
     * @param regions
     */
    public
    Image ( Mat actualImage, IIntSize blockSize1, int i, int j, int[] blockSize/*List <IImageBlock <A>> regions */ ) {
//        this(actualImage, roi, i, j);
        this.actualImage = actualImage;
        this.blockSize = blockSize1;
//        this.roi = roi;
        x = i;
        y = j;
        //      this.regions = regions;
        originalImageWidth = actualImage.width();
        originalImageHeight = actualImage.height();
        //size=new IntSize(actualImage);
    }

    /**
     * @param dest
     * @param size
     * @param actualImage
     * @param blockSize
     */
    public
    Image ( MatOfInt actualImage, IIntSize blockSize ) {
        this.actualImage = actualImage;
        this.blockSize = blockSize;
//        this.roi = roi;
        x = 0;
        y = 0;
        originalImageWidth = actualImage.width();
        originalImageHeight = actualImage.height();
    }

    /**
     * @param roi
     * @param x
     * @param y
     * @param blockWidth
     * @throws ValueError
     */
    public
    Image ( IImage <A> roi, int x, int y, int blockWidth, int blockHeight ) throws ValueError {
        actualImage = roi.getMat();
        this.x = x;
        this.y = y;
        blockSize = new IntSize(blockWidth, blockHeight);
        originalImageWidth = actualImage.width();
        originalImageHeight = actualImage.height();
    }

    /**
     * @param image
     * @param roi
     * @param address
     * @param blockSize
     */
    public
    Image ( MatOfInt image, IAddress <A> address, IIntSize blockSize ) {
        actualImage = image;
        x = address.getX();
        y = address.getY();
        this.blockSize = blockSize;
        originalImageWidth = actualImage.width();
        originalImageHeight = actualImage.height();
    }

//    /**
//     * @param dest
//     * @param roi
//     * @param blockSize
//     */
//    public
//    Image ( Mat dest, IIntSize blockSize ) {
//        actualImage = dest;
//        this.blockSize = blockSize;
//        this.roi = roi;
//        originalImageWidth = actualImage.width();
//        originalImageHeight = actualImage.height();
//        x = 0;
//        y = 0;
//    }
//

    /**
     * @param image
     * @param roi
     * @param address
     * @param blockSize
     * @param factory
     */
    public
    Image ( IImage <A> image, IAddress <A> address, IIntSize blockSize, GeometryFactory factory ) {
        actualImage = image.getMat();
        x = address.getX();
        y = address.getY();
        this.blockSize = blockSize;

        this.factory = factory;
        originalImageWidth = actualImage.width();
        originalImageHeight = actualImage.height();
    }

    /**
     * @param submat
     * @param blockSize
     * @param originalImageWidth
     * @param originalImageHeight
     */
    @Contract(pure = true)
    public
    Image ( Mat submat, IIntSize blockSize, int originalImageWidth, int originalImageHeight ) {
        actualImage = submat;
        this.blockSize = blockSize;
        this.originalImageWidth = originalImageWidth;
        this.originalImageHeight = originalImageHeight;
        x = 0;
        y = 0;
    }

    public
    Image ( IImage <A> image, int x, int y, IIntSize blockSize ) {
        actualImage = image.getMat();
        this.x = x;
        this.y = y;
        this.blockSize = blockSize;
        this.originalImageWidth = blockSize.getWidth();
        this.originalImageHeight = blockSize.getHeight();
    }

    public
    Image ( IImage <A> image, int x, int y, int blockWidth, List <IImageBlock <A>> regions ) {
        actualImage = image.getMat();
        this.x = x;
        this.y = y;
        this.blockSize = new IntSize(blockWidth, blockWidth);
        this.originalImageWidth = blockSize.getWidth();
        this.originalImageHeight = blockSize.getHeight();
    }

    public
    Image ( Mat mat ) {
        super();
    }


    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    public
    IImageBlock <A> subImage ( int rowStart, int rowEnd, int colStart, int colEnd ) {
        return (IImageBlock <A>) submat(rowStart, rowEnd, colStart, colEnd);
    }

    //    @Override
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

    //    @Override
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

    //    @Override
    public
    int getBoundaryDimension () {
        return 0;
    }

    public
    void normalize () {

    }

    public
    int pixelAmount () {
        return getWidth() * getHeight();
    }

//    @Override
//    public
//    void setAddress ( IAddress <A> address ) {

    protected List <ImageTransform <A, ?>> transforms;


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
        /*createAddressTable()*/
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
    double[] put ( IAddress <A> addr, double[] data ) throws ValueError {
        put(addr.getX(), addr.getY(), data);

        return data;
    }

    /**
     * @param inputImage
     * @return
     */
    public
    IImage <A> reduce ( IImage <A> inputImage ) {
        IImage <A> image = new Image <>(
                inputImage.getMat(),
                inputImage.getSize(),
                originalImageWidth,
                originalImageHeight);

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
    void putPixels ( IAddress <A> addr, double[] data ) throws ValueError {
        put(addr.getX(), addr.getY(), data);
    }

    /**
     * @return
     */
    public
    IImage <A> createInputImage ( IImage <A> image ) throws ValueError {
        return new Image <>(image.getMat(), image.getSize(), originalImageWidth, originalImageHeight);
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
     * @param contractivity
     * @return
     */
    @Override
    public
    IImage <A> contract ( int contractivity ) {
        IImage <A> image = new Image <>((MatOfInt) this.getMat(), this.getSize());

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
    IImage <A> resize ( double scale ) {
        IImage <A> image = new Image <>((MatOfInt) this.getMat(), this.getSize());

        int newWidth = (int) (image.getWidth() * scale);
        int newHeight = (int) (image.getHeight() * scale);

        Imgproc.resize(this.getMat(), image.getMat(), new Size(newWidth, newHeight));

        return image;
    }

    public
    Image <A> resize ( int contractivity ) {
        int newWidth = this.getWidth() / contractivity;
        int newHeight = this.getHeight() / contractivity;
        int channels = this.getChannelsAmount();
        double[] newPixelValues = new double[newWidth * newHeight*channels];
        for (int c = 0; c < channels; c++) {
            for (int i = 0; i < newWidth; i++) {
                for (int j = 0; j < newHeight; j++) {
                    newPixelValues(i ,j)[c] = (double) ((
                              pixelValues(i * 2,j * 2)[c]
                            + pixelValues(i * 2,j * 2 + 1)[c]
                            + pixelValues(i * 2 + 1,j * 2)[c]
                            + pixelValues(i * 2 + 1,j * 2)[c]
                    ) / contractivity * contractivity);
                }
            }
        }
        IImage <A> image = new Image <>();
        this.swidth = newWidth;
        this.height = newHeight;
        this.pixelValues = newPixelValues;
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

    @Override
    public
    void setMeanPixelValue ( double[] meanPixelValue ) {
        this.meanPixelValue = meanPixelValue;
    }

    /**
     * @param other the object to be compared.
     * @return
     */
    @Override
    public
    int compareTo ( @NotNull IImage <A> other ) {
        return 0;
    }

    /**
     * @param blocks
     */
    @Override
    public
    void setRegions ( List <IImageBlock <A>> blocks ) {

    }

    /**
     * @return
     */
    @Override
    public
    List <IImageBlock <A>> getRegions () {
        return null;
    }

    /**
     * @param address
     */
    public
    void putPixel ( IAddress <A> address, double[] pixel ) throws ValueError {
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
    double[] getPixel ( IAddress <A> address ) {
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
        return this instanceof GrayScaleImage <?>;
    }

    @Override
    public
    List <IImage <A>> getComponents () {
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

    /**
     * @return
     */
    @Override
    public
    List <IImage <A>> split () {
//        List <Mat> list = new ArrayList <>();
        List <IImage <A>> layers = new ArrayList <>();
//        fixme
//        Core . split(getMat(), Collections.unmodifiableList(list));
//        for (Mat mat : list) {
//            layers.add(new Image <>((MatOfInt) mat, blockSize));
//        }

        return layers;
    }

    /**
     * @param layers
     * @return
     */
    @Override
    public
    IImage <A> merge ( List <IImage <A>> layers, IImage <A> inputImage ) {
        List <Mat> l = copyOf(layers);
        IImage <A> img = new Image <>((MatOfInt) inputImage.getMat(), blockSize);
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
//    @SuppressWarnings("unchecked")
    @Override
    public
    IImageBlock <A> getSubImage ( int x, int y, int width, int height ) throws ValueError {
        return new ImageBlock <>(this, x, y, width, height);
    }

    /**
     * @param i
     * @param i1
     * @param sideSize
     * @param img1pixels
     * @param i2
     * @param i3
     */
    @Override
    public
    void getRGB ( int i, int i1, int sideSize, double[] img1pixels, int i2, int i3 ) {

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
                     //   @Override
    public
    void getRGB ( int i, int i1, int width, int height, double[] img1pixels, int i2, int i3 ) {
        getRGB(i, i1, width, img1pixels, i2, i3);
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
     * @param col
     * @param row
     * @return
     */
    public
    double[] getPixel ( int col, int row ) {
        return getMat().get(col, row);
    }

//    /**
//     * @return
//     */
//    @Override
//    @Contract(pure = true)
//    public final
//    List <IImageBlock <A>> getRegions () {
//        return regions;
//    }

    /**
     * @param row
     * @param col
     * @return
     * @throws ValueError
     */
    @Override
    public
    IAddress <A> getAddress ( int row, int col ) throws ValueError {
        return create(row, getWidth(), col, getAddressKind());
    }

    /**
     * @return
     */
    @Override
    public
    EAddressKind getAddressKind () {
        return EAddressKind.ORDINARY;
    }

//    /**
//     * @param regions
//     */
//    @Override
//    public
//    void setRegions ( List <IImageBlock <A>> regions ) {
//        this.regions.addAll(regions);
//    }

    /**
     * @return
     */
    public
    int[][]etAddressTable () {
        return new int[0][0];//table;
    }
//
//    /**
//     * @param pixelData
//     */
//    /**
//     * Create a new "split edge" with the section of points between
//     * (and including) the two intersections.
//     * The label for the new edge is the same as the label for the parent edge.
//     */
//    Edge createSplitEdge( EdgeIntersection ei0, EdgeIntersection ei1)
//    {
////Debug.print("\ncreateSplitEdge"); Debug.print(ei0); Debug.print(ei1);
//        int npts = ei1.segmentIndex - ei0.segmentIndex + 2;
//
//        Coordinate lastSegStartPt = edge.pts[ei1.segmentIndex];
//        // if the last intersection point is not equal to the its segment start pt,
//        // add it to the points list as well.
//        // (This check is needed because the distance metric is not totally reliable!)
//        // The check for point equality is 2D only - Z values are ignored
//        boolean useIntPt1 = ei1.dist > 0.0 || ! ei1.coord.equals2D(lastSegStartPt);
//        if (! useIntPt1) {
//            npts--;
//        }
//
//        Coordinate[] pts = new Coordinate[npts];
//        int ipt = 0;
//        pts[ipt++] = new Coordinate(ei0.coord);
//        for (int i = ei0.segmentIndex + 1; i <= ei1.segmentIndex; i++) {
//            pts[ipt++] = edge.pts[i];
//        }
//        if (useIntPt1) pts[ipt] = ei1.coord;
//        return new Edge(pts, new Label(edge.label));
//    }

    /**
     * @return
     */
    @Override
    public
    double[] getPixels () {
        return new double[(int) (getMat().total() * getMat().channels())];
    }

    /**
     * @param x
     * @param y
     * @param pixelData
     * @return
     */
    protected
    double[] put ( int x, int y, double[] pixelData ) {
        getMat().put(x, y, pixelData);

        return pixelData;
    }

    /**
     * @return
     */
    @Override
    public
    double[] getMeanPixelValue () {
        return meanPixelValue;
    }

    @Override
    public
    IImageBlock <A> getSubImage ( Rectangle rectangle ) throws ValueError {
        return this.getSubImage(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    @Override
    public
    IImageBlock <A> getSubImage () throws ValueError {
        return new ImageBlock <>(getMat(), new Rectangle(0, 0, getWidth(), getHeight()));
    }

    /**
     * @return
     */
    @Override
    public
    int getOriginalImageHeight () {
        return originalImageHeight;
    }

    public
    GeometryFactory getFactory () {
        return factory;
    }

    public
    double[] get ( int c, int r, double[] data ) {
        data = getMat().get(c, r);
        return data;
    }

    /**
     *
     */
    enum EColorType {
        GRAYSCALE(0),
        GRAYSCALE_WITH_ALPHA(4),
        INDEXED_COLOR(3),
        TRUE_COLOR(2),
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

    public
    int getChannelsAmount () {
        return getMat().channels();
    }

    @Override
    public
    double[] pixelValues ( int x, int y ) {
        return getPixel(x, y);
    }

    @Override
    public
    double getPixelValuesLayer ( int x, int y, int ch ) {
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
     * @param h
     * @param originalImageWidth
     * @param originalImageHeight
     * @return
     */
    @Override
    public
    IIntSize restoreSize ( int h, int originalImageWidth, int originalImageHeight ) {
        return null;
    }
}