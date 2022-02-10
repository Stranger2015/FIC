package org.stranger2015.opencv.fic.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.SipTreeNode.SipLayerClusterNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IAddressMath;
import org.stranger2015.opencv.fic.core.codec.Pixel;
import org.stranger2015.opencv.fic.core.codec.SipAddress;
import org.stranger2015.opencv.fic.core.codec.SipImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.stranger2015.opencv.fic.core.codec.IAddressMath.pow;
import static org.stranger2015.opencv.fic.core.codec.SipAddress.radix;

/**
 * Data structure Description
 * <p>
 * sa_ADD_TABLE Matrix used for spiral addition.
 * sa_MINUS_TABLE Matrix used for inverse spiral addition.
 * sa_MULT_TABLE Matrix used for spiral multiplication.
 * sa_HEX_ADDR Position of the hexagons (e.g. for the display with sa_imview()).
 * sa_LOG_ADD Matrices used for log space transformation to shift an image.
 * sa_LOG_MULT Matrices used for spiral multiplying an image by log space transformation.
 * sa_OCTAVE_POLS Contains polar coordinates of the first hexagon of each octave.
 * <p>
 * 3.1 Displaying the Spiral Architecture
 * <p>
 * The sa_imview() function is used to display an image on the spiral architecture in order
 * to be able to visualize the outcome of image processing operations on spiral images.
 * Images can be either greyscale or full (RGB) color images and all image data types provided
 * in Matlab are supported. An example of an image displayed using sa_imview() is shown in Figure 2.
 * <p>
 * Table 1 Predefined data structures in saLib
 * <p>
 * ============================================
 * Function Description
 * <p>
 * sa_spiralCount() spiral counting
 * Performs spiral counting for a given number of addresses starting at a given starting address and
 * returns the resulting address.
 * <p>
 * sa_add() spiral addition
 * Performs spiral addition (see Section 2.2) of two given addresses (using sa_ADD_TABLE) and returns
 * the result.
 * If at least one of the two values to add is negative, sa_add calls the sa_minus function.
 * <p>
 * sa_minus() spiral minus
 * Performs spiral minus, the inverse of spiral addition, on two given spiral addresses, using
 * sa_MINUS_TABLE.
 * <p>
 * sa_multiply() spiral multiplication
 * Performs spiral multiplication (see Section 2.3) of two given addresses using sa_MULT_TABLE and
 * returns the result.
 * <p>
 * sa_modAdd() modulus spiral addition
 * Performs modulus spiral addition (see Section 2.2) of two given addresses and returns the result.
 * <p>
 * sa_modMultiply() modulus spiral multiplication
 * Performs modulus spiral multiplication (see Section 2.3) of two given addresses and returns the result.
 * <p>
 * sa_hex2cart() Cartesian coordinates from spiral address
 * Converts a given spiral address to Cartesian coordinates.
 * <p>
 * sa_cart2hex() spiral address from Cartesian coordinates
 * Converts a given set of Cartesian coordinates to their corresponding spiral address. 1, 2 or 3 addresses
 * are returned, depending on whether the given point is within a hexagon, on the edge, or in the cusp.
 * <p>
 * Table 2 Address-based functions in saLib
 *
 * @param <A>
 */
public
class SipLibrary<A extends SipAddress <A>> implements IAddressMath <A> {

    public int layerIndex;
    public int layersAmount;

    public int pixelCapacity;
    public int pixelAmount;//pixels may or may not occupy the whole cluster of the outermost layer
    public int address;

    public int clustersAmount;
    public int clusterIndex;

//    public final List <Point> pixelLocations = new ArrayList <>();

//    private final List <Point> pixelShiftAddresses = new ArrayList <>(getPixelCapacity());

    private final int[] powersOfThree = new int[]{
            1,              //0
            3,              //1
            9,              //2
            27,             //3
            81,             //4
            243,            //5
            729,            //6
            2187,           //7
            6561,           //8
            19683,          //9
            59049,          //10
            177147,         //11
            531441,         //12
            1594323,        //13
            4782969,        //14
            14348907,       //15
            43046721,       //16
            129140163,      //17
            387420489,      //18
            1162261467,     //19
    };

    /**
     *
     */
    public
    SipLibrary () {
        layerIndex = 0;
    }

