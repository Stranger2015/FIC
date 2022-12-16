package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static org.stranger2015.opencv.fic.core.EDirection.*;
import static org.stranger2015.opencv.fic.core.TreeNodeBase.EType.BLACK;
import static org.stranger2015.opencv.fic.core.TreeNodeBase.EType.GRAY;
import static org.stranger2015.opencv.fic.core.TreeTraverser.EOrder.IN;
//import static org.stranger2015.opencv.fic.core.TreeTraverser.ESearchType.DEPTH_FIRST;

/**
 * To traverse arbitrary trees (not necessarily binary trees) with depth-first search,
 * perform the following operations at each node:
 * If the current node is empty then return.
 * Visit the current node for pre-order traversal.
 * For each i from 1 to the current node's number of subtrees − 1, or
 * from the latter to the former for reverse traversal, do:
 * Recursively traverse the current node's i-th subtree.
 * Visit the current node for in-order traversal.
 * Recursively traverse the current node's last subtree.
 * Visit the current node for post-order traversal.
 *
 * @param <N>
 */
public
class TreeTraverser<N extends TreeNode <N>> {
    public static final EnumSet <EDirection> SIDES =
            EnumSet.of(NORTH, EAST, SOUTH, WEST);
    public static final EnumSet <EDirection> QUADRANTS =
            EnumSet.of(NORTH_WEST, NORTH_EAST, SOUTH_EAST, SOUTH_WEST);

    public static final EnumSet <EDirection> SIDES_QUADS =
            EnumSet.allOf(EDirection.class);

    protected final Tree <N> tree;
    protected final TreeNodeTask action;

//    protected final ESearchType searchType = DEPTH_FIRST;
    protected final List <TreeNode<N>> nodes;
    protected int depth;
    protected EOrder order = IN;
    protected EDirection dir = NORTH;
    private List <TreeNode<N>> neighborsT;
    private List <TreeNode<N>> neighborsA;

    /**
     * @param tree
     * @param depth
     * @param action
     */
    public
    TreeTraverser ( Tree <N> tree,
                    int depth,
                    TreeNodeTask action ) {
        this.tree = tree;
        this.depth = depth;
        this.action = action;
        this.nodes = getNodes();
    }

    /**
     * @param node
     * @return
     */
    protected static
    <N extends TreeNode <N>>
    void add ( List <TreeNode<N>> nodes, N node, EDirection dir ) {
        nodes.add(dir.getOrd(), node);
    }

    /**
     * @param nodes
     * @param dir
     * @param <N>
     * @return
     */
    protected static
    <N extends TreeNode <N>>
    N get ( List <N> nodes, EDirection dir ) {
        return nodes.get(dir.ordinal());
    }

    /**
     * @return
     */
    protected
    List <TreeNode<N>> getNodes () {
        return this.getTree().getNodes();
    }

    /**
     * @param node
     * @param quadrant
     * @param <N>
     * @return
     */
    public static
    <N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
    TreeNodeBase<N> child ( TreeNode <N> node, EDirection quadrant ) {
        return node.getChildren().get(quadrant.getOrd());
    }

    /**
     * @param nodeDir
     * @param quadrant
     * @return
     */
    public
    TreeNodeBase <N> child ( EDirection nodeDir, EDirection quadrant ) {
        TreeNode <N> node = nodes.get(nodeDir.getOrd());
        return node.children.get(quadrant.getOrd());
    }

    /**
     * @param nodeDir
     * @param quadrant
     * @return
     */
    public
    TreeNodeBase <N> child1 ( EDirection nodeDir, EDirection quadrant ) {
        TreeNode <N> node = neighborsA.get(nodeDir.getOrd());
        if (!node.isGray()) {
            node = (TreeNode <N>) child(node, quadrant);
        }

        return node;
    }

    /**
     * @return
     */
    public
    EOrder getOrder () {
        return order;
    }

//    /**
//     * @return
//     */
//    public
//    ESearchType getSearchType () {
//        return searchType;
//    }

