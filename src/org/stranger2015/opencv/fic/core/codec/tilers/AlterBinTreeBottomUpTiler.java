package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.codec.IEncoder;

import java.util.List;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class AlterBinTreeBottomUpTiler
        extends AlterBinTreeTiler
        implements IBottomUpTiler {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    AlterBinTreeBottomUpTiler ( IImage image,
                                IIntSize rangeSize,
                                IIntSize domainSize,
                                IEncoder encoder,
                                ITreeNodeBuilder<?> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    /**
     * @return
     */
    @Override
    public
    ITiler instance () {
        return new AlterBinTreeBottomUpTiler(
                getImage(),
                this.getCurrentDomainSize(),
                this.getCurrentDomainSize(),
                getEncoder(),
                getBuilder()
        );
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentGeometry ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {
        List.of(imageBlock);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentPolygon ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {
        List.of(imageBlock);
    }

    /**
     * @param block
     * @return
     * @throws ValueError
     */
    @Override
    public
    ClassificationScheme createQuadrants ( ImageBlockInfo block ) throws ValueError {
        return null;
    }
}