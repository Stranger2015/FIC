package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.fic.utils.SipLibrary;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 *
 */
public
class CodecTask<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>, G extends BitBuffer>
        extends BidiTask <N, A, M, G> {

    private/* final*/ IEncoder <N, A, M, G> encoder;
    private /*final*/ IDecoder <M, A> decoder;

    /**
     * @param encoder
     * @param decoder
     * @param tasks
     */
    @SafeVarargs
    public
    CodecTask ( String fn,
                EPartitionScheme scheme,
                IEncoder <N, A, M, G> encoder,
                IDecoder <M, A> decoder,
                Task <N, A, M, G>... tasks ) {

        this(fn, scheme,encoder, decoder,List.of(tasks));
    }

    /**
     * @param encoder
     * @param decoder
     * @param tasks
     */
    public
    CodecTask ( String fn,
                EPartitionScheme scheme,
                IEncoder <N, A, M, G> encoder,
                IDecoder <M, A> decoder,
                List <Task <N, A, M, G>> tasks ) {

        super(fn, scheme, tasks);

        this.encoder = encoder;
        this.decoder = decoder;
    }

    /**
     * @param fn
     */
    public
    CodecTask (String fn, EPartitionScheme scheme, List <Task<N,A,M,G>> tasks, IEncoder <N, A, M, G> encoder, IDecoder <M,A> decoder) {
        super(fn, new EncodeTask <>(fn, scheme, tasks, encoder), new DecodeTask<>(fn, scheme, tasks, decoder));
    }

//    /**
//     * @param fn
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    @Override
//    public
//    SipImage <A> loadSipImage ( String fn ) throws ValueError {
//        image = loadImage(fn);
//        SipLibrary <A> sipLib = new SipLibrary <>();
//        SipTreeNodeBuilder <N, A, M, G> builder = new SipTreeNodeBuilder <>(image);
//        sipLib.convertImageToSipImage(builder.buildTree(), image);
//
//        return (SipImage <A>) image;
//    }

    /**
     * @return
     */
    public
    IEncoder <N, A, M, G> getEncoder () {
        return encoder;
    }

    /**
     * @return
     */
    public
    IDecoder <M, A> getDecoder () {
        return decoder;
    }
}
