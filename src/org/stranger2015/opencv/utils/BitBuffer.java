package org.stranger2015.opencv.utils;

//import jdk.internal.misc.Unsafe;

import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.nio.ByteBuffer;

import static java.lang.Double.doubleToLongBits;
import static java.lang.Double.longBitsToDouble;
import static org.stranger2015.opencv.fic.core.search.ExhaustiveSearchProcessor.ifsRecordLength;
import static org.stranger2015.opencv.fic.transform.ImageTransform.Masks.*;

/**
 *
 */
public
class BitBuffer {
    final static int wordLength = 64;
    //    private static final byte[] MASKS = new byte[wordLength];
//    private static final Unsafe UNSAFE;

    static {
//        for (int i = 0; i < MASKS.length; i++)
//            MASKS[i] = (byte) (pow(2, i) - 1);
//            MASKS[i] = (byte) (1 << i - 1);

        try {
//            final Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
//            theUnsafe.setAccessible(true);
//            UNSAFE = (Unsafe) theUnsafe.get(null);
        } catch (Exception reason) {
            throw new RuntimeException(reason);
        }
    }

    int wordMaskDisplacement = 0;
    int recCount = 0;

    /**
     * @return
     */
    public
    ByteBuffer getBytes () {
        return bytes;
    }

    private ByteBuffer bytes;
    private int position = 0;
    private long buffer;

    /**
     * @param bytes
     */
    public
    BitBuffer ( byte[] ifsRecords ) {
        bytes.get(ifsRecords);
        buffer = bytes.getLong(position);
    }

    /**
     * @param value
     * @param shift
     * @return
     */
    public
    int writeBits ( long value, int shift ) {
        buffer |= value >>> shift;

        //Save the full buffer to memory and pull out the next one.
        if (wordMaskDisplacement != 0) {
            buffer = bytes.putLong(position++).getLong(position) | value << (shift -= wordLength);
        }

        return shift;
    }

    /**
     * @param count
     * @param shift
     * @return
     */
    public
    int readBits ( int mask, int shift ) {
        long value = 0;

        if (wordMaskDisplacement != 0) {
            value |= (buffer = bytes.getLong(position++)) << (shift -= wordLength);
        }

        return (int) value & (mask << shift);
    }

    /**
     * @param position
     * @return
     */
    public
    BitBuffer position ( int position ) {
        this.position = position;

        return this;
    }

    /**
     * @return
     */
    public
    BitBuffer clear () {
        return position(0);
    }

    /**
     * @param bytes
     * @return
     */
    public static
    BitBuffer allocate ( int bytes ) {
        return new BitBuffer(ByteBuffer.allocate(bytes).array());
    }

    /**
     * @param stride
     * @return
     */
    public
    ImageTransform readIfsCodeRecord ( int stride ) throws ValueError {

        wordMaskDisplacement = recCount * (ifsRecordLength - wordLength) % wordLength;

        int x = readBits(
                X_MASK.getMask(),
                X_MASK.getShift()
        );
        int y = readBits(
                Y_MASK.getMask(),
                Y_MASK.getShift()
        );
        int brightnessOfs = readBits(
                BRIGHTNESS_OFS_MASK.getMask(),
                BRIGHTNESS_OFS_MASK.getShift()
        );
        double contrastScale = longBitsToDouble(
                readBits(
                        CONTRAST_SCALE_MASK.getMask(),
                        CONTRAST_SCALE_MASK.getShift()
                )
        );
        int dAti = readBits(
                DIHEDRAL_TRANSFORM_MASK.getMask(),
                DIHEDRAL_TRANSFORM_MASK.getShift()
        );

        recCount++;

        return ImageTransform.create(x, stride,y, brightnessOfs, contrastScale, dAti);
    }

    /**
     * @param transform
     * @param
     */
    public
    void writeIfsCodeRecord ( ImageTransform transform ) {
        wordMaskDisplacement = recCount * (ifsRecordLength - wordLength) % wordLength;

        int x = transform.getAddress().getX();
        int y = transform.getAddress().getY();
        int brightnessOfs = transform.brightnessOffset;
        double contrastScale = transform.contrastScale;
        int dAti = transform.dihedralAffineTransformIndex;

        writeBits(x, X_MASK.getShift());
        writeBits(y, Y_MASK.getShift());
        writeBits(brightnessOfs, BRIGHTNESS_OFS_MASK.getShift());
        writeBits((int) doubleToLongBits(contrastScale), CONTRAST_SCALE_MASK.getShift());
        writeBits(dAti, DIHEDRAL_TRANSFORM_MASK.getShift());

        recCount++;
    }

    /**
     * @return
     */
    public
    long getBuffer () {
        return buffer;
    }

    /**
     * @param bytes
     */
    public
    void setBytes ( ByteBuffer bytes ) {
        this.bytes = bytes;
    }
}