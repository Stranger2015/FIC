package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 
 * @param
 */
public
class SearchlessDecoder<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends Decoder <N> {

    /**
     * @param image
     */
    public
    SearchlessDecoder ( IImage image ) {

        super(image);
    }

    /**
     * @param codec
     */
    @Override
    public
    void onCodecCreated ( ICodec<N> codec ) {

    }
}
