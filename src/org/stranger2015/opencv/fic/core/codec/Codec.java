package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.opencv.core.Size;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.stranger2015.opencv.fic.core.codec.Encoder.ZERO_SIZE;

/**
 * @param <N>
 * @param <M>
 * @param <A>
 */
public abstract
class Codec<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>, G extends BitBuffer>
        implements ICodec <N, A, M, G> {

    private final EPartitionScheme scheme;
    private final EncodeAction action;

    /**
     * @param scheme
     * @param action
     */
    @Contract(pure = true)
    protected
    Codec ( EPartitionScheme scheme, EncodeAction action ) {
        this.scheme = scheme;
        this.action = action;
    }

    /**
     *
     */
    protected
    Codec () {
        scheme = EPartitionScheme.QUAD_TREE;
        action = new EncodeAction("???");
    }

    /**
     * @param scheme
     * @param action
     * @return
     */
    public
    Codec <N, A, M, G> create ( EPartitionScheme scheme, EncodeAction action ) {
        return null;
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
    EncodeAction getAction () {
        return action;
    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
    public abstract
    IEncoder <N, A, M, G> getEncoder ( M image, Size rangeSize, Size domainSize );

    /**
     * @param scheme
     * @param action
     * @param lit
     * @param <N>
     * @param <A>
     * @param <M>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static
    <N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>, G extends BitBuffer>
    @NotNull Codec <N, A, M, G> create ( EPartitionScheme scheme, EncodeAction action, LoadSaveImageTask lit ) {
        try {
            Class <Codec <N, A, M, G>> codecClass =
                    (Class <Codec <N, A, M, G>>) Class.forName(scheme.getCodecClassName());
            Constructor <Codec <N, A, M, G>> ctor = codecClass.getDeclaredConstructor(
                    Image.class,
                    Size.class,
                    Size.class);

            return ctor.newInstance(lit.loadImage(action.getFilename()), ZERO_SIZE, ZERO_SIZE);//fixme
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                InvocationTargetException e) {
            e.printStackTrace();
            System.exit(0);
        }

        throw new IllegalStateException();
    }
}
