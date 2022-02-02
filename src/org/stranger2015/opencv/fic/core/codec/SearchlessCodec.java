package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.EPartitionScheme;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 */
public
class SearchlessCodec<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
        extends Codec <N, A, M> {
    /**
     * @param scheme
     * @param action
     * @return
     */
    @Override
    public
    Codec <N, A, M> create ( EPartitionScheme scheme, EncodeAction action ) {
        return new SearchlessCodec <>();
    }

    /**
     * @param image
     * @return
     */
    @Override
    public
    M encode ( M image ) {
        return null;
    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
    @Override
    public
    IEncoder <N, A, M> getEncoder ( M image, Size rangeSize, Size domainSize ) {
        return new SearchlessEncoder <>(image, rangeSize, domainSize);
    }
}
