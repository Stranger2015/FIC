package org.stranger2015.opencv.fic.transform;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.codec.DecAddress;
import org.stranger2015.opencv.fic.core.codec.IAddress;
import org.stranger2015.opencv.utils.BitBuffer;

import java.io.Serializable;

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
class ImageTransform<M extends  IImage<A> , A extends Address <A>, G extends BitBuffer>
        implements ITransform <M, A, G>, Serializable {

    /**
     * @param image
     * @param type
     * @param address
     */
    public
    ImageTransform ( M image, EInterpolationType type, IAddress <A> address ) {
        this(image, type, address, 0, 1.0, -1);
    }

    /**
     * @throws ValueError
     */
    protected
    ImageTransform () throws ValueError {
        address = new DecAddress <>(0);
        outputImage = null;
        type = EInterpolationType.BILINEAR;
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

    protected final M outputImage;
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
    @SuppressWarnings("unchecked")
    public
    ImageTransform ( M image,
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

        outputImage = (M) new CompressedImage<>(image);
    }

    /**
     * @param image
     * @param address
     * @param <M>
     * @param <A>
     * @param <G>
     * @return
     */
    @Contract("_, _ -> new")
    public static
    <M extends  IImage<A> , A extends Address <A>, G extends BitBuffer>
    @NotNull ImageTransform <M, A, G> create ( M image, IAddress <A> address ) {
        return new NoneTransform <>(
                image,
                null,
                address,
                0,
                1,
                -1);
    }

    /**
     *
     *
     * @return
     */
    public
    M getOutputImage () {
        return outputImage;
    }

    /**
     *
     *
     * @param inputImage
     * @param transformMatrix
     * @param interpolationType
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    Mat warpAffine ( Mat inputImage, Mat transformMatrix, EInterpolationType interpolationType ) {
        M outputImage = (M) new Image <>(inputImage);
        Imgproc.warpAffine( inputImage, outputImage.getMat(), transformMatrix, new Size(1,1));//fixme

        return outputImage.getMat();
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
    void writeBits ( G bb, ImageTransform <M, A, G> transform ) {
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
    ImageTransform <M, A, G> readBits ( G bb ) throws ValueError {
        address.newInstance((int) bb.readBits(16));
        brightnessOffset = (int) bb.readBits(5);
        contrastScale = bb.readBits(7) / 255.0;
        dihedralAffineTransformIndex = (int) bb.readBits(3);

        return this;
    }
}
