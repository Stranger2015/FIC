package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.fic.core.codec.RegionOfInterest;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 
 * @param <G>
 */
public
class RestoreImageTask<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */,
        G extends BitBuffer>

        extends BidiTask <N, A, G> {

    /**
     * @param filename
     * @param scheme
     * @param list
     */
    public
    RestoreImageTask ( String filename,
                       EPartitionScheme scheme,
                       ICodec <N, A, G> codec,
                       List <Task <N, A, G>> list ) {

        super(filename, scheme, codec, list);
    }

    /**
     * @param instance
     */
    @Override
    public
    void onCreated ( ICodec <N, A, G> instance ) {
        super.onCreated(instance);
    }

    /**
     * @param processor
     * @param filename
     * @param image
     */
    @Override
    public
    void onPreprocess ( IImageProcessor <N, A, G> processor, String filename, IImage <A> image ) throws ValueError {
        super.onPreprocess(processor, filename, image);
    }

    /**
     * @param processor
     * @param outputImage
     */
    @Override
    public
    void onPostprocess ( IImageProcessor <N, A, G> processor, CompressedImage <A> outputImage ) {
        super.onPostprocess(processor, outputImage);

        outputImage = unionRegions(outputImage.getRegions());
    }

    /**
     * @param regions
     * @return
     */
    @Contract("_ -> new")
    private @NotNull
    CompressedImage <A> unionRegions (List <RegionOfInterest <A>> regions ) {
        Mat dest=new Mat();
        for (RegionOfInterest <A> region : regions) {
            region.getMat().copyTo(dest);
        }

        return new CompressedImage <>(dest);
    }

}


