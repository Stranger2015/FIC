package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.io.IOException;
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
 * A Proposed Hybrid Fractal Image Compression Based on Graph Theory and Isosceles Triangle Segmentation
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
interface IEncoder
        extends IImageProcessorListener,
                ICodecListener,
                IConstants,
                ICompositeEncoder {
    /**
     *
     */
    void initialize () throws Exception;

    /**
     * @return
     */
    default
    void encode ( IImage image ) throws Exception {
        assert image != null : "Cannot compress null image";
        initialize();

        doEncode(image);
    }

    ICompressedImage getOutputImage ();

    /**
     * @param image
     */
    void doEncode ( IImage image ) throws Exception;

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
    IImage flipAxis ( IImage image, int axis );

    /**
     * @param image
     * @param transform
     * @return
     */
    IImage randomTransform ( IImage image, ImageTransform transform );

    /**
     * @param image
     * @param transform
     * @return
     */
    IImage applyTransform ( IImage image, ImageTransform transform );

    /**
     * @param image
     * @param transform
     * @return
     */
    IImage applyAffineTransform ( IImage image, AffineTransform transform );

    /**
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
    List <IImageBlock> generateAllTransformedBlocks (
            IImage image,
            int sourceSize,
            int destinationSize,
            int step

    ) throws ValueError;

    /**
     * @return
     */
    Set <ImageTransform> getTransforms ();

    /**
     * @return
     */
    IImage getInputImage ();

    /**
     * @param tiler
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
    ImageBlockGenerator <?> createBlockGenerator (
            IPartitionProcessor partitionProcessor,
            EPartitionScheme scheme,
            IEncoder encoder,
            IImage image,
            IIntSize rangeSize,
            IIntSize domainSize
    );

    /**
     * @return
     */
    default
    IPartitionProcessor createPartitionProcessor ( ITiler tiler ) {
        IPartitionProcessor partitionProcessor = getPartitionProcessor();
        if (partitionProcessor == null) {
            partitionProcessor = doCreatePartitionProcessor(tiler);
        }

        return partitionProcessor;
    }

    /**
     * @return
     */
    IPartitionProcessor doCreatePartitionProcessor ( ITiler tiler );

    /**
     * @return
     */
    IPartitionProcessor getPartitionProcessor ();

    /**
     * @return
     */
    FCImageModel getModel ();

    /**
     * @param filename
     * @return
     */
    FCImageModel loadModel ( String filename ) throws ValueError, IOException;

    /**
     * @param node
     */
    void add ( TreeNode <?> node );

    /**
     * @param node
     */
    void addLeafNode ( LeafNode <?> node );

    /**
     * @return
     */
    Class <?> getTilerClass ();

    /**
     * @param imageBlock
     * @return
     */
    EnumSet <ESplitKind> chooseDirection ( IImageBlock imageBlock );

    void segmentImage ( IImageBlock roi, int blockWidth, int blockHeight ) throws ValueError;

    // progress report
    // int percent = 100 * (rangeBlocks.indexOf(rangeBlock) + 1) / rangeBlocks.size();
    //System.err.printf("%d%%", percent);

    /**
     * @param image
     * @return
     */
    @SuppressWarnings("unchecked")
    default
    IImageBlock iterateRangeBlocks ( IImageBlock roi, List <IImageBlock> rangeBlocks ) throws ValueError {
        int channels = roi.getChannelsAmount();
        int[] minDistance = new int[channels];

        for (IImageBlock rangeBlock : rangeBlocks) {
            for (int c = 0; c < channels; c++) {
                minDistance[c] = 0;
            }
            ImageTransform bestTransform = ImageTransform.create((IImage) roi, rangeBlock.getAddress(0, 0));
            try {
                iterateDomainBlocks(
                        rangeBlock,
                        getDomainBlocks(),
                        minDistance,
                        bestTransform
                );
            } catch (ValueError e) {
                throw new RuntimeException(e);
            }
            getTransforms().add(bestTransform);
        }
        getOutputImage().setTransforms((List <ImageTransform>) getTransforms());

        return (IImageBlock) getOutputImage();
    }

    /**
     * @return
     */
    List <IImageBlock> getRangeBlocks ();

    /**
     * @return
     */
    List <IImageBlock> getDomainBlocks ();

    /**
     * @param rangeBlock
     * @param bestTransform
     */
    //        ImageBlock  domainBlock = (ImageBlock ) rangeBlock.resize(2);
    default
    void iterateDomainBlocks ( IImageBlock rangeBlock,
                               List <IImageBlock> domainBlocks,
                               int[] minDistance,
                               ImageTransform bestTransform )
            throws ValueError {

        for (IImageBlock domainBlock : domainBlocks) {
            int channels = domainBlock.getChannelsAmount();
            double[] alpha = new double[channels];
            double[] newPixelValue = new double[channels];
            int c = 0;
            for (; c < channels; c++) {
                alpha[c] = 0;
                for (int i = 0; i < domainBlock.getWidth(); i++) {
                    for (int j = 0; j < domainBlock.getHeight(); j++) {
                        alpha[c] += (domainBlock.pixelValues(i, j)[c] - domainBlock.getMeanPixelValue()[c])
                                * (rangeBlock.pixelValues(i, j)[c] - rangeBlock.getMeanPixelValue()[c]);
                    }
                }
                double contrast = alpha[c] / domainBlock.getBeta();
                int brightness = (int) (rangeBlock.getMeanPixelValue()[c] -
                        contrast * domainBlock.getMeanPixelValue()[c]
                );

                for (int indx = 0; indx < 8; indx++) {
                    IImageBlock transformedDomainBlock = new ImageBlock(
                            domainBlock,
                            domainBlock.getX(),
                            domainBlock.getY(),
                            domainBlock.getWidth(),
                            domainBlock.getHeight()
                    );

                    for (int x = 0; x < domainBlock.getWidth(); x++) {
                        for (int y = 0; y < domainBlock.getHeight(); y++) {
                            int newX = x * dihedralAffineTransforms[indx][0] + y * dihedralAffineTransforms[indx][1];
                            int newY = x * dihedralAffineTransforms[indx][2] + y * dihedralAffineTransforms[indx][3];
                            if (newX < 0) {
                                newX += domainBlock.getWidth();
                            }
                            if (newY < 0) {
                                newY += domainBlock.getHeight();
                            }
                            newPixelValue[c] = (contrast * domainBlock.pixelValues(x, y)[c] + brightness);
                            if (newPixelValue[c] < MAX_PIXEL_CHANNEL_VALUE && newPixelValue[c] >= 0) {
                                transformedDomainBlock.pixelValues(newX, newY)[c] = newPixelValue[c];
                            }
                            else {
                                transformedDomainBlock.pixelValues(newX, newY)[c] = MAX_PIXEL_CHANNEL_VALUE / 2;
                            }
                        }
                    }
                    int[] distance = getDistance(rangeBlock, transformedDomainBlock);

                    if ((distance[c] < minDistance[c]) && (distance[c] != 0)) {
                        minDistance[c] = distance[c];

                        bestTransform.dihedralAffineTransformIndex = indx;
                        bestTransform.setOriginalDomainX(domainBlock.getX());
                        bestTransform.setOriginalDomainY(domainBlock.getY());
                        bestTransform.brightnessOffset = brightness;
                        bestTransform.contrastScale = contrast;
                    }
                }
            }
        }
    }

    /**
     * @return
     */
    Set <IImageFilter> getFilters ();

    /**
     * @param range
     * @param domain
     * @return
     */
    default
    int[] getDistance ( IImageBlock rangeBlock, IImageBlock domainBlock ) {
        int channels = rangeBlock.getChannelsAmount();
        double[] error = new double[channels];
        int[] dist = new int[channels];
        for (int c = 0; c < channels; c++) {
            error[c] = 0;
            for (int i = 0; i < rangeBlock.getWidth(); i++) {
                for (int j = 0; j < rangeBlock.getHeight(); j++) {
                    error[c] += Math.pow(rangeBlock.pixelValues(i, j)[c] - domainBlock.pixelValues(i, j)[c], 2);
                }
            }
            error[c] /= rangeBlock.getWidth() * rangeBlock.getHeight();
            dist[c] = (int) error[c];
        }

        return dist;
    }

    /**
     * @return
     */
    List <Class <ITiler>> getAllowableSubtilers ();

    /**
     * @param tiler
     */
    void addAllowableSubtiler ( Class <ITiler> tilerClass );
}