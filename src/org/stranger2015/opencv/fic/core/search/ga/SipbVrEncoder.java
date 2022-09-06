package org.stranger2015.opencv.fic.core.search.ga;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.IIntSize;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.SipEncoder;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.codec.tilers.SipTiler;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 
 * @param <G>
 */
public
class SipbVrEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends SipEncoder<N, A, G> {

    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    public
    SipbVrEncoder ( IImage<A> inputImage, IIntSize rangeSize, IIntSize domainSize ) {
        super(inputImage, rangeSize, domainSize);
    }

    /**
     * @return
     */
    @Override
    public
    ITiler <N, A, G> createPartition0 () {
        return new SipTiler <>(
                getImage(),
                rangeSize,
                domainSize,
                this,
                nodeBuilder
        );
    }
}
