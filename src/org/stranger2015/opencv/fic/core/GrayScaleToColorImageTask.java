package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.ICodec;

import java.util.List;

public
class GrayScaleToColorImageTask extends Task {
    /**
     * @param filename
     * @param scheme
     * @param codec
     * @param tasks
     */
    public
    GrayScaleToColorImageTask ( String filename, EPartitionScheme scheme, ICodec codec, List <Task> tasks ) {
        super(filename, scheme, codec, tasks);
    }

    /**
     * @param processor
     * @param image
     * @param outputImages
     */
    @Override
    public
    void onPostprocess ( IImageProcessor processor, IImage image, List <IImage> outputImages ) {
        image.merge(outputImages, image);
        image = new CompressedImage(image);//todo
    }

    /**
     * @param processor
     * @param filename
     */
    @Override
    public
    void onPreprocess ( IImageProcessor processor, String filename ) {

    }

    IImage merge ( List <IImage> layers, IImage inputImage ) {
    }
}
