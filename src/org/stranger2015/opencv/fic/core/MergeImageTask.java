package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param
 
 * @param <G>
 */
public
class MergeImageTask<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>

        extends Task <N> {

    /**
     * @param filename
     * @param scheme
     * @param tasks
     */
    public
    MergeImageTask ( String filename,
                     EPartitionScheme scheme,
                     ICodec <N> codec,
                     List <Task <N>> tasks ) {

        super(filename, scheme, codec, tasks);
    }

    /**
     *
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    void onPostprocess ( IImageProcessor <N> processor, CompressedImage  outputImage ) {
        super.onPostprocess(processor, , outputImage);

        outputImage = (CompressedImage ) inputImage.merge(layers, outputImage);
        saveImage(filename, outputImage);
    }
}
