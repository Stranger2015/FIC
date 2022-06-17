package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Collection;
import java.util.List;

import static org.stranger2015.opencv.fic.core.TreeNodeBase.*;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class BushTreeNodeBuilder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>

        extends TreeNodeBuilder <N, A, G> {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param library
     */
    public
    BushTreeNodeBuilder ( IImage <A> image,
                          IIntSize rangeSize,
                          IIntSize domainSize,
                          IEncoder <N, A, G> encoder,
                          Library <A> library ) {

        super(image, rangeSize, domainSize, encoder, library);
    }

    /**
     * @param imageBlock
     * @return
     */
    @Override
    public
    Tree <N, A, G> buildTree ( IImageBlock <A> imageBlock ) throws ValueError {
        TreeNode <N, A, G> root = (TreeNode <N, A, G>) lastNode.createNode(
                null,
                imageBlock,
                new DecAddress <>(0));
        Tree <N, A, G> tree = Tree.create(BushTree.class, root, imageBlock);

        return tree;
    }

    /**
     * @param root
     * @param imageBlock
     * @return
     */
    @Contract(pure = true)
    private @Nullable
    Collection <? extends TreeNode <N, A, G>> buildNodes ( TreeNode <N, A, G> root, IImageBlock <A> imageBlock ) {

        return null;
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
    TreeNode <N, A, G> getLastNode () {
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
}