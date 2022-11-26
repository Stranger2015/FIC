package org.stranger2015.opencv.fic.core;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @param <N>
 * @param <A>
 
 * @param <G>
 */
public abstract
class TreeNodeBuilder<N extends TreeNode <N, A, G>, A extends IAddress <A>,  G extends BitBuffer>
        implements ITreeNodeBuilder <N, A, G> {

    /**
     * maps block coordinate (point(x,y)) to image blk instance
     */
    protected final Map <Point, IImageBlock <A>> mapBlockCoordToBlock = new DualHashBidiMap <>();
    protected final List <IImageBlock <A>> blockList = new ArrayList <>();

//    public final int sideSize;
    public/* final */IImage <A> image;
    private /*final */IIntSize rangeSize;
    private /*final*/ IIntSize domainSize;
    private/* final*/ IEncoder <N, A, G> encoder;

    public/* final*/ Library <A> library;

    protected TreeNode <N, A, G> lastNode;

    public
    TreeNodeBase <N, A, G> getLastNodeBase () {
        return lastNodeBase;
    }

    protected TreeNodeBase <N, A, G> lastNodeBase;
    protected LeafNode <N, A, G> lastLeafNode;

    protected
    TreeNodeBuilder ( IImage<A> image,
                      IIntSize rangeSize,
                      IIntSize domainSize,
                      IEncoder<N, A, G> encoder,
                      Library<A> library) {

        this.image = image;
        this.rangeSize = rangeSize;
        this.domainSize = domainSize;
        this.encoder = encoder;
        this.library = library;
    }

    public
    Library <A> getLibrary () {
        return library;
    }

    public
    IIntSize getRangeSize () {
        return rangeSize;
    }

    public
    IIntSize getDomainSize () {
        return domainSize;
    }

    public
    IEncoder <N, A, G> getEncoder () {
        return encoder;
    }

    /**
     * @param imageBlock
     * @return
     * @throws ValueError
     */
    @Override
    public
    Tree <N, A, G> buildTree ( IImageBlock <A> imageBlock ) throws ValueError {
        TreeNode <N, A, G> root = (TreeNode <N, A, G>) lastNode.createNode(
                null,
                imageBlock,
                lastNode.address);//fixme

        Tree <N, A, G> tree = ITree.create(lastNode.getTreeClass(), root, imageBlock);
        tree.getNodes().add(root);

        return tree;
    }

    /**
     *
     * @param parent
     * @return
     */
    public TreeNodeBase<N, A, G> buildNode(TreeNodeBase<N, A, G> parent){

        return parent;
    }
}