    /**
     * @return
     */
    public
    Tree <N> getTree () {
        return tree;
    }

    /**
     * Perform  a neighbored traversal, visiting the  children in  the  order  specified  by  childorder.
     *
     * @param node
     * @param depth
     * @param neighbors
     * @param action
     * @throws DepthLimitExceeded
     */
    @SuppressWarnings("unchecked")
    public
    void traverse ( TreeNode <N> node,
                    int depth,
                    List <TreeNode<N>> neighbors,
                    TreeNodeTask action )
            throws DepthLimitExceeded {
        if (--depth == 0) {
            throw new DepthLimitExceeded();
        }
        if (node == null) {
            return;
        }

        if (node.isWhite()) {
            for (EDirection dir : SIDES) {
                TreeNode <N> childNode = neighbors.get(dir.getOrd());
                if (childNode.isBlack()) {

                }
            }
        }
        if (!node.isGray()) { // Descend a level in the tree
            action.apply(action.getFilename());

            return;
        }

        /* 1 */
        findNeighbor(dir,
                (TreeNode <N>) child1(dir,
                        dir.opSide().quadrant(dir.cSide())));
        /* 2 */
        findNeighbor(dir.quadrant(dir.cSide()),
                (TreeNode <N>) child1(dir.opSide().quadrant(dir.ccSide()),
                        dir.opSide().quadrant(dir.ccSide())));
        /* 3 */
        findNeighbor(dir.cSide(),
                (TreeNode <N>) child1(dir.cSide(),
                        dir.quadrant(dir.ccSide())));
        /* 4 */
        findNeighbor(dir.cSide(),
                (TreeNode <N>) child1(dir.cSide(),
                        dir.opSide().quadrant(dir.ccSide())));
        /* 5 */
        findNeighbor(dir.opSide(),
                (TreeNode <N>) child((TreeNodeBase <N>) node,
                        dir.opSide().quadrant(dir.cSide())));
        /* 6 */
        findNeighbor(dir.opSide().quadrant(dir.ccSide()),
                (TreeNode <N>) child((TreeNodeBase <N>) node,
                        dir.opSide().quadrant(dir.ccSide())));
        /* 7 */
        findNeighbor(dir.ccSide(),
                (TreeNode <N>) child((TreeNodeBase <N>) node,
                        dir.quadrant(dir.ccSide())));
        /* 8 */
        findNeighbor(dir.quadrant(dir.ccSide()),
                (TreeNode <N>) child1(dir,
                        dir.opSide().quadrant(dir.ccSide())));

        traverse((TreeNode <N>) child((TreeNodeBase <N>) node, dir.quadrant(dir.cSide())),
                depth,
                neighborsT,
                action);
    }

    /**
     * @param dir
     * @param childNode
     */
    private
    void findNeighbor ( EDirection dir, TreeNode <N> childNode ) {
        neighborsT.set(dir.getOrd(), childNode);
    }

    public
    void setNeighborsA ( List <TreeNode <N>> neighborsA ) {
        this.neighborsA = neighborsA;
    }

    public
    void setNeighborsT ( List <TreeNode <N>> neighborsT ) {
        this.neighborsT = neighborsT;
    }

    /**
     *
     */
    enum EOrder {
        PRE,
        IN,
        POST,
    }

       /**
     * @param node
     * @param level
     */
    public static
    <N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
    void qmatToQuadTree ( TreeNode <N> node, int level ) throws ValueError {
        //Given a QMAT rooted at node P spanning a 2LEVEL x 2LEVEL space, find its corresponding quadtree
        if (node.isBlack()) {
            if (node.getDistance() > (1 << (level - 1))) {
                //Node P  subsumes  some  of  its  neighbors
                for (EDirection dir : SIDES) {
                    TreeNode <N> q = makeEqualAdjacentNeighbor(node, dir);
                    if (q != null) {
                        propagateAdjacent(q,
                                level,
                                node.getDistance() - 1 << (level - 1),
                                dir);
                    }
                    q = makeEqualCornerNeighbor(
                            node,
                            dir.quadrant(dir.cSide()));
                    if (q != null) {
                        propagateCorner(q,
                                level,
                                node.getDistance() - 1 << (level - 1),
                                dir);
                    }
                }
                node.setDistance(0);
            }
        }
        else if (node.isGray()) {
            for (EDirection dir : QUADRANTS) {
                qmatToQuadTree((TreeNode <N>) node.getChild(dir), level);
            }
        }
    }

