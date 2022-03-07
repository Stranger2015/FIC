package org.stranger2015.opencv.fic.core;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * Step 1: Construct the domain pools Ddepth, corresponding to
 * each quad tree partition level starting from minimum
 * partitions to maximum partitions (depth=0 to max_part-min_part).
 * <p>
 * Step 2: Calculate the block pixel value difference using
 * equation (5) of all the domain blocks in each pool Ddepth.
 * <p>
 * Step  3:  Classify  and  sort  the  domains  in  each  pool  Ddepth  in
 * ascending order of the pixel value difference, and place on a
 * list structure.
 * <p>
 * Step 4: Search for a best match between a range and domain
 * belonging to the same class.
 * write_header_info; (min_part, max_part, domain_step, hsize, vsize)
 * depth=0; ec =rms_tol;
 * <p>
 * Function Quadtree(image, depth) {
 * best_rms = infinity;
 * β0 = initial value; βdepth *= 1.25;
 * While (depth < min_part) Quadtree (image, depth+1);
 * Set R1 = I2 and mark it uncovered.
 * While there are uncovered ranges Ri do {
 * //Select the domain pool list Ddepth corresponding to the
 * current range block Ri.
 * <p>
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
@Deprecated
public
class ImagePartitionProcessor<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>,
        G extends BitBuffer>
        extends ImageProcessor <N, A, M, G> {

    protected final List <M> rangeBlocks = new ArrayList <>();
    protected final List <M> domainBlocks = new ArrayList <>();

    protected final Tree <N, A, M, G> tree;

    protected int row;
    protected int col;

    /**
     * @param fn
     * @param scheme
     * @param tree
     */
    public
    ImagePartitionProcessor ( String fn, EPartitionScheme scheme, Tree <N, A, M, G> tree ) {
        super(fn, scheme, List.of(), Codec.create(scheme, new EncodeTask<N,A,M,G>(fn, scheme, List.of(), new Encoder <N, A, M, G>() {
            @Override
            protected
            ImageBlockGenerator <N, A, M, G> createBlockGenerator ( IEncoder <N, A, M, G> encoder, M image, Size rangeSize, Size domainSize ) {
                return null;
            }
        })));

        this.tree = tree;
    }

    /**
     * @param scheme
     */
    public
    ImagePartitionProcessor ( String fn, EPartitionScheme scheme ) {
        this(fn, scheme, null);
    }

//

    /**
     * image mat is of the square shape so, width and height are equal and are of power of two
     *
     * @return
     */
//    private
//    void decompose ( Tree <N, M> tree ) {
//        row = col = 0;
//        var parent = (TreeNodeBase) tree;
//
//        for (int wh = 1; wh < image.width(); wh *= 2) {
//            int i = 0;
//            tree.getRoot().getChildren().clear();
//            for (; i < 4; i++) {
//                if (currTreeNode.isLeaf()) {
//                    currTreeNode.getChildren().add(new LeafNode(parent, image, new Rect(col, row, wh, wh)));//fixme!!
//                }
//                else {
//                    currTreeNode.getChildren().add(new QuadTreeNode(parent, new Rect(0,0,0,0), nodes));
//                }
//            }
//
//            currTreeNode.set(currQuads);
//            if (Objects.equals(tree, currTreeNode)) {//fixme
//                TreeNodeBase<N> mergedLeaf = currTreeNode.merge();
//                currTreeNode.getChildren().clear();
//                currTreeNode.getChildren().add(mergedLeaf);
//                i=1;
//                continue;
//            }
//        }
//    }
//
    private
    boolean isLeaf ( TreeNode <N, A, M, G> treeNode ) {
        return treeNode.isLeaf();
    }

//    private
//    TreeNode<N, A,M> merge ( TreeNode<N, A,M> treeNode ) {
//        if (treeNode.isLeaf()) {
//            return treeNode;
//        }
//        return tree.getRoot().getChildren().get(0);
//    }

    private
    Color getColor ( M image, int i ) {
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
        return null;
    }


    /**
     * @return
     */
    public
    List <M> getRangeBlocks () {
        return rangeBlocks;
    }

    /**
     * @return
     */
    public
    List <M> getDomainBlocks () {
        return domainBlocks;
    }

    /**
     * @return
     */
    @Override
    public
    M getImage () {
        return null;
    }//todo

    /**
     * @return
     */
    @Override
    public
    EPartitionScheme getScheme () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    Codec <N, A, M, G> getCodec () {
        return null;
    }
}
