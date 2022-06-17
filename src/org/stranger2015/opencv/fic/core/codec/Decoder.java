package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.io.FractalWriter;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public abstract
class Decoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        implements IDecoder <N, A, G> {

    /**
     * @param codec
     */
    @Override
    public
    void onCodecCreated ( ICodec <N, A, G> codec ) {
        IDecoder.super.onCodecCreated(codec);
    }

    private final IImage<A> image;

    /**
     *
     */
    protected
    Decoder ( IImage  <A> image ) {
        this.image = image;
    }

    @SuppressWarnings("unchecked")
    public static
    <N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
    IDecoder <N, A, G> create ( @NotNull EPartitionScheme scheme, Class <?>[] paramTypes, Object... params )
            throws ReflectiveOperationException {

        return (IDecoder <N, A, G>) Utils.create(
                scheme.getDecoderClassName(),
                null,
                paramTypes,
                params);
    }

    /**
     *
     * @return
     */
    public
    IImage<A> decode ( FractalModel <N, A, G> fractalModel ) {
        final Map <Point, IImage<A>> simpleModel = new HashMap <>();

        int blockWidth = fractalModel.getModel().keySet().iterator().next().getWidth();
        int blockHeight = fractalModel.getModel().keySet().iterator().next().getHeight();

        int maxWidth = 0;
        int maxHeight = 0;

        for (IImage<A> domain : fractalModel.getModel().keySet()) {
            for (ImageTransform <? extends IAddress<A>, G> transform : fractalModel.getModel().get(domain).keySet()) {
                for (Point point : fractalModel.getModel().get(domain).get(transform)) {
                    simpleModel.put(point, domain);///fixme
                    if (maxWidth < point.getX()) {
                        maxWidth = (int) point.getX();
                    }
                    if (maxHeight < point.getY()) {
                        maxHeight = (int) point.getY();
                    }
                }
            }
        }

        int imgWidth = ++maxWidth * blockWidth;
        int imgHeight = ++maxHeight * blockHeight;

        return image;
    }

    /**
     * @param filename
     * @param fractalModel
     */
    @Override
    public
    void saveModel ( String filename, FractalModel <N, A, G> fractalModel ) {
        FractalWriter <N, A, G> writer = new FractalWriter <>(
                new File(filename),
                fractalModel.getImageInfo());
        writer.writeModel(fractalModel);
        writer.close();
    }

    /**
     *
     */
//    @Override
    public
    void onCreateCodec () {

    }

    /**
     * @return
     */
    public
    IImage<A> getImage () {
        return image;
    }
}