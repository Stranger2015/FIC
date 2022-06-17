package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.stranger2015.opencv.fic.core.IShape.*;

/**
 * @param <N>
 * @param <A>
 
 * @param <G>
 */
@Deprecated
public
class RectangularTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>

        extends Tiler <N, A, G> {

    private int rows;
    private int cols;
    private IIntSize rangeSize;
    private IIntSize domainSize;

    /**
     *
     */
    @Contract(pure = true)
    public
    RectangularTiler ( IImage <A> image, IIntSize rangeSize, IIntSize domainSize, int rows, int cols ) {
        this(image, rangeSize, domainSize);

        this.rows = rows;
        this.width = cols;
    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    RectangularTiler ( IImage <A> image, IIntSize rangeSize, IIntSize domainSize ) {
        this(image,image.getWidth(),image.getHeight(),rangeSize, domainSize);
    }

    public
    RectangularTiler ( IImage<A> image, int width, int height ) {
        super(image, width, height);
    }

    public
    RectangularTiler ( IImage<A> image, int width, int height, IIntSize rangeSize, IIntSize domainSize ) {
        this(image,width,height);


        this.rangeSize = rangeSize;
        this.domainSize = domainSize;
    }

    @Override
    public
    ITiler <N, A, G> instance () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 0;
    }

    /**
     * @param imageBlock
     */
    @Override
    public
    void segmentRectangle ( IImageBlock <A> imageBlock ) {

    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( IImageBlock <A> imageBlock ) throws ValueError {

    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentTriangle ( IImageBlock <A> imageBlock ) throws ValueError {

    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( TreeNode <N, A, G> node ) {

    }

    /**
     * @param imageBlockShape
     * @param imageBlock
     * @param minRangeSize
     * @param queue
     * @throws ValueError
     */
    @Override
    public
    void segmentShape ( EShape imageBlockShape,
                        IImageBlock <A> imageBlock,
                        IIntSize minRangeSize,
                        Deque <IImageBlock <A>> queue )
            throws ValueError {

    }

    /**
     *
     */
    @Override
    protected
    void onFinish () {

    }

//    @Override
    protected
    void doTile ( IntSize minRangeSize, Deque <IImageBlock <A>> queue ) throws ValueError {
        super.doTile(minRangeSize, queue);
    }

//    /**
//     * @param image
//     * @return
//     */
//    @Override
//    public
//    List <IImageBlock <A>> tile ( IImage <A> image, IntSize size, Deque<IImageBlock<A>> queue )
//            throws ValueError {
//        IImage <A> img = adjustImageSizeDown(image, rows, cols);//it's redundant since defined preproc. phase,
//         where image is normalized to square form and the side size is set to the nearest greater power of two.
//
//        int blockheight = img.getHeight() / rows;
//        int blockwidth = img.getWidth() / cols;
//
//        List <IImageBlock <A>> blocklist = new ArrayList <>(rows * cols);
//        for (int y = 0; y < rows; y++) {
//            for (int x = 0; x < cols; x++) {
//                blocklist.add((IImageBlock <A>)
//                        img.subImage(
//                                blockwidth * x,
//                                blockwidth,
//                                blockheight * y,
//                                blockheight));
//            }
//        }
//
//        return blocklist;
//    }
//
//    /**
//     * Adjust image size such that it can be split by
//     * the given rows and columns.
//     * Adjust takes place by reducing the image size.
//     *
//     * @param image the image to split and fit in rows and columns
//     * @param rows  the rows to split the image
//     * @param cols  the columns to split the image
//     * @return the adjusted image, the image with the correct size
//     */
//    private
//    IImage <A> adjustImageSizeDown ( IImage <A> image, int rows, int cols ) throws ValueError {
//        int width = image.getWidth();
//
//        while (width % cols != 0) {
//            width--;
//        }
//
//        int height = image.getHeight();
//
//        while (height % rows != 0) {
//            height--;
//        }
//
//        return image.getSubImage(0, 0, width, height);
//    }
}
