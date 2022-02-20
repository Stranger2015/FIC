package org.stranger2015.opencv.fic.core;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.jetbrains.annotations.NotNull;
import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.SipTreeNode.SipLayerClusterNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.SipAddress;
import org.stranger2015.opencv.fic.core.codec.SipImage;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.fic.utils.SipLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.stranger2015.opencv.fic.core.SipImageBlock.blockSideSize;
import static org.stranger2015.opencv.fic.core.codec.SipAddress.radix;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 */
public
class SipTreeNodeBuilder<N extends TreeNode <N, A, M, G>, A extends SipAddress <A>, M extends IImage>
        implements ITreeNodeBuilder <N, A, M, G> {

    public final static Rect BB = new Rectangle(0, 0, blockSideSize, blockSideSize);

    /**
     * maps block coordinate (point(x,y)) to image blk instance
     */
    protected final Map <Point, SipImageBlock> blocks = new DualHashBidiMap <>();
    protected final List <SipImageBlock> blockList = new ArrayList <>();

    public final int sideSize;
    public final SipImage sipImage;
    public final SipLibrary <A> sipLib;

    TreeNode <N, A, M, G> lastNode;
    SipLayerClusterNode <N, A, M, G> lastCluster;

    NodeList <N, A, M, G> clusters = new NodeList <>();
//    protected final List <Point> pixelShiftAddresses;

    /**
     *
     */
    public
    SipTreeNodeBuilder ( M image ) throws ValueError {
        sipLib = new SipLibrary <>();
        sideSize = image.getWidth();
        sipImage = sipLib.convertImageToSipImage( buildTree(), image);
    }

    /**
     * @return
     */
    public
    Map <Point, SipImageBlock> getBlocks () {
        return blocks;
    }


    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    public
    SipTree <N, A, M, G> buildTree () throws ValueError {
        SipTreeNode <N, A, M, G> root = new SipTreeNode <>(null, (M) sipImage, BB);
        SipTree <N, A, M, G> tree = new SipTree <>(root, (M) sipImage, null);
        tree.getNodes().add(root);
        tree.getNodes().add(buildLayers(root));

        return tree;
    }

    /**
     *
     */
    void onNewCluster () {
        if (sipLib.clusterIndex == sipLib.clustersAmount) {
            if (sipLib.layersAmount == sipLib.layerIndex) {
                sipLib.layersAmount++;
            }
//            onNewLayer(++layerIndex, clusterIndex, address);
        }
        if (sipLib.address % radix == 0) {
            sipLib.clusterIndex++;
        }
    }

    /**
     * @param layerIndex
     * @return
     */
    public
    int calcClustersAmount ( int layerIndex ) {
        return sipLib.calcPixelCapacity(layerIndex) / radix;
    }

    /**
     * @param sipImage
     * @param layerIndex
     * @param clusterIndex
     * @param address
     * @return
     */
    SipLayerClusterNode <N, A, M, G> createNewClusterNode0 ( SipImage sipImage,
                                                          int layerIndex,
                                                          int clusterIndex,
                                                          int address ) {
        lastCluster = new SipLayerClusterNode <>(
                lastNode,
                sipImage,
                null,
                layerIndex,
                clusterIndex,
                address);
        sipLib.clusterIndex++;

        return lastCluster;
    }

    /**
     * @param root
     * @return
     */
    private
    NodeList <N, A, M, G> buildLayers ( SipTreeNode <N, A, M, G> root ) throws ValueError {
        blocks.putAll(splitSipImageToBlocks(sipImage, blockSideSize));
        sipLib.layersAmount = sipLib.calcLayersAmount(sideSize);
        int startAddress = 0;
        int endAddress = 0;
        while (sipLib.layerIndex++ < sipLib.layersAmount) {
            sipLib.clustersAmount = calcClustersAmount(sipLib.layerIndex);
            switch (sipLib.layerIndex) {
                case 0:
                    continue;
                case 1:
                    clusters = createNewClusterNodes(root, sipLib.layerIndex, startAddress, radix - 1);
                    break;
                default:
                    try {
                        startAddress = endAddress + 1;
                        endAddress = startAddress + sipLib.clustersAmount * radix;
                        NodeList <N, A, M, G> l = createNewClusterNodes(root, sipLib.layerIndex, startAddress, endAddress);
                        clusters.add(l);

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
     * @throws ValueError
     */
    private
    NodeList <N, A, M, G> createNewClusterNodes ( SipTreeNode <N, A, M, G> parent,
                                               int layerIndex,
                                               int startAddress,
                                               int endAddress ) throws ValueError {
        NodeList <N, A, M, G> l = new NodeList <>();
        List <Point> ccs = sipLib.getCartesianCoordinates(radix);
        for (int i = 0, amount = endAddress - startAddress; i < amount; i++) {
            List<ImageBlock<A>> domainPool = new ArrayList <>();
            List <N> leaves=new ArrayList <>();
            List <Point> shifts = sipLib.derivePixelShifts(
                    new SipTree <>(parent,
                            blocks,
                            new TreeNodeAction <>(domainPool, (NodeList <N, A, M, G>) leaves)),
                    ccs,
                    sipLib.pixelCapacity,
                    startAddress,
                    i);
            lastNode = lastCluster = createNewClusterNode(
                    parent,
                    sipImage,//fixme loc var
                    layerIndex,
                    i);
            l.add(lastNode);
            parent = (SipTreeNode <N, A, M, G>) lastNode;
            onNewCluster();
        }

        return l;
    }

    /**
     * @param parent
     * @param image
     * @param layerIndex
     * @param address
     * @return
     */
    @SuppressWarnings("unchecked")
    private
    SipLayerClusterNode <N, A, M, G> createNewClusterNode (
            TreeNode <N, A, M, G> parent,
            SipImage image,
            int layerIndex,
            int address ) {

        lastNode = lastCluster = createNewClusterNode0(image, layerIndex, sipLib.clusterIndex, address);
        parent.setChild(0, (N) lastCluster);

        return lastCluster;
    }

    /**
     * @param image
     * @param blockSideSize
     * @return
     */
    private @NotNull
    Map <Point, SipImageBlock> splitSipImageToBlocks ( SipImage image, int blockSideSize ) {
        Map <Point, SipImageBlock> map = new DualHashBidiMap <>();
        for (int x = 0; x < sideSize; x += blockSideSize) {
            for (int y = 0; y < sideSize; y += blockSideSize) {
                SipImageBlock sipImageBlock = (SipImageBlock) image.submat(
                        new Rect(
                                x,
                                y,
                                blockSideSize,
                                blockSideSize)
                );
                map.put(new Point (x, y), sipImageBlock);//fixme
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
}
