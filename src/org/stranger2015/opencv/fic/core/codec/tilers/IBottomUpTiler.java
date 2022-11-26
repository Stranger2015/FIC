package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImageBlock;
import org.stranger2015.opencv.fic.core.TreeNodeBase;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.core.codec.IImageBlock;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;

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
    List <Vertex> generateVerticesSet ( IImageBlock <A> roi, int blockWidth, int blockHeight ) {
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
    List <IImageBlock <A>> generateInitialRangeBlocks ( IImageBlock <A> roi,
                                                        int blockWidth,
                                                        int blockHeight ) throws ValueError {

        List <Vertex> pointSet = generateVerticesSet(roi, blockWidth, blockHeight);
        List <IImageBlock <A>> initialRangeBlocks = new ArrayList <>(pointSet.size());

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
    List <IImageBlock <A>> generateRangeBlocks ( IImageBlock <A> roi, int blockWidth, int blockHeight )
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
    void segmentRectangle ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    void segmentSquare ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    default
    @Override
    void segmentTriangle ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
    }

    /**
     *
     */
    @Override
    void onSuccessors ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock );

    /**
     * @param node
     * @param imageBlock
     */
    @Override
    void onSuccessor ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock );

    /**
     * @return
     */
    default
    boolean isBottomUpTiler () {
        return true;
    }
}