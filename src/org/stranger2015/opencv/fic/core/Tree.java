package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Rect;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.EnumSet;

import static org.stranger2015.opencv.fic.core.Tree.EAffineTransform.*;

/**
 * @param <N>
 * @param <M>
 */
abstract public
class Tree<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage, G extends BitBuffer> {

    public static final int DEFAULT_DEPTH = Integer.MAX_VALUE;
    public static final Rect DEFAULT_BOUNDING_BOX = new Rect(0, 0, 0, 0);

    protected final NodeList <N, A, M, G> leaves = new NodeList <>();
    protected final NodeList <N, A, M, G> nodes = new NodeList <>();

    protected M image;

    protected TreeNode <N, A, M, G> root;
    protected TreeNodeAction <N, A, M, G> action;

    private Rect area;

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
    Tree ( TreeNode <N, A, M, G> root, M image, TreeNodeAction <N, A, M, G> action, Rect area, int depth ) {
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
    Tree ( TreeNode <N, A, M, G> root, M image, TreeNodeAction <N, A, M, G> action ) {
        this(
                root,
                image,
                action,
                DEFAULT_BOUNDING_BOX,
                DEFAULT_DEPTH
        );
    }

    @SuppressWarnings("unchecked")
    public static
    <N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage, G extends BitBuffer>
    @NotNull Tree <N, A, M, G> create ( String className ) {
        int rc = 0;
        try {
            Class <Tree <N, A, M, G>> clazz = (Class <Tree <N, A, M, G>>) Class.forName(className);
            Tree <N, A, M, G> tree = clazz.getDeclaredConstructor().newInstance();
            tree.initialize();

            return tree;
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
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
        return EnumSet.of(
                FLIP,
                ROTATE,
                SCALE
        );
    }

    /**
     * @param parent
     * @param rect
     * @return
     */
    abstract public
    TreeNode <N, A, M, G> nodeInstance ( TreeNode <N, A, M, G> parent, EDirection quadrant, Rect rect )
            throws ValueError;

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    public
    Class <? extends TreeNode <N, A, M, G>> getNodeClass ( TreeNode <N, A, M, G> clazz ) {
        return (Class <? extends TreeNode <N, A, M, G>>) clazz.getClass();
    }

    /**
     * @return
     */
    public
    TreeNode <N, A, M, G> getRoot () {
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
    TreeTraverser <N, A, M, G> getTraverser ( Tree <N, A, M, G> tree,
                                           int depth,
                                           TreeNodeAction <N, A, M, G> action ) {
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
    NodeList <N, A, M, G> getNodes () {
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