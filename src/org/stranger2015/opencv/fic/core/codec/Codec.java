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
class Codec<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>, G extends BitBuffer>
        implements ICodec <N, A, M, G> {

    protected final EPartitionScheme scheme;

    protected final EncodeTask <N, A, M, G> encodeTask;
    protected final DecodeTask <N, A, M, G> decodeTask;

    /**
     * @param scheme
     * @param encodeTask
     */
    @Contract(pure = true)
    protected
    Codec ( EPartitionScheme scheme, EncodeTask <N, A, M, G> encodeTask, DecodeTask <N, A, M, G> decodeTask ) {

        this.scheme = scheme;
        this.encodeTask = encodeTask;
        this.decodeTask = decodeTask;
    }

//    /**
//     *
//     */
//    protected
//    Codec () {
//        this(QUAD_TREE,
//                new EncodeTask <>(null,
//                        QUAD_TREE,
//                        List.of(),
//                        new QuadTreeEncoder <>(
//                                new SquareImageBlockGenerator <N, A, M, G>(
//                                        new RectangularTiler <>(8),
//                                        //    QUAD_TREE,
//                                        List.of()))),
//                new DecodeTask <>(null,
//                        QUAD_TREE,
//                        List.of(),
//                        new QuadTreeDecoder <M, A>()));
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
    EncodeTask <N, A, M, G> getAction () {
        return encodeTask;
    }

//    /**
//     * @param image
//     * @param rangeSize
//     * @param domainSize
//     * @return
//     */
//    public
//    IEncoder <N, A, M, G> getEncoder ( M image, Size rangeSize, Size domainSize ) {
//        return encoder;
//    }

    /**
     * @param <N>
     * @param <A>
     * @param <M>
     * @param scheme
     * @return
     */
    @SuppressWarnings("unchecked")
    public static
    <N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>, G extends BitBuffer>
    @NotNull ICodec <N, A, M, G> create ( @NotNull EPartitionScheme scheme ) {//????? fixme
        try {
            Class <ICodec <N, A, M, G>> codecClass = (Class <ICodec <N, A, M, G>>) Class
                    .forName(scheme.getCodecClassName());
            Constructor <ICodec <N, A, M, G>> ctor = codecClass.getDeclaredConstructor(
                    Image.class,
                    Size.class,
                    Size.class);

            return ctor.newInstance(encodeTask.loadImage(encodeTask.getFilename()), ZERO_SIZE, ZERO_SIZE);

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                InvocationTargetException e) {

            e.printStackTrace();
            System.exit(0);
        }

        throw new IllegalStateException();
    }

    /**
     * @return
     */
    public
    DecodeTask <N, A, M, G> getDecodeTask () {
        return decodeTask;
    }
}
