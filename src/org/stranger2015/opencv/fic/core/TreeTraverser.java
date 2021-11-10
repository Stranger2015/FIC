package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Nullable;
import org.opencv.core.Rect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import static org.stranger2015.opencv.fic.core.SideDirection.NORTH;
import static org.stranger2015.opencv.fic.core.TreeNode.Type.BLACK;
import static org.stranger2015.opencv.fic.core.TreeNode.Type.GRAY;
import static org.stranger2015.opencv.fic.core.TreeTraverser.Order.IN;
import static org.stranger2015.opencv.fic.core.TreeTraverser.SearchType.DEPTH_FIRST;

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

    public static final EnumSet <SideDirection> SIDES =
            EnumSet.allOf(SideDirection.class);
    public static final EnumSet <CornerDirection> QUADRANTS =
            EnumSet.allOf(CornerDirection.class);
    public static final EnumSet <Direction> SIDES_QUADS =
            EnumSet.allOf(Direction.class);
    private static TreeNode <? extends TreeNode> node;
    private static Direction dir;

    //    public final NeighborVector <N> neighbors;
    protected final Tree <N, ?> tree;
    protected final TreeNodeAction <N> action;
    //    protected final Deque <N> stack = new ArrayDeque <>();
    protected final SearchType searchType = DEPTH_FIRST;
    protected int depth;
    protected Order order = IN;
    protected SideDirection direction = NORTH;

    /**
     * @param tree
     * @param depth
     * @param action
     */
    public
    TreeTraverser ( Tree <N, ?> tree,
                    int depth,
                    TreeNodeAction <N> action
    ) {
        this.tree = tree;
        this.depth = depth;
        this.action = action;
    }

    /**
     * t[ dir ]                           = SONI( A[ dir ], quadrant( opSide( dir),  cSide( dir)));
     * t[ quadrant(dir,  cSide(dir))]            = SONI( A[ quadrant( dir,  cSide( dir))], quadrant( opSide( dir), ccSide( dir)));
     * t[  cSide(dir)]                     = SONI( A[  cSide( dir)], quadrant( dir, ccSide( dir)));
     * t[ quadrant(opSide(dir),  cSide(dir))]    = SONI( A[  cSide( dir)], quadrant( opSide( dir), ccSide( dir)));
     * t[ opSide(dir)]                    = child(  P, quadrant( opSide( dir),  cSide( dir)));
     * t[ quadrant(opSide(dir), ccSide(dir))]   = child(  P, quadrant( opSide( dir), ccSide( dir)));
     * t[ ccSide(dir)]                    = child(  P, quadrant( dir, ccSide( dir)));
     * t[ quadrant(dir, ccSide(dir))]           = SONI( A[ dir], quadrant( opSide( dir), ccSide( dir)));
     *
     * @param node
     * @return
     */
    protected static
    <N extends TreeNode <N>>
    void add ( NodeList nodes, N node, CornerDirection dir ) {
        nodes.add(dir.ordinal(), node);
    }

    protected static
    <N extends TreeNode <N>>
    N get ( List <N> nodes, CornerDirection dir ) {
        return nodes.get(dir.ordinal());
    }

    protected
    NodeList getNodes () {
        return this.getTree().getNodes();
    }

    /**
     * @param node
     * @param quadrant
     * @param <N>
     * @return
     */
    public static
    <N extends TreeNode <N>>
    TreeNode <N> child ( TreeNode <N> node, CornerDirection quadrant ) {
        return node.getChildren().get(quadrant.ordinal());
    }

    /**
     * @param node
     * @param quadrant
     * @param <N>
     * @return
     */
    public static
    <N extends TreeNode <N>>
    TreeNode <N> child1 ( TreeNode <N> node, CornerDirection quadrant ) {
        TreeNode <N> result;
        if (node.isLeaf()) {
            result = node;
        }
        else {
            result = child(node, quadrant);
        }

        return result;
    }

    /**
     * @return
     */
    public
    Order getOrder () {
        return order;
    }

    /**
     * @return
     */
    public
    SearchType getSearchType () {
        return searchType;
    }

    /**
     * @return
     */
    public
    Tree <N, ?> getTree () {
        return tree;
    }

    /**
     * if node = null
     * return
     * stack ← empty stack
     * stack.push(node)
     * while not stack.isEmpty()
     * node ← stack.pop()
     * visit(node)
     * // right child is pushed first so that left is processed first
     * if node.right ≠ null
     * stack.push(node.right)
     * if node.left ≠ null
     * stack.push(node.left)
     * <p>
     * ==========================
     * <p>
     * If the tree is represented by an array (first index is 0), it is possible to calculate
     * the index of the
     * next element:[8]
     * <p>
     * procedure bubbleUp(array, i, leaf)
     * k ← 1
     * i ← (i - 1)/2
     * while (leaf + 1) % (k * 2) ≠ k
     * i ← (i - 1)/2
     * k ← 2 * k
     * return i
     * <p>
     * procedure preorder(array)
     * i ← 0
     * while i ≠ array.size
     * visit(array[i])
     * if i = size - 1
     * i ← size
     * else if i < size/2
     * i ← i * 2 + 1
     * else
     * leaf ← i - size/2
     * parent ← bubble_up(array, i, leaf)
     * i ← parent * 2 + 2
     *
     * @param node
     */
    public
    void traverse ( TreeNode <N> node, int depth, NodeList neighbors, TreeNodeAction <N> action )
            throws DepthLimitExceeded {
        traverse(node, depth, neighbors, action, IN);
    }

    public
    void traverse ( TreeNode <N> node, int depth, NodeList neighbors, TreeNodeAction <N> action, Order order )
            throws DepthLimitExceeded {
        if (--depth == 0) {
            throw new DepthLimitExceeded();
        }
        if (node == null) {
            return;
        }
        if (node.isGray()) { // Descend a level in the tree
            action.accept((N) node);
        }
        this.order = order;
        switch (order) {
            case PRE:
            case POST:
                break;
            case IN:
                NodeList nl = new NodeList();
//                for (int i = 0, size = node.getChildren().size(); i < size; i++) {
//                    TreeNode <N> child = child(node, direction.quadrant(direction.cSide()));
//                    traverse(child, depth, neighbors, action);
//                }
//                break;
            default:
                throw new IllegalStateException("Unexpected value: " + order);
        }
    }

    private
    NodeList findNeighbors ( TreeNode <N> node, Direction dir ) {

        NodeList nodes = tree.getNodes();
        NodeList neighbors = new NodeList();

        neighbors.set(dir, );
        return neighbors;
    }

    /**
     *
     */
    enum Order {
        PRE,
        IN,
        POST,
    }

    /**
     *
     */
    enum SearchType {
        DEPTH_FIRST,
        BREADTH_FIRST,
    }

    /**
     * @param node
     * @param level
     */
    public static
    <N extends TreeNode <N>>
    void qmatToQuadTree ( TreeNode <N> node, int level ) {
        //Given a QMAT rooted at node P spanning a 2LEVEL x 2LEVEL space, find its corresponding quadtree
        if (node.isBlack()) {
            if (node.getDistance() > (1 << (level - 1))) {
                //Node P  subsumes  some  of  its  neighbors
                for (SideDirection dir : DIRECTIONS) {
                    TreeNode <N> q = makeEqualAdjacentNeighbor(node, dir);
                    if (q != null) {
                        propagateAdjacent(q,
                                level,
                                node.getDistance() - 1 << (level - 1),
                                dir);
                    }
                    q = makeEqualCornerNeighbor(
                            node,
                            dir.cSide().quadrant(dir));
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
            for (CornerDirection dir : QUADRANTS) {
                qmatToQuadTree(node.getChild(dir), level);
            }
        }
    }

    // its appropriate children are to be converted to BLACK nodes if they are not already BLACK.
    private static
    <N extends TreeNode <N>>
    void propagateCorner ( TreeNode <N> node, int level, int t, SideDirection dir ) {
        if (1 << level > t) {
            // P is too large to be totally subsumed by its corner adjacent QMAT node
            if (node.isBlack()) {
                return;
            }
            if (node.isWhite()) {
                addFourWhiteChildren(node);
            }
            propagateCorner(child(node, dir.opSide().quadrant(dir.ccSide())),
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
                propagateCorner(child(node.getParent(), dir.quadrant(dir.cSide())),
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
    <N extends TreeNode <N>>
    TreeNode <N> makeEqualAdjacentNeighbor ( TreeNode <N> node, SideDirection dir ) {
        TreeNode <N> q = node.getParent() != null && dir.adjacent(childType(node)) ?
                // Find a common ancestor
                makeEqualAdjacentNeighbor(node.getParent(), dir) :
                node.getParent();
        // Follow the reflected path to locate the neighbor
        if (q != null && !q.isBlack()) {
            NodeList l;
            if (node.isGray()) {
                l = (List <N>) child(Collections.singletonList(q),
                        dir.reflect(childType(node))
                );
            }
            else {
                l = (List <N>) child(TreeTraverser. <N>addFourWhiteChildren(node),
                        dir.reflect(childType(node))
                );
            }
        }

        return node;
    }

    /**
     * @param node
     * @return
     */
    private static
    <N extends TreeNode <N>>
    List <N> addFourWhiteChildren ( TreeNode <N> node ) {
        final List <N> l = new ArrayList <>(4);
        node.setType(GRAY);
        for (CornerDirection quadrant : QUADRANTS) {
            int w = node.boundingBox.width / 2;
            int h = node.boundingBox.height / 2;
            TreeNode <N> ch = node.createChild(quadrant,
                    new Rect(node.boundingBox.x,
                            node.boundingBox.y,
                            w,
                            h));
            l.add((N) ch);
            for (CornerDirection q1 : QUADRANTS) {
                ch.setChild(q1, null);
            }
        }

        return l;
    }

    private static
    <N extends TreeNode <N>>
    CornerDirection childType ( TreeNode <N> node ) {
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
    TreeNode <N> makeEqualCornerNeighbor ( TreeNode <N> node, CornerDirection quadrant ) {
        TreeNode <N> q;// Find a common ancestor
        if (node.getParent() != null && childType(node) != quadrant.opQuad()) {
            if (childType(node) == quadrant) {
                q = makeEqualCornerNeighbor(node.getParent(), quadrant);
            }
            else {
                q = makeEqualAdjacentNeighbor(node.getParent(), childType(node).commonSide(quadrant));
            }
        }
        else {
            q = node.getParent();
        }

        return q;
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
    void propagateAdjacent ( TreeNode <N> node, int level, int t, SideDirection dir ) {
        if ((1 << level) > t) {
            // P is too large to be totally subsumed by its adjacent QMAT node
            if (node.isBlack()) {
                return;
            }
            if (node.isWhite()) {
                addFourWhiteChildren(node);
                propagateAdjacent(child(node, dir.opSide().quadrant(dir.ccSide())),
                        level - 1,
                        t,
                        dir);
                propagateAdjacent(child(node, dir.opSide().quadrant(dir.cSide())),
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
                    propagateAdjacent(child(node.getParent(), dir.reflect(childType(node))),
                            level,
                            t - 1 << level,
                            dir);
                }
            }
        }
    }
}