    // its appropriate children are to be converted to BLACK nodes if they are not already BLACK.
    private static
    <N extends TreeNode <N>>
    void propagateCorner ( TreeNode <N> node, int level, int t, EDirection dir ) throws ValueError {
        if (1 << level > t) {
            // P is too large to be totally subsumed by its corner adjacent QMAT node
            if (node.isBlack()) {
                return;
            }
            if (node.isWhite()) {
                addFourWhiteChildren(node);
            }
            propagateCorner((TreeNode <N>) child(node, dir.opSide().quadrant(dir.ccSide())),
                    level - 1,
                    t,
                    dir);
        }
        else {
            if (node.isGray()) {
                return;//sons to avail (node)
            }
            node.setType(BLACK);
            if (1 << level < t) {
                // Propagate subsumption to  neighbors of  P  on  its  corner and
                // the  two sides  adjacent to  the  corner
                propagateAdjacent(child(node.getParent(), dir.quadrant(dir.ccSide())),
                        level,
                        t - 1 << level,
                        dir);
                propagateCorner((TreeNode <N>) child(node.getParent(), dir.quadrant(dir.cSide())),
                        level,
                        t - 1 << level,
                        dir);
                propagateAdjacent(child(node.getParent(), dir.opSide().quadrant(dir.cSide())),
                        level,
                        t - 1 << level,
                        dir.cSide());
            }
        }
    }

    private static
    void propagateAdjacent ( Object child, int level, int t, EDirection dir ) {

    }

    private static
    <G extends BitBuffer, A extends IAddress , N extends TreeNode <N>>
    Object child ( Object parent, EDirection quadrant ) {
        return null;
    }

    /**
     * --------->MAKE-EQUAL-ADJ-NEIGHBOR( P,  dir,  Q);
     * Return in Q the neighbor of node P in horizontal or vertical direction dir which is
     * equal in size to P.
     * If P is adjacent to the border of the image along the specified direction, then NULL
     * is returned.
     * If the neighbor is of size greater than P and is WHITE, then it is broken up into four
     * WHITE quadrants in order to obtain the desired neighbor.
     * If the neighbor is BLACK, then it is not broken up further.
     *
     * @param node
     * @param dir
     * @return
     */
    public static
    <N extends TreeNode <N>, A extends IAddress ,G extends BitBuffer>
    TreeNode <N> makeEqualAdjacentNeighbor ( TreeNode <N> node, EDirection dir ) {
        TreeNode <N> q = node.getParent() != null && dir.adjacent(childType(node)) ?
                // Find a common ancestor
                makeEqualAdjacentNeighbor((TreeNode <N>) node.getParent(), dir) :
                (TreeNode <N>) node.getParent();
        // Follow the reflected path to locate the neighbor
        if (q != null && !q.isBlack()) {
            List <TreeNode<N>> l;
//            if (node.isGray()) {
//                l = child(q,
//                        dir.reflect(childType(node))
//                );
//            }
//            else {
//                l = child(TreeTraverser. <N>addFourWhiteChildren(node),
//                        dir.reflect(childType(node))
//                );
//            }
        }

        return node;
    }

    /**
     * @param node
     * @return
     */
    @SuppressWarnings("unchecked")
    private static
    <N extends TreeNode <N>>
    @NotNull List <TreeNode<N>> addFourWhiteChildren ( TreeNodeBase<N> node ) throws ValueError {
        final List <TreeNode<N>> l = new ArrayList <>(4);
        node.setType(GRAY);
        for (EDirection quadrant : QUADRANTS) {
            int w = node.boundingBox.getWidth() / 2;
            int h = node.boundingBox.getHeight() / 2;
            TreeNode <N> ch = null;
            ch = (TreeNode <N>) node.createChild(
                    quadrant.getOrd(),
                    new Rectangle(
                            node.boundingBox.getX(),
                            node.boundingBox.getY(),
                            w,
                            h)
            );
            l.add(ch);
            for (EDirection q1 : QUADRANTS) {
                ch.setChild(q1, null);
            }
        }

        return l;
    }

