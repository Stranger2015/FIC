package org.stranger2015.opencv.fic.core.codec.tilers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.IPartitionProcessor;
import org.stranger2015.opencv.fic.core.codec.RegionOfInterest;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.stranger2015.opencv.fic.core.ETilerOperation.*;

/**
 * Tiler & ImageBlkGenerator
 *
 * @param <A>
 */
public
interface ITiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer> {

    /**
     * @param <N>
     * @param <A>
     * @param <G>
     * @return
     */
    @Contract(pure = true)
    static
    <N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
    @NotNull IPartitionProcessor <N, A, G> create ( IEncoder <N, A, G> encoder, ITiler <N, A, G> tiler ) {
        @NotNull IPartitionProcessor <N, A, G> partitionProcessor = encoder.createPartitionProcessor(tiler);

        return partitionProcessor;
    }

    /**
     * @return
     */
    default
    List <IImageBlock <A>> tile ( IImageBlock <A> imageBlock,
                                  IIntSize minRangeSize,
                                  @NotNull Deque <IImageBlock <A>> queue )
            throws ValueError {

        queue.push(imageBlock);

        return doTile(imageBlock, minRangeSize, queue);
    }

    /**
     * @param imageBlock
     * @param minRangeSize
     * @param queue
     * @return
     * @throws ValueError
     */
    default
    List <IImageBlock <A>> doTile ( IImageBlock <A> imageBlock,
                                    IIntSize minRangeSize,
                                    Deque <IImageBlock <A>> queue )
            throws ValueError {

        List <IImageBlock <A>> result = new ArrayList <>();
        for (ETilerOperation operation = TILE_START; operation != TILE_FINISH; ) {
            queue.pop();//todo here??
            switch (operation) {
                case TILE_START:
                    if (imageBlock.getSize().compareTo(minRangeSize) <= 0) {
                        operation = TILE_FINISH;
                        break;
                    }
                    operation = TILE_SEGMENT_SHAPE;
                    break;
                case TILE_SEGMENT_SHAPE:
                    result = segmentGeometry(imageBlock, minRangeSize, queue);
                    operation = TILE_SUCCESSORS;
                    break;
                case TILE_SUCCESSORS:
                    List <TreeNodeBase <N, A, G>> successors = getBuilder().getSuccessors();
                    int succIndex = 0;
                    TreeNodeBase <N, A, G> node = successors.get(succIndex);
                    getBuilder().add(node);
                    if (node.isLeaf()) {
                        getBuilder().addLeafNode((TreeNode.LeafNode <N, A, G>) node);
//                        imageBlock = ((ILeaf <N, A, G>) node).getImageBlock();//todo push??
                        operation = TILE_LEAF;
                    }
                    else {
                        EDirection q = node.getQuadrant();
                        operation = TILE_SUCCESSOR;
                    }
                    break;
                case TILE_SUCCESSOR:
                    queue.push(imageBlock);
                    break;
                case TILE_LEAF:
                    imageBlock = queue.pop();//fixme
                    break;
                case TILE_FINISH:
                    onFinish();
                    continue;
                default:
                    throw new IllegalStateException("Unexpected value: " + operation);
            }
        }

        return result;
    }

    /**
     *
     */
    default void onFinish () {
        getLogger().info("On finishing doTile() ...");
    }

    /**
     * @return
     */
    Logger getLogger ();

    /**
     * @return
     */
    ITreeNodeBuilder <N, A, G> getBuilder ();

    /**
     * @return
     */
    IIntSize getRangeSize ();

    /**
     * @return +
     */
    IIntSize getDomainSize ();

    /**
     * @param imageBlockShape
     * @param imageBlock
     * @param minRangeSize
     * @param queue
     */
    default
    List <IImageBlock <A>> segmentGeometry (
            IImageBlock <A> imageBlock,
            IIntSize minRangeSize,

            Deque <IImageBlock <A>> queue ) throws ValueError {

        return List.of(imageBlock);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    List <IImageBlock <A>> segmentRectangle ( IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    List <IImageBlock <A>> segmentSquare ( IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    List <IImageBlock <A>> segmentTriangle ( IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @return
     * @throws ValueError
     */
    List <IImageBlock <A>> segmentPolygon ( IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @return
     * @throws ValueError
     */
    List <IImageBlock <A>> segmentQuadrilateral ( IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param node
     */
    void addLeafNode ( TreeNode <N, A, G> node );

    /**
     * @param roi
     * @return
     */
    List <Vertex> generateVerticesSet ( RegionOfInterest <A> roi, int blockWidth, int blockHeight );

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     */
    List <IImageBlock <A>> generateInitialRangeBlocks (
            RegionOfInterest <A> roi,
            int blockWidth,
            int blockHeight ) throws ValueError;

    /**
     * @return
     */
//        Vertex[] vertices = generateVerticesSet(roi, blockWidth, blockHeight);
//        List <IImageBlock <A>> rangeBlocks = new ArrayList <>(vertices.length);
//        int sumOfPixelValues = 0;
//        int[] pixels = roi.getPixels();
//
//
//        for (int i = 0, verticesLength = vertices.length; i < verticesLength; i++) {
//            Vertex vertex = vertices[i];
//            int x = (int) vertex.getX();
//            int y = (int) vertex.getY();
//            IImageBlock <A> rangeBlock = roi.getSubImage(x, y, blockWidth, blockHeight);
//
//            int[] data = new int[4];
//
//            rangeBlock.put(x, y, data);//pixelValues(i, (blockWidth + x) * (blockHeight + y)));
//            sumOfPixelValues += rangeBlock.pixelValues(x, y);
//
//            rangeBlock.setMeanPixelValue((int) (sumOfPixelValues / (double) (blockWidth * blockHeight)));
//            IImageBlock <A> rangeBlock = new ImageBlock <>(roi.getSubImage(x, y, blockWidth, blockHeight).getMat());
//            rangeBlocks.add(rangeBlock);
//        }
//
//        return rangeBlocks;
//    }
    int successorAmount ();

    default
    List <IImageBlock <A>> generateRangeBlocks (
            RegionOfInterest <A> roi,
            int blockWidth,
            int blockHeight )

            throws ValueError {

        return generateInitialRangeBlocks(roi, blockWidth, blockHeight);
    }
}
