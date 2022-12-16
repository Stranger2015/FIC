package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.search.EMetrics;

/**
 * an image comparator returns the distance between two images
 */
public
class ImageComparator<A extends IAddress >
        implements IDistanceator {

    private final double fuzz;
    private final EMetrics metrics;

    /**
     * Distance between two given objects
     *
     * @return the distance as defined by a metric between the objects
     * @see EMetrics
     */
    @Contract(value = "null -> fail", pure = true)
    public
    ImageComparator ( EMetrics distanceMetrics ) {
        this(distanceMetrics, 0);
    }

    /**
     * @param distanceMetrics
     * @param fuzz
     */
    @Contract(value = "null, _ -> fail", pure = true)
    public
    ImageComparator ( final EMetrics distanceMetrics, final double fuzz ) {
        assert (distanceMetrics != null) && (fuzz >= 0);

        this.metrics = distanceMetrics;
        this.fuzz = fuzz;
    }

    /**
     * Measure the distance between two images as defined by the pre-defined metric
     * <p>
     * NOTE: the images must have the same size!
     *
     * @param img1 the first image to compare to
     * @param img2 the second image to compare with
     * @return the distance between the images
     * @see EMetrics
     */
    @Override
    public
    double distance ( final IImage img1, final IImage img2 ) {
        assert (img1 != null) && (img2 != null);

        int width = img1.getWidth();
        int height = img1.getHeight();
        int area = width * height;

        //        img1.getRGB(0, 0, width, height, img1pixels, 0, 0);
//        img2.getRGB(0, 0, width, height, img2pixels, 0, 0);

        double distance = 0;

        double[] img1pixels = new double[area];
        double[] img2pixels = new double[area];
        for (int pixelRow = 0; pixelRow < height; pixelRow++) {
            for (int pixelCol = 0; pixelCol < width; pixelCol++) {
                distance += metrics.distance(
                        img1pixels[pixelRow * width + pixelCol],
                        img2pixels[pixelRow * width + pixelCol], fuzz);
            }
        }

        return distance;
    }

    /**
     * @return
     */
    public
    EMetrics getMetrics () {
        return metrics;
    }
}
