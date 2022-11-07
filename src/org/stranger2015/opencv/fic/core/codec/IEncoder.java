package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * 3.  PROPOSED ALGORITHM
 * The main objective of this research is to compress the image
 * with high ratio and increase the PSNR of the encoding phase
 * <p>
 * Step 1: Divide color image into sub windows. Each sub window
 * block is called R-block.
 * <p>
 * Step 2: For each R-block, find a Domain block, which most likely
 * represents current R-block after a certain transform.
 * <p>
 * Step 3:  For  every  R-block,  find  D-block  from  D-pool  which  is
 * most likely parallel to it.
 * Crop  the  D-block  size  to  the  size  of  R-block  by  Shrink
 * operation and, mark the D-block as D-block after Shrink operation is applied.
 * <p>
 * Transpose the D- block by using the following eight relative transformation equation matrices proposed by Jacquin.
 * The eight relative transformations matrices are represented in the equations as follows,
 * <p>
 * Consider T = [aij]n with n = 2 where 1 ≤ (i,j) ≤ 2,
 * <p>
 * Relative transformations matrices can be represented with the following Eq.(1) to Eq.(8),
 * T0 is aij 1 if 0
 * i j
 * else
 * T1 is aij
 * 1 if
 * 0
 * i j
 * i j
 * else
 * T2 is aij 1 if
 * 0
 * i j
 * else
 * T3 is aij
 * 1 if
 * 0
 * ji
 * i j
 * else
 * T4 is aij
 * 0
 * ij
 * i j
 * else
 * T5 is aij
 * 1 if
 * 0
 * ii
 * i j
 * else
 * T6 is aij
 * 0
 * i j
 * i j
 * else
 * T7 is aij1 if
 * 0
 * i j
 * else
 * Above eight relative transformation blocks are generated for
 * each D-block and these blocks are composite new D-pool.
 * Compare each R-block with the blocks which are in D- pool
 * and obtain nearly all like block. The parallelism can be measured
 * with normal conflict MSE using the Eq.(9).
 * where X and Y are the coefficients and should have the values for
 * making  the  MSE  minimum.  The  Xk  and  Yk  are  represented  in
 * Eq.(10) and Eq.(11) respectively,
 * Xk = 2
 * Yk = 2
 * B
 * W = {Dk(x,y), Tk, Xk, Yk} (12)
 * where  Rk  represents  the  relative  transformation  W  for  each  R-block.
 * Fractal  image  coding,  the  original  image  is  segmented  into
 * non-collaborating  regions  called  range  blocks  and  collaborating
 * regions  called  domains  blocks.  For  each  range  block  affine
 * transformations  given  in  the  Eq.(13)  is  used  to  match  as  best
 * domain block.
 * <p>
 * where  Si  controls the  contrast and  0i  controls the  brightness and
 * ai,  bi,  ci,  di,  ei,  fi  denote  the  eight  symmetries  such  as  identity,
 * reflection about mid-vertical and horizontal axis, first and second diagonal.
 * The Fig.1 is the proposed fractal image compression.
 * 1. Partition  each  plane  image  into  non-overlapping  blocks,
 * called Range or R-block.
 * 2. Select the maximum Range block of size (Rmax) as 16 or 8
 * and minimum block of size (Rmin) of 4 or 8. R-blocks are
 * compared with domains from the domain pool, which are
 * double the range size.
 * 3. A window size of α×α is slided on 3 images in α/2 or α/4.
 * The pixels are averaged in groups to reduce to the size.
 * 4. After  partitioning  and  transformation,  fractal  encoding
 * finds  suitable  candidate  from  all  blocks  to  encode  any
 * particular R block.
 * 5. To  increase  encoding  speed  classification  of  sub-image
 * into  upper  right,  upper  left,  lower  ri
 * =======================================================================================================
 * A Proposed Hybrid Fractal Image Compression Based on Graph Theory and Isosceles Triangle1 Segmentation
 * <p>
 * <b>Proposed algorithms encoding steps as following:</b>
 * <p>
 * <p>
 * Step 1: Segment the initial image with graph-based image segmentation algorithm, and set label for all pixels;
 * <p>
 * Step 2: Divide initial image into different R blocks with non-overlapping, each R block is 8*8 size, and set
 * label for each R block, classify R block based on isosceles triangle segmentation rather than rectangles;
 * <p>
 * Step 3: Divide image into different D blocks allowing some overlapping, the size is four times as R block,
 * and set label for each D block, classify D block based on isosceles triangle segmentation rather than rectangles;
 * <p>
 * Step 4: Shrink D block to R block’s size;
 * <p>
 * Step 5: Calculate D block’s variance, and for D blocks in the same class, sort them according to their variance;
 * <p>
 * Step 6: Choose one R block; calculate its variance and matching threshold;
 * <p>
 * Step 7: In the search space where R block located in, find a D block which has the closest variance to
 * current R block;
 * <p>
 * Step 8: Define a searching window; the ratio between the number of blocks which located in the searching
 * window and the number of total blocks is W%, and half of blocks in the searching window have a larger
 * variance than the D block mentioned in step 7, others have a smaller variance.
 * <p>
 * Step 9: In the searching window, after affine transformation and gray migration, find the best-match D block;
 * <p>
 * Step 10: Calculate the matching error between best-match D block and R block, if the error is larger than
 * threshold ‘bias’, divide R block into four sub-blocks, add them to the corresponding search space according
 * to their labels, and slide certain steps, divide some new D blocks, add them to corresponding search space
 * according to their labels too. If the error is smaller than threshold bias, storage affine transformation parameters;
 * now, calculate the variance of range and domain blocks by the equation below. The variance of block I is
 * defined as,
 * <p>
 * Var(I)  =  2 * 2
 * Where n is the size of the block and Xi is the pixel value of the range blocks.
 * <p>
 * Step 11: For all R blocks, repeat Step 6-10;
 * <p>
 * Step 12: Write out the compressed data in the form of a local IFS code;
 * <p>
 * Step 13: Apply a fractal compression algorithm to obtain a compressed IFS code.
 */
