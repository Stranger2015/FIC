package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <N>
 
 * @param <A>
 */
public abstract
class Codec<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */, G extends BitBuffer>
        implements ICodec <N, A, G> {

    protected final EPartitionScheme scheme;

    protected Class <?>[] paramTypes;
    protected Object[] params;

    protected IEncoder <N, A, G> encoder;
    protected IDecoder <N, A, G> decoder;

    protected EncodeTask <N, A, G> encodeTask;
    protected DecodeTask <N, A, G> decodeTask;

    protected final List <ICodecListener <N, A, G>> listeners = new ArrayList <>();

    /**
     * @param scheme
     * @param paramTypes
     * @param params
     */
    @SuppressWarnings("unchecked")
    @Contract(pure = true)
    protected
    Codec ( EPartitionScheme scheme, Class <?>[] paramTypes, Object... params ) {
        this.scheme = scheme;
        this.paramTypes = paramTypes;
        this.params = params;
        this.encodeTask = (EncodeTask <N, A, G>) params[0];
        this.decodeTask = (DecodeTask <N, A, G>) params[1];
    }

    /**
     * @return
     */
    public
    EPartitionScheme getScheme () {
        return scheme;
    }

    /**
     * @return
     */
    public
    EncodeTask <N, A, G> getEncodeTask () {
        return encodeTask;
    }

    @SuppressWarnings("unchecked")
    public static
    <N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */, G extends BitBuffer>
    ICodec <N, A, G> create ( @NotNull EPartitionScheme scheme,
                                 Class <?>[] paramTypes,
                                 Object... params ) throws ReflectiveOperationException {

        return (ICodec <N, A, G>) Utils.create(
                scheme.getCodecClassName(),
                ICodec.getListeners(),
                paramTypes,
                params
        );
    }

    /**
     * @return
     */
    public
    DecodeTask <N, A, G> getDecodeTask () {
        return decodeTask;
    }

    /**
     * @return
     */
    @Override
    public
    IEncoder <N, A, G> getEncoder () {
        return encoder;
    }

    /**
     * @param scheme
     * @param encoder
     * @param decoder
     */
    @Contract(pure = true)
    public
    Codec ( EPartitionScheme scheme, IEncoder <N, A, G> encoder, IDecoder <N, A, G> decoder ) {
        this.scheme = scheme;
        this.encoder = encoder;
        this.decoder = decoder;
    }

    /**
     * @return
     */
    @Override
    public
    IDecoder <N, A, G> getDecoder () {
        return decoder;
    }

    /**
     * @return
     */
    @Override
    public
    int getImageSizeBase () {
        return 0;//fixme
    }

    /**
     * @param address
     * @return
     * @throws ValueError
     */
    @Override
    public abstract
    IAddress <A> createAddress ( int address ) throws ValueError;

    /**
     * @param task
     */
    @Override
    public
    void addTask ( Task <N, A, G> task ) {
//todo
    }

    /**
     * @return
     */
    public
    Class <?>[] getParamTypes () {
        return paramTypes;
    }

    /**
     * @return
     */
    public
    Object[] getParams () {
        return params;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public
    void run () {
        fireCodecInvoked(listeners);
    }

    /**
     * @param listeners
     */
    private
    void fireCodecCreated ( List <ICodecListener <N, A, G>> listeners ) {
        listeners.forEach(listener -> listener.onCreated(this));
    }

    /**
     * @param listeners
     */
    private
    void fireCodecInvoked ( List <ICodecListener <N, A, G>> listeners ) {
        listeners.forEach(listener -> listener.onCreated(this));
    }
}
