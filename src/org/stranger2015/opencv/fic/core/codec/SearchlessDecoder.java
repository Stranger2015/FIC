package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.TreeNodeBase;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 
 * @param <A>
 */
public
class SearchlessDecoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */,
        G extends BitBuffer> extends Decoder <N, A, G> {
    /**
     * @param image
     */
    public
    SearchlessDecoder ( M image ) {
        super(image);
    }

    /**
     * @param codec
     */
    @Override
    public
    void onCodecCreated ( ICodec codec ) {

    }
}
