package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.codec.EtvColorSpace;
import org.stranger2015.opencv.fic.core.codec.ICodec;

import java.util.List;

import static org.stranger2015.opencv.fic.core.CodecTask.ColorspaceConversionTask;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class FromTvToRgbColorspaceConversionTask        extends ColorspaceConversionTask  {

    /**
     * @param imageFilename
     * @param scheme
     * @param colorSpace
     * @param tasks
     */
    public
    FromTvToRgbColorspaceConversionTask ( String imageFilename,
                                          EPartitionScheme scheme,
                                          ICodec  codec,
                                          EtvColorSpace colorSpace,
                                          List <Task > tasks ) {

        super(imageFilename, scheme, codec, colorSpace, tasks);
    }

    /**
     * yig -> RGB.
     *
     * @param y Luma. In the range [0..1].
     * @param i Chrominance. In the range [-0.5..0.5].
     * @param g Chrominance. In the range [-0.5..0.5].
     * @return RGB color space.
     */
    @Contract(pure = true)
    public static
    int @NotNull [] yigToRGB ( float y, float i, float g ) {
        int[] rgb = new int[3];

        rgb[0] = (int) ((1.000 * y + 0.956f * i + 0.621f * g) * 255);
        rgb[1] = (int) ((1.000 * y - 0.272f * i - 0.647f * g) * 255);
        rgb[2] = (int) ((1.000 * y - 1.105f * i + 1.702f * g) * 255);

        return rgb;
    }

    /**
     * YUV -> RGB.
     *
     * @param y Luma. In the range [0..1].
     * @param u Chrominance. In the range [-0.5..0.5].
     * @param v Chrominance. In the range [-0.5..0.5].
     * @return RGB color space.
     */
    @Contract(pure = true)
    public static
    int @NotNull [] yuvToRGB ( float y, float u, float v ) {
        int[] rgb = new int[3];

        rgb[0] = (int) ((1.000 * y + 0.000f * u + 1.140f * v) * 255);
        rgb[1] = (int) ((1.000 * y - 0.396f * u - 0.581f * v) * 255);
        rgb[2] = (int) ((1.000 * y + 2.029f * u + 0.000f * v) * 255);

        return rgb;
    }

    /**
     * YCrCb -> RGB.
     *
     * @param y  Luma. In the range [0..1].
     * @param cr Chrominance. In the range [-0.5..0.5].
     * @param cb Chrominance. In the range [-0.5..0.5].
     * @return RGB color space.
     */
    @Contract(pure = true)
    public static
    int @NotNull [] yCrCbToRGB ( float y, float cr, float cb ) {
        int[] rgb = new int[3];

        rgb[0] = (int) ((1.000 * y + 0.000 * cr + 1.403 * cb) * 255);
        rgb[1] = (int) ((1.000 * y - 0.344 * cr + 0.500 * cb) * 255);
        rgb[2] = (int) ((1.000 * y + 1.772 * cr + 0.000 * cb) * 255);

        return rgb;
    }

    /**
     * YPrPb -> RGB.
     *
     * @param y  Luma. In the range [0..1].
     * @param pr Chrominance. In the range [-0.5..0.5].
     * @param pb Chrominance. In the range [-0.5..0.5].
     * @return RGB color space.
     */
    @Contract(pure = true)
    public static
    int @NotNull [] yPrPbToRGB ( float y, float pr, float pb ) {
        int[] rgb = new int[3];

        rgb[0] = (int) ((1.000 * y + 0.000 * pr + 1.575 * pb) * 255);
        rgb[1] = (int) ((1.000 * y - 0.187 * pr - 0.468 * pb) * 255);
        rgb[2] = (int) ((1.000 * y + 1.856 * pr + 0.000 * pb) * 255);

        return rgb;
    }

    /**
     * @param cc
     * @param colorSpace
     * @return
     */
    @Contract(pure = true)
    public
    int[] convertColorToRGB ( float[] cc, EtvColorSpace colorSpace ) {
        int[] x;

        switch (colorSpace) {
            case YIG:
                x = yigToRGB(cc[0], cc[1], cc[2]);
                break;
            case YUV:
                x = yuvToRGB(cc[0], cc[1], cc[2]);
                break;
            case Y_CB_CR:
                x = yCrCbToRGB(cc[0], cc[1], cc[2]);
                break;
            case Y_PB_PR:
                x = yPrPbToRGB(cc[0], cc[1], cc[2]);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + colorSpace);
        }

        return x;
    }

    /**
     * @return
     */
//    @Override
    public
    EtvColorSpace getColorSpace () {
        return colorSpace;
    }
}
