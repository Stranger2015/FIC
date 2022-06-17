package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.opencv.core.Point;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.ESplitKind;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Deque;

import static java.lang.Math.max;
import static org.stranger2015.opencv.fic.core.IShape.EShape;

/**
 * @param <A>
 */
public
class BinTreeTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Tiler <N, A, G> {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    protected
    BinTreeTiler ( IImage <A> image,
                   IIntSize rangeSize,
                   IIntSize domainSize,
                   IEncoder <N, A, G> encoder,
                   ITreeNodeBuilder <N, A, G> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);

        r = new Rectangle[builder.getSuccessors().size()];
    }

    @Override
    public
    ITiler <N, A, G> instance () {
        return new BinTreeTiler <>(
                getImage(),
                getRangeSize(),
                getDomainSize(),
                getEncoder(),
                getBuilder());
    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 2;
    }

    /**
     * @param imageBlockShape
     * @param imageBlock
     * @param minRangeSize
     * @param queue
     */
    @Override
    public
    void segmentShape ( EShape imageBlockShape,
                        IImageBlock <A> imageBlock,
                        IIntSize minRangeSize,
                        Deque <IImageBlock <A>> queue
    ) throws ValueError {

        switch (imageBlockShape) {
            case RECTANGLE:
                segmentRectangle(imageBlock);

                break;
            case SQUARE:

                segmentSquare(imageBlock);

                break;
            case QUADRILATERAL:

                break;
            case TRIANGLE:
                segmentTriangle(imageBlock);

                break;
            case HEXAGON:

                break;
            case CIRCLE:

                break;
            case SQUIRAL:

                break;
            case IRREGULAR:

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + imageBlockShape);
        }
    }

    /**
     *
     */
    @Override
    protected
    void onFinish () {

    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    @Contract(value = "_, _ -> new")
    public
    void segmentRectangle ( IImageBlock <A> imageBlock )
            throws ValueError {

        int sideSize = chooseLongestSide(imageBlock);

        if (sideSize == imageBlock.getWidth() / 2) {
            r[0] = new Square(0, 0, sideSize);
            r[1] = new Square(sideSize, 0, sideSize);
        }
        else {
            sideSize = imageBlock.getHeight() / 2;
            r[0] = new Square(0, 0, sideSize);
            r[1] = new Square(0, sideSize, sideSize);
        }
    }

    /**
     * @param image
     * @return
     */
    private
    int chooseLongestSide ( IImage <A> image ) {
        return max(image.getWidth(), image.getHeight());
    }

    /**
     * @param imageBlock
     * @return
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( IImageBlock <A> imageBlock ) throws ValueError {
        int w = imageBlock.getWidth();
        int h = imageBlock.getHeight();

        ESplitKind dir = chooseDirection(imageBlock);
        switch (dir) {
            case HORIZONTAL:
                r[0] = new Rectangle(0, 0, w, h / 2);
                r[1] = new Rectangle(0, h / 2, w, h / 2);
                break;
            case VERTICAL:
                r[0] = new Rectangle(0, 0, w / 2, h);
                r[1] = new Rectangle(w / 2, 0, w / 2, h);
                break;
            case DIAGONAL:
                Point p0 = new Point(0, 0);
                Point p1 = new Point(w, 0);
                Point p2 = new Point(0, h);
                Point p3 = new Point(w, h);

                r[0] = new Triangle1 <A>(p0, p1, p2);
                r[1] = new Triangle1 <A>(p2, p1, p3);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + dir);
        }
    }

    /**
     * @param imageBlock
     */
    @Override
    public
    void segmentTriangle ( IImageBlock <A> imageBlock ) {


    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( TreeNode <N, A, G> node ) {

    }
}
