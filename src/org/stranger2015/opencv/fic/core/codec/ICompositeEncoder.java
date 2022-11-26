package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.codec.tilers.NoneTiler;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
interface ICompositeEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer> {

    /**
     * @return
     */
    default
    List <Class <ITiler <N, A, G>>> getAllowableSubtilers () {
        return (List <Class <ITiler <N, A, G>>>) List.of(NoneTiler.class);
    }

    /**
     * @param tiler
     */
    void addAllowableSubtiler ( Class<ITiler <N, A, G>> tiler );
}
