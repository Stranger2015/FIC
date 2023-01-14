package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.codec.ICodec;

import java.util.List;

/**
 * @param <N>
 * @param
 
 * @param <G>
 */
public
class RestoreImageTask extends BidiTask{

    /**
     * @param filename
     * @param scheme
     * @param list
     */
    public
    RestoreImageTask ( String filename,
                       EPartitionScheme scheme,
                       ICodec codec,
                       List <Task> list ) {

        super(filename, scheme, codec, list);
    }

    /**
     * @param instance
     */
    @Override
    public
    void onCreated ( ICodec instance ) {
        super.onCreated(instance);
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

    /**
     * @param processor
     * @param filename
     * @param image
     */
    @Override
    public
    void onPreprocess ( IImageProcessor processor, String filename, IImage image ) throws ValueError {
        super.onPreprocess(processor, filename, image);
    }

    /**
     * @param processor
     * @param outputImage
     */
    @Override
    public
    void onPostprocess ( IImageProcessor processor, CompressedImage  outputImage ) {

        outputImage = unionRegions(outputImage.getRegions());
    }

    /**
     * @param regions
     * @return
     */
    @Contract("_ -> new")
    private @NotNull
    CompressedImage  unionRegions (List <IImageBlock > regions ) {
        Mat dest=new Mat();
        for (IImageBlock  region : regions) {
            region.getMat().copyTo(dest);
        }

        return new CompressedImage ( dest);
    }

}


