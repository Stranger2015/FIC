package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.ICodec;

import java.util.List;

/**
 *
 */
public
class SplitMergeImageTask extends BidiTask {
    /**
     * @param fn
     * @param scheme //     * @param tasks
     */
    SplitMergeImageTask ( String fn, EPartitionScheme scheme, ICodec codec ) {
        super(fn, scheme, codec, List.of(
                new ColorToGrayScaleImageTask(fn, scheme, codec, List.of()),
                new GrayScaleToColorImageTask(fn, scheme, codec, List.of())
        ));
    }

    /**
     * @param processor
     * @param outputImage
     */
    @Override
    public
    void onPostprocess ( IImageProcessor processor, CompressedImage outputImage ) {

    }

    /**
     * @param processor
     * @param filename
     * @throws ValueError
     */
    @Override
    public
    void onPreprocess ( IImageProcessor processor, String filename ) throws ValueError {

    }
}
