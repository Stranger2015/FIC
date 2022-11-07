package org.stranger2015.opencv.fic.core.codec.tilers;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.ESplitKind;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.RegionOfInterest;
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
    @Override
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
                                                 int blockHeight ) throws ValueError {

        return List.of(roi.getSubImage());
    }

    /**
     * @param imageBlockGeometry
     * @param imageBlock
     */
    @Override
    public
    void segmentGeometry ( IImageBlock <A> imageBlock ) throws ValueError {

    }

    @Override
    public
    void segmentQuadrilateral ( IImageBlock <A> imageBlock ) throws ValueError {
    }

    @Override
    public
    Deque <IImageBlock <A>> getDeque () {
        return null;
    }

    @Override
    public
    void onAddNode ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) {

    }

    @Override
    public
    void onAddLeafNode ( TreeNode.LeafNode <N, A, G> leafNode, IImageBlock <A> imageBlock ) {

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
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( IImageBlock <A> imageBlock ) throws ValueError {
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

        getDeque().push(result1);
        getDeque().push(result2);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    @Contract(value = "_, _ -> new")
    public
    void segmentRectangle ( IImageBlock <A> imageBlock ) throws ValueError {
        ESplitKind dir = chooseDirection(imageBlock);

        doSegmentRectangle(imageBlock, dir);
    }

    /**
     * @param imageBlock
     * @param dir
     * @return
     * @throws ValueError
     */
    protected
    void doSegmentRectangle ( IImageBlock <A> imageBlock, ESplitKind dir ) throws ValueError {
        int x1 = imageBlock.getX();
        int y1 = imageBlock.getY();

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

        getDeque().push(result1);
        getDeque().push(result2);
    }

    /**
     * @param imageBlock
     */
    @Override
    public
    void segmentTriangle ( IImageBlock <A> imageBlock ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public
    void segmentPolygon ( IImageBlock <A> imageBlock ) throws ValueError {

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

        return List.of(roi);
    }
}