    /**
     * @param address1
     * @param address2
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    A plus ( A address1, A address2 ) throws ValueError {
        int sum = address1.intValue() + address2.intValue();

        return (A) new SipAddress <A>(sum);
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
    int[][] getPlusTable () {
        return new int[0][];
    }

    /**
     * @return
     */
    @Override
    public
    int[][] getMinusTable () {
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

    public
    int pow3 ( int i ) {
        return powersOfThree[i];
    }


    /**
     * Sum(i=1, n) = 3^(i-1) * L(ai)
     *
     * @param shifts
     * @param <A>
     * @return
     * @throws ValueError
     */
    public
    <A extends SipAddress <A>>
    SipAddress <A> convertCartesianCoordsToSipAddress ( SipTree <?, ?, ?> tree, List <Point> shifts )
            throws ValueError {
        int address = 0;
        int sum = 0;
        SipLayerClusterNode <?, ?, ?> node = (SipTreeNode.SipLayerClusterNode <?, ?, ?>) tree.getRoot().getChild(0);
        for (Point shift : shifts) {
            int x = shift.getX();
            int y = shift.getY();
            if (x == node.getX()) {
                int ai = 0;
            }
        }

        return new SipAddress <>(address);
    }

    /**
     * @param sipAddress
     * @param <A>
     * @return
     */
    public
    <A extends SipAddress <A>>
    List <Point> convertSipAddressToCartesianCoords ( SipTree <?, ?, ?> tree, SipAddress <A> sipAddress )
            throws ValueError {
        int address = sipAddress.getNumber();
        List <Point> shifts = new ArrayList <>();
        for (int i = 0; i < address; i++) {
            shifts.addAll(
                    derivePixelShifts(
                            tree,
                            getCartesianCoordinates(radix),
                            calcPixelCapacity(layersAmount),
                            i,
                            0
                    )
            );
        }

        return shifts;
    }

    /**
     * SIP Conversion
     * <p>
     * HIP conversion relies on use of a resampling scheme [3, 5]
     * to match the location of points in the square and
     * hexagonal images. As SIP is based on square images,
     * no resampling scheme is needed. We need only to
     * convert the lattice of a square image to the new SIP
     * format based on the spiral addressing scheme. The
     * steps are as follows.
     * For a given image with size <b>M × N</b> , the number of SIP layers λ
     * can be found by λ = (log(M) + log(N))/log(9); then the length of
     * the SIP image is 9^λ.
     * Because the SIP address scheme is base 9, the conversion between
     * the SIP address and a decimal number can be found by
     * (an, an−1 ... a1) = an × 9^n−1 + an−1 × 9^n−2 + ... + a1,
     * where the values ai of a SIP address are 0 ≤ ai < 9. We can adapt
     * the spiral addressing scheme for HIP [9] and the SIP address can be represented as:
     * an, an−1 ... a1 = n∑ i=1 ai × 10^i−1 (1)
     * where `∑' denotes Spiral Addition and `×' indicates Spiral Multiplication [5].
     * For example, the point at SIP address 867 can be located by finding the addresses of
     * 800, 60 and 7. Next, we explain how to locate these SIP addresses in a standard 2D
     * square image.
     * For a point represented by Cartesian coordinate (x, y), we define the centre point as L(0) = (0, 0).
     * Based on the SIP addressing scheme, we can find the points in <b>layer-1</b>:
     * L(1)=(-1,0), L(2)=(-1,1), L(3)=(0,1), L(4)=(1,1), L(5)=(1,0), L(6)=(1,-1),
     * L(7)=(0,-1) and L(8)=(-1,-1). To locate the points in a higher layer, we calculate the number
     * of pixels required to shift the point from the centre to the target point by
     * <p>
     * L(ai × 10^i−1) = 3^i−1 × L(ai) (2)
     * <p>
     * For example, based on Eq.(1) and Eq.(2), the point L(87) can be located by
     * L(87) = L(80) + L(7) = 3 × L(8)+L(7) = 3×(−1, −1)+(0, −1) = (−3, −4). To locate a point at
     * L(4536), L(4536) = L(4000) + L(500) + L(30) + L(6) = 33 × L(4) + 32 × L(5) + 3 × L(3) + L(6) =
     * 27 × (1, 1) + 9 × (1, 0) + 3 × (0, 1) + (1, −1) = (37, 29).
     * <p>
     * Hence, the point L(4536) can be found by shifting the start point from (0,0) to (37,29).
     * Based                    on these shifting rules, we can then convert a square image to a SIP image
     * (which is stored as a vector).
     *
     * @param input
     * @return
     */
    public
    <M extends IImage>
    @NotNull
            SipImage convertImageToSipImage ( SipTree <?, ?, ?> tree, @NotNull M input ) throws ValueError {
        int addressBase = 0;
        int addressOffset = 0;
        List <Point> pixelShifts = new ArrayList <>();
        for (int i = 0, imgSize = input.getWidth() * input.getHeight(); ; i++) {
            int pixelCapacity = calcPixelCapacity(i);
            if (pixelCapacity >= imgSize) {
                break;
            }
            pixelShifts = derivePixelShifts(tree,
                    getCartesianCoordinates(radix),
                    pixelCapacity,
                    addressBase,
                    addressOffset);
        }

        return relocatePixelData(input, pixelShifts);
    }

    /**
     * @param input
     * @param pixelShifts
     * @return
     */
    public
    <M extends IImage>
    @NotNull SipImage relocatePixelData ( M input, List <Point> pixelShifts ) {
        SipImage sipImage = new SipImage((Mat) input);
        List <Scalar> scalars = new ArrayList <>();
        for (int i = 0; i < input.getWidth(); i++) {
            for (int j = 0; j < input.getHeight(); j++) {
                scalars.add(new Scalar(((Mat) input).get(i, j)));
            }
        }
        SipImage output = new SipImage((Mat) input);
        //            getPixelShiftAddresses().addAll(pixelShifts);
        for (Scalar scalar : scalars) {
            output.put(output.getPixels(), scalar.val[0], scalar.val[1], scalar.val[2], scalar.val[3]);
        }

        return sipImage;
    }


    /**
     * L( 0 ) = (  0,  0 )   0
     * L( 1 ) = ( -1,  0 ),  1 layer-1:
     * L( 2 ) = ( -1,  1 ),  2
     * L( 3 ) = (  0,  1 ),  3
     * L( 4 ) = (  1,  1 ),  4
     * L( 5 ) = (  1,  0 ),  5
     * L( 6 ) = (  1,  -1 ), 6
     * L( 7 ) = (  0,  -1 ), 7
     * L( 8 ) = ( -1,  -1 )  8
     *
     * @param radix
     * @return
     */
    @Override
    public
    List <Point> getCartesianCoordinates ( int radix ) throws ValueError {
        List <Point> l;
        switch (radix) {
            case 7:
                l = getCartesianCoordinates7();
                break;
            case 9:
                l = getCartesianCoordinates9();
                break;
            case 10:
                l = getCartesianCoordinates10();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + radix);
        }

        return l;
    }

