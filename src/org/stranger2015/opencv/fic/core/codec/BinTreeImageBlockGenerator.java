package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class BinTreeImageBlockGenerator<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends ImageBlockGenerator <N, A, G> {

    /**
     * @param tiler
     * @param scheme
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    BinTreeImageBlockGenerator ( ITiler <N, A, G> tiler,
                                 EPartitionScheme scheme,
                                 IEncoder <N, A, G> encoder,
                                 IImage <A> image,
                                 IIntSize rangeSize,
                                 IIntSize domainSize
    ) {
        super(
                tiler,
                scheme,
                encoder,
                image,
                rangeSize,
                domainSize
        );
    }

    /**
     * @param inputImage
     */
//    @Override
    public
    List <RegionOfInterest <A>> generateRegions ( IImage <A> inputImage, List<Rectangle> bounds ) throws ValueError {
        return super.generateRegions(inputImage, bounds);
    }
}
