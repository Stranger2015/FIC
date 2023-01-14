package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.ICodec;

import java.util.List;

public
class ColorToGrayScaleImageTask extends Task {
    /**
     * @param filename
     * @param scheme
     * @param codec
     * @param tasks
     */
    public
    ColorToGrayScaleImageTask ( String filename, EPartitionScheme scheme, ICodec codec, List <Task> tasks ) {
        super(filename, scheme, codec, tasks);
    }

    /**
     * @param processor
     * @param filename
     * @throws ValueError
     */
    @Override
    public
    void onPreprocess ( IImageProcessor processor, String filename ) throws ValueError {
        inputImage = loadImage(filename);
        layers = inputImage.split();
        for (IImage layer : layers) {
            super.onPreprocess(processor, filename, layer);
        }
    }
}
