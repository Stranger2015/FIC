package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.tilers.BinTreeTiler;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;

/**
 * @param <N>
 * @param 
 * @param <G>
 */
public abstract
class QuadTreeTiler extends BinTreeTiler {

       /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    protected
    QuadTreeTiler (
            IImage image,
            IIntSize rangeSize,
            IIntSize domainSize,
            IEncoder encoder,
            ITreeNodeBuilder <?> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    /**
     * @return
     */
    @Override
    public abstract
    ITiler instance ();


    /**
     * @param imageBlock
     */
    @Override
    public
    void segmentRectangle ( TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError {
        throw new UnsupportedOperationException("QuadTreeTiler#segmentRectangle()");
    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 4;
    }
}
