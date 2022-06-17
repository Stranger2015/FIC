package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 *
 */
public
class DecodeTask<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */, G extends BitBuffer>
        extends Task<N, A, G> {

        private final IDecoder <N, A, G> decoder;

    /**
     * @param filename
     */
    public
    DecodeTask ( String filename,
                 EPartitionScheme scheme,
                 ICodec <N, A, G> codec,
                 List <Task <N, A, G>> tasks
               /*  IDecoder <N, A, G> decoder */) {

        super(filename, scheme, codec, tasks );

        this.decoder = codec.getDecoder();
    }

    /**
     * @return
     */
    public
    IDecoder <N, A, G> getDecoder () {
        return decoder;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    protected
    M execute ( String filename ) throws ValueError {
        M image = super.execute(filename);

        FractalModel <N, A, G> fm =  codec.getEncoder().getModel();

        return decoder.decode(fm);
    }
}
