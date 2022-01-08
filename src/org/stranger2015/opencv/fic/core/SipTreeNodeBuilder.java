package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.SipTreeNode.SipLayerClusterNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.SipAddress;
import org.stranger2015.opencv.fic.core.codec.SipImage;

import java.util.ArrayList;
import java.util.List;

import static org.stranger2015.opencv.fic.core.codec.IAddressMath.pow;
import static org.stranger2015.opencv.fic.core.codec.SipAddress.radix;
import static org.stranger2015.opencv.fic.utils.ImageUtils.convertImageToSipImage;
import static org.stranger2015.opencv.fic.utils.ImageUtils.nextPixelCapacity;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 */
public
class SipTreeNodeBuilder<N extends TreeNode <N, A, M>, A extends SipAddress <A>, M extends SipImage>
        implements ITreeNodeBuilder <N, A, M> {

    public final static Rect BB = new Rectangle(0, 0, 3, 3);
    protected final List <ImageBlock> blocks = new ArrayList <>();

    M sipImage;

    int layerIndex;
    int address;

    int layersAmount;

    int pixelCapacity;
    int pixelAmount;//pixels may or may not occupy the whole cluster of the outermost layer
    int clustersAmount;
    int clusterIndex;

    TreeNode <N, A, M> lastNode;
    SipLayerClusterNode <N, A, M> lastCluster;

    int nextPixelCapacity;
    int pixelAmountRemains;

    /**
     *
     */
    public
    SipTreeNodeBuilder () {
        layerIndex = 0;
        address = 0;

        layersAmount = 0;

        pixelCapacity = pow(radix, layersAmount);
        pixelAmount = 0;//pixels may or may not occupy the whole cluster of the outermost layer
        clustersAmount = 0;
        clusterIndex = 0;
    }

    /**
     * @return
     */
    public
    List <ImageBlock> getBlocks () {
        return blocks;
    }

    /**
     * @param image
     * @return
     */
    @SuppressWarnings("unchecked")
    public
    SipTree <N, A, M> buildTree ( M image ) throws ValueError {
        sipImage = (M) convertImageToSipImage(image);
        SipTreeNode <N, A, M> root = new SipTreeNode <>(null, image, BB);
        SipTree <N, A, M> tree = new SipTree <>(root, (M) sipImage, new TreeNodeAction <>(null, List.of()));
        tree.getNodes().add(root);
        tree.getNodes().add(buildLayers(tree.getNodes(), sipImage, root));

        return tree;
    }

    /**
     * @param layerIndex
     * @param clusterIndex
     * @param address
     * @throws ValueError
     */
    void onNewLayer ( int layerIndex, int clusterIndex, int address ) throws ValueError {
        onNewCluster(layerIndex, clusterIndex, address);
    }

    /**
     * @param layerIndex
     * @param clusterIndex
     * @param address
     * @throws ValueError
     */
    void onNewCluster ( int layerIndex, int clusterIndex, int address ) throws ValueError {
        if (address % radix == 0) {
            onNewAddress(layerIndex, clusterIndex, address);
        }
    }

    /**
     * @param layerIndex
     * @param clusterIndex
     * @param address
     * @throws ValueError
     */
    void onNewAddress ( int layerIndex, int clusterIndex, int address ) throws ValueError {
        lastCluster = createNewClusterNode0(layerIndex, clusterIndex, address, radix);
    }

    /**
     * @param layerIndex
     * @return
     */
    private
    int calcClustersAmount ( int layerIndex ) {
        return nextPixelCapacity(layerIndex) / radix;
    }

    SipLayerClusterNode <N, A, M> createNewClusterNode0 ( int layerIndex, int clusterIndex, int address, int radix )
            throws ValueError {
        lastCluster = new SipLayerClusterNode <N, A, M>(lastNode, layerIndex, clusterIndex, address);
        pixelAmountRemains = radix;
        nextPixelCapacity = nextPixelCapacity(layerIndex);
        lastCluster.getChildren().add(buildChildrenOf(lastCluster));
        clusterIndex++;

        return lastCluster;
    }

    /**
     * @param nodes
     * @param image
     * @param root
     * @return
     */
    @SuppressWarnings("unchecked")
    private
    NodeList <N, A, M> buildLayers ( NodeList <N, A, M> nodes, SipImage image, SipTreeNode <N, A, M> root )
            throws ValueError {
        blocks.addAll(splitSipImageToBlocks(image));
        for (layersAmount = calcLayersAmount(image.cols()); layerIndex < layersAmount; layerIndex++) {
            ImageBlock imageBlock = blocks.get(clusterIndex);//fixme
            switch (layerIndex) {
                case 0:
                    continue;
                case 1:
                    onNewLayer(layerIndex, clusterIndex, address);
                    lastNode = createNewClusterNode(root, imageBlock, layerIndex, address, radix);
                    nodes.add(lastNode);
                    break;
                default:
                    try {
                        NodeList <N, A, M> l = getRestNodes(
                                lastNode,
                                imageBlock,
                                layerIndex,
                                address,
                                clusterIndex
                        );
                        nodes.add(l);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

        return nodes;
    }

    /**
     * @param w
     * @return
     */
    private
    int calcLayersAmount ( int w ) {
        return (int) (2 * Math.log10(w) / Math.log10(radix));
    }

    /**
     * @param parent
     * @param image
     * @param layerIndex
     * @param address
     * @param radix
     * @return
     * @throws ValueError
     */
    @SuppressWarnings("unchecked")
    private
    TreeNode <N, A, M> createNewClusterNode ( TreeNode <N, A, M> parent,
                                              ImageBlock image,
                                              int layerIndex,
                                              int address,
                                              int radix ) throws ValueError {

        lastCluster = createNewClusterNode0(layerIndex, clusterIndex, address, radix);
        parent.setChild(0, (N) lastCluster);
        lastCluster.imageBlock = image;

        return lastCluster;
    }

    /**
     * children
     *
     * @param
     * @param image
     * @param layerIndex
     * @param address
     * @return
     */
//    @SuppressWarnings("unchecked")
    private
    NodeList <N, A, M> getRestNodes (
            TreeNode <N, A, M> parent,
            ImageBlock image,
            int layerIndex,
            int clusterIndex,
            int address
    ) throws ValueError {

        final NodeList <N, A, M> list = new NodeList <>();
        boolean loop = true;
        while (loop) {
            lastNode = createNewClusterNode(parent, image, layerIndex, address, clusterIndex);
            parent = (TreeNode <N, A, M>) lastNode.parent;
            list.add(lastNode);
            loop = lastNode != null;
        }

        return list;
    }

    /**
     * @param node
     * @return
     * @throws ValueError
     */
    NodeList <N, A, M> buildChildrenOf ( TreeNode <N, A, M> node ) throws ValueError {
        NodeList <N, A, M> list = new NodeList <>();
        lastNode = node.createChild(
                layerIndex, clusterIndex++, address
        );
        lastNode.parent = lastNode;
        list.add(lastNode);
        NodeList <N, A, M> children = new NodeList <>();
        lastNode.getChildren().add(children);

        return list;
    }

    /**
     * @param image
     * @return
     */
    private @NotNull
    List <ImageBlock> splitSipImageToBlocks ( SipImage image ) {
        List <ImageBlock> blocks = new ArrayList <>();
        for (int i = 0; i < image.cols(); i += 3) {
            for (int j = 0; j < image.rows(); j += 3) {
                blocks.add(new SipImageBlock3x3(image, i, i + 3, j, j + 3));
            }
        }

        return blocks;
    }

    /**
     * @return
     */
    public
    int getLayerAmount () {
        return layersAmount;
    }
}