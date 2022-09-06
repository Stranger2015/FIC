package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImageBlock;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.core.codec.RegionOfInterest;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 *
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
        return List.of(roi.getSubImage());
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     */
    default
    List <IImageBlock <A>> generateRangeBlocks ( RegionOfInterest <A> roi,
                                                 int blockWidth,
                                                 int blockHeight ) throws ValueError{
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
     * @return
     * @throws ValueError
     */
    @Override

    List <IImageBlock <A>> segmentRectangle ( IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @return
     * @throws ValueError
     */
    default
    @Override
    List <IImageBlock <A>> segmentSquare ( IImageBlock <A> imageBlock ) throws ValueError {
        return List.of(imageBlock);
    }

    default
    @Override
    List <IImageBlock <A>> segmentTriangle ( IImageBlock <A> imageBlock ) throws ValueError {
        return List.of(imageBlock);
    }
}