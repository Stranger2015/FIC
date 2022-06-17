package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
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
@SuppressWarnings("WriteOnlyObject")
public
class ImageBlockGenerator<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer> {

    protected final ITiler <N, A, G> tiler;
    protected final EPartitionScheme scheme;
    protected final IEncoder <N, A, G> encoder;
    protected final IImage <A> image;

    protected IIntSize rangeSize;
    protected IIntSize domainSize;
    protected IIntSize codebookSize;

//    protected Point[] pointSet;

    /**
     * @param tiler
     * @param encoder
     * @param rangeSize
     * @param domainSize
     */
    @Contract(pure = true)
    public
    ImageBlockGenerator ( ITiler <N, A, G> tiler,
                          EPartitionScheme scheme,
                          IEncoder <N, A, G> encoder,
                          IImage <A> image,
                          IIntSize rangeSize,
                          IIntSize domainSize
    ) {
        this.tiler = tiler;
        this.scheme = scheme;
        this.encoder = encoder;
        this.image = image;
        this.rangeSize = rangeSize;
        this.domainSize = domainSize;
        this.codebookSize = domainSize;//fixme
    }

    /**
     * @return
     */
    public
    ImageBlockGenerator <N, A, G> newInstance () {
        return this;//todo
    }

    /**
     * @param roi
     * @return
     */
    public
    Vertex[] generatePointSet ( RegionOfInterest <A> roi, int blockWidth, int blockHeight ) {
        int numOfPointsPerWidth = roi.getWidth() / blockWidth + 1;
        int numOfPointsPerHeight = roi.getHeight() / blockHeight + 1;

        int pointAmount = numOfPointsPerHeight * numOfPointsPerWidth;

        List <Vertex> points = new ArrayList <>(pointAmount);
        Vertex currPoint;

        for (int i = 0; i < numOfPointsPerWidth; i++) {
            for (int j = 0; j < numOfPointsPerHeight; j++) {
                currPoint = new Vertex(i * blockWidth, j * blockHeight);
                points.add(currPoint);
            }
        }

        return points.toArray(new Vertex[pointAmount]);
    }

    /**
     * @param roi
     * @return
     */
    public
    List <IImageBlock <A>> generateRangeBlocks ( RegionOfInterest <A> roi ) {
        int blockWidth = rangeSize.getWidth();
        int blockHeight = rangeSize.getHeight();
        int numOfBlocksPerWidth = roi.getWidth() / blockWidth;//fixme
        int numOfBlocksPerHeight = roi.getHeight() / blockHeight;
        List <IImageBlock <A>> blocks = roi.getRangeBlocks();
        for (int i = 0; i < numOfBlocksPerWidth; i++) {
            for (int j = 0; j < numOfBlocksPerHeight; j++) {
                IImageBlock <A> block = new ImageBlock <>(roi.getMat()/*, i, blockWidth*/);//fixme check-me
                int sumOfPixelValues = 0;
                for (int x = 0; x < blockWidth; x++) {
                    for (int y = 0; y < blockHeight; y++) {
                        block.put(x, y = roi.pixelValues(i, (blockWidth + x) * (blockHeight + y)));
                        sumOfPixelValues += block.pixelValues(x, y);
                    }
                }

                block.setMeanPixelValue((int) (sumOfPixelValues / (double) (blockWidth * blockHeight)));
                blocks.add(block);
            }
        }

        return blocks;
    }

    /**
     * @param roi
     * @return
     */
    public
    List <IImageBlock <A>> generateDomainBlocks ( RegionOfInterest <A> roi ) {
        int blockWidth = domainSize.getWidth();
        int blockHeight = domainSize.getHeight();
        int numOfBlocksPerRow = roi.getWidth() - blockWidth + 1;
        int numOfBlocksPerCol = roi.getHeight() - blockHeight + 1;
        List <IImageBlock <A>> blocks = roi.getDomainBlocks();

        for (int i = 0; i < numOfBlocksPerRow; i++) {
            for (int j = 0; j < numOfBlocksPerCol; j++) {
                IImageBlock <A> block = new ImageBlock <A>(roi, i, j, blockWidth, blockHeight);
                double sumOfPixelValues = 0;
                for (int x = 0; x < blockWidth; x++) {
                    for (int y = 0; y < blockHeight; y++) {
                        block.pixelValues(x, y);//roi.pixelValues(i + x, j + y); todo apply
                        sumOfPixelValues += block.pixelValues(x, y);
                    }
                }

                block.setMeanPixelValue((int) (sumOfPixelValues / (blockWidth * blockWidth)));
                for (int x = 0; x < blockWidth; x++) {
                    for (int y = 0; y < blockHeight; y++) {
                        block.setBeta((block.pixelValues(x, y) - block.getMeanPixelValue()));
//                        final int i1 = block.pixelValues(x, y) - block.getMeanPixelValue();
                    }
                }

                block.contract(2);
                blocks.add(block);
            }
        }

        return blocks;
    }

    /**
     * @param roi
     * @return
     */
    public
    List <IImageBlock <A>> createCodebookBlocks ( RegionOfInterest <A> roi )
            throws ValueError {

        List <IImageBlock <A>> blocks = new ArrayList <>();
        if (blocks.isEmpty()) {
            throw new ValueError("");
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
}
