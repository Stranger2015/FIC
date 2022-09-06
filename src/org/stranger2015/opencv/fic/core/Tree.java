package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static java.util.EnumSet.of;

/**
 * @param <N>
 */
abstract public
class Tree<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        implements ITree <N, A, G> {

    public static final int DEFAULT_DEPTH = MAX_VALUE;
    public static final IRectangle DEFAULT_BOUNDING_BOX = null;

    protected final List <LeafNode <N, A, G>> leaves = new ArrayList <>();

    protected final List <TreeNode <N, A, G>> nodes = new ArrayList <>();

    protected IImage <A> image;

    protected TreeNodeBase <N, A, G> root;
    protected TreeNodeTask <N, A, G> action;

    private IRectangle area;

    /**
     *
     */
    protected int depth;          // The depth of the tree... sa/sip layer??

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
    Tree ( TreeNodeBase <N, A, G> root,
           IImage <A> image,
           TreeNodeTask <N, A, G> action,
           IRectangle area,
           int depth
    ) {
        this.image = image;
        this.root = root;
        this.area = area;
        this.depth = depth;
        this.action = action;
        nodes.add((TreeNode <N, A, G>) root);
    }

    /**
     * @param root
     * @param image
     * @param action
     */
    protected
    Tree ( TreeNodeBase <N, A, G> root, IImage <A> image, TreeNodeTask <N, A, G> action ) {
        this(root,
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
    void initialize ( TreeNode <N, A, G> root, IImageBlock <A> imageBlock ) {
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
    TreeNode <N, A, G> nodeInstance ( TreeNodeBase <N, A, G> parent, EDirection quadrant,
                                      IIntSize rect )
            throws ValueError;

    /**
     * @return
     */
    public
    TreeNodeBase <N, A, G> getRoot () {
        return root;
    }

    /**
     * @return
     */
    public
    IImage <A> getImage () {
        return image;
    }

    /**
     * @param tree
     * @param depth
     * @param action
     * @return
     */
    public
    TreeTraverser <N, A, G> getTraverser ( Tree <N, A, G> tree,
                                           int depth,
                                           TreeNodeTask <N, A, G> action
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
    List <LeafNode <N, A, G>> getLeaves () {
        return leaves;
    }

    /**
     * @return
     */
    public
    List <TreeNode <N, A, G>> getNodes () {
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