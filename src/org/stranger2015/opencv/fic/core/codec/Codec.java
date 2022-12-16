package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.EPartitionScheme;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.Task;
import org.stranger2015.opencv.fic.core.ValueError;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <N>
 
 * @param
 */
public abstract
class Codec implements ICodec{
    protected final EPartitionScheme scheme;

//    protected Class <?>[] paramTypes;
//    protected Object[] params;

    protected IEncoder encoder;
    protected IDecoder decoder;

    protected EncodeTask encodeTask;
    protected DecodeTask decodeTask;

    protected final List <ICodecListener > listeners = new ArrayList <>();

//    /**
//     * @param scheme
//     * @param paramTypes
//     * @param params
//     */
//    @SuppressWarnings("unchecked")
//    @Contract(pure = true)
//    protected
//    Codec ( EPartitionScheme scheme, Class <?>[] paramTypes, Object... params ) {
//        this.scheme = scheme;
//        this.paramTypes = paramTypes;
//        this.params = params;
//        this.encodeTask = (EncodeTask <N>) params[0];
//        this.decodeTask = (DecodeTask <N>) params[1];
//    }

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
    EncodeTask  getEncodeTask () {
        return encodeTask;
    }

//    @SuppressWarnings("unchecked")
//    public static
//    <N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
//    ICodec <N> create ( @NotNull EPartitionScheme scheme,
//                                 Class <?>[] paramTypes,
//                                 Object... params ) throws ReflectiveOperationException {
//
//        return (ICodec <N>) Utils.create(
//                scheme.getCodecClassName(),
//                ICodec.getListeners(),
//                paramTypes,
//                params
//        );
//    }

    /**
     * @return
     */
    public
    DecodeTask getDecodeTask () {
        return decodeTask;
    }

    /**
     * @return
     */
    @Override
    public
    IEncoder getEncoder () {
        return encoder;
    }

    /**
     * @param scheme
     * @param encoder
     * @param decoder
     */
//    @Contract(pure = true)
    public
    Codec ( EPartitionScheme scheme, IEncoder encoder, IDecoder decoder ) {
        this.scheme = scheme;
        this.encoder = encoder;
        this.decoder = decoder;
    }

    /**
     * @return
     */
    @Override
    public
    IDecoder getDecoder () {
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
    IAddress  createAddress ( int address ) throws ValueError;

    /**
     * @param task
     */
    @Override
    public
    void addTask ( Task task ) {

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
    void fireCodecCreated ( List <ICodecListener> listeners ) {
        listeners.forEach(listener -> listener.onCreated(this));
    }

    /**
     * @param listeners
     */
    private
    void fireCodecInvoked ( List <ICodecListener> listeners ) {
        listeners.forEach(listener -> listener.onCreated(this));
    }
}
