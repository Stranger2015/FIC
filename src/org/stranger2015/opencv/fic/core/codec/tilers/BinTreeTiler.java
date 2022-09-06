package org.stranger2015.opencv.fic.core.codec.tilers;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.ESplitKind;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.RegionOfInterest;
import org.stranger2015.opencv.fic.core.geom.Geometry;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Deque;
import java.util.List;

import static org.stranger2015.opencv.fic.core.codec.ESplitKind.HORIZONTAL;
import static org.stranger2015.opencv.fic.core.codec.ESplitKind.VERTICAL;

/**
 * @param <A>
 */
public abstract
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
    }

    /**
     * @return
     */
    public abstract
    ITiler <N, A, G> instance ();

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 2;
    }

    @Override
    public
    List <IImageBlock <A>> generateRangeBlocks ( RegionOfInterest <A> roi,
                                                 int blockWidth,
                                                 int blockHeight )
            throws ValueError {

        return List.of(roi.getSubImage());
    }

    /**
     * @param imageBlockGeometry
     * @param imageBlock
     * @param minRangeSize
     * @param queue
     */
    //@Override
    public
    List <IImageBlock <A>> segmentGeometry ( Geometry <?> imageBlockGeometry,
                                             IImageBlock <A> imageBlock,
                                             IIntSize minRangeSize,
                                             Deque <IImageBlock <A>> queue
    ) throws ValueError {
//
//        List <IImageBlock <A>> result = null;
////        switch (imageBlockGeometry) {
//////            case RECTANGLE:
////                result = segmentRectangle(imageBlock);
////                break;
//////            case SQUARE:
////                result = segmentSquare(imageBlock);
////                break;
//////            case QUADRILATERAL:
////                result = segmentQuadrilateral(imageBlock);
////                break;
//////            case TRIANGLE:
////                result = segmentTriangle(imageBlock);
////                break;
//////            case HEXAGON:
//////            case IRREGULAR:
//////            case SQUIRAL:
////            case CIRCLE:
////                break;
////            default:
////                throw new IllegalStateException("Unexpected value: " + imageBlockGeometry);
////        }
//
        return null;//result;
    }

    @Override
    public
    List <IImageBlock <A>> segmentQuadrilateral ( IImageBlock <A> imageBlock ) throws ValueError {
        return List.of(imageBlock);
    }

    /**
     *
     */
    @Override
    public
    void onFinish () {

    }

    /**
     * @param imageBlock
     * @return
     * @throws ValueError
     */
    @Override
    public
    List <IImageBlock <A>> segmentSquare ( IImageBlock <A> imageBlock ) throws ValueError {
        int x = imageBlock.getX();
        int y = imageBlock.getY();
        int w = imageBlock.getWidth();
        int h = imageBlock.getHeight();

        IImageBlock <A> result1;
        IImageBlock <A> result2;

        ESplitKind dir = chooseDirection(imageBlock);

        if (dir == HORIZONTAL) {
            result1 = imageBlock.getSubImage(x, y, w, h / 2);
            result2 = imageBlock.getSubImage(x, y + h / 2, w, h / 2);
        }
        else if (dir == VERTICAL) {
            result1 = imageBlock.getSubImage(x, y, w / 2, h);
            result2 = imageBlock.getSubImage(x + w / 2, y, w / 2, h);
        }
        else {
            throw new IllegalStateException("Unexpected value: " + dir);
        }
        //            case DIAGONAL:
////                GeometryFactory geomFactory = new GeometryFactory();
//
//                Coordinate p0 = new Coordinate(x, y);
//                Coordinate p1 = new Coordinate(x + w, y);
//                Coordinate p2 = new Coordinate(x, y + h);
//                Coordinate p3 = new Coordinate(x + w, y + h);
//
//                result1 = imageBlock.getTriangleSubImage(p0, p1, p2);//geomFactory
//                result2 = imageBlock.getTriangleSubImage(p2, p1, p3);
//                break;

        return List.of(result1, result2);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    @Contract(value = "_, _ -> new")
    public
    List <IImageBlock <A>> segmentRectangle ( IImageBlock <A> imageBlock ) throws ValueError {
        ESplitKind dir = chooseDirection(imageBlock);

        return doSegmentRectangle(imageBlock, dir);

    }

    /**
     * @param imageBlock
     * @param dir
     * @return
     * @throws ValueError
     */
    protected
    List <IImageBlock <A>> doSegmentRectangle ( IImageBlock <A> imageBlock, ESplitKind dir ) throws ValueError {
        int x1 = imageBlock.getX();
        int y1 = imageBlock.getY();
//        int x2 = imageBlock.getY();
        int w = imageBlock.getWidth();
        int h = imageBlock.getHeight();

        IImageBlock <A> result1 = null;
        IImageBlock <A> result2 = null;
        if (dir == VERTICAL) {
            result1 = imageBlock.getSubImage(x1, y1, w / 2, h);
            result2 = imageBlock.getSubImage(x1 + w / 2, y1, w / 2, h);
        }
        else if (dir == HORIZONTAL) {
            result1 = imageBlock.getSubImage(x1, y1, w, h / 2);
            result2 = imageBlock.getSubImage(x1, y1 + h / 2, w, h / 2);
        }
        else {
            throw new IllegalStateException("Unexpected value: " + dir);
        }

        return List.of(result1, result2);
    }

    /**
     * @param imageBlock
     */
    @Override
    public
    List <IImageBlock <A>> segmentTriangle ( IImageBlock <A> imageBlock ) {
        throw new UnsupportedOperationException();
    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( TreeNode <N, A, G> node ) {
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     */
    @Override
    public
    List <Vertex> generateVerticesSet ( RegionOfInterest <A> roi,
                                        int blockWidth,
                                        int blockHeight ) {
        return List.of(new Vertex[0]);
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     * @throws ValueError
     */
    @Override
    public
    List <IImageBlock <A>> generateInitialRangeBlocks ( RegionOfInterest <A> roi,
                                                        int blockWidth,
                                                        int blockHeight )
            throws ValueError {

        return List.of();
    }
}
