package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.SipTreeNode.SipLayerClusterNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.SipAddress;
import org.stranger2015.opencv.fic.core.codec.SipImage;
import org.stranger2015.opencv.fic.utils.ImageUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 */
public
class SipNodeBuilder<N extends TreeNode <N, A, M>, A extends SipAddress <A>, M extends Image> {

    public final static Rect BB = new Rect(0, 0, 3, 3);
    protected int numLayers;
    protected final List <ImageBlock3x3<M>> blocks = new ArrayList <>();

    /**
     * @return
     */
    public
    List <ImageBlock3x3<M>> getBlocks () {
        return blocks;
    }

    /**
     * @param image
     * @return
     */
    @SuppressWarnings("unchecked")
    public
    SipTree <N, A, M> buildTree ( M image) throws ValueError {
        SipImage sipImage=ImageUtils.imageToSipImage(image);
        SipTreeNode <N, A, M> root = new SipTreeNode <>(null, image, BB);
        SipTree <N, A, M> tree = new SipTree <>(root, image, new TreeNodeAction <>(null, List.of()));
        tree.getNodes().add(root);
        tree.getNodes().getList().addAll((Collection <? extends TreeNode <N, A, M>>) buildLayers(image));

        return tree;
    }

    /**
     * @param image
     * @return
     * @throws ValueError
     */
    @SuppressWarnings("unchecked")
    private @NotNull
    NodeList <N, A, M> buildLayers ( M image ) throws ValueError {
        int w = image.cols();
        numLayers = (int) (2 * Math.log10(w) / Math.log10(9));
        blocks.addAll(splitImageToBlocks(image, w));
        NodeList <N, A, M> nodes = new NodeList <>();
        for (int i = 0; i < numLayers; i++) {
            switch (i) {
                case 0:
                    continue;
                case 1:
                    nodes.getList().addAll((Collection <? extends TreeNode <N, A, M>>)
                            new SipLayerClusterNode <N, A, M>(null, image, BB));
                    break;
                default:
            }
        }

        return nodes;
    }

    /**
     * @param image
     * @param w
     * @return
     */
    private @NotNull
    List <ImageBlock3x3 <M>> splitImageToBlocks (M image, int w ) {
        List <ImageBlock3x3 <M>> blocks = new ArrayList <>();
        for (int i = 0; i < image.cols(); i += 3) {
            for (int j = 0; j < image.rows(); j += 3) {
                blocks.add(new ImageBlock3x3 <M>( i, i + 3, j, j + 3));
            }
        }

        return blocks;
    }

    /**
     * @return
     */
    public
    int getNumLayers () {
        return numLayers;
    }
}
