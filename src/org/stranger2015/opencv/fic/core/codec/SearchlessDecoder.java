package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 
 * @param <A>
 */
public
class SearchlessDecoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Decoder <N, A, G> {

    /**
     * @param image
     */
    public
    SearchlessDecoder ( IImage<A> image ) {

        super(image);
    }

    /**
     * @param codec
     */
    @Override
    public
    void onCodecCreated ( ICodec<N, A, G> codec ) {

    }
}
