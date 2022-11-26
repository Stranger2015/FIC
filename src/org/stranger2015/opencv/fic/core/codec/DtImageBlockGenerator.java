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
class DtImageBlockGenerator<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends BinTreeImageBlockGenerator <N, A, G> {

    /**
     * @param tiler
     * @param scheme
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    DtImageBlockGenerator ( ITiler <N, A, G> tiler,
                            EPartitionScheme scheme,
                            IEncoder <N, A, G> encoder,
                            IImage <A> image,
                            IIntSize rangeSize,
                            IIntSize domainSize ) {

        super(tiler, scheme, encoder, image, rangeSize, domainSize);
    }

    /**
     * @param inputImage
     * @param bounds
     */
    @Override
    public
    List <IImageBlock <A>> generateRegions ( IImage <A> inputImage, List <Rectangle> bounds ) throws ValueError {
        return super.generateRegions(inputImage, bounds);
    }

    /**
     * @param roi
     * @param rangeSize
     * @param domainSize
     * @return
     */
    @Override
    public
    List <IImageBlock <A>> generateRangeBlocks ( IImageBlock <A> roi, int rangeSize, int domainSize ) {
//        List <IImageBlock <A>> list = new ArrayList <>();
//        int x=0;
//        int y=0;
//
//        int blockWidth=4;
//        int blockHeight=4;
//
//        for (int i = 0; i < roi.getWidth(); i+= blockWidth) {
//            for (int j = 0; j < roi.getHeight(); j+=blockHeight) {
//                IImageBlock <A> imageBlock =roi.getSubImage(x,y, blockWidth,blockHeight);
//                list.add(imageBlock);
//            }
//          //IImageBlock<A> tImageBlk0= split(imageBlock);
//        }

        return super.generateRangeBlocks(roi, rangeSize, domainSize);
    }
}
