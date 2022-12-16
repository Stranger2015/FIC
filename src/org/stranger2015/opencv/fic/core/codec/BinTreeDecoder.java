package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.FCImageModel;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.ValueError;

import java.io.IOException;

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
    IImage decode ( String fileName ) throws ValueError, IOException {
       return super.decode(fileName);
    }

    /**
     * @param filename
     * @param fractalModel
     */
    @Override
    public
    void saveModel ( String filename, FCImageModel fractalModel ) {

    }

    @Override
    public
    void onCreated ( ICodec instance ) {
        super.onCreated(instance);
    }
}

