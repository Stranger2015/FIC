package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * Schematic diagram of SABVR method for FIC with the following steps;
 * <p>
 *
 * i. Partition the input grey image into hexagon shape units as Rgi.
 *
 * ii. Partition the input image obtaining Dmj blocks for 343 and 49 overlapping hexagonal units of Rgj.
 *
 * iii. Compute average intensity value for each Dm with 7 clustered hexagonal Rg unit resulting a new
 *
 * cluster of Dmj represented by AgDm.
 *
 * iv. Select the initial 10 Dm having minimum distance and offset as per equation 7 and 8 from 34zzDm pool.
 *
 * v. Generate a code book(Cb) match with Rg.Dm = [Ag (Rgi)-Ag (Dmj)], for initial 10 Dm
 *
 *  vi. Use median to find Dm blocks Cb from Rg found in earlier step v
 *
 * vii. For each and Cbk, compute offset and scaling coefficients using affine transforms of translation
 * and rotation from table 1 using the least square technique.
 *
 * viii. Compute C contrast scaling B offset brightness for quantified new Dm pool
 *
 * ix. Find MSE using equation 3 from all Cb s with minimum error identified as min E[(Rgi),(Cbk) ]
 *
 * x. Changing Rg size in iterations of 7 ,49 and 343 repeat steps i to x
 *
 * xi. Generate Cb s of current Rg with variation in C and B coefficients by index identification for 3
 * iterations to obtain the optimum Cb block as SABVR compressed image.
 * International Journal of Advanced Science and Technology
 *
 * xii. Calculate the PSNR and CR by equation 9 and 10 for the reconstructed image by inverse mapping
 * B & C coefficients.
 *
 * xiii. Compute the average C.T by taking mean time of each iteration required in mapping Dm-Rg as
 * compression time elapsed for proposed SABVR method.
 * PS
 *
 * A Spiral Architecture Based Variable Range Fractal Image Compression Method
 *
 * @param <N>
 * @param <M>
 * @param <A>
 */
public
class SabVrEncoder<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>, G extends BitBuffer>
        extends SaEncoder <N, A, M, G> {

    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    public
    SabVrEncoder ( M inputImage, Size rangeSize, Size domainSize ) {
        super(inputImage, rangeSize, domainSize);
    }

    @Override
    public
    ImageBlockGenerator <N, A, M, G> createBlockGenerator ( IEncoder <N, A, M, G> encoder,
                                                              M image,
                                                              Size rangeSize,
                                                              Size domainSize ) {

        return new SaImageBlockGenerator <>(encoder, image, rangeSize, domainSize);
    }

    /**
     * @return
     */
    @Override
    public
    int getImageSizeBase () {
        return 7;
    }
}
