package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.utils.IPowers;
import org.stranger2015.opencv.fic.utils.Point;

import java.util.List;

/**
 * @param <A>
 */
public
class Library<A extends IAddress <A>> implements IPowers {

//    public int clusterIndex;
//    public int clustersAmount;
//    public int layersAmount;
//    public int layerIndex;
    public int addr;
    public A pixelCapacity;
    protected Tree <?, A, ?, ?> tree;
    private List <Point> list;
    protected IIntSize size;

    /**
     *
     */
    @Contract(pure = true)
    protected
    Library () {
    }

    /**
     * @param tree
     * @param clusterIndex
     */
    @Contract(pure = true)
    public
    Library ( Tree <?, A, ?, ?> tree, int clusterIndex ) {
        this.tree = tree;
        this.clusterIndex = clusterIndex;
    }

    /**
     *
     * @param base
     * @param power
     * @return
     */
    public static
    int nearestGreaterPowerOf ( int base, int power ) {
        switch (base) {
            case 2:
                return powersOfTwo[power];
            case 3:
                return powersOfThree[power];
            case 7:
                return powersOfSeven[power];
            case 9:
                return powersOfNine[power];
            default:
                throw new IllegalStateException("Unexpected value: " + base);
        }
    }

    /**
     * @param i
     * @return
     */
    public
    int pow2 ( int i ) {
        return powersOfTwo[i];
    }

    /**
     * @param i
     * @return
     */
    public
    int pow3 ( int i ) {
        return powersOfThree[i];
    }

    /**
     * @param i
     * @return
     */
    public
    int pow7 ( int i ) {
        return powersOfSeven[i];
    }

    /**
     * @param i
     * @return
     */
    public
    int pow9 ( int i ) {
        return powersOfNine[i];
    }


    /**
     * @param layerIndex
     * @return
     */
    public
    int calcPixelCapacity ( int layerIndex ) {
        return 0;
    }

    /**
     * @param tree
     * @param list
     * @param size
     * @param layerIndex
     * @param clusterIndex
     */
    public
    void derivePixelShifts ( Tree <?, A, ?, ?> tree,
                             List <Point> list,
                             IntSize size,
                             int layerIndex,
                             int clusterIndex
    ) {
        this.tree = tree;
        this.list = list;
        this.size = size;
        this.layerIndex = layerIndex;
        this.clusterIndex = clusterIndex;
    }

    /**
     * @param radix
     * @return
     */
    public
    List <Point> getCartesianCoordinates ( int radix ) {
        return null;
    }

    /**
     * @param sideSize
     * @return
     */
    public
    int calcLayersAmount ( int sideSize ) {
        return 0;
    }

    /**
     * @return
     */
    public
    List <Point> getList () {
        return list;
    }

    /**
     * @return
     */
    public
    IIntSize getSize () {
        return size;
    }
}
