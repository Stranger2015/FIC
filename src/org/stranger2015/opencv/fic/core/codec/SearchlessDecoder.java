package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.IImage;

/**
 * @param
 */
public
class SearchlessDecoder extends Decoder {

    /**
     * @param image
     */
    public
    SearchlessDecoder ( IImage image ) {

        super(image);
    }

    /**
     * @param codec
     */
    @Override
    public
    void onCodecCreated ( ICodec codec ) {

    }
}
