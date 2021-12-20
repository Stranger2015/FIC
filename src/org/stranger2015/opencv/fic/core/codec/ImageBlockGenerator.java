package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.ImageBlock;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract
class ImageBlockGenerator {

    protected Size rangeSize;
    protected Size domainSize;

    /**
     * @param rangeSize
     * @param domainSize
     */
    public
    ImageBlockGenerator ( Size rangeSize, Size domainSize ) {
        this.rangeSize = rangeSize;
        this.domainSize = domainSize;
    }

    /**
     * @param inputImage
     * @return
     */
    public
    List <ImageBlock> generateRangeBlocks ( Image inputImage ) {

        int blockWidth = (int) rangeSize.width;
        int blockHeight = (int) rangeSize.height;
        int numOfBlocksPerRow = inputImage.getWidth() / blockWidth;
        int numOfBlocksPerCol = inputImage.getHeight() / blockHeight;

        List <ImageBlock> blocks = List.of();

        for (int i = 0; i < numOfBlocksPerRow; i++) {
            for (int j = 0; j < numOfBlocksPerCol; j++) {
                ImageBlock block = new ImageBlock(i, j, blockWidth, blockHeight);
                double sumOfPixelValues = 0;
                for (int x = 0; x < blockWidth; x++) {
                    for (int y = 0; y < blockHeight; y++) {
//                        block.put(x, y] =inputImage.pixelValues[i blockWidth + x]
//       						                                                [j blockHeight +y];
//                        sumOfPixelValues += block.pixelValues[x, y];
                    }
                }
                block.meanPixelValue = sumOfPixelValues / (double) (blockWidth * blockHeight);
                blocks.add(block);
            }
        }
        return blocks;
    }

    /**
     * @param inputImage
     * @return
     */
    public
    List <ImageBlock> generateDomainBlocks ( /*Grayscale*/Image inputImage ) {
        int blockWidth = (int) domainSize.width;
        int blockHeight = (int) domainSize.height;
        int numOfBlocksPerRow = inputImage.getWidth() - blockWidth + 1;
        int numOfBlocksPerCol = inputImage.getHeight() - blockHeight + 1;
        List <ImageBlock> blocks = List.of();

        for (int i = 0; i < numOfBlocksPerRow; i++) {
            for (int j = 0; j < numOfBlocksPerCol; j++) {
                ImageBlock block = new ImageBlock(i, j, blockWidth, blockHeight);
                double sumOfPixelValues = 0;
                for (int x = 0; x < blockWidth; x++) {
                    for (int y = 0; y < blockHeight; y++) {
//                        block.pixelValues[x, y] =inputImage.pixelValues[i + x, j + y];
//                        sumOfPixelValues += block.pixelValues[x, y];
                    }
                }
                block.meanPixelValue = sumOfPixelValues / (double) (blockWidth * blockHeight);
                for (int x = 0; x < blockWidth; x++) {
                    for (int y = 0; y < blockHeight; y++) {
//                        block.beta += (block.pixelValues[x, y]-block.meanPixelValue)
//                        (block.pixelValues[x, y]-block.meanPixelValue);
                    }
                }
                block.resize(2);
                blocks.add(block);
            }
        }

        return blocks;
    }
}