    private static
    <N extends TreeNode <N>>
    EDirection childType ( TreeNode <N> node ) {
        return node.getQuadrant();
    }

    /**
     * Return the neighbor of node P in the direction of corner C of P which is equal in size to P.
     * If P is adjacent to the border of the image along the specified direction, then null is returned.
     * If the neighbor is of size greater than P and is WHITE, then it is broken up into four WHITE quadrants
     * in order to obtain the desired neighbor. If the neighbor is BLACK, then it is not broken up further.
     *
     * @param node
     * @param quadrant
     * @param <N>
     * @return
     */
    public static
    <N extends TreeNode <N>>
    TreeNode <N> makeEqualCornerNeighbor ( TreeNode <N> node, EDirection quadrant ) {
        TreeNode <N> q;// Find a common ancestor
        if (node.getParent() != null && childType(node) != quadrant.opQuad()) {
            if (childType(node) == quadrant) {
                q = makeEqualCornerNeighbor((TreeNode <N>) node.getParent(), quadrant);
            }
            else {
                q = (TreeNode <N>) makeEqualAdjacentNeighbor(node.getParent(), childType(node).commonSide(quadrant));
            }
        }
        else {
            q = (TreeNode <N>) node.getParent();
        }

        return q;
    }

    private static
    <N extends TreeNode <N>>
    Object makeEqualAdjacentNeighbor ( TreeNodeBase <N> parent, EDirection dir ) {
        return null;
    }

    /**
     * Node P  at  level 'level' is  adjacent to  side  dir  of  a  QMAT node whose span  exceeds
     * its  width by t-i.e., P  is  subsumed or  partially subsumed by  that node. P  or
     * its appropriate CHILDREN  are to be converted to BLACK nodes if they are not
     * already BLACK.
     *
     * @param <N>
     * @param node
     * @param level
     * @param t
     * @param dir
     */
    private static
    <N extends TreeNode <N>>
    void propagateAdjacent ( TreeNode<N> node, int level, int t, EDirection dir ) throws ValueError {
        if ((1 << level) > t) {
            // P is too large to be totally subsumed by its adjacent QMAT node
            if (node.isBlack()) {
                return;
            }
            if (node.isWhite()) {
                addFourWhiteChildren(node);
                propagateAdjacent((TreeNode <N>) child(node, dir.opSide().quadrant(dir.ccSide())),
                        level - 1,
                        t,
                        dir);
                propagateAdjacent((TreeNode <N>) child(node, dir.opSide().quadrant(dir.cSide())),
                        level - 1,
                        t,
                        dir);
            }
            else {
                // P is subsumed by its adjacent QMAT nod
                if (node.isGray()) {// Account for nondisjointness of the QMAT
                    return;//node;//SONS  to  avail  (P);
                }
                node.setType(BLACK); // Change P to BLACK if not
                if (1 << level < t) {// Propagate subsumption to neighbors of node on side `dir`
                    propagateAdjacent((TreeNode <N>) child(node.getParent(), dir.reflect(childType(node))),
                            level,
                            t - 1 << level,
                            dir);
                }
            }
        }
    }

    /**
     * @param <N>
     */
    static
    class
    Neighbors<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
            extends ArrayList<TreeNode <N>> {

        /**
         *
         */
        public
        Neighbors () {
            super();
        }
    }

    /**
     *
     */
    static
    class Edge {
        int length;
        int posit;
        double d;

        /**
         * @param length
         * @param posit
         * @param d
         */
        public
        Edge ( int length, int posit, double d ) {
            this.length = length;
            this.posit = posit;
            this.d = d;
        }
    }
}