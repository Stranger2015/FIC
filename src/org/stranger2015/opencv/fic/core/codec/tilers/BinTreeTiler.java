package org.stranger2015.opencv.fic.core.codec.tilers;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.ESplitKind;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.IImageBlock;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

import static org.stranger2015.opencv.fic.core.codec.ESplitKind.HORIZONTAL;
import static org.stranger2015.opencv.fic.core.codec.ESplitKind.VERTICAL;

/**
 * @param
 */
public abstract
class BinTreeTiler extends Tiler {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    protected
    BinTreeTiler ( IImage image,
                   IIntSize rangeSize,
                   IIntSize domainSize,
                   IEncoder encoder,
                   ITreeNodeBuilder <?> builder ) {

        super(image,
                rangeSize,
                domainSize,
                encoder,
                builder
        );
    }

    /**
     * @return
     */
    @Override
    public abstract
    ITiler instance ();

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 2;
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
    List <IImageBlock > generateRangeBlocks ( IImageBlock  roi,
                                                 int blockWidth,
                                                 int blockHeight ) throws ValueError {
        return List.of(roi.getSubImage());
    }

    /**
     * @param imageBlockGeometry
     * @param node
     * @param imageBlock
     */
    @Override
    public
    void segmentGeometry ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {

    }

    @Override
    public
    void segmentQuadrilateral ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {
        super.segmentQuadrilateral(node, imageBlock);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {
        int x = imageBlock.getX();
        int y = imageBlock.getY();

        int w = imageBlock.getWidth();
        int h = imageBlock.getHeight();

        IImageBlock  r1;
        IImageBlock  r2;

        ESplitKind dir = chooseDirection(imageBlock);

        if (dir == HORIZONTAL) {
            r1 = imageBlock.getSubImage(x, y, w, h / 2);
            r2 = imageBlock.getSubImage(x, y + h / 2, w, h / 2);
        }
        else if (dir == VERTICAL) {
            r1 = imageBlock.getSubImage(x, y, w / 2, h);
            r2 = imageBlock.getSubImage(x + w / 2, y, w / 2, h);
        }
        else {
            throw new IllegalStateException("Unexpected value: " + dir);
        }

        getImageBlockDeque().push(r1);
        getImageBlockDeque().push(r2);

        getNodeDeque().push(node.createChild(r1.getAddress(r1.getX(), r1.getY())));
        getNodeDeque().push(node.createChild(r2.getAddress(r2.getX(), r2.getY())));
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    @Contract(value = "_, _ -> new")
    public
    void segmentRectangle ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {
        ESplitKind dir = chooseDirection(imageBlock);

        doSegmentRectangle(node, imageBlock, dir);
    }

    /**
     * @param imageBlock
     * @param dir
     * @return
     * @throws ValueError
     */
    protected
    void doSegmentRectangle ( TreeNodeBase <N> node, IImageBlock  imageBlock, ESplitKind dir )
            throws ValueError {

        int x1 = imageBlock.getX();
        int y1 = imageBlock.getY();

        int w = imageBlock.getWidth();
        int h = imageBlock.getHeight();

        IImageBlock  r1;
        IImageBlock  r2;
        if (dir == VERTICAL) {
            r1 = imageBlock.getSubImage(x1, y1, w / 2, h);
            r2 = imageBlock.getSubImage(x1 + w / 2, y1, w / 2, h);
        }
        else if (dir == HORIZONTAL) {
            r1 = imageBlock.getSubImage(x1, y1, w, h / 2);
            r2 = imageBlock.getSubImage(x1, y1 + h / 2, w, h / 2);
        }
        else {
            throw new IllegalStateException("Unexpected value: " + dir);
        }

        getImageBlockDeque().push(r1);
        getImageBlockDeque().push(r2);

        getNodeDeque().push(node.createNode(node, r1.getAddress(r1.getX(), r1.getY())));
        getNodeDeque().push(node.createNode(node, r2.getAddress(r2.getX(), r2.getY())));
    }

    /**
     * @param imageBlock
     */
    @Override
    public
    void segmentTriangle ( TreeNodeBase <N> node, IImageBlock  imageBlock ) {
        throw new UnsupportedOperationException();
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentPolygon ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {

    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     */
    @Override
    public
    List <Vertex> generateVerticesSet ( IImageBlock  roi, int blockWidth, int blockHeight ) {
        return List.of(new Vertex[0]);
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     * @throws ValueError
     */
    public
    List <IImageBlock > generateInitialRangeBlocks ( IImageBlock  roi, int blockWidth, int blockHeight )
            throws ValueError {

        return List.of(roi.getSubImage());
    }
}
