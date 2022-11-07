package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.FicFileModel;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class BinTreeDecoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Decoder <N, A, G> {
    /**
     * @param image
     */
    public
    BinTreeDecoder ( IImage <A> image ) {
        super(image);
    }

    @Override
    public
    IImage <A> decode ( FicFileModel <N, A, G> fractalModel ) {
        return super.decode(fractalModel);
    }

    @Override
    public
    void onCreated ( ICodec <N, A, G> instance ) {
        super.onCreated(instance);
    }
}

