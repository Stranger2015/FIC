package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static java.util.EnumSet.of;

/**
 * @param <N>
 */
abstract public
class Tree<N extends TreeNode <N>>
        implements ITree <N> {

    public static final int DEFAULT_DEPTH = MAX_VALUE;
    public static final IRectangle DEFAULT_BOUNDING_BOX = null;

    protected final List <LeafNode <N>> leaves = new ArrayList <>();

    protected final List <TreeNode <N>> nodes = new ArrayList <>();

    protected IImage image;

    protected TreeNodeBase <N> root;
    protected TreeNodeTask action;
    /**
     *
     */
    protected int depth;          // The depth of the tree... sa/sip layer??
    private IRectangle area;

    /**
     *
     */
    public
    Tree () {
    }

    /**
     * Constructs a new object.
     *
     * @param image
     * @param area
     */
    protected
    Tree ( TreeNodeBase <N> root,
           IImage image,
           TreeNodeTask  action,
           IRectangle area,
           int depth
    ) {
        this.image = image;
        this.root = root;
        this.area = area;
        this.depth = depth;
        this.action = action;
        nodes.add((TreeNode <N>) root);
    }

    /**
     * @param root
     * @param image
     * @param action
     */
    protected
    Tree ( TreeNodeBase <N> root, IImage image, TreeNodeTask action ) {
        this(
                root,
                image,
                action,
                DEFAULT_BOUNDING_BOX,
                DEFAULT_DEPTH
        );
    }

    /**
     * @param root
     * @param imageBlock
     */
    protected
    void initialize ( TreeNode <N> root, IImageBlock  imageBlock ) {

    }

    /**
     *
     */
    protected
    void initialize () {

    }

    /**
     * @return
     */
    public
    EnumSet <EAffineTransform> getTransforms () {
        return of(
                EAffineTransform.FLIP,
                EAffineTransform.ROTATE,
                EAffineTransform.SCALE
        );
    }

    /**
     * @param parent
     * @param rect
     * @return
     */
    abstract public
    TreeNode <N> nodeInstance ( TreeNodeBase <N> parent,
                                      EDirection quadrant,
                                      IIntSize rect )
            throws ValueError;

    /**
     * @return
     */
    public
    TreeNodeBase <N> getRoot () {
        return root;
    }

    /**
     * @return
     */
    public
    IImage getImage () {
        return image;
    }

    /**
     * @param tree
     * @param depth
     * @param action
     * @return
     */
    public
    TreeTraverser <N> getTraverser ( Tree <N> tree,
                                           int depth,
                                           TreeNodeTask action
    ) {
        return new TreeTraverser <>(tree, depth, action);
    }

    /**
     * @return
     */
    public
    IRectangle getArea () {
        return area;
    }

    /**
     * @return
     */
    public
    List <LeafNode <N>> getLeaves () {
        return leaves;
    }

    /**
     * @return
     */
    public
    List <TreeNode <N>> getNodes () {
        return nodes;
    }

    /**
     *
     */
    public
    enum EAffineTransform {
        FLIP, // flip/flop
        ROTATE,
        SCALE,
        // SKEW,//???
        // TRANSLATE,//???
    }
}