package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.codec.IConverter;

import java.util.List;

import static org.stranger2015.opencv.fic.core.ImageProcessor.getNearestGreaterPow2;

/**
 * @param <M>
 */
public
class LoadSaveImageTask<M extends Image> extends BidiTask <M> {
    private final String fileName;

    /**
     * @param fileName
     * @param tasks
     */
    @SafeVarargs
    public
    LoadSaveImageTask ( String fileName, Task <M>... tasks ) {
        super(tasks);
        this.fileName = fileName;
    }

    /**
     * @param fileName
     * @param tasks
     */
    public
    LoadSaveImageTask ( String fileName, List <Task <M>> tasks ) {
        this(fileName, tasks.get(0), tasks.get(1));
    }

    /**
     * @return
     */
    public
    String getFileName () {
        return fileName;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param fileName the function argument
     * @return the function result
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    M apply ( String fileName ) {
        return loadImage(fileName);
    }

    /**
     * @param fn
     * @return
     */
    @Override
    public
    M loadImage ( String fn ) {

        return null;//todo//todo
    }

    /**
     * @param fn
     * @param image
     */
    @Override
    public
    void saveImage ( String fn, M image ) {
//todo
    }

    /**
     *
     */
    static
    class NormalizeImageShapeTask<M extends Image> extends BidiTask <M> {
        /**
         * @param tasks
         */
        public
        NormalizeImageShapeTask ( List <Task <M>> tasks ) {
            super(tasks);
        }

        /**
         * @param fn
         * @return
         */
        @Override
        public
        M loadImage ( String fn ) {
            return null;//todo
        }

        /**
         * @param fn
         * @param image
         */
        @Override
        public
        void saveImage ( String fn, M image ) {

        }

        /**
         * Applies this function to the given argument.
         *
         * @param filename function argument
         * @return the function result
         */
        @SuppressWarnings("*")
        @Override
        public
        M apply ( String filename ) {
            M image = loadImage(filename);
            int sideSize = getNearestGreaterPow2(Math.max(image.getHeight(), image.getWidth()));
            return (M) new Image(image, new Size(sideSize, sideSize));
        }
    }

    /**
     * @param <M>
     */
    static
    class BidiImageColorModelTask<M extends Image> extends BidiTask <M> {
        /**
         * @param tasks
         */
        public
        BidiImageColorModelTask ( List <Task <M>> tasks ) {
            super(tasks);
        }

        /**
         * @param task
         * @param inverseTask
         */
        public
        BidiImageColorModelTask ( RgbToYuvImageColorModelTask <M> task,
                                  YuvToRgbImageColorModelTask <M> inverseTask ) {
            super(task, inverseTask);
        }

        /**
         * Applies this function to the given argument.
         *
         * @param m the function argument
         * @return the function result
         */
//    @Override
        public
        M apply ( M m ) {
            return null;//todo
        }

        /**
         * @param fn
         * @return
         */
        @Override
        public
        M loadImage ( String fn ) {
            return null;//todo
        }

        /**
         * @param fn
         * @param image
         */
        @Override
        public
        void saveImage ( String fn, M image ) {

        }

        /**
         * Applies this function to the given argument.
         *
         * @param s the function argument
         * @return the function result
         */
        @Override
        public
        M apply ( String s ) {
            return null;//todo
        }

        /**
         *
         */
        static
        class RgbToYuvImageColorModelTask<M extends Image> extends Task <M>
                implements IConverter <M, M> {
            /**
             * @param image
             */
            public
            RgbToYuvImageColorModelTask ( M image ) {
                super(image);
            }

            /**
             * RGB -> YUV.
             * Y in the range [0..1].                          @param input
             * U in the range [-0.5..0.5].                     @return
             * V in the range [-0.5..0.5].                    /
             *
             * @param red   Values in the range [0..255].       verride
             * @param green Values in the range [0..255].     blic
             * @param blue  Values in the range [0..255].      map ( M input ) {
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

            /**
             * @param input
             * @return
             */
            @Override
            public
            M map ( M input ) {
                for (int i = 0, sideSize = (int) input.size().width; i < sideSize; i++) {
//                    rgbToYUV();
                }

                return null;//todo
            }

            /**
             * @param output
             * @return
             */
            @SuppressWarnings("unchecked")
            @Override
            public
            M unmap ( M output ) {
                int[] rgb = yuvToRGB(0, 0, 0);//todo
                Mat m = new Mat();
                output.copyTo(m);

                return (M) new Image(m);
            }

            /**
             * Applies this function to the given argument.
             *
             * @param s the function argument
             * @return the function result
             */
            @Override
            public
            M apply ( String s ) {
                return null;//todo
            }
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

        /**
         * @param <M>
         */
        static
        class YuvToRgbImageColorModelTask<M extends Image> extends Task <M> {
            /**
             * @param image
             */
            public
            YuvToRgbImageColorModelTask ( M image ) {
                super(image);
            }

            /**
             * Applies this function to the given argument.
             *
             * @param s the function argument
             * @return the function result
             */
            @Override
            public
            M apply ( String s ) {
                return null;//todo
            }
        }
    }
}