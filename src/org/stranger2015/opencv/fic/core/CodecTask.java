package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 *
 */
public
class CodecTask<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends BidiTask <N, A, G> {

    private/* final*/ IEncoder <N, A, G> encoder;
    private /*final*/ IDecoder <N, A, G> decoder;

    /**
     * @param encoder
     * @param decoder
     * @param tasks
     */
    @SafeVarargs
    public
    CodecTask ( String fn,
                EPartitionScheme scheme,
            ICodec<N, A, G> codec,
                IEncoder <N, A, G> encoder,
                IDecoder <N, A, G> decoder,
                Task <N, A, G>... tasks ) {

        this(fn, scheme, codec, encoder, decoder, List.of(tasks));
    }

    /**
     * @param encoder
     * @param decoder
     * @param tasks
     */
    public
    CodecTask ( String fn,
                EPartitionScheme scheme,
                ICodec<N, A, G> codec,

                IEncoder <N, A, G> encoder,
                IDecoder <N, A, G> decoder,
                List <Task <N, A, G>> tasks ) {

        super(fn, scheme, codec, tasks);

        this.encoder = encoder;
        this.decoder = decoder;
    }

    /**
     * @param fn
     */
    public
    CodecTask ( String fn,
                EPartitionScheme scheme,
                ICodec<N, A, G> codec,
                List <Task <N, A, G>> tasks) {
        super(
                fn,
                scheme,
                codec,
                new EncodeTask <>(fn, scheme, codec, tasks/*, encoder*/),
                new DecodeTask <>(fn, scheme, codec, tasks/*, decoder*/)
        );
    }/**/

    /**
     * @return
     */
    public
    IEncoder <N, A, G> getEncoder () {
        return encoder;
    }

    /**
     * @return
     */
    public
    IDecoder <N, A, G> getDecoder () {
        return decoder;
    }
}
