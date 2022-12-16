package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.fic.core.codec.IImageBlock;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 *
 * make squared of side size radix equal to nearest greater power of radix
 */
public
class NormalizeImageShapeTask<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>

        extends Task <N> {

    /**
     * @param fileName
     * @param tasks
     */
    @SafeVarargs
    public
    NormalizeImageShapeTask ( String fileName,
                              EPartitionScheme scheme,
                              ICodec <N> codec,
                              Task <N>... tasks ) {
        this(fileName, scheme, codec, List.of(tasks));
    }

    /**
     * @param filename
     * @return
     */
    @Override
    protected
    IImage execute ( String filename ) throws ValueError {
        IImage image = super.execute(filename);

        return image;
    }

    /**
     * @param fileName
     * @param scheme
     * @param tasks
     */
    NormalizeImageShapeTask ( String fileName,
                              EPartitionScheme scheme,
                              ICodec <N> codec,
                              List <Task <N>> tasks ) {

        super(fileName, scheme, codec, tasks);
    }

    /**
     * @param instance
     */
//    @Override
    public
    void onCreate ( ICodec <N> instance ) {

    }

    /**
     * @param processor
     * @param filename
     * @param image
     */
    @Override
    public
    void onPreprocess ( IImageProcessor <N> processor, String filename, IImage image ) throws ValueError {
        final List <IImageBlock > roiList = new ArrayList <>();
        super.onPreprocess(processor, filename, image);
        for (int i = 0; i < image.getRegions().size(); i++) {
            IImageBlock  roi = image.getRegions().get(i);
            roi = (IImageBlock ) processor.preprocess(roi);
            roiList.add(roi);
        }
    }

    /**
     * Returns a composed function that first applies the {@code before}
     * function to its input, and then applies this function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param before the function to apply before this function is applied
     * @return a composed function that first applies the {@code before}
     * function and then applies this function
     * @throws NullPointerException if before is null
     * @see #andThen(Function)
     */
    @Override
    public
    <V> Function <V, IImage> compose ( @NotNull Function <? super V, ? extends String> before ) {
        return super.compose(before);
    }

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     * @see #compose(Function)
     */
    @NotNull
    @Override
    public
    <V> Function <String, V> andThen ( Function <? super IImage, ? extends V> after ) {
        return super.andThen(after);
    }

    /**
     * @param codec
     */
    @Override
    public
    void onCodecCreated ( ICodec <N> codec ) {
        super.onCodecCreated(codec);
    }
}
