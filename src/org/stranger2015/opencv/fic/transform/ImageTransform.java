package org.stranger2015.opencv.fic.transform;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.utils.BitBuffer;

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
public abstract
class ImageTransform<A extends IAddress <A>, G extends BitBuffer>
        implements ITransform <A, G> {

    /**
     * @param image
     * @param type
     * @param address
     */
    public
    ImageTransform ( IImage <A> image, EInterpolationType type, IAddress <A> address ) {
        this(image, type, address, 0, 1.0, -1);
    }

    /**
     * @throws ValueError
     */
    protected
    ImageTransform () throws ValueError {
        address = valueOf(0);
        outputImage = null;
        type = BILINEAR;
    }

    /**
     * @param address
     */
    public
    void setAddress ( @NotNull IAddress <A> address ) {
        this.address = address;
    }

    /**
     *
     */
    enum Masks {
        ADDRESS_MASK(0b1111_1111_1111_1111_0000_0000_0000_0000),
        BRIGHTNESS_OFFSET_MASK(0b0000_0000_0000_0000_1111_1000_0000_0000),
        CONTRAST_SCALE_MASK(0b0000_0000_0000_0000_0000_0111_1111_0000),
        DIHEDRAL_TRANSFORM_MASK(0b0000_0000_0000_0000_0000_0000_0000_1110),
        ;

        private final int mask;

        /**
         * @param mask
         */
        @Contract(pure = true)
        Masks ( int mask ) {
            this.mask = mask;
        }

        /**
         * @return
         */
        @Contract(pure = true)
        public
        int getMask () {
            return mask;
        }
    }

    protected final IImage <A> outputImage;
    protected final EInterpolationType type;

    protected IAddress <A> address;//16 bits        >>> 16
    public int brightnessOffset;//5 bits             >>> 11
    public double contrastScale; //0..255, 7 bits   >>> 4
    public int dihedralAffineTransformIndex;//8 matrices, 3 bits >>> 1

    /**
     * @param image
     * @param brightnessOffset
     * @param contrastScale
     * @param dihedralAffineTransformIndex
     */
//    @SuppressWarnings("unchecked")
    public
    ImageTransform ( IImage <A> image,
                     EInterpolationType type,
                     IAddress <A> address,
                     int brightnessOffset,
                     double contrastScale,
                     int dihedralAffineTransformIndex
    ) {
        this.type = type;
        this.address = address;
        this.brightnessOffset = brightnessOffset;
        this.contrastScale = contrastScale;
        this.dihedralAffineTransformIndex = dihedralAffineTransformIndex;

        outputImage = new CompressedImage <>(image);
    }

    /**
     * @param image
     * @param address
     * @param <A>
     * @param <G>
     * @return
     */
    @Contract("_, _ -> new")
    @SuppressWarnings("unchecked")
    public static
    <A extends IAddress <A>, G extends BitBuffer>
    @NotNull ImageTransform <A, G> create ( IImage <A> image, IAddress <A> address ) {
        return new NoneTransform <>(
                image,
                null,
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
    IImage <A> transform ( @NotNull IImage <A> inputImage,
                           IImage <A> transformMatrix,
                           EInterpolationType type ) {

        return ITransform.super.transform(inputImage, transformMatrix, type);
    }

    /**
     * @return
     */
    public
    IImage <A> getOutputImage () {
        return outputImage;
    }

    /**
     * @param inputImage
     * @param transformMatrix
     * @param interpolationType
     * @return
     */
//    @SuppressWarnings("unchecked")
    @Override
    public final
    IImage <A> warpAffine ( IImage <A> inputImage, IImage <A> transformMatrix, EInterpolationType interpolationType ) {
        IImage <A> outputImage = new GrayScaleImage <>(inputImage);
        Imgproc.warpAffine(
                inputImage.getMat(),
                outputImage.getMat(),
                transformMatrix.getMat(),
                new Size(1, 1));//fixme

        return outputImage;
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
     * @param transform
     */
    @Override
    public final
    void writeBits ( G bb, ImageTransform <A, G> transform ) {
        bb.writeBits(transform.dihedralAffineTransformIndex, 3);
        bb.writeBits((long) transform.contrastScale * 255, 7);
        bb.writeBits(transform.brightnessOffset, 5);
        bb.writeBits(transform.address.getIndex(), 16);
    }

    /**
     * @param bb
     * @return
     */
    @Override
    public final
    ImageTransform <A, G> readBits ( G bb ) throws ValueError {
        address.newInstance((int) bb.readBits(16));
        brightnessOffset = (int) bb.readBits(5);
        contrastScale = bb.readBits(7) / 255.0;
        dihedralAffineTransformIndex = (int) bb.readBits(3);

        return this;
    }
}