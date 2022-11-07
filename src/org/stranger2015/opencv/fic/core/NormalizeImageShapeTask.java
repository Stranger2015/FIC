package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.fic.core.codec.RegionOfInterest;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 *
 * make squared of side size radix equal to nearest greater power of radix
 */
public
class NormalizeImageShapeTask<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>

        extends Task <N, A, G> {

    /**
     * @param fileName
     * @param tasks
     */
    @SafeVarargs
    public
    NormalizeImageShapeTask ( String fileName,
                              EPartitionScheme scheme,
                              ICodec <N, A, G> codec,
                              Task <N, A, G>... tasks ) {
        this(fileName, scheme, codec, List.of(tasks));
    }

    /**
     * @param filename
     * @return
     */
    @Override
    protected
    IImage<A> execute ( String filename ) throws ValueError {
        IImage<A> image = super.execute(filename);

        return image;
    }

    /**
     * @param fileName
     * @param scheme
     * @param tasks
     */
    NormalizeImageShapeTask ( String fileName,
                              EPartitionScheme scheme,
                              ICodec <N, A, G> codec,
                              List <Task <N, A, G>> tasks ) {

        super(fileName, scheme, codec, tasks);
    }

    /**
     * @param instance
     */
//    @Override
    public
    void onCreate ( ICodec <N, A, G> instance ) {

    }

    /**
     * @param processor
     * @param filename
     * @param image
     */
    @Override
    public
    void onPreprocess ( IImageProcessor <N, A, G> processor, String filename, IImage<A> image ) throws ValueError {
        final List <RegionOfInterest <A>> roiList = new ArrayList <>();
        super.onPreprocess(processor, filename, image);
        for (int i = 0; i < image.getRegions().size(); i++) {
            RegionOfInterest <A> roi = image.getRegions().get(i);
            roi = (RegionOfInterest <A>) processor.preprocess(roi);
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
    <V> Function <V, IImage <A>> compose ( @NotNull Function <? super V, ? extends String> before ) {
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
    <V> Function <String, V> andThen ( Function <? super IImage <A>, ? extends V> after ) {
        return super.andThen(after);
    }

    /**
     * @param codec
     */
    @Override
    public
    void onCodecCreated ( ICodec <N, A, G> codec ) {
        super.onCodecCreated(codec);
    }
}
