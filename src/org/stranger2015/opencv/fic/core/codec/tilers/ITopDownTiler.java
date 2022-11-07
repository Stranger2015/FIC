package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.RegionOfInterest;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;

import static org.stranger2015.opencv.fic.core.ETopDownTilerOperation.*;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
interface ITopDownTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends ITiler <N, A, G> {

    /**
     * @param imageBlock
     * @throws ValueError
     * @throws DepthLimitExceeded
     */
    @Override
    default
    void doTile ( IImageBlock <A> imageBlock ) throws ValueError, DepthLimitExceeded {
        TreeNodeBase <N, A, G> node = null;
        for (ETopDownTilerOperation operation = TILE_START; operation != TILE_FINISH; ) {
            imageBlock = getDeque().pop();//todo here??
            switch (operation) {
                case TILE_START:
                    if (imageBlock.getSize().compareTo(getMinRangeSize()) <= 1) {
                        operation = TILE_FINISH;
                        break;
                    }
                    operation = TILE_SEGMENT_GEOMETRY;
                    break;
                case TILE_SEGMENT_GEOMETRY:
                    segmentGeometry(imageBlock);
                    operation = TILE_SUCCESSORS;
                    break;
                case TILE_SUCCESSORS:
                    List <TreeNodeBase <N, A, G>> successors = getBuilder().getSuccessors();
                    int succIndex = 0;
                    node = successors.get(succIndex);
                    getBuilder().add(node);
                    if (node.isLeaf()) {
                        getBuilder().addLeafNode((TreeNode.LeafNode <N, A, G>) node);
                        operation = TILE_LEAF;
                    }
                    else {
                        EDirection q = node.getQuadrant();
                        operation = TILE_SUCCESSOR;
                    }
                    break;
                case TILE_SUCCESSOR:
                    getDeque().push(imageBlock);
                    onAddNode(node, imageBlock);
                    operation = TILE_START;
                    break;
                case TILE_LEAF:
                    //                    imageBlock = getDeque().pop();
                    onAddLeafNode((TreeNode.LeafNode <N, A, G>) node, imageBlock);
                    operation = TILE_START;
                    break;
                case TILE_FINISH:
                    onFinish();
                    operation = INACTIVE;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + operation);
            }
        }
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     */
    @Override
    default
    List <Vertex> generateVerticesSet ( RegionOfInterest <A> roi, int blockWidth, int blockHeight ) {
        if (roi.getWidth() != blockWidth || roi.getHeight() != blockHeight) {
            throw new IllegalStateException("roi.size(s) != blockSize(s)");
        }
        int numOfVerticesPerWidth = roi.getWidth() / blockWidth + 1;
        int numOfVerticesPerHeight = roi.getHeight() / blockHeight + 1;

        int verticesAmount = numOfVerticesPerHeight * numOfVerticesPerWidth;
        List <Vertex> vertices = new ArrayList <>(verticesAmount);
        Vertex currVertex;

        for (int i = 0; i < numOfVerticesPerWidth; i++) {
            for (int j = 0; j < numOfVerticesPerHeight; j++) {
                currVertex = new Vertex(i * blockWidth, j * blockHeight);
                vertices.add(currVertex);
            }
        }

        return vertices;
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     * @throws ValueError
     */
    @Override
    default
    List <IImageBlock <A>> generateInitialRangeBlocks ( RegionOfInterest <A> roi,
                                                        int blockWidth,
                                                        int blockHeight ) throws ValueError {

        List <Vertex> vertices = generateVerticesSet(roi, blockWidth, blockHeight);
        List <IImageBlock <A>> rangeBlocks = new ArrayList <>(vertices.size());
        double[] pixels = roi.getPixels();

        for (Vertex vertex : vertices) {
            int x = (int) vertex.getX();
            int y = (int) vertex.getY();
            IImageBlock <A> rangeBlock = roi.getSubImage(x, y, blockWidth, blockHeight);

            rangeBlock.put(x, y, pixels);

//            rangeBlock.setMeanPixelValue((int) (sumOfPixelValues / (double) (blockWidth * blockHeight)));
            rangeBlocks.add(rangeBlock);

            double[] sumOfPixelValues = new double[rangeBlock.getChannelsAmount()];
            for (int c = 0; c < sumOfPixelValues.length; c++) {
                sumOfPixelValues[c] = 0;
                for (int i = 0; i < blockWidth; i++) {
                    for (int j = 0; j < blockHeight; j++) {
                        sumOfPixelValues[c] += rangeBlock.getPixelValuesLayer(i, j, c);
//                        rangeBlock.setBeta(rangeBlock.pixelValues(x, y) - rangeBlock.getMeanPixelValue());
                    }
                }

                rangeBlock.setMeanPixelValuesLayer(c, sumOfPixelValues[c] / (blockWidth * blockWidth));
            }
        }

        return rangeBlocks;
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     * @throws ValueError
     */
    default
    @Override
    List <IImageBlock <A>> generateRangeBlocks ( RegionOfInterest <A> roi, int blockWidth, int blockHeight )
            throws ValueError {

        List <IImageBlock <A>> rangeBlocks = generateInitialRangeBlocks(roi, blockWidth, blockHeight);
        if (roi.isSquare()) {
            segmentSquare(roi);
        }

        return rangeBlocks;
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    default
    void segmentRectangle ( IImageBlock <A> imageBlock ) throws ValueError {
        IImageBlock <A> result1;
        IImageBlock <A> result2;

        int w = imageBlock.getWidth();
        int h = imageBlock.getHeight();

        if (w < h) {
            int x1 = imageBlock.getX();
            int y1 = imageBlock.getY();
            int y2 = y1 + h / 2;

            result1 = imageBlock.getSubImage(x1, y1, w, h / 2);
            result2 = imageBlock.getSubImage(x1, y2, w, h / 2);
        }
        else { //w > h
            int x1 = imageBlock.getX();
            int x2 = x1 + w / 2;
            int y1 = imageBlock.getY();

            result1 = imageBlock.getSubImage(x1, y1, w / 2, h);
            result2 = imageBlock.getSubImage(x2, y1, w / 2, h);
        }

        getDeque().push(result1);
        getDeque().push(result2);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    default
    void segmentSquare ( IImageBlock <A> imageBlock ) throws ValueError {
    }

    default
    void segmentPolygon ( IImageBlock <A> imageBlock ) throws ValueError {
    }

    /**
     * @return
     */
    @Override
    default
    boolean isBottomUpTiler () {
        return false;
    }
}
