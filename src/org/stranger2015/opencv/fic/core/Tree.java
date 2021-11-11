package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Consumer;

/**
 * @param <N>
 * @param <M>
 */
abstract public
class Tree<N extends TreeNode <N>, M extends Mat> {
    public static final int DEFAULT_DEPTH = Integer.MAX_VALUE;
    public static final Rect DEFAULT_BBOX = new Rect(0, 0, 0, 0);

    protected final NodeList<N> leaves = new NodeList<>();
    protected final NodeList<N> nodes = new NodeList<>();

    protected final M image;
    protected final TreeNode <N> root;
    protected final TreeNodeAction <N> action;

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
    Tree ( TreeNode<N> root, M image, TreeNodeAction <N> action, Rect area, int depth ) {
        this.image = image;
        this.root = root;
        this.area = area;
        this.depth = depth;
        this.action = action;
        nodes.add(root);
    }

    /**
     * @param root
     * @param image
     * @param action
     */
    protected
    Tree ( TreeNode<N > root, M image, TreeNodeAction <N> action ) {
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
    }//TODO

    /**
     * @param parent
     * @param rect
     * @return
     */
    abstract public
    TreeNode <N> nodeInstance (TreeNode<N> parent, CornerDirection quadrant, Rect rect );

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
                                     int depth,
                                     TreeNodeAction <N> action ) {
        return new TreeTraverser <>(tree, depth, action);
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
    NodeList<N> getNodes () {
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