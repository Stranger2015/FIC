package org.stranger2015.opencv.fic.core.search;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.Distanceator;
import org.stranger2015.opencv.fic.core.IImage;


public
class ImageComparator<M extends IImage<A>, A extends Address<A>> extends Distanceator<M> {

        private final double fuzz;
        private final EMetrics metrics;

        public ImageComparator(EMetrics distanceMetric) {
            this(distanceMetric, 0);
        }

        public ImageComparator(EMetrics distanceMetric, final double fuzz) {
            assert (distanceMetric != null) && (fuzz >= 0);

            this.metrics = distanceMetric;
            this.fuzz   = fuzz;
        }

        /**
         * Measure the distance between two images as defined by the pre-defined metric
         *
         * NOTE: the images must have the same size!
         *
         * @param img1 the first image to compare to
         * @param img2 the second image to compare with
         * @return the distance between the images
         *
         * @see EMetrics
         */
        @Override
        public double distance( final M img1, final M img2) {
            assert (img1 != null) && (img2 != null);

            int width  = img1.getWidth();
            int height = img1.getHeight();
            int area   = width * height;

            int[] img1pixels = new int[area];
            int[] img2pixels = new int[area];
            img1.getRGB(0, 0, width, height, img1pixels, 0, 0);
            img2.getRGB(0, 0, width, height, img2pixels, 0, 0);

            double distance = 0;

            for (int pixelrow = 0; pixelrow < height; pixelrow++) {
                for (int pixelcol = 0; pixelcol < width; pixelcol++) {
                    distance += metrics.distance(img1pixels[pixelrow * width + pixelcol],
                            img2pixels[pixelrow * width + pixelcol],
                            fuzz);
                }
            }

            return distance;
        }
}
