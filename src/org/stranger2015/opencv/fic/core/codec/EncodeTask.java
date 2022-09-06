package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 *
 */
public
class EncodeTask<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Task <N, A, G> {

    protected final IEncoder <N, A, G> encoder;

    /**
     * @param filename
     * @param scheme
     */
    public
    EncodeTask ( String filename,
                 EPartitionScheme scheme,
                 ICodec <N, A, G> codec,
                 List <Task <N, A, G>> tasks
               /*  IEncoder <N, A, G> encoder*/ ) {

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
    IImage<A> execute ( String filename ) throws ValueError {
        return encoder.encode(super.execute(filename));
    }

    /**
     * @return
     */
    public
    IEncoder <N, A, G> getEncoder () {
        return encoder;
    }
}