package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.ICodec;

import java.util.List;

/**
 * @param <N>
 * @param
 
 * @param <G>
 */
public
class SplitImageTask extends Task {

    /**
     * @param filename
     * @param scheme
     * @param tasks
     */
    public
    SplitImageTask ( String filename,
                     EPartitionScheme scheme,
                     ICodec codec,
                     List <Task> tasks ) {

        super(filename, scheme, codec, tasks);
    }

    /**
     *
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    void onPreprocess ( IImageProcessor processor, String filename ) throws ValueError {
                inputImage = loadImage(filename);
        layers = inputImage.split();
        for (int i = 0; i < layers.size(); i++) {
            super.onPreprocess(processor, filename, layers.get(i));
        }
    }
}
