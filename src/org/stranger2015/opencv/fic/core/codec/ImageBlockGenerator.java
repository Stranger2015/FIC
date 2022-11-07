package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.geom.Geometry;
import org.stranger2015.opencv.fic.core.geom.GeometryFactory;
import org.stranger2015.opencv.fic.core.geom.LinearRing;
import org.stranger2015.opencv.fic.core.geom.Polygon;
import org.stranger2015.opencv.fic.core.geom.impl.CoordinateArraySequence;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.of;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public abstract
class ImageBlockGenerator<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer> {

    protected IPartitionProcessor <N, A, G> partitionProcessor;

    protected final EPartitionScheme scheme;
    protected final IEncoder <N, A, G> encoder;
    protected final IImage <A> image;

    protected IIntSize rangeSize;
    protected IIntSize domainSize;
    protected IIntSize codebookSize;

    /**
     * @param tiler
     * @param topDownTiler
     * @param bottomUpTiler
     * @param encoder
     * @param rangeSize
     * @param domainSize
     */
    @Contract(pure = true)
    protected
    ImageBlockGenerator (
            IPartitionProcessor <N, A, G> partitionProcessor,
            EPartitionScheme scheme,
            IEncoder <N, A, G> encoder,
            IImage <A> image,
            IIntSize rangeSize,
            IIntSize domainSize
    ) {
        this.partitionProcessor = partitionProcessor;
        this.scheme = scheme;
        this.encoder = encoder;
        this.image = image;
        this.rangeSize = rangeSize;
        this.domainSize = domainSize;
        this.codebookSize = domainSize;
    }

    public abstract
    ImageBlockGenerator <N, A, G> newInstance (
            IPartitionProcessor <N, A, G> partitionProcessor,
            EPartitionScheme scheme,
            IEncoder <N, A, G> encoder,
            IImage <A> image,
            IIntSize rangeSize,
            IIntSize domainSize );

    /**
     * @param roi
     * @return
     */
    public
    List <Vertex> generateVerticesSet ( RegionOfInterest <A> roi, int blockWidth, int blockHeight ) {
        return partitionProcessor.generateVerticesSet(roi, blockWidth, blockHeight);
    }

    /**
     * @param roi
     * @param rangeSize
     * @param domainSize
     * @return
     */
    public
    List <IImageBlock <A>> generateRangeBlocks ( RegionOfInterest <A> roi, int rangeSize, int domainSize )
            throws ValueError {

        return partitionProcessor.generateRangeBlocks(roi, rangeSize, domainSize);
    }


//        int numOfBlocksPerWidth = roi.getWidth() / blockWidth;
//        int numOfBlocksPerHeight = roi.getHeight() / blockHeight;
//        for (int i = 0; i < numOfBlocksPerWidth; i++) {
//            for (int j = 0; j < numOfBlocksPerHeight; j++) {
//                IImageBlock <A> block = new ImageBlock <>(roi.getMat());//fixme check-me
//                int sumOfPixelValues = 0;
//                for (int x = 0; x < blockWidth; x++) {
//                    for (int y = 0; y < blockHeight; y++) {
//                        block.put(x, y = roi.pixelValues(i, (blockWidth + x) * (blockHeight + y)));
//                        sumOfPixelValues += block.pixelValues(x, y);
//                    }
//                }
//
//                block.setMeanPixelValue((int) (sumOfPixelValues / (double) (blockWidth * blockHeight)));
//                blocks.add(block);
//            }
//        }
//

    protected
    List <IImageBlock <A>> generateInitialRangeBlocks ( RegionOfInterest <A> roi,
                                                        int blockWidth,
                                                        int blockHeight )
            throws ValueError {
//        Vertex[] vertices = generateVerticesSet(roi, blockWidth, blockHeight);
//        List <IImageBlock <A>> rangeBlocks = new ArrayList <>(vertices.length);
//        int sumOfPixelValues = 0;
//        int[] pixels = roi.getPixels();
//
//
//        for (int i = 0, verticesLength = vertices.length; i < verticesLength; i++) {
//            Vertex vertex = vertices[i];
//            int x = (int) vertex.getX();
//            int y = (int) vertex.getY();
//            IImageBlock <A> rangeBlock = roi.getSubImage(x, y, blockWidth, blockHeight);
//
//            int[] data = new int[4];
//
//            rangeBlock.put(x, y, data);//pixelValues(i, (blockWidth + x) * (blockHeight + y)));
//            sumOfPixelValues += rangeBlock.pixelValues(x, y);
//
//            rangeBlock.setMeanPixelValue((int) (sumOfPixelValues / (double) (blockWidth * blockHeight)));
////            IImageBlock <A> rangeBlock = new ImageBlock <>(roi.getSubImage(x, y, blockWidth, blockHeight).getMat());
//            rangeBlocks.add(rangeBlock);
//        }

        return partitionProcessor.generateInitialRangeBlocks(roi, blockWidth, blockHeight);
    }

