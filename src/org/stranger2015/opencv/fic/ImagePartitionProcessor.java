package org.stranger2015.opencv.fic;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.stranger2015.opencv.fic.core.*;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

import static org.stranger2015.opencv.fic.Main.currNode;


/**
 * Step  1:  Construct  the  domain  pools  Ddepth,  corresponding  to
 * each quad tree partition level starting from minimum
 * partitions to  maximum partitions (depth=0 to max_part-
 * min_part).
 * Step  2:  Calculate  the  block  pixel  value  difference  using
 * equation (5) of all the domain blocks in each pool Ddepth.
 * Step  3:  Classify  and  sort  the  domains  in  each  pool  Ddepth  in
 * ascending order of the pixel value difference, and place on a
 * list structure.
 * Step 4: Search for a best match between a range and domain
 * belonging to the same class.
 * write_header_info; (min_part, max_part, domain_step,
 * hsize, vszie)
 * depth=0; ec =rms_tol;
 * Function Quadtree(image, depth) {
 * best_rms = infinity;
 * β0 = initial value; βdepth *= 1.25;
 * While (depth < min_part) Quadtree (image, depth+1);
 * Set R1 = I2 and mark it uncovered.
 * While there are uncovered ranges Ri do {
 * //Select the domain pool list Ddepth corresponding to the current range block Ri.
 * For (j = 1; j < num_domains; ++j) {
 * If  (Rpdiff  < β * Dpdiff) {
 * Compute s, o, sym_op;
 * Compute E(Ri, Di);
 * If E (Ri, Di ) ≤ best_rms {
 * best_rms = E(Ri, Di );
 * best_domain = (domain_x,domain_y);
 * }
 * }// End for num_domains
 * If (best_rms > ec) and (depth < max_part)
 * Quadtree (image, depth+1);
 * Else
 * Write_transformations (best_domain, s, o, sym_op);
 * }// End while uncovered ranges
 * }// End function Quadtree()
 */
public
class ImagePartitionProcessor<N extends Node, M extends Mat> {

    protected final M image;
    protected final PartitionScheme scheme;
    protected final List <M> rangeBlocks = new ArrayList <>();
    protected final Tree <N, M> tree;
    private final Rect boundingBox = new Rect(new double[0]);
    protected int row;
    protected int col;
    protected boolean drawQuads;

    public
    ImagePartitionProcessor ( M image, PartitionScheme scheme, List <M> rangeBlocks, Tree <N, M> tree ) {
        this.image = image;
        this.scheme = scheme;
        this.rangeBlocks.clear();
        this.rangeBlocks.addAll(rangeBlocks);
        this.tree = tree;}

    public static
    int getNearestGreaterPow2 ( int n ) {
        int ngp2 = 1;
        for (; ngp2 < n; ngp2 *= 2) {

        }

        return ngp2;
    }

    /**
     * 1. Divide the image into range block.
     * 2. Divide  the  image  into  non-overlapping  domain  blocks,  Di.
     * The  union  of  the  domain  blocks  must  cover  the  entire
     * image, G, but they can be any size or shape [1].
     * 3. Define  a  finite  set  of  contractive  affine  transformations,  wi
     * (which map from a range block R to a domain block Di).
     * 4. For each domain block {
     * For each range block {
     * For each transformation {
     * Calculate the Hausdorff distance h(wi(R  G), Di  G) (or use another metric)
     * }
     * }
     * 5. Record the transformed domain block which is found to be the best approximation for
     * the current range block is assigned to that range block.
     * 6. Next domain block [1].
     */
    public
    void process () {
        List <M> rangeBlocks = createRangeBlocks(image, 4, 4);
        List <M> domainBlocks = createDomainBlocks(image, 8, 8);

//        root = decompose();
    }

    private
    List <M> createDomainBlocks ( M image, int w, int h ) {
        List <M> l = new ArrayList <>();
        final QuadTree <N, M> quadTree = new QuadTree <>();
        final N root = quadTree.getRoot();

        for (int i = 0, width = image.width(); i < width / w; i++) {
            for (int j = 0, height = image.height(); j < height / h; j++) {

            }
        }

        return l;
    }

    private
    List <M> createRangeBlocks ( M image, int w, int h ) {
        return createBlocks(image, w, h);
    }

    private
    List <M> createBlocks ( M image, int w, int h ) {
        List <M> l = new ArrayList <>();
        for (int i = 0, width = image.width(); i < width; i += w) {
            for (int j = 0, height = image.height(); j < height; j += h) {
                l.add((M) image.submat(i, j, i + w, j + h));
            }
        }

        return l;
    }

    /**
     * image mat is of the square shape so, width and height are equal and are of power of two
     *
     * @return
     */
    private
    void decompose ( Tree <N, M> tree ) {
        row = col = 0;
        for (int wh = 1; wh < image.width(); wh *= 2) {
            int i = 0;
            tree.getRoot().getChildren().clear();
            for (; i < 4; i++) {
                if (currNode.isLeaf()) {
                    currNode.getChildren().add(new Leaf(image));//fixme
                }
                else {
                    currNode.getChildren().add(new QuadNode(EnumSet.noneOf(Tree.AffineTransform.class)));
                }
            }

//            currNode.set(currQuads);
            if (Objects.equals(tree.getImage(), currNode)) {//fixme
                Node mergedLeaf = currNode.merge();
                currNode.getChildren().clear();
                currNode.getChildren().add(mergedLeaf);
//                i=1;
//                continue;
            }
        }
    }

    private
    boolean isLeaf ( Node node ) {
        return currNode.getChildren().size() == 0;
    }

//    private
//    boolean sameColor ( Tree tree ) {
//        Color color = Color.BLACK;
//        for (Node node : tree.getChildren()) {
//            if (node.isLeaf()) {
//                mat = ((Leaf) node).getColor();
//                if (nodeColor != color) {
//                    return false;
//                }
//
//                return false;
//            }
//        }
//
//        return true;
//    }

    private
    Node merge ( Node node ) {
        if (node.isLeaf()) {
            return node;
        }
        return tree.getRoot().getChildren().get(0);
    }

    private
    Color getColor ( Mat image, int i ) {
        switch (i) {
            case 0:
                col = 0;
                row = 0;
                break;
            case 1:
                col = 1;
                row = 0;
                break;
            case 2:
                col = 0;
                row = 1;
                break;
            case 3:
                col = 1;
                row = 1;
                break;
        }

        double[] cdata = image.get(col, row);

        return getColor(new Scalar(cdata));
    }

    private
    Color getColor ( Scalar data ) {
        if (Color.BLACK.getData() == data) {
            return Color.BLACK;
        }
        else {
            return Color.WHITE;
        }
    }

    public
    void draw () {


    }
}
