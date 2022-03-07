package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.ITiler;
import org.stranger2015.opencv.fic.core.ImageBlock;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 * @param <G>
 */
public
class ImageBlockGenerator<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>,
        G extends BitBuffer> {

    protected final ITiler<M, A> tiler;
    protected final IEncoder <N, A, M, G> encoder;
    protected final IImage<A> image;

    protected Size rangeSize;
    protected Size domainSize;

    /**
     * @param tiler
     * @param rangeSize
     * @param domainSize
     */
    @Contract(pure = true)
    protected
    ImageBlockGenerator ( ITiler <M, A> tiler,
                          IEncoder <N, A, M, G> encoder,
                          IImage <A> image,
                          Size rangeSize,
                          Size domainSize ) {
        this.tiler = tiler;
        this.encoder = encoder;
        this.image = image;
        this.rangeSize = rangeSize;
        this.domainSize = domainSize;
    }

    /**
     * @return
     */
    public
    ImageBlockGenerator <N, A, M, G> newInstance () {
        return this;//todo
    }

    /**
     * @param inputImage
     * @return
     */
    public
    List <ImageBlock <A>> generateRangeBlocks ( M inputImage ) {
        int blockWidth = (int) rangeSize.width;
        int blockHeight = (int) rangeSize.height;
        int numOfBlocksPerRow = inputImage.getWidth() / blockWidth;
        int numOfBlocksPerCol = inputImage.getHeight() / blockHeight;
        List <ImageBlock<A>> blocks = encoder.getRangeBlocks();

        for (int i = 0; i < numOfBlocksPerRow; i++) {
            for (int j = 0; j < numOfBlocksPerCol; j++) {
                ImageBlock<A> block = new ImageBlock<>(inputImage, i, j, blockWidth, blockHeight);//fixme check-me
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
    List <ImageBlock <A>> generateDomainBlocks ( M inputImage ) {
        int blockWidth = (int) domainSize.width;
        int blockHeight = (int) domainSize.height;
        int numOfBlocksPerRow = inputImage.getWidth() - blockWidth + 1;
        int numOfBlocksPerCol = inputImage.getHeight() - blockHeight + 1;
        List <ImageBlock<A>> blocks = encoder.getDomainBlocks();

        for (int i = 0; i < numOfBlocksPerRow; i++) {
            for (int j = 0; j < numOfBlocksPerCol; j++) {
                var block = new ImageBlock <>(inputImage, i, j, blockWidth, blockHeight);
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

        return blocks;
    }

    /**
     *  @param inputImage
     * @param domainBlocks
     * @return
     */
    public
    List <ImageBlock <A>> createCodebookBlocks ( M inputImage, List <ImageBlock<A>> domainBlocks ) {
        if(domainBlocks.isEmpty() || encoder.getCodebookBlocks().isEmpty()){

        }
        List <ImageBlock<A>> blocks = encoder.getCodebookBlocks();

        return blocks;
    }
}