    /**
     * @param roi
     * @return
     */
    public
    List <IImageBlock <A>> generateDomainBlocks ( RegionOfInterest <A> roi, List <IImageBlock <A>> rangeBlocks )
            throws ValueError {

        List <IImageBlock <A>> domainBlocks = new ArrayList <>();

        int blockWidth = domainSize.getWidth();
        int blockHeight = domainSize.getHeight();
        int numOfBlocksPerRow = roi.getWidth() - blockWidth + 1;
        int numOfBlocksPerCol = roi.getHeight() - blockHeight + 1;

        for (IImageBlock <A> rangeBlock : rangeBlocks) {
            for (int i = 0; i < numOfBlocksPerRow; i++) {
                for (int j = 0; j < numOfBlocksPerCol; j++) {
                    GeometryFactory factory = new GeometryFactory();
                    Geometry <?> geometry = new Polygon <>(
                            new LinearRing(
                                    new CoordinateArraySequence(blockHeight),
                                    new LinearRing[]{},//fixme
                                    factory));
                    IImageBlock <A> block = new ImageBlock <>(rangeBlock, i, j, blockWidth, blockHeight, geometry);
                    double[] sumOfPixelValues = new double[rangeBlock.getChannelsAmount()];
                    for (int c = 0; c < sumOfPixelValues.length; c++) {
                        sumOfPixelValues[c] = 0;
                        for (int k = 0; k < blockWidth; k++) {
                            for (int l = 0; l < blockHeight; l++) {
                                sumOfPixelValues[c] += block.getPixelValuesLayer(k, l, c);
                                block.setBeta((block.pixelValues(k, l) - block.getMeanPixelValue()));
                            }
                        }
                        block.setMeanPixelValuesLayer(c, sumOfPixelValues[c] / (blockWidth * blockWidth));
                        block.contract(2);
                        domainBlocks.add(block);
                    }
                }
            }
        }

        return domainBlocks;
    }

    /**
     * @param roi
     * @return
     */
    public
    List <IImageBlock <A>> createCodebookBlocks ( RegionOfInterest <A> roi,
                                                  List <IImageBlock <A>> domainBlocks )
            throws ValueError {

        List <IImageBlock <A>> blocks = new ArrayList <>();
        for (int i = 0; i < domainBlocks.size(); i++) {

        }

        return blocks;
    }

    /**
     * @param inputImage
     */
    public
    List <RegionOfInterest <A>> generateRegions ( IImage <A> inputImage, List <Rectangle> bounds )
            throws ValueError {

        List <RegionOfInterest <A>> blocks = new ArrayList <>();
        if (bounds.isEmpty()) {
            return of(new RegionOfInterest <>(inputImage));
        }
        for (Rectangle bound : bounds) {
            blocks.add(new RegionOfInterest <>(inputImage.getSubImage(bound)));
        }
        inputImage.setRegions(blocks);

        return blocks;
    }

    protected
    ImageBlockGenerator <N, A, G> create ( IPartitionProcessor <N, A, G> partitionProcessor,
                                           EPartitionScheme scheme,
                                           IEncoder <N, A, G> encoder,
                                           IImage <A> image,
                                           IIntSize rangeSize,
                                           IIntSize domainSize ) {

        return newInstance(
                partitionProcessor,
                scheme,
                encoder,
                image,
                rangeSize,
                domainSize);
    }

    /**
     * @param blocks
     * @param indices
     * @return
     */
    public
    IImageBlock <A> mergeBlocks ( @NotNull RegionOfInterest <A> roi,
                                  List <IImageBlock <A>> blocks,
                                  List <IImageBlock <A>> blocksToMerge ) {

        return roi.merge(blocks, blocksToMerge);
    }

    public
    void generateDomainBlocks ( List <IImageBlock <A>> rangeBlocks, IIntSize rangeSize, IIntSize domainSize ) {
    }
}
