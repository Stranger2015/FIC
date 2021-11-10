package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.stranger2015.opencv.fic.DomainBlock;
import org.stranger2015.opencv.fic.DomainPool;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import static org.stranger2015.opencv.fic.Main.currTreeNode;

/**
 * Step 1: Construct the domain pools Ddepth, corresponding to
 * each quad tree partition level starting from minimum
 * partitions to maximum partitions (depth=0 to max_part-min_part).
 *
 * Step 2: Calculate the block pixel value difference using
 * equation (5) of all the domain blocks in each pool Ddepth.
 *
 * Step  3:  Classify  and  sort  the  domains  in  each  pool  Ddepth  in
 * ascending order of the pixel value difference, and place on a
 * list structure.
 *
 * Step 4: Search for a best match between a range and domain
 * belonging to the same class.
 * write_header_info; (min_part, max_part, domain_step, hsize, vsize)
 * depth=0; ec =rms_tol;
 *
 *  Function Quadtree(image, depth) {
 * best_rms = infinity;
 * β0 = initial value; βdepth *= 1.25;
 * While (depth < min_part) Quadtree (image, depth+1);
 * Set R1 = I2 and mark it uncovered.
 * While there are uncovered ranges Ri do {
 * //Select the domain pool list Ddepth corresponding to the
 * current range block Ri.
 *
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
class ImagePartitionProcessor<N extends TreeNode<N>, M extends Mat> {

    protected final M image;
    protected final PartitionScheme scheme;

    public
    List <M> getRangeBlocks () {
        return rangeBlocks;
    }

    protected final List <M> rangeBlocks = new ArrayList <>();

    public
    List <DomainBlock> getDomainBlocks () {
        return domainBlocks;
    }

    protected final List <DomainBlock> domainBlocks = new ArrayList <>();

    protected final Tree <N, M> tree;

    private final
    Consumer <N> action = n -> {
    };

    protected int row;
    protected int col;

    public
    ImagePartitionProcessor ( M image, PartitionScheme scheme, Tree <N, M> tree ) {
        this.image = image;
        this.scheme = scheme;
        this.tree = tree;
    }

    public
    ImagePartitionProcessor ( M image, PartitionScheme scheme ) {
        this(image, scheme, null);
    }

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
//        List <M> domainBlocks = createDomainBlocks(image, 8, 8);

    }

//    private
//    List <M> createDomainBlocks ( M image, int w, int h ) {
//        List <M> l = new ArrayList <>();
//        Consumer<M> action = new TreeNodeAction(new DomainPool());
//        final QuadTree <Leaf, M> quadTree = new QuadTree <N, M>(image, action);
//
//
//        final TreeNode root = quadTree.getRoot();
//        TreeNode node = root.getChildren().get(0);
//
//        for (int i = 0, width = image.width(); i < width / w; i++, width /= 2) {
//            for (int j = 0, height = image.height(); j < height / h; j++, height /= 2) {
//
//            }
//        }
//
//        return l;
//    }


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
//    private
//    void decompose ( Tree <N, M> tree ) {
//        row = col = 0;
//        var parent = (TreeNode) tree;
//
//        for (int wh = 1; wh < image.width(); wh *= 2) {
//            int i = 0;
//            tree.getRoot().getChildren().clear();
//            for (; i < 4; i++) {
//                if (currTreeNode.isLeaf()) {
//                    currTreeNode.getChildren().add(new Leaf(parent, image, new Rect(col, row, wh, wh)));//fixme!!
//                }
//                else {
//                    currTreeNode.getChildren().add(new QuadTreeNode(parent, new Rect(0,0,0,0), nodes));
//                }
//            }
//
//            currTreeNode.set(currQuads);
//            if (Objects.equals(tree, currTreeNode)) {//fixme
//                TreeNode<N> mergedLeaf = currTreeNode.merge();
//                currTreeNode.getChildren().clear();
//                currTreeNode.getChildren().add(mergedLeaf);
//                i=1;
//                continue;
//            }
//        }
//    }
//
//    private
    boolean isLeaf ( TreeNode treeNode ) {
        return currTreeNode.getChildren().size() == 0;
    }

    private
    TreeNode merge ( TreeNode treeNode ) {
        if (treeNode.isLeaf()) {
            return treeNode;
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

    protected
    void findMatchesFor () {

    }

    //    /*
// * Fractal Image Compression. Copyright 2004 Alex Kennberg.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *   http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
//*/
//
//#include <cstdio>
//#include <cstdlib>
//#include <vector>
//#include <string>
//    using namespace std;
//
//#include "Image.h"
//            #include "IFSTransform.h"
//            #include "Encoder.h"
//            #include "QuadTreeEncoder.h"
//
//    extern int verb;
//    extern bool useYCbCr;
//
//#define BUFFER_SIZE		(16)
//
//    QuadTreeEncoder::QuadTreeEncoder(int threshold, bool symmetry)
//    {
//        this->threshold = threshold;
//        this->symmetry = symmetry;
//    }
//
//    QuadTreeEncoder::~QuadTreeEncoder()
//    {
//    }
//
//    Transforms* QuadTreeEncoder::Encode( Image* source)
//    {
//        Transforms* transforms = new Transforms;
//
//        img.width = source->GetWidth();
//        img.height = source->GetHeight();
//        img.channels = source->GetChannels();
//        transforms->channels = img.channels;
//
//        for (int channel = 1; channel <= img.channels; channel++)
//        {
//            // Load image into a local copy
//            img.imagedata = new PixelValue[img.width * img.height];
//            source->GetChannelData(channel, img.imagedata, img.width * img.height);
//
//            if (img.width % 32 != 0 || img.height %32 != 0)
//            {
//                printf("Error: Image must have dimensions that are multiples of 32.\n");
//                exit(-1);
//            }
//
//            // Make second channel the downsampled version of the image.
//            img.imagedata2 = IFSTransform::DownSample(img.imagedata, img.width, 0, 0, img.width / 2);
//
//            // When using YCbCr we can reduce the quality of colour, because the eye
//            // is more sensitive to intensity which is channel 1.
//            if (channel >= 2 && useYCbCr)
//                threshold *= 2;
//
//            // Go through all the range blocks
//            for (int y = 0; y < img.height; y += BUFFER_SIZE)
//            {
//                for (int x = 0; x < img.width; x += BUFFER_SIZE)
//                {
//                    findMatchesFor(transforms->ch[channel-1], x, y, BUFFER_SIZE);
//                    printf(".");
//                }
//                printf("\n");
//            }
//
//            // Bring the threshold back to original.
//            if (channel >= 2 && useYCbCr)
//                threshold /= 2;
//
//            delete []img.imagedata2;
//            img.imagedata2 = NULL;
//            delete []img.imagedata;
//            img.imagedata = NULL;
//            printf("\n");
//        }
//
//        return transforms;
//    }
//
//    void QuadTreeEncoder::findMatchesFor( Transform& transforms, int toX, int toY, int blockSize)
//    {
//        int bestX = 0;
//        int bestY = 0;
//        int bestOffset = 0;
//        IFSTransform::SYM bestSymmetry = IFSTransform::SYM_NONE;
//        double bestScale = 0;
//        double bestError = 1e9;
//
//        PixelValue* buffer = new PixelValue[blockSize * blockSize];
//
//        // Get average pixel for the range block
//        int rangeAvg = GetAveragePixel(img.imagedata, img.width, toX, toY, blockSize);
//
//        // Go through all the downsampled domain blocks
//        for (int y = 0; y < img.height; y += blockSize * 2)
//        {
//            for (int x = 0; x < img.width; x += blockSize * 2)
//            {
//                for (int symmetry = 0; symmetry < IFSTransform::SYM_MAX; symmetry++)
//                {
//                    IFSTransform::SYM symmetryEnum = (IFSTransform::SYM)symmetry;
//                    IFSTransform* ifs = new IFSTransform(x, y, 0, 0, blockSize, symmetryEnum, 1.0, 0);
//                    ifs->Execute(img.imagedata2, img.width / 2, buffer, blockSize, true);
//
//                    // Get average pixel for the downsampled domain block
//                    int domainAvg = GetAveragePixel(buffer, blockSize, 0, 0, blockSize);
//
//                    // Get scale and offset
//                    double scale = GetScaleFactor(img.imagedata, img.width, toX, toY, domainAvg,
//                            buffer, blockSize, 0, 0, rangeAvg, blockSize);
//                    int offset = (int)(rangeAvg - scale * (double)domainAvg);
//
//                    // Get error and compare to best error so far
//                    double error = GetError(buffer, blockSize, 0, 0, domainAvg,
//                            img.imagedata, img.width, toX, toY, rangeAvg, blockSize, scale);
//
//                    if (error < bestError)
//                    {
//                        bestError = error;
//                        bestX = x;
//                        bestY = y;
//                        bestSymmetry = symmetryEnum;
//                        bestScale = scale;
//                        bestOffset = offset;
//                    }
//
//                    delete ifs;
//
//                    if (!symmetry)
//                        break;
//                }
//            }
//        }
//
//        delete []buffer;
//        buffer = NULL;
//
//        if (blockSize > 2 && bestError >= threshold)
//        {
//            // Recurse into the four corners of the current block.
//            blockSize /= 2;
//            findMatchesFor(transforms, toX, toY, blockSize);
//            findMatchesFor(transforms, toX + blockSize, toY, blockSize);
//            findMatchesFor(transforms, toX, toY + blockSize, blockSize);
//            findMatchesFor(transforms, toX + blockSize, toY + blockSize, blockSize);
//        }
//        else
//        {
//            // Use this transformation
//            transforms.push_back(
//                    new IFSTransform(
//                            bestX, bestY,
//                            toX, toY,
//                            blockSize,
//                            bestSymmetry,
//                            bestScale,
//                            bestOffset
//                    )
//            );
//
//            if (verb >= 1)
//            {
//                printf("to=(%d, %d)\n", toX, toY);
//                printf("from=(%d, %d)\n", bestX, bestY);
//                printf("best error=%lf\n", bestError);
//                printf("best symmetry=%d\n", (int)bestSymmetry);
//                printf("best offset=%d\n", bestOffset);
//                printf("best scale=%lf\n", bestScale);
//            }
//        }
//    }

}