package org.stranger2015.opencv.fic.core.codec.tilers;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.RegionOfInterest;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Deque;
import java.util.List;

import static org.stranger2015.opencv.fic.core.IShape.EShape;
import static org.stranger2015.opencv.fic.core.codec.IPartitionProcessor.ESearchType;

public
class SaTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        implements ITiler <N, A, G> {

    /**
     * @param image
     * @param minRangeSize
     * @param queue
     * @return
     */

    @Override
    public
    List <IImageBlock <A>> tile ( IImageBlock <A> imageBlock,
                                  ESearchType searchType,
                                  IIntSize minRangeSize,
                                  @NotNull Deque <IImageBlock <A>> queue ) throws ValueError {
        return List.of(imageBlock);
    }

    @Override
    public
    List <IImageBlock <A>> doTile ( IImageBlock <A> imageBlock,
                                    IIntSize minRangeSize,
                                    Deque <IImageBlock <A>> queue ) throws ValueError {
        return List.of();
    }

    /**
     * @return
     */
    @Override
    public
    IIntSize getRangeSize () {
        return null;
    }

    /**
     * @return +
     */
    @Override
    public
    IIntSize getDomainSize () {
        return null;
    }

    @Override
    public
    List <IImageBlock <A>> segmentGeometry ( //EShape imageBlockShape,
                                             IImageBlock <A> imageBlock,
                                             IIntSize minRangeSize,
                                             Deque <IImageBlock <A>> queue ) throws ValueError {

        return ITiler.super.segmentGeometry(imageBlock, minRangeSize, queue);
    }

    @Override
    public
    List <IImageBlock <A>> segmentRectangle ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
    }

    @Override
    public
    List <IImageBlock <A>> segmentSquare ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
    }

    @Override
    public
    List <IImageBlock <A>> segmentTriangle ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
    }

    @Override
    public
    List <IImageBlock <A>> segmentPolygon ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
    }

    @Override
    public
    List <IImageBlock <A>> segmentQuadrilateral ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
    }

    @Override
    public
    void addLeafNode ( TreeNode <N, A, G> node ) {

    }

    @Override
    public
    List <Vertex> generateVerticesSet ( RegionOfInterest <A> roi, int blockWidth, int blockHeight ) {
        return new Vertex[0];
    }

    @Override
    public
    List <IImageBlock <A>> generateInitialRangeBlocks (
            RegionOfInterest <A> roi,
            int blockWidth,
            int blockHeight ) throws ValueError {

        return null;
    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 1;
    }

    @Override
    public
    List <IImageBlock <A>> generateRangeBlocks ( RegionOfInterest <A> roi, int blockWidth, int blockHeight ) throws ValueError {
        return null;
    }

    /**
     * @param imageBlockShape
     * @param imageBlock
     * @param minRangeSize
     * @param queue
     */
//    @Override
    public
    void segmentShape ( EShape imageBlockShape,
                        IImageBlock <A> imageBlock,
                        IntSize minRangeSize,
                        Deque <IImageBlock <A>> queue ) throws ValueError {
    }
}
