package org.stranger2015.opencv.fic.transform;

//import org.jetbrains.annotations.Contract;
//import org.jetbrains.annotations.NotNull;
//import org.opencv.core.Mat;
//import org.opencv.core.Size;
//import org.opencv.imgproc.Imgproc;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.IImageBlock;
import org.stranger2015.opencv.fic.core.ValueError;

import static org.stranger2015.opencv.fic.core.EAddressKind.ORDINARY;
import static org.stranger2015.opencv.fic.core.IAddress.valueOf;
import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 * Produces transformed copies of a given input image.
 * <p>
 * The Affine transform theory:
 * <p>
 * [ x']   [  m00  m01  m02  ] [ x ]   [ m00x + m01y + m02 ]
 * [ y'] = [  m10  m11  m12  ] [ y ] = [ m10x + m11y + m12 ]
 * [ 1 ]   [   0    0    1   ] [ 1 ]   [         1         ]
 * <p>
 * The corresponding matrix values:
 * <p>
 * [  m00  m01  m02  ]     [ scx  shx  trx ]
 * [  m10  m11  m12  ] <=> [ shy  scy  try ]
 * [   0    0    1   ]     [  0   0     1  ]
 */
public
class ImageTransform implements ITransform {
    private IImageBlock image;
    private int originalDomainX;
    private int originalDomainY;

    /**
     * @param image
     * @param type
     * @param address
     */
    public
    ImageTransform ( IImage image,
                     EInterpolationType type,
                     IAddress address ) {
        this(address, 0, 1.0, -1);
        this.image = (IImageBlock) image;
        this.type = type;
    }

    /**
     * @throws ValueError
     */
    public
    ImageTransform () throws ValueError {
        address = valueOf(0, ORDINARY);//TODO squiral == SIP
        outputImage = null;
        type = BILINEAR;
        image = null;
    }

    /**
     * @param image
     * @param brightnessOffset
     * @param contrastScale
     * @param dihedralAffineTransformIndex
     */
//    @Contract(pure = true)
    public
    ImageTransform ( IAddress address,
                     int brightnessOffset,
                     double contrastScale,
                     int dihedralAffineTransformIndex
    ) {
        this.address = address;
        this.brightnessOffset = brightnessOffset;
        this.contrastScale = contrastScale;
        this.dihedralAffineTransformIndex = dihedralAffineTransformIndex;
    }

    /**
     * @param image
     * @param type
     * @param address
     * @param brightnessOffset
     * @param contrastScale
     * @param dihedralAffineTransformerIndex
     * @throws ValueError
     */
//    @Contract(pure = true)
    public
    ImageTransform ( IImageBlock image,
                     EInterpolationType type,
                     IAddress address,
                     int brightnessOffset,
                     double contrastScale,
                     int dihedralAffineTransformerIndex ) throws ValueError {
        this(
                address,
                brightnessOffset,
                contrastScale,
                dihedralAffineTransformerIndex
        );

        this.image = image;
        this.type = type;
    }

    /**
     * @param x
     * @param y
     * @param brightnessOfs
     * @param contrastScale
     * @param dAti
     * @return
     */
//    @Contract("_, _, _, _, _ -> new")
    public static// @NotNull
    ImageTransform create ( IAddress address, int brightnessOfs, double contrastScale, int dAti ) throws ValueError {
       /* @NotNull*/

        return new AffineTransform(
                 null,
                 BILINEAR,
                 address,
                 brightnessOfs,
                 contrastScale,
                 dAti
         ) {
         };
    }

    /**
     * @param x
     * @param y
     * @param brightnessOfs
     * @param contrastScale
     * @param dAti
     * @return
     */
    public static
    ImageTransform create ( int x, int stride, int y, int brightnessOfs, double contrastScale, int dAti )
            throws ValueError {

        return create(IAddress.valueOf(x, stride, y), brightnessOfs, contrastScale, dAti);
    }

    /**
     * @param address
     */
    public
    void setAddress ( IAddress address ) {
        this.address = address;
    }

    /**
     * @param originalDomainX
     */
    public
    void setOriginalDomainX ( int originalDomainX ) {
        this.originalDomainX = originalDomainX;
    }

    /**
     * @return
     */
    public
    int getOriginalDomainX () {
        return originalDomainX;
    }

    /**
     * @param originalDomainY
     */
    public
    void setOriginalDomainY ( int originalDomainY ) {
        this.originalDomainY = originalDomainY;
    }

    /**
     * @return
     */
    public
    int getOriginalDomainY () {
        return originalDomainY;
    }

    public
    IImageBlock getImage () {
        return image;
    }

    /**
     *
     */
    public
    enum Masks {
        X_MASK(/*------------------->*/0b1111_1111_0000_0000_0000_0000_0000_0000, 0, 8),
        Y_MASK(/*------------------->*/0b0000_0000_1111_1111_0000_0000_0000_0000, 8, 8),
        BRIGHTNESS_OFS_MASK(/*------>*/0b0000_0000_0000_0000_1111_1000_0000_0000, 16, 5),
        CONTRAST_SCALE_MASK(/*------>*/0b0000_0000_0000_0000_0000_0111_1111_0000, 21, 7),
        DIHEDRAL_TRANSFORM_MASK(/*-->*/0b0000_0000_0000_0000_0000_0000_0000_1110, 28, 3),
        ;
        private final int shift;
        private final int length;
        private final int mask;

        /**
         * @param i
         * @param i1
         * @param mask
         * @param shift
         * @param length
         */
//        @Contract(pure = true)
        Masks ( int mask, int shift, int length ) {
            this.mask = mask;
            this.shift = shift;
            this.length = length;
        }

        /**
         * @return
         */
//        @Contract(pure = true)
        public
        int getMask () {
            return mask;
        }

        public
        int getShift () {
            return shift;
        }

        public
        int getLength () {
            return length;
        }
    }

    protected IImage outputImage;
    protected EInterpolationType type;

    protected IAddress address;//16 bits        >>> 16
    public int brightnessOffset;//5 bits             >>> 11
    public double contrastScale; //0..255, 7 bits   >>> 4
    public int dihedralAffineTransformIndex;//8 matrices, 3 bits >>> 1

    /**
     * @param image
     * @param address
     * @param
     * @param <G>
     * @return
     */
//    @Contract("_, _ -> new")
    @SuppressWarnings("unchecked")
    public static
//    @NotNull
    ImageTransform create ( IImage image, IAddress address ) throws ValueError {
        return new NoneTransform(
                image,
                BILINEAR,
                address,
                0,
                1,
                -1);
    }

    /**
     * @param inputImage
     * @param transformMatrix
     * @param type
     * @return
     */
    @Override
    public
    IImage transform ( IImage inputImage,
                       Mat transformMatrix,
                       EInterpolationType type ) {

        return ITransform.super.transform(inputImage, transformMatrix, type);
    }

    /**
     * @return
     */
    public
    IImage getOutputImage () {
        return outputImage;
    }

    /**
     * @param inputImage
     * @param transformMatrix
     * @param interpolationType
     * @return
     */
    @Override
    public final
    IImage warpAffine ( IImage inputImage, Mat transformMatrix, EInterpolationType interpolationType ) {
        Imgproc.warpAffine(
                inputImage.getMat(),
                outputImage.getMat(),
                transformMatrix,
                new Size(1, 1));//fixme

        return outputImage;
    }

    /**
     * @return
     */
    @Override
    public
    IAddress getAddress () {
        return address;
    }
}