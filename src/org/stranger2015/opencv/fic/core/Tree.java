package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

import java.lang.reflect.InvocationTargetException;
import java.util.EnumSet;

import static org.stranger2015.opencv.fic.core.Tree.EAffineTransform.*;

/**
 * @param <N>
 * @param <M>
 */
abstract public
class Tree<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image> {

    public static final int DEFAULT_DEPTH = Integer.MAX_VALUE;
    public static final Rect DEFAULT_BOUNDING_BOX = new Rect(0, 0, 0, 0);

    protected final NodeList <N, A, M> leaves = new NodeList <>();
    protected final NodeList <N, A, M> nodes = new NodeList <>();

    protected M image;

    protected TreeNode <N, A, M> root;
    protected TreeNodeAction <N> action;

    private Rect area;
    /**
     *
     */
    protected int depth;          // The depth of the tree... vsa/sip layer????????????

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
    Tree ( TreeNode <N, A, M> root, M image, TreeNodeAction <N> action, Rect area, int depth ) {
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
    Tree ( TreeNode <N, A, M> root, M image, TreeNodeAction <N> action ) {
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
    <N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
    @NotNull Tree <N, A, M> create ( String className ) {
        int rc = 0;
        try {
            Class <?> clazz = Class.forName(className);
            Tree <N, A, M> tree = (Tree <N, A, M>) clazz.getDeclaredConstructor().newInstance();
            tree.initialize();

            return tree;
        } catch (ClassNotFoundException |
                NoSuchMethodException |
                InvocationTargetException |
                InstantiationException |
                IllegalAccessException e) {

            e.printStackTrace();
            rc = -1;
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
    TreeNode <N, A, M> nodeInstance ( TreeNode <N, A, M> parent, EDirection quadrant, Rect rect )
            throws ValueError;

    /**
     * @return
     */
    public abstract
    Class <N> getNodeClass ();

    /**
     * @return
     */
    public
    TreeNode <N, A, M> getRoot () {
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
    TreeTraverser <N, A, M> getTraverser ( Tree <N, A, M> tree,
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
    NodeList <N, A, M> getNodes () {
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
        // SKEW,//??????????
        // TRANSLATE,//?????????????
        ;
    }
}