    /**
     * /* Cartesian coordinates
     * {  0,  0 }  0
     * {  0, -1 }  1
     * { -1, -1 }  2
     * { -1,  0 }  3
     * {  0,  1 }  4
     * {  1,  1 }  5
     * {  1,  0 }  6
     *
     * @return
     */
    private
    List <Point> getCartesianCoordinates7 () throws ValueError {
        return Arrays.asList(
                new Point(0, 0),
                new Point(0, -1),
                new Point(-1, -1),
                new Point(-1, 0),
                new Point(0, 1),
                new Point(1, 1),
                new Point(1, 0)
        );
    }

    /**
     * L( 0 ) = (  0,  0 )   0
     * L( 1 ) = ( -1,  0 ),  1 layer-1:
     * L( 2 ) = ( -1,  1 ),  2
     * L( 3 ) = (  0,  1 ),  3
     * L( 4 ) = (  1,  1 ),  4
     * L( 5 ) = (  1,  0 ),  5
     * L( 6 ) = (  1,  -1 ), 6
     * L( 7 ) = (  0,  -1 ), 7
     * L( 8 ) = ( -1,  -1 )  8
     *
     * @return
     */
    @Contract(pure = true)
    private
    List <Point> getCartesianCoordinates9 () throws ValueError {
        return Arrays.asList(
                new Point(0, 0),
                new Point(-1, 0),
                new Point(-1, 1),
                new Point(0, 1),
                new Point(1, 1),
                new Point(1, 0),
                new Point(1, -1),
                new Point(0, -1),
                new Point(-1, -1)
        );
    }

    private
    List <Point> getCartesianCoordinates10 () {
        return null;//TODO
    }

