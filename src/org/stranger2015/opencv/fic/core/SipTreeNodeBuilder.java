package org.stranger2015.opencv.fic.core;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.SipImage;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.fic.utils.SipLibrary;

import java.util.*;

import static org.stranger2015.opencv.fic.core.SipAddress.radix;
import static org.stranger2015.opencv.fic.core.SipImageBlock.blockSideSize;
import static org.stranger2015.opencv.fic.core.SipTreeNode.SipLayerClusterNode.blocks;

/**
 * @param <N>
 * @param 
 */
public
class SipTreeNodeBuilder<N extends TreeNode <N>

        extends TreeNodeBuilder <N> {

    protected SipLibrary  sipLibrary;
    protected IImageBlock  imageBlock;
    protected TreeNode <N> lastCluster;
    private SipImage  image;
    private int layerIndex;
    private int clusterIndex;
    protected int sideSize;

    /**
     *
     */
    public
    SipTreeNodeBuilder ( IImage image ) {
        super(image, new SipLibrary <>());
        sipLibrary = (SipLibrary ) this.getLibrary();
    }

    public
    SipTreeNodeBuilder ( IImageBlock  image,
                         IIntSize rangeSize,
                         IIntSize domainSize,
                         IEncoder <N> encoder,
                         Library  library ) {

        super(image, rangeSize, domainSize, encoder, library);
   }


    /**
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public
    SipTree <N> buildTree ( IImageBlock  imageBlock ) {
//        SipTreeNode <N> root = new SipTreeNode <>(
//                null,
//                imageBlock,
//                lastNode.boundingBox);//fixme
//        SipTree <N> tree = new SipTree <>(root, imageBlock);
//        tree.getNodes().add(root);
//        tree.getNodes().addAll(buildMpfr(root));


        SipTree <N> tree
                = null;
        return tree;
    }

    /**
     * @return
     */
    @Override
    public
    List <TreeNodeBase <?>> getSuccessors () {
        return null;
    }

    /**
     * @param node
     */
    @Override
    public
    void add ( TreeNodeBase <?> node ) {

    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( LeafNode <?> node ) {

    }

    /**
     * @return
     */
    @Override
    public
    TreeNodeBase <N> getLastNode () {
        return lastNode;
    }

    /**
     * @return
     */
    @Override
    public
    LeafNode <N> getLastLeafNode () {
        return lastLeafNode;
    }

    /**
     *
     */
    void onNewCluster () {
        SipLibrary  sipLibrary = (SipLibrary ) library;
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
    List <TreeNode <N>> buildLayers ( SipTreeNode <N> root ) throws ValueError {
        blocks.addAll((Collection <? extends ImageBlock <?>>) splitSipImageToBlocks((SipImage ) imageBlock));
//        int sideSize = 0;
        sipLibrary.layersAmount = sipLibrary.calcLayersAmount(sideSize);
        int startAddress = 0;
        int endAddress = 0;
        List <TreeNode <N>> clusters = null;
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
                        List <TreeNode <N>> l =
                                createNewClusterNodes(
                                        root,
                                        sipLibrary.layerIndex,
                                        startAddress,
                                        endAddress);
                        clusters.add((TreeNode <N>) l);

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
    List <TreeNode <N>> createNewClusterNodes ( SipTreeNode <N> parent,
                                                      int layerIndex,
                                                      int startAddress,
                                                      int endAddress ) throws ValueError {
        List <TreeNode <N>> l = new ArrayList <>();
        List <Point> ccs = sipLibrary.getCartesianCoordinates(radix);
        for (int i = 0, amount = endAddress - startAddress; i < amount; i++) {
            List <IImageBlock > domainPool = new ArrayList <>();
            List <N> leaves = new ArrayList <>();
            List <Point> shifts = sipLibrary.derivePixelShifts(
                    new SipTree <N>(
                            parent,
                            blocks,
                            new TreeNode Task <N>(null, domainPool, leaves)),
                    ccs,
                    sipLibrary.pixelCapacity,
                    startAddress,
                    i
            );
            lastNode = lastCluster = createNewClusterNode(
                    parent,
                    (SipImage ) imageBlock,//fixme loc var
                    layerIndex,
                    i
            );

            l.add(lastNode);
            parent = (SipTreeNode <N>) lastNode;
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
    TreeNode <N> createNewClusterNode (
            TreeNode <N> parent,
            SipImage  image,
            int layerIndex,
            int addr ) {

        lastNode = lastCluster = createNewClusterNode0(image, layerIndex, sipLibrary.clusterIndex, addr);
        parent.setChild(0, lastCluster);

        return lastCluster;
    }

    protected
    TreeNode <N> createNewClusterNode0 ( SipImage  image, int layerIndex, int clusterIndex, int addr ) {
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
    Map <Point, IImageBlock > splitSipImageToBlocks ( SipImage  image ) {
        Map <Point, IImageBlock > map = new DualHashBidiMap <>();
        for (int x = 0; x < sideSize; x += blockSideSize) {
            for (int y = 0; y < sideSize; y += blockSideSize) {
                IImageBlock  imageBlock = new SipImageBlock <>(image.submat(x, y, blockSideSize, blockSideSize));
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
//    ITiler <N> instance ( IIntSize rangeSize, IIntSize domainSlze ) {
//        return null;
//    }

    @Override
    public
    ITiler <N> instance () {
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
    List <IImageBlock > tile ( IImage image, IIntSize minRangeSize, Deque <IImageBlock > queue )
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
                        IImageBlock  imageBlock,
                        IIntSize minRangeSize,
                        Deque <IImageBlock > queue ) {

    }

    /**
     * @param imageBlock
     */
    @Override
    public
    void segmentRectangle ( IImageBlock  imageBlock ) {
        throw new UnsupportedOperationException("SipTreeNodeBuilder # segmentRectangle()");
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( IImageBlock  imageBlock ) throws ValueError {

    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( TreeNode <N> node ) {

    }

    /**
     * @return
     */
    public
    SipImage  getImage () {
        return image;
    }

    /**
     * @param image
     */
    public
    void setImage ( SipImage  image ) {
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
