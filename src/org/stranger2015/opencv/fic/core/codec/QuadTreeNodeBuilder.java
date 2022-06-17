package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 
 * @param <G>
 */
public
class QuadTreeNodeBuilder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends BinTreeNodeBuilder<N, A, G> {
    public
    QuadTreeNodeBuilder ( IImage <A> image,
                          IntSize rangeSize,
                          IntSize domainSize,
                          IEncoder <N, A, G> encoder,
                          Library<A> library ) {
        super(image, rangeSize, domainSize, encoder, library);
    }
}
