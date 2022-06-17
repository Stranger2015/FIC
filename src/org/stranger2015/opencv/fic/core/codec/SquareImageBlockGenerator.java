package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 *
 */
public
class SquareImageBlockGenerator<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */,
        G extends BitBuffer>

        extends ImageBlockGenerator <N, A, G> {

    /**
     * @param inputImage
     * @return
     */
    @Override
    public
    List <IImageBlock <A>> generateRangeBlocks (RegionOfInterest <A> inputImage ) {
        return super.generateRangeBlocks(inputImage);

    }

    /**
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    SquareImageBlockGenerator ( ITiler <N, A, G> tiler,
                                EPartitionScheme scheme,
                                IEncoder <N, A, G> encoder,
                                IImage <A> image,
                                IIntSize rangeSize,
                                IIntSize domainSize
    ) {

        super(tiler, scheme, encoder, image, rangeSize, domainSize);
    }

    /**
     * @return
     */
    @Override
    public
    SquareImageBlockGenerator <N, A, G> newInstance () {
        return this;
    }
}
