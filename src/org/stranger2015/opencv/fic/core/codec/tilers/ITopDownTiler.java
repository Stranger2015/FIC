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
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
interface  ITopDownTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
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
                                                        int blockHeight )
            throws ValueError {

        List<Vertex> vertices = generateVerticesSet(roi, blockWidth, blockHeight);
        List <IImageBlock <A>> rangeBlocks = new ArrayList <>(vertices.size());
        int sumOfPixelValues = 0;
        int[] pixels = roi.getPixels();

        for (Vertex vertex : vertices) {
            int x = (int) vertex.getX();
            int y = (int) vertex.getY();
            IImageBlock <A> rangeBlock = roi.getSubImage(x, y, blockWidth, blockHeight);

            rangeBlock.put(x, y, pixels);
            sumOfPixelValues += rangeBlock.pixelValues(x, y);

            rangeBlock.setMeanPixelValue((int) (sumOfPixelValues / (double) (blockWidth * blockHeight)));
            rangeBlocks.add(rangeBlock);
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
    List <IImageBlock <A>> generateRangeBlocks ( RegionOfInterest <A> roi,
                                                 int blockWidth,
                                                 int blockHeight )
            throws ValueError {

        List <IImageBlock <A>> rangeBlocks = generateInitialRangeBlocks(roi, blockWidth, blockHeight);
        if (roi.isSquare()) {
            List <IImageBlock <A>> segments = segmentSquare(roi);

        }

        return  List.of();
    }

    /**
     * @param imageBlock
     * @return
     * @throws ValueError
     */
    @Override
    default
    List <IImageBlock <A>> segmentRectangle ( IImageBlock <A> imageBlock ) throws ValueError {
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

        return List.of(result1, result2);
    }
}
