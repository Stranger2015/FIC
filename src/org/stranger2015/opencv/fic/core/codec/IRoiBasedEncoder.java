package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
interface IRoiBasedEncoder<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends IEncoder <N> {
    /**
     * @param image
     * @param bounds
     * @return
     * @throws ValueError
     */
    List <IImageBlock > segmentImage ( IImage image, List <Rectangle> bounds )
            throws ValueError;

    /**
     * @param image
     * @return
     * @throws ValueError
     */
    default
    List <IImageBlock > segmentImage ( IImage image ) throws ValueError {
        return segmentImage(image, List.of());
    }

    /**
     * @param image
     * @param blockWidth
     * @param blockHeight
     * @throws ValueError
     */
    void segmentRegion ( IImageBlock  roi, int blockWidth, int blockHeight ) throws ValueError;

    /**
     * @param image
     * @return
     */
    @Contract("_ -> param1")
    default
    IImageBlock  iterateRegions ( List <IImageBlock > regions ) throws ValueError {
        IImageBlock  imageBlock = null;
        for (IImageBlock  region : regions) {
            imageBlock = iterateRangeBlocks(region, region.getRangeBlocks());

        }

        return imageBlock;
    }

    /**
     * @param regions
     * @return
     * @throws ValueError
     */
    default
    List <List <IImageBlock >> handleRegionList ( List <IImageBlock > regions )
            throws ValueError {

        List <List <IImageBlock >> list = new ArrayList <>();
        for (IImageBlock  roi : regions) {
            IImage roiImage = roi;
            /*
             * Normalization. Before tiling the image, pass it throw a set of filters.
             * This might improve results, if used wisely.
             */
            for (IImageFilter  filter : getFilters()) {
                roi = (IImageBlock ) filter.filter(roiImage);
            }
//            list.add(imageBlockGenerator.generateRangeBlocks(
//                    roi,
//                    rangeSize.getWidth(),
//                    rangeSize.getHeight()));
        }

        return list;
    }
}
