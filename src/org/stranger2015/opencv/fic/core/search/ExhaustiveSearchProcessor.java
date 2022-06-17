package org.stranger2015.opencv.fic.core.search;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.transform.ITransform;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 
 * @param <A>
 * @param <G>
 */
public
class ExhaustiveSearchProcessor<N extends TreeNode <N, A, G>, /* M extends IImage <A> */, A extends IAddress <A>,
        G extends BitBuffer>
        extends SearchProcessor <N, A, G> {

    private final IEncoder <N, A, G> encoder;

    /**
     *
     */
    @Override
    public
    ITransform <A, G> searchForBestTransform () {
        return getBestTransform();
    }

    /**
     * @param encoder
     */
    public
    ExhaustiveSearchProcessor ( IEncoder <N, A, G> encoder ) {
        this.encoder = encoder;
    }

    /**
     * @return
     */
    @Override
    public
    GrayScaleImage <A> search () {

        return null;
    }

    /**
     * @return
     */
    @Override
    public
    double evaluate () {
        return 0;
    }

    public
    IEncoder <N, A, G> getEncoder () {
        return encoder;
    }
}