    /**
     * // L( 0 ) = (  0,  0 )   0
     * // L( 1 ) = ( -1,  0 ),  1 layer-1:
     * // L( 2 ) = ( -1,  1 ),  2
     * // L( 3 ) = (  0,  1 ),  3
     * // L( 4 ) = (  1,  1 ),  4
     * // L( 5 ) = (  1,  0 ),  5
     * // L( 6 ) = (  1,  -1 ), 6
     * // L( 7 ) = (  0,  -1 ), 7
     * // L( 8 ) = ( -1,  -1 )  8
     * <p>
     * Figure 1. The spiral architecture for a layer-2 hexagonal image.
     * <p>
     * Figure 2. The spiral addressing scheme for a layer-2 SIP-based image.
     * <p>
     * Figure 3. One-dimensional address values for SIP image.
     * <p>
     * the pixels in <b>layer-1</b> are labelled from 0 to 8, indexed
     * in a clockwise direction. The base 9 indexing continues into each layer,
     * e.g. <b>layer-2</b> starts from 10, 11, 12, ..., and finishes at 88.
     * Subsequent layers are structured recursively.
     * The SIP image is stored in a one-dimensional vector with addresses
     * as illustrated (in part) in Fig. 3.
     * <p>
     * 2.2 SIP Conversion
     * <p>
     * HIP conversion relies on use of a resampling scheme [3, 5] to match
     * the location of points in the square and hexagonal images. As SIP is
     * based on square images, no resampling scheme is needed. We need only to
     * convert the lattice of a square image to the new SIP format based on the
     * spiral addressing scheme. The steps are as follows.
     * For a given image with size M × N , the number of SIP layers λ can be
     * found by λ = (logM + logN )/log(9); then the length of the SIP image is 9λ.
     * Because the SIP address scheme is base 9, the conversion between the SIP
     * address and a decimal number can be found by (a(n)*a(n−1)...a1) = a(n) × 9^(n−1) + a(n−1) ×
     * 9^(n−2) + ... + a1, where the values ai of a SIP address are 0 ≤ ai < 9. We can
     * adapt the spiral addressing scheme for HIP [9] and the SIP address can be represented as:
     * a(n)*a(n−1) ... a1 = n∑i=1 ai × 10^(i−1) (1)
     * where ∑ denotes Spiral Addition and × indicates Spiral Multiplication [5].
     * For example, the point at SIP address 867 can be located by finding the addresses of
     * 800, 60 and 7. Next, we explain how to locate these SIP addresses in a standard
     * 2D square image.
     * For a point represented by Cartesian coordinate (x,y), we define the centre point
     * as L(0) = (0, 0). Based on the SIP addressing scheme, we can find the points in layer-1:
     * L(1)=(-1, 0),
     * L(2)=(-1, 1),
     * L(3)=( 0, 1),
     * L(4)=( 1, 1),
     * L(5)=( 1, 0),
     * L(6)=( 1,-1),
     * L(7)=( 0,-1),
     * L(8)=(-1,-1).
     * <p>
     * To locate the points in a higher layer, we calculate the number of pixels required to shift
     * the point from the centre to the target point by
     * L(ai × 10^*(i−1) = 3^(i−1) × L(ai) (2)
     * <p>
     * For example, based on Eq.(1) and Eq.(2), the point L(87) can be located by L(87) = L(80) + L(7) =
     * 3 × L(8) + L(7) = 3 × (−1, −1) + (0, −1) = (−3, −4).
     * o locate a point at L(4536), L(4536) = L(4000) + L(500) + L(30) + L(6) =
     * 33 × L(4) + 32 × L(5) + 3 × L(3) + L(6) = 27 × (1, 1) + 9 × (1, 0) + 3 × (0, 1) + (1, −1) = (37, 29).
     * Hence, the point L(4536) can be found by shifting the start point from (0,0) to (37,29).
     * Based on these shifting rules, we can then convert a square image to a SIP image
     * (which is stored as a vector).
     *
     * @param tree
     * @param points
     * @param pixelCapacity
     * @param addressBase
     * @param addressOffset
     * @return
     */
    public
    List <Point> derivePixelShifts (
            SipTree <?, ?, ?> tree,
            List <Point> points,
            int pixelCapacity,
            int addressBase,
            int addressOffset ) {
        List <Point> shifts = new ArrayList <>();
        for (int i = 0; i < pixelCapacity / radix; i++) {//++layerIndex ??? check & fixme
            Point shift = derivePixelShift(tree, points, addressBase++ + addressOffset);
            shifts.add(shift);
        }

        return shifts;
    }

    /**
     * @param tree
     * @param ccs
     * @param address
     */
    public
    Point derivePixelShift ( SipTree <?, ?, ?> tree, List <Point> ccs, int address ) {
        Point result = new Point(0, 0);
        for (int i = 1; i < address; i++) {
            int ai = pow3(i - 1);
            int digit = address % radix;
            address -= digit;
            address /= radix;
            result = plus(mult(ccs.get(digit), ai), result);
        }

        return result;
    }

