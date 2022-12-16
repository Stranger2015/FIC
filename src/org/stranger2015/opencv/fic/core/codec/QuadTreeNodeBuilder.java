package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param 
 
 * @param <G>
 */
public
class QuadTreeNodeBuilder<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends BinTreeNodeBuilder<N> {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param library
     */
    public
    QuadTreeNodeBuilder ( IImage image,
                          IIntSize rangeSize,
                          IIntSize domainSize,
                          IEncoder <N> encoder,
                          Library library ) {

        super(image, rangeSize, domainSize, encoder, library);
    }

    @Override
    public
    Tree <N> buildTree ( IImageBlock  imageBlock ) throws ValueError {
        return super.buildTree(imageBlock);
    }
}
