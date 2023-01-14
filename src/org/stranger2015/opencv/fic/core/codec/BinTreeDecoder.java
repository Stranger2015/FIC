package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.FCImageModel;
import org.stranger2015.opencv.fic.core.IImage;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class BinTreeDecoder extends Decoder {
    /**
     * @param image
     */
    public
    BinTreeDecoder ( IImage image ) {
        super(image);
    }

    @Override
    public
    IImage decode ( String fileName ) throws Exception {
        return super.decode(fileName);
    }

    /**
     * @param filename
     * @param fractalModel
     */
    @Override
    public
    void saveModel ( String filename, FCImageModel fractalModel ) throws Exception {
        super.saveModel(filename, fractalModel);
    }

    @Override
    public
    void onCreated ( ICodec instance ) {
        super.onCreated(instance);
    }
}

