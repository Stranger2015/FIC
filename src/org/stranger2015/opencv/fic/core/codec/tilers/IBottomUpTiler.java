package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.RegionOfInterest;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;

import static org.stranger2015.opencv.fic.core.EBottomUpTilerOperation.*;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
interface IBottomUpTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends ITiler <N, A, G> {

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     */
    @Override
    default
    List <Vertex> generateVerticesSet ( RegionOfInterest <A> roi, int blockWidth, int blockHeight ) {
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
    @Override
    default
    List <IImageBlock <A>> generateInitialRangeBlocks ( RegionOfInterest <A> roi,
                                                        int blockWidth,
                                                        int blockHeight ) throws ValueError {

        List <Vertex> pointSet = generateVerticesSet(roi, blockWidth, blockHeight);
        List <IImageBlock <A>> initialRangeBlocks = new ArrayList <>(pointSet.size());

        for (Vertex point : pointSet) {
            initialRangeBlocks.add(roi.getSubImage((int) point.getX(), (int) point.getY(), blockWidth, blockHeight));
        }

        return initialRangeBlocks;
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     */
    default
    List <IImageBlock <A>> generateRangeBlocks ( RegionOfInterest <A> roi, int blockWidth, int blockHeight )
            throws ValueError {

        List <IImageBlock <A>> rangeBlocks = generateInitialRangeBlocks(roi, blockWidth, blockHeight);
        int mergeables = successorAmount();

        IImageBlock <A> block1;
        IImageBlock <A> block2;

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
    void segmentRectangle ( IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    default
    @Override
    void segmentSquare ( IImageBlock <A> imageBlock ) throws ValueError {
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    default
    @Override
    void segmentTriangle ( IImageBlock <A> imageBlock ) throws ValueError {
    }

    @Override
    default
    void doTile ( IImageBlock <A> imageBlock ) throws ValueError, DepthLimitExceeded {
            TreeNodeBase <N, A, G> node = null;
            for (EBottomUpTilerOperation operation = TILE_START; operation != TILE_FINISH; ) {
                imageBlock = getDeque().pop();//todo here??
                switch (operation) {
                    case TILE_START:
                        if (imageBlock.getSize().compareTo(getMinRangeSize()) <= 1) {
                            operation = TILE_FINISH;
                            break;
                        }
                        operation=GENERATE_RANGE_BLOCKS;
//                        operation = TILE_SEGMENT_GEOMETRY;
                        break;
                    case GENERATE_RANGE_BLOCKS:
                        generateRangeBlocks(
                                new RegionOfInterest<>(imageBlock),
                                getMinRangeSize().getWidth(),
                                getMinRangeSize().getHeight());
//                        operation = TILE_SUCCESSORS;
                        operation = TILE_PREDECESSORS;
                        break;
                    case TILE_PREDECESSORS:
//                        List <TreeNodeBase <N, A, G>> successors = getBuilder().getSuccessors();
//                        int succIndex = 0;
//                        node = successors.get(succIndex);
//                        getBuilder().add(node);
//                        if (node.isLeaf()) {
//                            getBuilder().addLeafNode((TreeNode.LeafNode <N, A, G>) node);
//                            operation = TILE_LEAF;
//                        }
//                        else {
//                            EDirection q = node.getQuadrant();
                            operation = TILE_PREDECESSOR;
//                        }
                        break;
                    case TILE_PREDECESSOR:
                        getDeque().push(imageBlock);
                        onAddNode(node, imageBlock);
                        operation = TILE_START;
                        break;
//                    case TILE_LEAF:
//                                            imageBlock = getDeque().pop();
//                        onAddLeafNode((TreeNode.LeafNode <N, A, G>) node, imageBlock);
//                        operation = TILE_START;
//                        break;
                    case TILE_FINISH:
                        onFinish();
                        operation = INACTIVE;
                        break;
                    case INACTIVE:
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + operation);
                }
            }
        }

    @Override
    default
    boolean isBottomUpTiler () {
        return true;
    }
}