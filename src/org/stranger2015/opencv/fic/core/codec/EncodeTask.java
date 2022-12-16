package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.EPartitionScheme;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.Task;

import java.util.List;

/**
 *
 */
public
class EncodeTask extends Task {

    protected final IEncoder encoder;

    /**
     * @param filename
     * @param scheme
     */
    public
    EncodeTask ( String filename,
                 EPartitionScheme scheme,
                 ICodec codec,
                 List <Task> tasks
            /*  IEncoder <N> encoder*/ ) {

        super(filename, scheme, codec, tasks);

        this.encoder = codec.getEncoder();
    }

    /**
     * @param filename
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    protected
    IImage execute ( String filename ) throws Exception {
        return encoder.encode(super.execute(filename));
    }

    /**
     * @return
     */
    public
    IEncoder getEncoder () {
        return encoder;
    }
}