package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

import java.util.List;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class DtImageBlockGenerator<N extends TreeNode <N>>
        extends BinTreeImageBlockGenerator <N> {

    /**
     * @param tiler
     * @param scheme
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    DtImageBlockGenerator ( IPartitionProcessor partitionProcessor,
                            EPartitionScheme scheme,
                            IEncoder encoder,
                            IImage image,
                            IIntSize rangeSize,
                            IIntSize domainSize ) {

        super(partitionProcessor, scheme, encoder, image, rangeSize, domainSize);
    }

    /**
     * @param roi
     * @param rangeSize
     * @param domainSize
     * @return
     */
    @Override
    public
    List <IImageBlock > generateRangeBlocks ( IImageBlock  roi, int rangeSize, int domainSize ) throws ValueError {
//        List <IImageBlock > list = new ArrayList <>();
//        int x=0;
//        int y=0;
//
//        int blockWidth=4;
//        int blockHeight=4;
//
//        for (int i = 0; i < roi.getWidth(); i+= blockWidth) {
//            for (int j = 0; j < roi.getHeight(); j+=blockHeight) {
//                IImageBlock  imageBlock =roi.getSubImage(x,y, blockWidth,blockHeight);
//                list.add(imageBlock);
//            }
//          //IImageBlock tImageBlk0= split(imageBlock);
//        }

        return super.generateRangeBlocks(roi, rangeSize, domainSize);
    }
}
