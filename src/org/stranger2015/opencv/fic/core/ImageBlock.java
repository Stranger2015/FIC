package org.stranger2015.opencv.fic.core;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.IImageBlock;

/**
 *
 */
public
class ImageBlock extends Image implements IImageBlock {
    public static final int[] EMPTY_INT_ARRAY = new int[0];

    public int x;
    public int y;

    public int width;
    public int height;

    public double beta;
    public double meanPixelValue;

    protected /*final*/ Image image;

    /**
     * @param subImage
     * @param rows
     * @param cols
     * @param type
     */
    public
    ImageBlock ( Image subImage, int rows, int cols, int type ) {
        super(rows, cols, type);
        this.image = subImage;
    }

    /**
     *
     */
    public
    ImageBlock () {
        this(null, 0, 0);
    }

    /**
     * @param image
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public
    ImageBlock ( Image image, int x, int y, int w, int h ) {
        super( x, y, w, h);

        this.image = image;
    }

    /**
     * @param image
     * @param address
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public
    ImageBlock ( Image image, int address, int x, int y, int w, int h ) {
        this(image, x, y, w, h);

    }

    public
    ImageBlock ( Image image, int blockSize, int address ) {
        super(image, address, blockSize);
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
    @Override
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

    public
    void setX ( int x ) {
        this.x = x;
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

        for (int i = 0; i < newWidth; i++) {
            for (int j = 0; j < newHeight; j++) {
                put(i, j, plus(get(i * 2, j * 2, EMPTY_INT_ARRAY),//fixme!!!
                        get(i * 2, j * 2 + 1, EMPTY_INT_ARRAY),
                        get(i * 2 + 1, j * 2, EMPTY_INT_ARRAY),
                        get(i * 2 + 1, j * 2, EMPTY_INT_ARRAY)
                ) / (1.0f * contractivity * contractivity));
            }
        }
    }

    /**
     * @param n
     * @return
     */
    public
    int plus ( int... n ) {
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
    Image subImage ( int rowStart, int rowEnd, int colStart, int colEnd ) {
        return super.subImage(rowStart, rowEnd, colStart, colEnd);
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
