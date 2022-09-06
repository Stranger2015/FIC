package org.stranger2015.opencv.fic.core.codec;

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
class AlterBinTreeDecoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends BinTreeDecoder <N, A, G> {
    /**
     * @param image
     */
    protected
    AlterBinTreeDecoder ( IImage <A> image ) {
        super(image);
    }
}
