package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.ImageBlock;

import java.util.List;

/**
 *
 */
//abstract
public class ImageBlockGenerator {
    protected final IEncoder <?, ?, ?> encoder;
    protected final IImage image;
    protected Size rangeSize;
    protected Size domainSize;

    /**
     * @param rangeSize
     * @param domainSize
     */
    @Contract(pure = true)
    protected
    ImageBlockGenerator ( IEncoder <?, ?, ?> encoder, IImage image, Size rangeSize, Size domainSize ) {
        this.encoder = encoder;
        this.image = image;
        this.rangeSize = rangeSize;
        this.domainSize = domainSize;
    }

    /**
     * @return
     */
    public
    ImageBlockGenerator newInstance () {
        return this;//todo
    }

    /**
     * @param inputImage
     */
    public
    void generateRangeBlocks ( IImage inputImage ) {
        int blockWidth = (int) rangeSize.width;
        int blockHeight = (int) rangeSize.height;
        int numOfBlocksPerRow = inputImage.getWidth() / blockWidth;
        int numOfBlocksPerCol = inputImage.getHeight() / blockHeight;
        List <ImageBlock> blocks = encoder.getRangeBlocks();

        for (int i = 0; i < numOfBlocksPerRow; i++) {
            for (int j = 0; j < numOfBlocksPerCol; j++) {
                ImageBlock block = new ImageBlock(inputImage, i, j, blockWidth, blockHeight);//fixme check-me
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
    }

    /**
     * @param inputImage
     */
    public
    void generateDomainBlocks ( IImage inputImage ) {
        int blockWidth = (int) domainSize.width;
        int blockHeight = (int) domainSize.height;
        int numOfBlocksPerRow = inputImage.getWidth() - blockWidth + 1;
        int numOfBlocksPerCol = inputImage.getHeight() - blockHeight + 1;
        List <ImageBlock> blocks = encoder.getDomainBlocks();

        for (int i = 0; i < numOfBlocksPerRow; i++) {
            for (int j = 0; j < numOfBlocksPerCol; j++) {
                ImageBlock block = new ImageBlock(inputImage, i, j, blockWidth, blockHeight);
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
                block.contract(2);
                blocks.add(block);
            }
        }

    }

    /**
     * @param inputImage
     * @param domainBlocks
     */
    public
    void createCodebookBlocks ( IImage inputImage, List <ImageBlock> domainBlocks ) {
        List <ImageBlock> blocks = encoder.getCodebookBlocks();
    }
}
