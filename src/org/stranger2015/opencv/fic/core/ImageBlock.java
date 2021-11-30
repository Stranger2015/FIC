package org.stranger2015.opencv.fic.core;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.IImageBlock;

/**
 *
 */
public
class ImageBlock extends Image implements IImageBlock {

    public static final int[] EMPTY_ARRAY = new int[0];

    public
    ImageBlock ( int rows, int cols, int blockWidth, int blockHeight ) {
        this(rows, cols, blockWidth, blockHeight, -1);//fixme
    }

    /**
     * @return
     */
    public
    int getX () {
        return x;
    }

    /**
     * @return
     */
    public
    int getWidth () {
        return width;
    }

    /**
     * @return
     */
    public
    int getHeight () {
        return height;
    }

    /**
     * @return
     */
    public
    double getBeta () {
        return beta;
    }

    public int x;
    public int y;
    public final int width;
    public final int height;
    //    public short[, ] pixelValues;
    public double beta;
    public double meanPixelValue;

    /**
     * @param width
     * @param height
     * @param type
     */
    public
    ImageBlock ( int width, int height, int type ) {
        this(0, 0, width, height, type);
    }

    /**
     * @param type
     */
    public
    ImageBlock ( int x, int y, int width, int height, int type ) {
        super(x, y, type);

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * @return
     */
    public
    int getY () {
        return y;
    }

    /**
     * @param y
     */
    public
    void setY ( int y ) {
        this.y = y;
    }

    /**
     * @param contractivity
     */
    public
    void resize ( int contractivity ) {
        int newWidth = width / contractivity;
        int newHeight = height / contractivity;

//        short[, ] newPixelValues = new short[newWidth, newHeight];
        for (int i = 0; i < newWidth; i++) {
            for (int j = 0; j < newHeight; j++) {
                put(i, j, plus( get(i * 2, j * 2, EMPTY_ARRAY),//fixme!!!
                        get(i * 2, j * 2 + 1, EMPTY_ARRAY) ,
                        get(i * 2 + 1, j * 2, EMPTY_ARRAY) ,
                        get(i * 2 + 1, j * 2, EMPTY_ARRAY)
                ) / (1.0f * contractivity * contractivity));
            }
        }

//        this.width = newWidth;
//        this.height = newHeight;
//        this.pixelValues = newPixelValues;
    }

    public int plus(int ... n){
       int result = 0;
        for (int j : n) {
            result += j;
        }

        return result;
    }

    /**
     * @return
     */
    @Override
    public
    Size getSize () {
//todo introduce static cache for that method?????????????
        return new Size(width, height);
    }
}
