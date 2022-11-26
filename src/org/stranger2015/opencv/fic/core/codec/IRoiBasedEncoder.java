package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
interface IRoiBasedEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends IEncoder <N, A, G> {
    /**
     * @param image
     * @param bounds
     * @return
     * @throws ValueError
     */
    List <IImageBlock <A>> segmentImage ( IImage <A> image, List <Rectangle> bounds )
            throws ValueError;

    /**
     * @param image
     * @return
     * @throws ValueError
     */
    default
    List <IImageBlock <A>> segmentImage ( IImage <A> image ) throws ValueError {
        return segmentImage(image, List.of());
    }

    /**
     * @param image
     * @param blockWidth
     * @param blockHeight
     * @throws ValueError
     */
    void segmentRegion ( IImageBlock <A> roi, int blockWidth, int blockHeight ) throws ValueError;

    /**
     * @param image
     * @return
     */
    @Contract("_ -> param1")
    default
    IImageBlock <A> iterateRegions ( List <IImageBlock <A>> regions ) throws ValueError {
        IImageBlock <A> imageBlock = null;
        for (IImageBlock <A> region : regions) {
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
    List <List <IImageBlock <A>>> handleRegionList ( List <IImageBlock <A>> regions )
            throws ValueError {

        List <List <IImageBlock <A>>> list = new ArrayList <>();
        for (IImageBlock <A> roi : regions) {
            IImage <A> roiImage = roi;
            /*
             * Normalization. Before tiling the image, pass it throw a set of filters.
             * This might improve results, if used wisely.
             */
            for (IImageFilter <A> filter : getFilters()) {
                roi = (IImageBlock <A>) filter.filter(roiImage);
            }
//            list.add(imageBlockGenerator.generateRangeBlocks(
//                    roi,
//                    rangeSize.getWidth(),
//                    rangeSize.getHeight()));
        }

        return list;
    }
}
