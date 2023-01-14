package org.stranger2015.opencv.fic.core.search;


import org.opencv.core.Core;
import org.stranger2015.opencv.fic.core.IImageBlock;

import java.util.stream.IntStream;

/**
 * different metrics to count the distance of two integers representing pixels.<br />
 *
 * @see #distance(double, double)
 */
public
enum
EMetrics {

    /**
     * absolute error count, number of different pixels (-fuzz effected) <br />
     * Return 1 if pixels differ, 0 if there is no difference
     */
    AE {
//        /**
//         * @param a    a pixel to compare to
//         * @param b    a pixel to compare with
//         * @param fuzz color normalization factor
//         * @return
//         */
//        @Override
//        public final
//        double[] distance ( final double[] a, final double[] b, final double[] fuzz ) {
//            double[] result = new double[a.length];
//            for (int i = 0; i < a.length; i++) {
//                result[i] = (a[i] == b[i]) ? 0 : 1;
//            }
//
//            return result;
//        }
//
//        /**
//         * @param a a pixel to compare to
//         * @param b a pixel to compare with
//         * @return
//         */
//        @Override
//        public final
//        double[] distance ( final double[] a, final double[] b ) {
//            double[] result = new double[a.length];
//            for (int i = 0; i < a.length; i++) {
//                result[i] = (a[i] == b[i]) ? 0 : 1;
//            }
//
//            return result;
//        }
//
        /**
         * @param imageBlock1
         * @param imageBlock2
         * @return
         */
        @Override
        public
        double[] error ( IImageBlock imageBlock1, IImageBlock imageBlock2 ) {
            return new double[0];
        }
    },

    /**
     * mean color distance
     */
    FUZZ {
//        @Override
//        public final
//        double[] distance ( final double[] a, final double[] b ) {
//            double[] result = new double[a.length];
//
//            return result;
//        }

        /**
         * @param imageBlock1
         * @param imageBlock2
         * @return
         */
        @Override
        public
        double[] error ( IImageBlock imageBlock1, IImageBlock imageBlock2 ) {
            return new double[0];
        }
    },

    /**
     * mean absolute error (normalized), average channel error distance
     */
    MAE {
//        @Override
//        public final
//        double[] distance ( final double[] a, final double[] b ) {
//            double[] result = new double[a.length];
//
//            return result;
//        }

        /**
         * @param imageBlock1
         * @param imageBlock2
         * @return
         */
        @Override
        public
        double[] error ( IImageBlock imageBlock1, IImageBlock imageBlock2 ) {
            return new double[0];
        }
    },

    /**
     * mean error per pixel (normalized mean error, normalized peak error)
     */
    MEPP {
//        @Override
//        public final
//        double[] distance ( final double[] a, final double[] b ) {
//            double[] result = new double[a.length];
//
//            return result;
//        }

        /**
         * @param imageBlock1
         * @param imageBlock2
         * @return
         */
        @Override
        public
        double[] error ( IImageBlock imageBlock1, IImageBlock imageBlock2 ) {
            return new double[0];
        }
    },

    /**
     * mean error squared, average of the channel error squared <br />
     * {@code MSE = x^2 + y^2 + ... + z^2;}
     */
    MSE {
        /**
         * @param a a pixel to compare to
         * @param b a pixel to compare with
         * @return
         */
//        @Override
//        public final
//        double[] distance ( final double[] a, final double[] b ) {
//       int reddiff   = PixelUtils.getRed  (a) - PixelUtils.getRed  (b);
//            int greendiff = PixelUtils.getGreen(a) - PixelUtils.getGreen(b);
//            int bluediff  = PixelUtils.getBlue (a) - PixelUtils.getBlue (b);
//
//            return reddiff * reddiff + greendiff * greendiff + bluediff * bluediff;


//            double[] result = new double[a.length];
//
//            return result;
//        }
//
        /**
         * @param imageBlock1
         * @param imageBlock2
         * @return
         */
        @Override
        public
        double[] error ( IImageBlock imageBlock1, IImageBlock imageBlock2 ) {
            return new double[0];
        }
    },

    /**
     * normalized cross correlation
     */
    NCC {
//        @Override
//        public final
//        double[] distance ( final double[] a, final double[] b ) {
//            double[] result = new double[a.length];
//
//            return result;
//        }

        /**
         * @param imageBlock1
         * @param imageBlock2
         * @return
         */
        @Override
        public
        double[] error ( IImageBlock imageBlock1, IImageBlock imageBlock2 ) {
            return new double[0];
        }
    },

    /**
     * peak absolute (normalize peak absolute)
     */
    PAE {
//        @Override
//        public final
//        double[] distance ( final double[] a, final double[] b ) {
//            double[] result = new double[a.length];
//
//            return result;
//        }

        /**
         * @param imageBlock1
         * @param imageBlock2
         * @return
         */
        @Override
        public
        double[] error ( IImageBlock imageBlock1, IImageBlock imageBlock2 ) {
            return new double[0];
        }
    },

    /**
     * peak signal to noise ratio
     */
    PSNR {
//        /**
//         * @param a a pixel to compare to
//         * @param b a pixel to compare with
//         * @return
//         */
//        @Override
//        public final
//        double[] distance ( final double[] a, final double[] b ) {
//            double[] result = Core.PSNR()//new double[a.length];
//
//            return result;
//        }
//
        /**
         * @param imageBlock1
         * @param imageBlock2
         */
        public
        double[] error ( IImageBlock imageBlock1, IImageBlock imageBlock2 ){
            Core.PSNR(imageBlock1.getMat(), imageBlock2.getMat());
            return new double[0];//fixme
        }
    },

    /**
     * root mean squared (normalized root mean squared). <br/>
     * {@code RMSE = Sqrt( x^2 + y^2 + ... + z^2 );}
     */
    RMSE {
        /**
         * @param a a pixel to compare to
         * @param b a pixel to compare with
         * @return
         */
//        @Override
//        public final
//        double[] distance ( final double[] a, final double[] b ) {
//            double[] result = new double[a.length];
//            for (int i = 0; i < a.length; i++) {
//                result[i] = (a[i] == b[i]) ? 0 : 1;
//            }
//
//            return result;
//        }

        /**
         * @param imageBlock1
         * @param imageBlock2
         * @return
         */
        @Override
        public
        double[] error ( IImageBlock imageBlock1, IImageBlock imageBlock2 ) {
            return new double[0];
        }
    },
    ;

    /**
     * @param a    a pixel to compare to
     * @param b    a pixel to compare with
     * @param fuzz color normalization factor
     * @return the distance between the two integers as defined by the metric
     * @see EMetrics
     * //     * @see PixelUtils
     */
    private
    double[] distance ( final double[] a, final double[] b, final double[] fuzz ) {
        return distance(a, b);
    }

    /**
     * @param a a pixel to compare to
     * @param b a pixel to compare with
     * @return the distance between the two integers as defined by the metric
     * @see EMetrics
     * //     * @see PixelUtils
     */
    private
    double[] distance ( final double[] a, final double[] b ) {
        return IntStream.range(0, a.length).mapToDouble(c -> Math.abs(a[c] - b[c])).toArray();
    }

    public abstract
    double[] error ( IImageBlock imageBlock1, IImageBlock imageBlock2 );
}
