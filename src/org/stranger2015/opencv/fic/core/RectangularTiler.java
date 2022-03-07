package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;

public
class RectangularTiler<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>,
        G extends BitBuffer>

        implements ITiler <M, A> {

    protected M image;

    private final int rows;
    private final int cols;

    /**
     * @param rows
     * @param cols
     */
    @Contract(pure = true)
    public
    RectangularTiler ( int rows, int cols ) {
        this.rows = rows;
        this.cols = cols;
    }

    /**
     * @param sideSize
     */
    @Contract(pure = true)
    public
    RectangularTiler ( int sideSize ) {
        this(sideSize, sideSize);
    }

    /**
     * @param t
     * @return
     */
//    @SuppressWarnings("unchecked")
    @Override
    public
    List <ImageBlock <A>> tile ( M t ) {
        M img = adjustImageSizeDown(image, rows, cols);

        int blockHeight = img.getHeight() / rows;
        int blockWidth = img.getWidth() / cols;

        List <ImageBlock<A>> blocksList = new ArrayList <>(rows * cols);
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                blocksList.add(new ImageBlock <>(img, blockWidth * x, blockHeight * y, blockWidth, blockHeight));
            }
        }

        return blocksList;
    }

    /**
     * Adjust image size such that it can be split by
     * the given rows and columns.
     * Adjust takes place by reducing the image size.
     *
     * @param image the image to split and fit in rows and columns
     * @param rows  the rows to split the image
     * @param cols  the columns to split the image
     * @return the adjusted image, the image with the correct size
     */
    private
    M adjustImageSizeDown ( final M image, final int rows, final int cols ) {
        int width = image.getWidth();

        while (width % cols != 0) {
            width--;
        }

        int height = image.getHeight();

        while (height % rows != 0) {
            height--;
        }

        return image.getSubImage(0, 0, width, height);
    }
}
