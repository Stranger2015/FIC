package org.stranger2015.opencv.fic.core;

import java.util.List;

/**
 *
 */
public
class RgbToYuvImageColorModelTask extends LoadSaveImageTask {


    /**
     * @param fileName
     * @param tasks
     */
    public
    RgbToYuvImageColorModelTask ( String fileName, EPartitionScheme scheme, Task... tasks ) {
        super(fileName, scheme, tasks);
    }


    /**
     * @param fileName
     * @param tasks
     */
    public
    RgbToYuvImageColorModelTask ( String fileName, EPartitionScheme scheme, List <Task> tasks ) {
        super(fileName, scheme, tasks);
    }

    /**
     * RGB -> YUV.
     * Y in the range [0 .. 1].
     * U in the range [-0.5 .. 0.5].
     * V in the range [-0.5 .. 0.5].
     *
     * @param red   Values in the range [0..255].
     * @param green Values in the range [0..255].
     * @param blue  Values in the range [0..255].
     * @return YUV color space.
     */
    public static
    float[] rgbToYUV ( int red, int green, int blue ) {
        float r = (float) red / 255;
        float g = (float) green / 255;
        float b = (float) blue / 255;

        float[] yuv = new float[3];
        float y;
        float u;
        float v;

        y = (float) (0.299 * r + 0.587 * g + 0.114 * b);
        u = (float) (-0.14713 * r - 0.28886 * g + 0.436 * b);
        v = (float) (0.615 * r - 0.51499 * g - 0.10001 * b);
        yuv[0] = y;
        yuv[1] = u;
        yuv[2] = v;

        return yuv;
    }
}