public
interface IEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends IImageProcessorListener <N, A, G>,
                ICodecListener <N, A, G>,
                IConstants {
    /**
     *
     */
    void initialize () throws Exception;

    /**
     * @return
     */
    default
    IImage <A> encode ( IImage <A> image ) throws Exception {
        initialize();

        return doEncode(image);
    }

    /**
     * @param image
     * @return
     */
    IImage <A> doEncode ( IImage <A> image ) throws ValueError;

    /**
     * @return
     */
//    List <RegionOfInterest <A>> segmentRegions ( IImage<A> image ) throws ValueError;

    /**
     * @param image
     * @param bounds
     * @return
     * @throws ValueError
     */
    List <RegionOfInterest <A>> segmentImage ( IImage <A> image, List <Rectangle> bounds )
            throws ValueError;

    /**
     * @param image
     * @return
     * @throws ValueError
     */
    default
    List <RegionOfInterest <A>> segmentImage ( IImage <A> image ) throws ValueError {
        return segmentImage(image, List.of());
    }

    /**
     * @param image
     * @param blockWidth
     * @param blockHeight
     * @throws ValueError
     */
    void segmentRegion ( RegionOfInterest <A> roi, int blockWidth, int blockHeight ) throws ValueError;

    /**
     * @return
     */
    default
    int getImageSizeBase () {
        return 2;
    }

    /**
     * @param image
     * @param axis
     * @return
     */
    IImage <A> flipAxis ( IImage <A> image, int axis );

    /**
     * @param image
     * @param transform
     * @return
     */
    IImage <A> randomTransform ( IImage <A> image, ImageTransform <A, G> transform );

    /**
     * @param image
     * @param transform
     * @return
     */
    IImage <A> applyTransform ( IImage <A> image, ImageTransform <A, G> transform );

    /**
     * @param image
     * @param transform
     * @return
     */
    IImage <A> applyAffineTransform ( IImage <A> image, AffineTransform <A, G> transform );

    /**
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
    List <IImageBlock <A>> generateAllTransformedBlocks ( IImage <A> image,
                                                          int sourceSize,
                                                          int destinationSize,
                                                          int step
    );

    /**
     * @return
     */
    Set <ImageTransform <A, G>> getTransforms ();

    /**
     * @return
     */
    IImage <A> getInputImage ();

    /**
     * @param tiler
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
    ImageBlockGenerator <N, A, G> createBlockGenerator (
            IPartitionProcessor <N, A, G> partitionProcessor,
            EPartitionScheme scheme,
            IEncoder <N, A, G> encoder,
            IImage <A> image,
            IIntSize rangeSize,
            IIntSize domainSize
    );

    /**
     * @return
     */
    default
    IPartitionProcessor <N, A, G> createPartitionProcessor ( ITiler <N, A, G> tiler ) {
        IPartitionProcessor <N, A, G> partitionProcessor = getPartitionProcessor();
        if (partitionProcessor == null) {
            partitionProcessor = doCreatePartitionProcessor(tiler);//fixme
        }

        return partitionProcessor;
    }

    /**
     * @return
     */
    IPartitionProcessor <N, A, G> doCreatePartitionProcessor ( ITiler <N, A, G> tiler );

    /**
     * @return
     */
    IPartitionProcessor <N, A, G> getPartitionProcessor ();

    /**
     * @return
     */
    FicFileModel <N, A, G> getModel ();

    /**
     * @param filename
     * @return
     */
    FicFileModel <N, A, G> loadModel ( String filename );

    /**
     * @param node
     */
    void add ( TreeNode <N, A, G> node );

    /**
     * @param node
     */
    void addLeafNode ( LeafNode <N, A, G> node );

    /**
     * @param node
     */
    void addLeafNode ( TreeNodeBase <N, A, G> node );

    /**
     * @return
     */
    Class <?> getTilerClass ();

    EnumSet<ESplitKind> chooseDirection ( IImageBlock <A> imageBlock );

    /**
     * @param image
     * @return
     */
    @SuppressWarnings("unchecked")
    default
    IImageBlock <A> iterateRangeBlocks ( IImageBlock <A> image, List <IImageBlock <A>> rangeBlocks ) {
        rangeBlocks.forEach(rangeBlock -> {
            int percent = 100 * (rangeBlocks.indexOf(rangeBlock) + 1) / rangeBlocks.size();
            System.err.printf("%d%%", percent);
            ImageTransform <A, G> bestTransform = ImageTransform.create(image, rangeBlock.getAddress());
            int minDistance = Integer.MAX_VALUE;
            try {
                iterateDomainBlocks((ImageBlock <A>) rangeBlock, bestTransform, minDistance);
            } catch (ValueError e) {
                throw new RuntimeException(e);
            }
            getTransforms().add(bestTransform);
        });
        outputImage.getTransforms().addAll(transforms);
//
        return (IImageBlock <A>) outputImage;
    }

    /**
     * @param rangeBlock
     * @param bestTransform
     * @param minDistance
     */
    default
    void iterateDomainBlocks ( ImageBlock <A> rangeBlock,
                               ImageTransform <A, G> bestTransform,
                               int minDistance ) throws ValueError {
//        for (ImageBlock <A> domainBlock : domainBlocks) {
            double alpha = 0;
            for (int i = 0; i < domainBlock.getWidth(); i++) {
                for (int j = 0; j < domainBlock.getHeight(); j++) {
                    double[] data = new double[4];//fixme
                    alpha += (domainBlock.get(i, j, data) - domainBlock.getMeanPixelValue());
                }
//            }

            for (int addr = 0; addr < domainBlock.getWidth(); addr++) {
                double[] data = new double[4];//fixme
                alpha += (domainBlock.get(domainBlock.getAddress().newInstance(addr), data) - domainBlock.meanPixelValue);
            }
//
            double contrast = alpha / domainBlock.beta;//byte
            int brightness = (int) (rangeBlock.meanPixelValue - contrast * domainBlock.meanPixelValue);

            for (int index = 0; index < 8; index++) {
                IImageBlock <A> transformedDomainBlock =
                        new ImageBlock <>(
                                inputImage,
                                domainBlock.getAddress(),
                                domainBlock.getSize(),
                                geometry);
                int pixelAmount = domainBlock.getWidth() * domainBlock.getWidth();
                for (int addr = 0; addr < pixelAmount; addr++) {
                    for (int y = 0; y < domainBlock.height; y++) {
                        int newAddr = addr * -1; // fixme dihedralAffineTransforms[index][0] + y * dihedralAffineTransforms[index][1];
                        int newY = x * dihedralAffineTransforms[index][2] + y * dihedralAffineTransforms[index][3];
                        if (newAddr < 0) {
                            newAddr += domainBlock.getWidth();
                        }
                        if (newY < 0) {
                            newY += domainBlock.height;
                        }
                        double newPixelValue = (short) (contrast * domainBlock.put(addr, new int[1]) + brightness);

                        double[] data = new double[4];
                        data[0]=
                                ((newPixelValue < MAX_PIXEL_VALUE) && (newPixelValue >= 0)) ?
                                        newPixelValue :
                                        (MAX_PIXEL_VALUE / 2);

                        transformedDomainBlock.put(
                                newAddr,
                                data
                        );
//                    }
                    }
                    int distance = getDistance(rangeBlock, transformedDomainBlock);
                    if ((distance < minDistance) && (distance != 0)) {
                        minDistance = distance;
                        bestTransform.dihedralAffineTransformIndex = index;
                        bestTransform.setAddress(transformedDomainBlock.getAddress());
                        bestTransform.brightnessOffset = brightness;
                        bestTransform.contrastScale = contrast;
                    }
                }
            }
        }
    }

}