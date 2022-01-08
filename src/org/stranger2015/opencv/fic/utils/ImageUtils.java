package org.stranger2015.opencv.fic.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.opencv.core.Point;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.core.codec.IAddressMath;
import org.stranger2015.opencv.fic.core.codec.Pixel;
import org.stranger2015.opencv.fic.core.codec.SipAddress;
import org.stranger2015.opencv.fic.core.codec.SipImage;

import java.util.ArrayList;
import java.util.List;

import static org.stranger2015.opencv.fic.core.codec.IAddressMath.*;
import static org.stranger2015.opencv.fic.core.codec.SipAddress.radix;

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
    class ImageFiller<A extends SipAddress <A>> implements IAddressMath<A>{

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

        protected int layerIndex;


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

            layerIndex = 0;
        }

        /**
         * @param row
         * @param col
         * @param addressBase
         * @param addressOffset
         */
        void setAddress ( int row, int col, int addressBase, int addressOffset ) {
            if (row < 0 || col < 0) {
                throw new IllegalStateException("Error: row or col < 0");
            }
            int cols = input.cols();
            addresses[row + cols * col] = addressBase + addressOffset;
        }

        /**
         * @param layerindex
         * @param size
         * @param addressBase
         * @param addressOffset
         * @return
         */
        int fillCluster ( int layerindex, int size, int addressBase, int addressOffset ) {
            rowDelta *= layerindex;
            colDelta *= layerindex;
            setAddress(row(), col(), addressBase, addressOffset++);
            setAddress(row(), col() - 1, addressBase, addressOffset++);
            setAddress(row() - 1, col() - 1, addressBase, addressOffset++);
            setAddress(row() - 1, col(), addressBase, addressOffset++);
            setAddress(row() - 1, col() + 1, addressBase, addressOffset++);
            setAddress(row(), col() + 1, addressBase, addressOffset++);
            setAddress(row() + 1, col() + 1, addressBase, addressOffset++);
            setAddress(row() + 1, col(), addressBase, addressOffset++);
            setAddress(row() + 1, col() - 1, addressBase, addressOffset++);

            addressBase = fillClusters(++layerindex, size, addressBase, addressOffset);

            return addressBase + addressOffset;
        }

        int row () {
            return row + rowDelta;
        }

        int col () {
            return col + colDelta;
        }

        /**
         * @param size
         * @param addressBase
         * @param addressOffset
         * @return
         */
        int fillLayer ( int layerIndex, int size, int addressBase, int addressOffset ) {
            try {
                addressBase = fillClusters(layerIndex, size, addressBase, addressOffset);
            } catch (Exception e) {
                return -1;
            }

            return addressBase;
        }

        /**
         * @param layerIndex
         * @param size
         * @param addressBase
         * @param addressOffset
         * @return
         */
        private
        int fillClusters ( int layerIndex, int size, int addressBase, int addressOffset ) {
            fillCluster(layerIndex, size, addressBase, addressOffset);

            rowDelta = 0;
            colDelta = -3;
            fillCluster(layerIndex, size, addressBase += radix, addressOffset);

            rowDelta = 3;
            colDelta = -3;
            fillCluster(layerIndex, size, addressBase += radix, addressOffset);

            rowDelta = 3;
            colDelta = 0;
            fillCluster(layerIndex, size, addressBase += radix, addressOffset);

            rowDelta = 3;
            colDelta = 3;
            fillCluster(layerIndex, size, addressBase += radix, addressOffset);

            rowDelta = 0;
            colDelta = 3;
            fillCluster(layerIndex, size, addressBase += radix, addressOffset);

            rowDelta = -3;
            colDelta = 3;
            fillCluster(layerIndex, size, addressBase += radix, addressOffset);

            rowDelta = -3;
            colDelta = 0;
            fillCluster(layerIndex, size, addressBase += radix, addressOffset);

            rowDelta = -3;
            colDelta = -3;
            fillCluster(layerIndex, size, addressBase += radix, addressOffset);

            return addressBase;
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

        /**
         * @param address1
         * @param address2
         * @return
         */
        @Override
        public
        A plus ( A address1, A address2 ) throws ValueError {
            return null;
        }

        /**
         * @param address1
         * @param address2
         * @return
         */
        @Override
        public
        A minus ( A address1, A address2 ) throws ValueError {
            return null;
        }

        /**
         * @param address1
         * @param address2
         * @return
         */
        @Override
        public
        A mult ( A address1, A address2 ) throws ValueError {
            return null;
        }

        /**
         * @return
         */
        @Override
        public
        int[][] getAddTable () {
            return new int[0][];
        }

        /**
         * @return
         */
        @Override
        public
        int[][] getMultTable () {
            return new int[0][];
        }

        /**
         * @return
         */
        @Override
        public
        int getIndex () {
            return 0;
        }

        @Override
        public
        Point getCartesianCoords ( int i ) {
            return null;
        }
    }

    /**
     * @param input
     * @return
     */
    public static
    <N extends TreeNode <N, A, M>, A extends SipAddress <A>, M extends Image>
    @NotNull SipImage convertImageToSipImage ( M input ) {
        ImageFiller <A> af = new ImageFiller <>(input);
        int addressBase = 0;
        int addressOffset = 0;

        for (int i = 0, size = input.cols(); ; i++) {
            int ss = nextPixelCapacity(i);
            if (ss >= size) {
                break;
            }
            addressOffset = af.fillLayer(af.layerIndex, ss, addressBase, addressOffset);
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
        SipImage sipImage = new SipImage(input, af.getAddresses());
        for (int i = 0; i < input.getWidth(); i++) {
            for (int j = 0; j < input.getHeight(); j++) {
                double[] pixelData = input.get(i, j);
                sipImage.put(af.getAddresses()[i], pixelData);
            }
        }

        return sipImage;
    }

    /**
     * @param layerIndex
     * @return
     */
    @Contract(pure = true)
    public static
    int nextPixelCapacity ( int layerIndex ) {
        return pow(radix, layerIndex);
    }
}
