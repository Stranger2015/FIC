package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;

import java.util.function.Function;

/**
 *
 */
public
class ImageUtils {
    /**
     *
     */
    @Contract(pure = true)
    private
    ImageUtils () {
    }

    static
    double[] sumPixels ( IImageBlock imageBlock ) {
        double[] data = new double[0];

        return data;
    }

    /**
     *
     */
    static Function <IImageBlock, double[]> calculatePixelIntensities = imageBlock -> {
        int channels = imageBlock.getChannelsAmount();
        double[] sumOfPixelValues = new double[channels];
        int blockWidth = imageBlock.getWidth() / 2;
        int blockHeight = imageBlock.getHeight() / 2;

        int numBlocksPerWidth = imageBlock.getWidth() / blockWidth + 1;
        int numBlocksPerHeight = imageBlock.getHeight() / blockHeight + 1;

        for (int c = 0; c < channels; ++c) {
            sumOfPixelValues[c] = 0;
            for (int i = 0, x = 0; i < numBlocksPerWidth; i++, x += blockWidth) {
                for (int j = 0, y = 0; j < numBlocksPerHeight; j++, y += blockHeight) {
                    IImageBlock block = null;
                    try {
                        block = imageBlock.getSubImage(x, y, blockWidth, blockHeight);
                    } catch (ValueError e) {
                        throw new RuntimeException(e);
                    }
                    double[] data = block.getPixel(x, y);
                    sumOfPixelValues[c] += data[c];
                }
            }
        }

        return sumOfPixelValues;
    };

    /**
     * @param imageBlock
     * @param function
     * @return
     */
    public
    double[] calculateData ( IImageBlock imageBlock, Function <IImageBlock, double[]> function ) {
        return function.apply(imageBlock);
    }
}
