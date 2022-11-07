package org.stranger2015.opencv.fic.core.codec.tilers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.IPartitionProcessor;
import org.stranger2015.opencv.fic.core.codec.RegionOfInterest;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Deque;
import java.util.List;

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
    void tile ( IImageBlock <A> imageBlock ) throws ValueError {
        getDeque().push(imageBlock);
        try {
            doTile(imageBlock);
        } catch (DepthLimitExceeded e) {
            getLogger().info("Max depth has been exceeded ...");
        }
    }

    /**
     * @return
     */
    IIntSize getMinRangeSize ();

    /**
     * @return
     */
    IIntSize getMinDomainSize ();

    /**
     * @return
     */
    Deque <IImageBlock <A>> getDeque ();

    /**
     * @param imageBlock
     * @param minRangeSize
     * @param queue
     * @return
     * @throws ValueError
     */
    void doTile ( IImageBlock <A> imageBlock ) throws ValueError, DepthLimitExceeded;

    /**
     * @param node
     * @param imageBlock
     */
    void onAddNode ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock );

    /**
     * @param leafNode
     * @param imageBlock
     */
    void onAddLeafNode ( LeafNode <N, A, G> leafNode, IImageBlock <A> imageBlock );

    /**
     *
     */
    default
    void onFinish () {
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
     */
    void segmentGeometry ( IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    void segmentRectangle ( IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    void segmentSquare ( IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    void segmentTriangle ( IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    void segmentPolygon ( IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    void segmentQuadrilateral ( IImageBlock <A> imageBlock ) throws ValueError;

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
    List <IImageBlock <A>> generateInitialRangeBlocks (RegionOfInterest <A> roi, int blockWidth, int blockHeight )
            throws ValueError;

    //    /**
//     * @return
//     */
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
    List <IImageBlock <A>> generateRangeBlocks (RegionOfInterest <A> roi, int blockWidth, int blockHeight )

            throws ValueError {

        return generateInitialRangeBlocks(roi, blockWidth, blockHeight);
    }

    /**
     * @return
     */
    default
    boolean isBottomUpTiler () {
        return this instanceof IBottomUpTiler;
    }
}
