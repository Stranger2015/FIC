package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.IImageBlock;
import org.stranger2015.opencv.fic.core.TreeNodeBase;
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
interface ITopDownTiler extends ITiler {

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     */
    @Override
    default
    List <Vertex> generateVerticesSet ( IImageBlock  roi, int blockWidth, int blockHeight ) {
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
    default
    List <IImageBlock > generateInitialRangeBlocks ( IImageBlock  roi,
                                                        int blockWidth,
                                                        int blockHeight ) throws ValueError {

        List <Vertex> vertices = generateVerticesSet(roi, blockWidth, blockHeight);
        List <IImageBlock > rangeBlocks = new ArrayList <>(vertices.size());
        double[] pixels = roi.getPixels();

        for (Vertex vertex : vertices) {
            int x = (int) vertex.getX();
            int y = (int) vertex.getY();
            IImageBlock  rangeBlock = roi.getSubImage(x, y, blockWidth, blockHeight);
            rangeBlock.put(x, y, pixels);
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
    List <IImageBlock > generateRangeBlocks ( IImageBlock  roi, int blockWidth, int blockHeight )
            throws ValueError {

        List <IImageBlock > rangeBlocks = generateInitialRangeBlocks(roi, blockWidth, blockHeight);
        if (roi.isSquare()) {
            // segmentSquare(roi);
        }

        return rangeBlocks;
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    default
    void segmentRectangle ( TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError {
        IImageBlock  r1;
        IImageBlock  r2;

        int w = imageBlock.getWidth();
        int h = imageBlock.getHeight();

        if (w < h) {
            int x1 = imageBlock.getX();
            int y1 = imageBlock.getY();
            int y2 = y1 + h / 2;

            r1 = imageBlock.getSubImage(x1, y1, w, h / 2);
            r2 = imageBlock.getSubImage(x1, y2, w, h / 2);
        }
        else { //w > h
            int x1 = imageBlock.getX();
            int x2 = x1 + w / 2;
            int y1 = imageBlock.getY();

            r1 = imageBlock.getSubImage(x1, y1, w / 2, h);
            r2 = imageBlock.getSubImage(x2, y1, w / 2, h);
        }

        getImageBlockDeque().push(r1);
        getImageBlockDeque().push(r2);

        getNodeDeque().push(node.createNode(node, r1.getAddress(r1.getX(), r1.getY())));
        getNodeDeque().push(node.createNode(node, r2.getAddress(r2.getX(), r2.getY())));
    }

        /**
         * @param imageBlock
         * @throws ValueError
         */
    @Override
    void segmentSquare ( TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError;

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    void segmentPolygon ( TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError;

    /**
     * @return
     */
    default
    boolean isBottomUpTiler () {
        return false;
    }
}
