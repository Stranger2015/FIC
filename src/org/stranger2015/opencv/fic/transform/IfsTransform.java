package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;

import static org.stranger2015.opencv.fic.transform.IfsTransform.SYM.*;

public
class IfsTransform<M extends Image, C extends CompressedImage> extends ImageTransform <M, C> {

    private final static int[] ia = new int[0];

    /**
     * @param inputImage
     * @param transformMatrix
     * @param interpolationType
     * @return
     */
    @Override
    public
    M transform ( M inputImage, M transformMatrix, EInterpolationType interpolationType ) {
        return null;
    }

    /**
     *
     */
    enum SYM {
        SYM_FDFLIP,
        SYM_HFLIP,
        SYM_MAX,
        SYM_NONE,
        SYM_R180,
        SYM_R270,
        SYM_R90,
        SYM_RDFLIP,
        SYM_VFLIP,
        ;
    }

    /**
     * @param fromX
     * @param fromY
     * @param toX
     * @param toY
     * @param size
     * @param symmetry
     * @param scale
     * @param offset
     */
    public
    IfsTransform (M image,
            int fromX,
                   int fromY,
                   int toX,
                   int toY,
                   int size,
                   SYM symmetry,
                   double scale,
                   int offset ) {
        super(image);

        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.size = size;
        this.symmetry = symmetry;
        this.scale = scale;
        this.offset = offset;
    }

    public
    M downSample ( M src, /*int srcWidth,*/ int startX, int startY, int targetSize ) {
        Image out = new Image();
        //        PixelValue* dest = new PixelValue[targetSize * targetSize];
//        int srcWidth = src.width();
        int destX = 0;
        int destY = 0;

        for (int y = startY; y < startY + targetSize * 2; y += 2) {
            for (int x = startX; x < startX + targetSize * 2; x += 2) {
                // Perform simple 2x2 average
                int pixel =
                        (src.get(x, y, ia) +
                                src.get(x + 1, y, ia) +
                                src.get(x, y + 1, ia) +
                                src.get(x + 1, y + 1, ia)) / 4;
                out.put(destX, destY, pixel);
                destX++;
            }
            destY++;
            destX = 0;
        }

        return src;//fixme
    }

    /**
     * @return
     */
    private
    boolean isScanlineOrder () {
        return (
                symmetry == SYM_NONE ||
                        symmetry == SYM_R180 ||
                        symmetry == SYM_HFLIP ||
                        symmetry == SYM_VFLIP
        );
    }

    /**
     * @return
     */
    private
    boolean isPositiveX () {
        return (
                symmetry == SYM_NONE ||
                        symmetry == SYM_R90 ||
                        symmetry == SYM_VFLIP ||
                        symmetry == SYM_RDFLIP
        );
    }

    /**
     * @return
     */
    private
    boolean isPositiveY () {
        return (
                symmetry == SYM_NONE ||
                        symmetry == SYM_R270 ||
                        symmetry == SYM_HFLIP ||
                        symmetry == SYM_RDFLIP
        );
    }

    // Spatial transformation
    private final int fromX;
    private final int fromY;
    private final int toX;
    private final int toY;
    private final int size;

    // Symmetry operation
    private final SYM symmetry;

    // Pixel intensity
    private final double scale;
    private final int offset;

    /**
     * @return
     */
    public
    int getFromX () {
        return fromX;
    }

    /**
     * @return
     */
    public
    int getFromY () {
        return fromY;
    }

    /**
     * @return
     */
    public
    int getToX () {
        return toX;
    }

    /**
     * @return
     */
    public
    int getToY () {
        return toY;
    }

    /**
     * @return
     */
    public
    int getSize () {
        return size;
    }

    /**
     * @return
     */
    public
    SYM getSymmetry () {
        return symmetry;
    }

    /**
     * @return
     */
    public
    double getScale () {
        return scale;
    }

    /**
     * @return
     */
    public
    int getOffset () {
        return offset;
    }

}