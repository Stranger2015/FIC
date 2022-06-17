package org.stranger2015.opencv.fic.core.search.ga;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.ITiler;
import org.stranger2015.opencv.fic.core.SipTiler;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.codec.SipEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 
 * @param <G>
 */
public
class SipbVrEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */, G extends BitBuffer>

        extends SipEncoder<N, A, G> {

    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    public
    SipbVrEncoder ( M inputImage, Size rangeSize, Size domainSize ) {
        super(inputImage, rangeSize, domainSize);
    }

    /**
     * @return
     */
    @Override
    public
    ITiler <N, A, G> createTiler0() {
        return new SipTiler <>()p
    }
}
