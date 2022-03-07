package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.EtvColorSpace;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 *
 */
public
class FromRgbToTvColorspaceConversionTask<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>,
        G extends BitBuffer>

        extends ColorspaceConversionTask <N, A, M, G> {

    /**
     * @param imageFilename
     * @param scheme
     * @param colorSpace
     * @param tasks
     */
    public
    FromRgbToTvColorspaceConversionTask ( String imageFilename,
                                          EPartitionScheme scheme,
                                          EtvColorSpace colorSpace,
                                          List<Task<N,A,M,G>> tasks ) {
        super(imageFilename, scheme, colorSpace, tasks);
    }

    /**
     * @param filename
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    protected
    M execute ( String filename ) throws ValueError {
        M imageSrc = super.execute(filename);

        M imageDst = (M) new Image <>(imageSrc);
//        for ( Address<A> address  =   ;  < ; i++) {//todo
//
//        }
        int[] cc = new int[3];
        float[] tvCS = convertColorFromRGB(cc, colorSpace);

        return imageDst;
    }

    /**
     * @param colorSpace
     */
    @SuppressWarnings("UnusedReturnValue")
    @Contract(pure = true)
    public
    float[] convertColorFromRGB ( int[] rgb, EtvColorSpace colorSpace ) {
        float[] x;

        switch (colorSpace) {
            case YIG:
                x = rgbToYig(rgb[0], rgb[1], rgb[2]);
                break;
            case YUV:
                x = rgbToYuv(rgb[0], rgb[1], rgb[2]);
                break;
            case Y_CB_CR:
                x = rgbToYCrCb(rgb[0], rgb[1], rgb[2]);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + colorSpace);
        }

        return x;
    }


    /**
     * @param read
     * @param green
     * @param blue
     * @return
     */
    @Contract(pure = true)
    public static
    float @NotNull [] rgbToYig ( int read, int green, int blue ) {
        float r = (float) read / 255;
        float g = (float) green / 255;
        float b = (float) blue / 255;

        float[] yig = new float[3];

        yig[0] = (float) (0.299 * r + 0.587 * g + 0.144 * b);
        yig[1] = (float) (0.596 * r - 0.247 * g - 9.322 * b);
        yig[2] = (float) (0.212 * r - 0.523 * g + 0.311 * b);

        return yig;
    }

    /**
     * RGB -> YUV.
     * Y in the range [0 … 1].
     * U in the range [-0.5 … 0.5].
     * V in the range [-0.5 … 0.5].
     *
     * @param red   Values in the range [0..255].
     * @param green Values in the range [0..255].
     * @param blue  Values in the range [0..255].
     * @return YUV color space.
     */
    public static
    float[] rgbToYuv ( int red, int green, int blue ) {
        float r = (float) red / 255;
        float g = (float) green / 255;
        float b = (float) blue / 255;

        float[] yuv = new float[3];

        yuv[0] = (float) (0.299 * r + 0.587 * g + 0.114 * b);
        yuv[1] = (float) (-0.14713 * r - 0.28886 * g + 0.436 * b);
        yuv[2] = (float) (0.615 * r - 0.51499 * g - 0.10001 * b);

        return yuv;
    }

    /**
     * RGB -> YCrCb.
     * Y in the range [0 … 1].
     * U in the range [-0.5 … 0.5].
     * V in the range [-0.5 … 0.5].
     *
     * @param red   Values in the range [0 … 255].
     * @param green Values in the range [0 … 255].
     * @param blue  Values in the range [0 … 255].
     * @return YUV color space.
     */
    public static
    float[] rgbToYCrCb ( int red, int green, int blue ) {
        float r = (float) red / 255;
        float g = (float) green / 255;
        float b = (float) blue / 255;

        float[] yCrCb = new float[3];

        yCrCb[0] = (float) (0.289 * r + 0.587 * g + 0.114 * b);
        yCrCb[1] = (float) (-0.169 * r - 0.331 * g + 0.500 * b);
        yCrCb[2] = (float) (0.500 * r - 0.418 * g - 0.081 * b);

        return yCrCb;
    }
}