    /**
     * @param tree
     * @param shifts
     * @return
     * @throws ValueError
     */
    List <SipAddress <?>> deriveAddresses ( SipTree <?, ?, ?> tree, List <Point> shifts ) throws ValueError {
        final List <SipAddress <?>> addresses = new ArrayList <>();
        for (Point shift : shifts) {
            addresses.add(deriveAddress(tree, shift));
        }

        return addresses;
    }

    /**
     * Figure 2: Example of using spiral addition and spiral multiplication to obtain SIP based
     * address.
     * the Cartesian coordinates (x, y). Consider layer-1 as an example, we can define
     * the location of a centre point as L(0) = (0, 0). Based on the SIP addressing
     * scheme shown in Fig. 1, we have the location of nine points in layer-1, ordered
     * clock-wise: L(1) = (−1, 0), L(2) = (−1, 1), L(3) = (0, 1), L(4) = (1, 1), L(5) =
     * (1, 0), L(6) = (1, −1), L(7) = (0, −1) and L(8) = (−1, −1). The location can be
     * represented as:
     * L =
     * [ 0, 0],
     * [−1, 0],
     * [−1, 1],
     * [ 0, 1],
     * [ 1, 1],
     * [ 1, 0],
     * [ 1, −1],
     * [ 0, −1],
     * [−1, −1]
     *
     * @param shift
     * @return
     * @throws ValueError
     */
    public
    SipAddress <?> deriveAddress ( SipTree <?, ?, ?> tree, Point shift ) throws ValueError {
        TreeNode <?, ?, ?> node = tree.findNode(shift);
        return new SipAddress <>(node.getAddress());
    }

    /**
     * @param layersAmount
     * @return
     */
    @Contract(pure = true)
    public
    int calcPixelCapacity ( int layersAmount ) {
        return pow(radix, layersAmount);
    }

    /**
     * @param pixelCapacity
     * @param radix
     * @return
     */
    @Contract(pure = true)
    public
    int calcClustersCapacity ( int pixelCapacity, int radix ) {
        return pixelCapacity / radix;
    }

    /**
     * @param sideSize
     * @return
     */
    public
    int calcLayersAmount ( int sideSize ) {
        return (int) (2 * Math.log10(sideSize) / Math.log10(radix));
    }

    /**
     * @return
     */
    public
    int getLayerIndex () {
        return layerIndex;
    }

    /**
     * @param layerIndex
     */
    public
    void setLayerIndex ( int layerIndex ) {
        this.layerIndex = layerIndex;
    }

    /**
     * @return
     */
    public
    int getLayersAmount () {
        return layersAmount;
    }

    /**
     * @param layersAmount
     */
    public
    void setLayersAmount ( int layersAmount ) {
        this.layersAmount = layersAmount;
    }

    /**
     * @return
     */
    public
    int getPixelCapacity () {
        return pixelCapacity;
    }

    /**
     * @param pixelCapacity
     */
    public
    void setPixelCapacity ( int pixelCapacity ) {
        this.pixelCapacity = pixelCapacity;
    }

    /**
     * @return
     */
    public
    int getPixelAmount () {
        return pixelAmount;
    }

    /**
     * @param pixelAmount
     */
    public
    void setPixelAmount ( int pixelAmount ) {
        this.pixelAmount = pixelAmount;
    }

    /**
     * @return
     */
    public
    int getAddress () {
        return address;
    }

    /**
     * @param address
     */
    public
    void setAddress ( int address ) {
        this.address = address;
    }

    /**
     * @return
     */
    public
    int getClustersAmount () {
        return clustersAmount;
    }

    /**
     * @param clustersAmount
     */
    public
    void setClustersAmount ( int clustersAmount ) {
        this.clustersAmount = clustersAmount;
    }

    /**
     * @return
     */
    public
    int getClusterIndex () {
        return clusterIndex;
    }

    /**
     * @param clusterIndex
     */
    public
    void setClusterIndex ( int clusterIndex ) {
        this.clusterIndex = clusterIndex;
    }

    @Override
    public
    A divide () throws ValueError {
        return null;
    }

//    public
//    List <Point> getPixelShiftAddresses () {
//        return pixelShiftAddresses;
//    }

    public
    int[] getPowersOfThree () {
        return powersOfThree;
    }
}
