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
 * @param
 
 * @param <G>
 */
public
class SipbVrEncoder
        extends SipEncoder {

    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    public
    SipbVrEncoder ( IImage inputImage, IIntSize rangeSize, IIntSize domainSize ) {
        super(inputImage, rangeSize, domainSize);
    }

    /**
     * @return
     */
    @Override
    public
    ITiler createPartition0 () {
        return new SipTiler(
                getImage(),
                rangeSize,
                domainSize,
                this,
                nodeBuilder
        );
    }
}
