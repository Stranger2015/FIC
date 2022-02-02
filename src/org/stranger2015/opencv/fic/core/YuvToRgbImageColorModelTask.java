package org.stranger2015.opencv.fic.core;

import java.util.List;

public
class YuvToRgbImageColorModelTask extends LoadSaveImageTask {
    /**
     * @param fileName
     * @param tasks
     */
    public
    YuvToRgbImageColorModelTask ( String fileName, EPartitionScheme scheme, Task... tasks ) {
        super(fileName, scheme, tasks);
    }

    /**
     * @param fileName
     * @param tasks
     */
    public
    YuvToRgbImageColorModelTask ( String fileName, EPartitionScheme scheme, List <Task> tasks ) {
        super(fileName, scheme, tasks);
    }

    /**
     * YUV -> RGB.
     *
     * @param y Luma. In the range [0..1].
     * @param u Chrominance. In the range [-0.5..0.5].
     * @param v Chrominance. In the range [-0.5..0.5].
     * @return RGB color space.
     */
    public static
    int[] yuvToRGB ( float y, float u, float v ) {
        int[] rgb = new int[3];
        float r, g, b;

        r = (float) ((y + 0.000 * u + 1.140 * v) * 255);
        g = (float) ((y - 0.396 * u - 0.581 * v) * 255);
        b = (float) ((y + 2.029 * u + 0.000 * v) * 255);

        rgb[0] = (int) r;
        rgb[1] = (int) g;
        rgb[2] = (int) b;

        return rgb;
    }
}
