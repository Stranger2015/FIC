package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.IImageBlock;
import org.stranger2015.opencv.fic.core.TreeNodeBase;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <N>
 * @param 
 * @param <G>
 */
public
interface IBottomUpTiler extends ITiler {

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     */
    @Override
    default
    List <Vertex> generateVerticesSet ( IImageBlock  roi, int blockWidth, int blockHeight ) {
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
     */
    default
    List <IImageBlock > generateInitialRangeBlocks ( IImageBlock  roi,
                                                        int blockWidth,
                                                        int blockHeight ) throws ValueError {

        List <Vertex> pointSet = generateVerticesSet(roi, blockWidth, blockHeight);
        List <IImageBlock > initialRangeBlocks = new ArrayList <>(pointSet.size());

        for (Vertex point : pointSet) {
            initialRangeBlocks.add(
                    roi.getSubImage(
                            (int) point.getX(),
                            (int) point.getY(),
                            blockWidth,
                            blockHeight)
            );
        }

        return initialRangeBlocks;
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     */
    @Override
    default
    List <IImageBlock > generateRangeBlocks ( IImageBlock  roi, int blockWidth, int blockHeight )
            throws ValueError {

        List <IImageBlock > rangeBlocks = generateInitialRangeBlocks(roi, blockWidth, blockHeight);
        int mergeables = successorAmount();

        IImageBlock  block1;
        IImageBlock  block2;

        for (int i = 0; i < rangeBlocks.size(); i++) {
            block1 = rangeBlocks.get(i);
            if (i + 1 < rangeBlocks.size()) {
                block2 = rangeBlocks.get(i + 1);
            }
        }

        return rangeBlocks;
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    void segmentRectangle ( TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    void segmentSquare ( TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    default
    @Override
    void segmentTriangle ( TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError {
    }

    /**
     *
     */
    @Override
    void onSuccessors ( TreeNodeBase <?> node, IImageBlock  imageBlock );

    /**
     * @param node
     * @param imageBlock
     */
//    @Override
    void onSuccessor ( TreeNodeBase <?> node, IImageBlock  imageBlock );

    /**
     * @return
     */
    default
    boolean isBottomUpTiler () {
        return true;
    }
}