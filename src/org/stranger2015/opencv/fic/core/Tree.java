package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Consumer;

abstract public
class Tree<N extends TreeNode <N>, M extends Mat> {
    public static final int DEFAULT_DEPTH = Integer.MAX_VALUE;
    public static final Rect DEFAULT_BBOX = new Rect(0, 0, 0, 0);

    protected final List <Leaf> leaves = new ArrayList <>();
    protected final List <N> nodes = new ArrayList <>();

    protected final M image;
    protected final TreeNode <N> root;
    protected final Consumer <N> action;

    private final Rect area;
    /**
     *
     */
    protected int depth;          // The depth of the tree

    /**
     * Constructs a new object.
     *
     * @param image
     * @param area
     */
    protected
    Tree ( N root, M image, Consumer <N> action, Rect area, int depth ) {
        this.image = image;
        this.root = root;
        this.area = area;
        this.depth = depth;
        this.action = action;
        nodes.add(root);
    }

    protected
    Tree ( N root, M image, Consumer <N> action ) {
        this(
                root,
                image,
                action,
                DEFAULT_BBOX,
                DEFAULT_DEPTH
        );
    }

    public
    EnumSet <AffineTransform> getTransforms () {
        return null;
    }

//    public
//    void insert ( N node ) {
//        if (node == null) {
//            throw new IllegalArgumentException();
//        }
//        int i = node.index;
//        N insertAfterNode = findNode(node);
//    }

    /**
     * @param parent
     * @param rect
     * @param nodes
     * @return
     */
    abstract public
    TreeNode <N> nodeInstance (N parent, CornerDirection quadrant, Rect rect, List <N> nodes );

    /**
     * @return
     */
    public
    TreeNode <N> getRoot () {
        return root;
    }

    /**
     * @return
     */
    public
    M getImage () {
        return image;
    }

    /**
     * @param tree
     * @param depth
     * @param action
     * @return
     */
    public
    TreeTraverser <N> getTraverser ( Tree <N, M> tree,
                               //      NeighborVector <N> neighbors,
                                     int depth,
                                     TreeNodeAction <N> action ) {
        return new TreeTraverser <>(tree, /*neighbors,*/ depth, action);
    }

    /**
     * @return
     */
    public
    Rect getArea () {
        return area;
    }

    /**
     * @return
     */
    public
    List<N> getNodes () {
        return nodes;
    }

    /**
     *
     */
    public
    enum AffineTransform {
        MIRROR, // flip/flop
        ROTATE,
        SCALE,
        SKEW,
        TRANSLATE,
    }
}