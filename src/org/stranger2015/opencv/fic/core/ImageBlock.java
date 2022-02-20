package org.stranger2015.opencv.fic.core;

import org.opencv.core.Size;

/**
 *
 */
public
class ImageBlock<A extends Address <A>> extends Image <A> {

    /**
     *
     */
//    public Address <A> address;

    public int width;
    public int height;

    public double beta;
    public double meanPixelValue;

    protected IImage <A> image;

    /**
     * @param subImage
     * @param rows
     * @param cols
     * @param type
     */
    public
    ImageBlock ( IImage <A> subImage, int rows, int cols, int type ) {
        super(subImage, rows, cols, type);

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
    ImageBlock ( IImage <A> image, int x, int y, int w, int h ) {
        super(image, x, y, w, h);

        this.image = image;
    }


    public
    ImageBlock ( IImage <A> image, int blockSize, int address ) {
        super(image, address, blockSize);
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
//
//    /**
//     * @param width
//     * @param height
//     * @param type
//     */
//    public
//    ImageBlock ( int width, int height, int type ) throws ValueError {
//        this( width, height, type);
//    }

    /**
     * @param type
     */
    public
    ImageBlock ( Address<A> address, int width, int height, int type ) {
        super(null, address, type);//fixme

        this.width = width;
        this.height = height;
    }

    @Override
    public
    Address <A> getAddress () {
        return address;
    }

    public
    void setAddress ( Address <A> address ) {
        this.address = address;
    }

    //    /**
//     * @param contractivity
//     */
//    public
//    void resize ( int contractivity ) {
//        int newWidth = width / contractivity;
//        int newHeight = height / contractivity;
//
//        for (int i = 0; i < newWidth; i++) {
//            for (int j = 0; j < newHeight; j++) {
//                Pixel[] pixels = new Pixel[4];
//                pixels[0] = new Pixel(get(i * 2, j * 2));
//                pixels[1] = new Pixel(get(i * 2, j * 2 + 1));
//                pixels[2] = new Pixel(get(i * 2 + 1, j * 2));
//                pixels[3] = new Pixel(get(i * 2 + 1, j * 2 + 1));
//            }
//        }
//
//      // /(1.0f * contractivity * contractivity));
//
//
//
//            //             put(i, j, Pixel.plus(
//        }

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
    IImage subImage ( int rowStart, int rowEnd, int colStart, int colEnd ) {
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
