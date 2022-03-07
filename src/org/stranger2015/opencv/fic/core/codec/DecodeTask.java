package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 *
 */
public
class DecodeTask<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>, G extends BitBuffer>
        extends Task<N, A, M, G> {

        private final IDecoder <M, A> decoder;

    /**
     * @param filename
     * @param decoder
     */
    public
    DecodeTask ( String filename,
                 EPartitionScheme scheme,
                 List <Task <N, A, M, G>> tasks,
                 IDecoder <M, A> decoder ) {

        super(filename, scheme, tasks );

        this.decoder = decoder;
    }

    public
    IDecoder <M, A> getDecoder () {
        return decoder;
    }

    /**
     * @param filename
     * @return
     */
    @Override
    protected
    M execute ( String filename ) throws ValueError {

        return super.execute(filename);
    }
}
