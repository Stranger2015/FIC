package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.fic.core.codec.IImageBlock;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param
 
 * @param <G>
 */
public
class RestoreImageTask<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>

        extends BidiTask <N> {

    /**
     * @param filename
     * @param scheme
     * @param list
     */
    public
    RestoreImageTask ( String filename,
                       EPartitionScheme scheme,
                       ICodec <N> codec,
                       List <Task <N>> list ) {

        super(filename, scheme, codec, list);
    }

    /**
     * @param instance
     */
    @Override
    public
    void onCreated ( ICodec <N> instance ) {
        super.onCreated(instance);
    }

    /**
     * @param processor
     * @param filename
     * @param image
     */
    @Override
    public
    void onPreprocess ( IImageProcessor <N> processor, String filename, IImage image ) throws ValueError {
        super.onPreprocess(processor, filename, image);
    }

    /**
     * @param processor
     * @param outputImage
     */
    @Override
    public
    void onPostprocess ( IImageProcessor <N> processor, CompressedImage  outputImage ) {
        super.onPostprocess(processor, outputImage);

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

        return new CompressedImage <>(dest);
    }

}


