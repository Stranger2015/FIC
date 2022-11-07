package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 
 * @param <G>
 */
public
class MergeImageTask<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>

        extends Task <N, A, G> {

    /**
     * @param filename
     * @param scheme
     * @param tasks
     */
    public
    MergeImageTask ( String filename,
                     EPartitionScheme scheme,
                     ICodec <N, A, G> codec,
                     List <Task <N, A, G>> tasks ) {

        super(filename, scheme, codec, tasks);
    }

    /**
     *
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    void onPostprocess ( IImageProcessor <N, A, G> processor, CompressedImage <A> outputImage ) {
        super.onPostprocess(processor, outputImage);

        outputImage = (CompressedImage <A>) inputImage.merge(layers, outputImage);
        saveImage(filename, outputImage);
    }
}
