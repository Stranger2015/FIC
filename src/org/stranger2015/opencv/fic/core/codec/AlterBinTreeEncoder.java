package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Set;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class AlterBinTreeEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends BinTreeEncoder <N, A, G> {

    /**
     * @param scheme
     * @param nodeBuilder
     * @param partitionProcessor
     * @param searchProcessor     //     * @param rangeSize
     * @param scaleTransform
     * @param imageBlockGenerator
     * @param comparator
     * @param set
     * @param set2
     * @param fractalModel
     */
    public
    AlterBinTreeEncoder ( EPartitionScheme scheme,
                          TreeNodeBuilder <N, A, G> nodeBuilder,
                          IPartitionProcessor <N, A, G> partitionProcessor,
                          ISearchProcessor <N, A, G> searchProcessor,
                          ScaleTransform <A, G> scaleTransform,
                          ImageBlockGenerator <N, A, G> imageBlockGenerator,
                          IDistanceator <A> comparator,
                          Set <ImageTransform <A, G>> transforms,
                          Set <IImageFilter <A>> filters,
                          FCImageModel <N, A, G> fractalModel
    ) {
        super(
                scheme,
                nodeBuilder,
                partitionProcessor,
                searchProcessor,
                scaleTransform,
                imageBlockGenerator,
                comparator,
                transforms,
                filters,
                fractalModel
        );
    }

    @Override
    public
    void initialize () throws Exception {
        super.initialize();
    }
}
