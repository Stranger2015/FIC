package org.stranger2015.opencv.fic.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IAddressMath;
import org.stranger2015.opencv.fic.core.codec.Pixel;
import org.stranger2015.opencv.fic.core.codec.SipAddress;
import org.stranger2015.opencv.fic.core.codec.SipImage;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public
class ImageUtils {

    /**
     *
     */
    private
    ImageUtils () {
    }

    /**
     * @param <A>
     */
    private static
    class ImageFiller<A extends SipAddress <A>> {

        /**
         *
         */
        private final int[] addresses;
        private final Image input;
        private final int pixelAmount;
        private final List <Pixel> pixels;

        protected int row;
        protected int col;

        protected int rowDelta;
        protected int colDelta;

        protected int layerno;


        /**
         * @return
         */
        public
        int getPixelAmount () {
            return pixelAmount;
        }

        /**
         * @param input
         */
        public
        ImageFiller ( Image input ) {
            this.input = input;
            pixelAmount = input.rows() * input.cols();
            pixels = new ArrayList <>(pixelAmount);
            addresses = new int[pixelAmount];
            row = input.rows() / 2;
            col = input.cols() / 2;
            rowDelta = 0;
            colDelta = 0;

            layerno = 0;
        }

        /**
         * @param row
         * @param col
         * @param addrBase
         * @param addrOfs
         */
        void setAddress ( int row, int col, int addrBase, int addrOfs ) {
            if (row < 0 || col < 0) {
                throw new IllegalStateException("Error: row or col < 0");
            }
            int cols = input.cols();
            addresses[row + cols * col] = addrBase + addrOfs;
        }

        /**
         * @param layerno
         * @param size
         * @param addrBase
         * @param addrOfs
         * @return
         */
        int fillCluster ( int layerno, int size, int addrBase, int addrOfs ) {
            rowDelta *= layerno;
            colDelta *= layerno;
            setAddress(row(), col(), addrBase, addrOfs++);
            setAddress(row(), col() - 1, addrBase, addrOfs++);
            setAddress(row() - 1, col() - 1, addrBase, addrOfs++);
            setAddress(row() - 1, col(), addrBase, addrOfs++);
            setAddress(row() - 1, col() + 1, addrBase, addrOfs++);
            setAddress(row(), col() + 1, addrBase, addrOfs++);
            setAddress(row() + 1, col() + 1, addrBase, addrOfs++);
            setAddress(row() + 1, col(), addrBase, addrOfs++);
            setAddress(row() + 1, col() - 1, addrBase, addrOfs++);

            addrBase = fillClusters(++layerno, size, addrBase, addrOfs);

            return addrBase + addrOfs;
        }

        int row () {
            return row + rowDelta;
        }

        int col () {
            return col + colDelta;
        }

        /**
         * @param size
         * @param addrBase
         * @param addrOfs
         * @return
         */
        int fillLayer ( int layerno, int size, int addrBase, int addrOfs ) {
            try {
                addrBase = fillClusters(layerno, size, addrBase, addrOfs);
            } catch (Exception e) {
                return -1;
            }

            return addrBase;
        }

        /**
         * @param layerno
         * @param size
         * @param addrBase
         * @param addrOfs
         * @return
         */
        private
        int fillClusters ( int layerno, int size, int addrBase, int addrOfs ) {
            fillCluster(layerno, size, addrBase, addrOfs);

            rowDelta = 0;
            colDelta = -3;
            fillCluster(layerno, size, addrBase += 9, addrOfs);

            rowDelta = 3;
            colDelta = -3;
            fillCluster(layerno, size, addrBase += 9, addrOfs);

            rowDelta = 3;
            colDelta = 0;
            fillCluster(layerno, size, addrBase += 9, addrOfs);

            rowDelta = 3;
            colDelta = 3;
            fillCluster(layerno, size, addrBase += 9, addrOfs);

            rowDelta = 0;
            colDelta = 3;
            fillCluster(layerno, size, addrBase += 9, addrOfs);

            rowDelta = -3;
            colDelta = 3;
            fillCluster(layerno, size, addrBase += 9, addrOfs);

            rowDelta = -3;
            colDelta = 0;
            fillCluster(layerno, size, addrBase += 9, addrOfs);

            rowDelta = -3;
            colDelta = -3;
            fillCluster(layerno, size, addrBase += 9, addrOfs);

            return addrBase;
        }

        /**
         * @return
         */
        public
        int[] getAddresses () {
            return addresses;
        }

        /**
         * @return
         */
        public
        List <Pixel> getPixels () {
            return pixels;
        }
    }

    /**
     * @param input
     * @return
     */
    public static
    <N extends TreeNode <N, A, M>, A extends SipAddress <A>, M extends Image>
    SipImage imageToSipImage ( M input ) {

        ImageFiller <A> af = new ImageFiller <>(input);
        int addrBase = 0;
        int addrOfs = 0;

        for (int i = 0, size = input.cols(); ; i++) {
//            int pow3 = IAddressMath.pow(3, i);
            int ss = nextSize(i);
            if (ss >= size) {
                break;
            }
            addrOfs = af.fillLayer(af.layerno, ss, addrBase, addrOfs);
        }

        return relocatePixelData(input, af);
    }

    /**
     * @param input
     * @param af
     * @param <M>
     * @param <A>
     * @return
     */
    private static
    <M extends Image, A extends SipAddress <A>>
    @NotNull SipImage relocatePixelData ( M input, ImageFiller <A> af ) {
        int w = input.getWidth();
        SipImage sipImage = new SipImage(input,af.getAddresses());
        for (int i = 0; i < input.getWidth(); i++) {
            for (int j = 0; j < input.getHeight(); j++) {
                double[] pixelData = input.get(i, j);
//                af.getPixels().add(addresses[i + w * j], new Pixel(pixelData));

                sipImage.put(i, j, pixelData);
            }
        }

        return sipImage;
    }

    private static
    <M extends Image>
    SipImage relocatePixelData ( M input ) {
        SipImage sipImage = new SipImage(input);
        for (int i = 0; i < input.getWidth(); i++) {
            for (int j = 0; j < input.getHeight(); j++) {
                double[] pixelData = input.get(i, j);

            }
        }

        return sipImage;
    }

    /**
     * @param layerno
     * @return
     */
    @Contract(pure = true)
    private static
    int nextSize ( int layerno ) {
//        switch (layerno) {
//            case 0:
//                return 1;
//            case 1:
//                return 3;
//            case 2:
//                return 9;
//            case 3:
//                return 15;
//            default:
//                throw new IllegalStateException("Unexpected value: " + layerno);
//        }
        return IAddressMath.pow(9, layerno);
    }
}
