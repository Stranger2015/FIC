package org.stranger2015.opencv.fic.core.search;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IDistanceator;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.IImageBlock;

/**
 
 * @param
 */
public
class ImageComparator implements IDistanceator {

    private final double fuzz;
    private final EMetrics metrics;

    /**
     * @param distanceMetric
     */
    @Contract(value = "null -> fail", pure = true)
    public
    ImageComparator ( EMetrics distanceMetric ) {
        this(distanceMetric, 0);
    }

    /**
     * @param distanceMetric
     * @param fuzz
     */
    @Contract(value = "null, _ -> fail", pure = true)
    public
    ImageComparator ( EMetrics distanceMetric, final double fuzz ) {
        assert (distanceMetric != null) && (fuzz >= 0);

        this.metrics = distanceMetric;
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
    double distance ( final IImageBlock img1, final IImageBlock img2 ) {
        assert (img1 != null) && (img2 != null);

        int width = img1.getWidth();
        int height = img1.getHeight();
        int area = width * height;

        int     channels=img1.getChannelsAmount();
        double[] distance=new double[channels];
        for (int ch = 0; ch < channels; ch++) {
            distance[ch]=0;
        }
        double[] img1pixels = new double[area];
        double[] img2pixels = new double[area];

        img1.getRGB(0, 0, width, height, img1pixels, 0, 0);
        img2.getRGB(0, 0, width, height, img2pixels, 0, 0);

        for (int pixelRow = 0; pixelRow < height; pixelRow++) {
            for (int pixelCol = 0; pixelCol < width; pixelCol++) {
                distance += metrics.distance(
                        img1pixels[pixelRow * width + pixelCol],
                        img2pixels[pixelRow * width + pixelCol],
                        fuzz
                );
            }
        }

        return distance;
    }

    /**
     * Distance between two given objects
     *
     * @param image1
     * @param image2
     * @return the distance as defined by a metric between the objects
     * @see EMetrics
     */
    @Override
    public
    double distance ( IImage image1, IImage image2 ) {
        return 0;
    }
}
