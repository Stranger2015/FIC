package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.EPartitionScheme;
import org.stranger2015.opencv.fic.core.FCImageModel;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.Task;

import java.util.List;

/**
 *
 */
public
class DecodeTask extends Task {

    private final IDecoder decoder;

    /**
     * @param filename
     */
    public
    DecodeTask ( String filename,
                 EPartitionScheme scheme,
                 ICodec codec,
                 List <Task > tasks ) {

        super(filename, scheme, codec, tasks);

        this.decoder = codec.getDecoder();
    }

    /**
     * @return
     */
    public
    IDecoder getDecoder () {
        return decoder;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    protected
    IImage execute ( String filename ) throws Exception {
        IImage image = super.execute(filename);
        FCImageModel fm = codec.getEncoder().getMOdel();

        return decoder.decode(fm);
    }
}
