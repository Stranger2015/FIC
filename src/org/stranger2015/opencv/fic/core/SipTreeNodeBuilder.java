package org.stranger2015.opencv.fic.core;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.SipImage;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.fic.utils.SipLibrary;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.*;

import static org.stranger2015.opencv.fic.core.IShape.EShape;
import static org.stranger2015.opencv.fic.core.SipAddress.radix;
import static org.stranger2015.opencv.fic.core.SipImageBlock.blockSideSize;
import static org.stranger2015.opencv.fic.core.SipTreeNode.SipLayerClusterNode.blocks;

/**
 * @param <N>
 * @param <A>
 */
public
class SipTreeNodeBuilder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>

        extends TreeNodeBuilder <N, A, G> {

    protected SipLibrary <A> sipLibrary;
    protected IImageBlock <A> imageBlock;
    protected TreeNode <N, A, G> lastCluster;
    private SipImage <A> image;
    private int layerIndex;
    private int clusterIndex;
    protected int sideSize;

    /**
     *
     */
    public
    SipTreeNodeBuilder ( IImage <A> image ) {
        super(image, new SipLibrary <>());
        sipLibrary = (SipLibrary <A>) this.getLibrary();
    }

    public
    SipTreeNodeBuilder ( IImageBlock <A> image,
                         IIntSize rangeSize,
                         IIntSize domainSize,
                         IEncoder <N, A, G> encoder,
                         Library <A> library ) {

        super(image, rangeSize, domainSize, encoder, library);
   }


    /**
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public
    SipTree <N, A, G> buildTree ( IImageBlock <A> imageBlock ) {
//        SipTreeNode <N, A, G> root = new SipTreeNode <>(
//                null,
//                imageBlock,
//                lastNode.boundingBox);//fixme
//        SipTree <N, A, G> tree = new SipTree <>(root, imageBlock);
//        tree.getNodes().add(root);
//        tree.getNodes().addAll(buildMpfr(root));


        SipTree <N, A, G> tree
                = null;
        return tree;
    }

    /**
     * @return
     */
    @Override
    public
    List <TreeNodeBase <N, A, G>> getSuccessors () {
        return null;
    }

    /**
     * @param node
     */
    @Override
    public
    void add ( TreeNodeBase <N, A, G> node ) {

    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( LeafNode <N, A, G> node ) {

    }

    /**
     * @return
     */
    @Override
    public
    TreeNodeBase <N, A, G> getLastNode () {
        return lastNode;
    }

    /**
     * @return
     */
    @Override
    public
    LeafNode <N, A, G> getLastLeafNode () {
        return lastLeafNode;
    }

    /**
     *
     */
    void onNewCluster () {
        SipLibrary <A> sipLibrary = (SipLibrary <A>) library;
        if (sipLibrary.clusterIndex == sipLibrary.clustersAmount) {
            if (sipLibrary.layersAmount == sipLibrary.layerIndex) {
                sipLibrary.layersAmount++;
            }
//            onNewLayer(++layerIndex, clusterIndex, address);
        }
        if (library.addr % radix == 0) {
            sipLibrary.clusterIndex++;
        }
    }

    /**
     * @param layerIndex
     * @return
     */
    public
    int calcClustersAmount ( int layerIndex ) {
        return library.calcPixelCapacity(layerIndex) / radix;
    }

    /**
     * @param root
     * @return
     */
    @SuppressWarnings("unchecked")
    private
    List <TreeNode <N, A, G>> buildLayers ( SipTreeNode <N, A, G> root ) throws ValueError {
        blocks.addAll((Collection <? extends ImageBlock <?>>) splitSipImageToBlocks((SipImage <A>) imageBlock));
//        int sideSize = 0;
        sipLibrary.layersAmount = sipLibrary.calcLayersAmount(sideSize);
        int startAddress = 0;
        int endAddress = 0;
        List <TreeNode <N, A, G>> clusters = null;
        while (sipLibrary.layerIndex++ < sipLibrary.layersAmount) {
            sipLibrary.clustersAmount = calcClustersAmount(sipLibrary.layerIndex);
            switch (sipLibrary.layerIndex) {
                case 0:
                    continue;
                case 1:
                    clusters = createNewClusterNodes(root,
                            sipLibrary.layerIndex,
                            startAddress,
                            radix - 1);
                    break;
                default:
                    try {
                        startAddress = endAddress + 1;
                        endAddress = startAddress + sipLibrary.clustersAmount * radix;
                        List <TreeNode <N, A, G>> l =
                                createNewClusterNodes(
                                        root,
                                        sipLibrary.layerIndex,
                                        startAddress,
                                        endAddress);
                        clusters.add((TreeNode <N, A, G>) l);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }//switch
        }//while

        return clusters;
    }

    /**
     * @param parent
     * @param layerIndex
     * @param startAddress
     * @param endAddress
     * @return
     */
    @SuppressWarnings("unchecked")
    private
    List <TreeNode <N, A, G>> createNewClusterNodes ( SipTreeNode <N, A, G> parent,
                                                      int layerIndex,
                                                      int startAddress,
                                                      int endAddress ) throws ValueError {
        List <TreeNode <N, A, G>> l = new ArrayList <>();
        List <Point> ccs = sipLibrary.getCartesianCoordinates(radix);
        for (int i = 0, amount = endAddress - startAddress; i < amount; i++) {
            List <IImageBlock <A>> domainPool = new ArrayList <>();
            List <N> leaves = new ArrayList <>();
            List <Point> shifts = sipLibrary.derivePixelShifts(
                    new SipTree <N, A, G>(
                            parent,
                            blocks,
                            new TreeNode Task <N, A, G>(null, domainPool, leaves)),
                    ccs,
                    sipLibrary.pixelCapacity,
                    startAddress,
                    i
            );
            lastNode = lastCluster = createNewClusterNode(
                    parent,
                    (SipImage <A>) imageBlock,//fixme loc var
                    layerIndex,
                    i
            );

            l.add(lastNode);
            parent = (SipTreeNode <N, A, G>) lastNode;
            onNewCluster();
        }

        return l;
    }

    /**
     * @param parent
     * @param image
     * @param layerIndex
     * @param addr
     * @return
     */
    @SuppressWarnings("unchecked")
    private
    TreeNode <N, A, G> createNewClusterNode (
            TreeNode <N, A, G> parent,
            SipImage <A> image,
            int layerIndex,
            int addr ) {

        lastNode = lastCluster = createNewClusterNode0(image, layerIndex, sipLibrary.clusterIndex, addr);
        parent.setChild(0, lastCluster);

        return lastCluster;
    }

    protected
    TreeNode <N, A, G> createNewClusterNode0 ( SipImage <A> image, int layerIndex, int clusterIndex, int addr ) {
        this.image = image;
        this.layerIndex = layerIndex;
        this.clusterIndex = clusterIndex;


        return null;
    }

    /**
     * @param image
     * @return
     */
    @SuppressWarnings("unchecked")
    //Image blk generator ---> generateRegions()
    private @NotNull
    Map <Point, IImageBlock <A>> splitSipImageToBlocks ( SipImage <A> image ) {
        Map <Point, IImageBlock <A>> map = new DualHashBidiMap <>();
        for (int x = 0; x < sideSize; x += blockSideSize) {
            for (int y = 0; y < sideSize; y += blockSideSize) {
                IImageBlock <A> imageBlock = new SipImageBlock <>(image.submat(x, y, blockSideSize, blockSideSize));
                map.put(new Point(x, y), imageBlock);//fixme
            }
        }

        return map;
    }

    /**
     * @return
     */
    public
    int getSideSize () {
        return sideSize;
    }

//    @Override
//    public
//    ITiler <N, A, G> instance ( IIntSize rangeSize, IIntSize domainSlze ) {
//        return null;
//    }

    @Override
    public
    ITiler <N, A, G> instance () {
        return new SipTreeNodeBuilder <>();
    }

    /**
     * @param image
     * @param minRangeSize
     * @param queue
     * @return
     */
    @Override
    public
    List <IImageBlock <A>> tile ( IImage <A> image, IIntSize minRangeSize, Deque <IImageBlock <A>> queue )
            throws ValueError, MinimalRangeSizeReached {

        return null;
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

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 1;
    }

    /**
     * @param imageBlockShape
     * @param imageBlock
     * @param minRangeSize
     * @param queue
     */
    @Override
    public
    void segmentShape ( EShape imageBlockShape,
                        IImageBlock <A> imageBlock,
                        IIntSize minRangeSize,
                        Deque <IImageBlock <A>> queue ) {

    }

    /**
     * @param imageBlock
     */
    @Override
    public
    void segmentRectangle ( IImageBlock <A> imageBlock ) {
        throw new UnsupportedOperationException("SipTreeNodeBuilder # segmentRectangle()");
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( IImageBlock <A> imageBlock ) throws ValueError {

    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( TreeNode <N, A, G> node ) {

    }

    /**
     * @return
     */
    public
    SipImage <A> getImage () {
        return image;
    }

    /**
     * @param image
     */
    public
    void setImage ( SipImage <A> image ) {
        this.image = image;
    }

    public
    int getLayerIndex () {
        return layerIndex;
    }

    public
    int getClusterIndex () {
        return clusterIndex;
    }
}
