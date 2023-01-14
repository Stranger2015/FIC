package org.stranger2015.opencv.fic.core.codec.classifiers;

import org.stranger2015.opencv.fic.core.codec.tilers.ImageBlockInfo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.ref.SoftReference;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Collections;
import java.util.Set;

import static java.nio.channels.FileChannel.MapMode.READ_WRITE;
import static java.util.Objects.requireNonNull;
import static org.stranger2015.opencv.fic.core.codec.classifiers.ECategory.UNCLASSIFIED;

/**
 * We now propose a procedure for the construction of a and for the directed search of the transformation pool
 * resulting global pool XX The quantization of the parameters of the transformations, as adopted in the following,
 * is dictated by storage requirements. The description of a block transformation should be kept as simple as
 * possible in order to obtain low bit rates. Note that the contractivities of the transformations can be obtained
 * by multiplying together the contractivities of the elementary transformations which compose them
 * For every range block p R, in the original image, encoding procedure consists, according to the nature of the block,
 * of one of the following.
 * <p>
 * Case 1: p1R, is a shade block. (smooth)
 * <p>
 * We simply approximate p1R, by a uniformly gray block, with gray level equal to the average gray level of plRR ,
 * which is denoted FIR. The transformation 7, which generates this uniform block is simply the absorption at g=*p1Rt.
 * -
 * <p>
 * Case 2: ji1R, is a midrange or an edge block.
 * <p>
 * Every element p1D, of the domain pool of the same type as the block plR , is scanned.
 * The analysis of a pair ( pR, p1D) determines the selection of the transformation 7, used to encode p1R.
 * <p>
 * Case 2.a: plR, is a midrange.
 * <p>
 * For midrange blocks, we restrict our attention to massic transformations that are compositions
 * of a constant scaling and a luminance shift, of the form:
 * <p>
 * q( TplD ) = ai( qplD ) + Agi
 * <p>
 * where ai is a contrast scaling factor which is allowed to take values in the set { 0.7, 0.8, 0.9, 1.O },
 * and Ag; is computed so that the average gray levels of the range block and the scaled domain block are
 * approximately the same, i.e.:
 * q ( TplD ) = ai ( qplD ) + Agi
 * <p>
 * where CY, is an upper bound for scaling factors. It is then quantized to the nearest value in the set
 * { 0.5, 0.6, 0.7, 0.8, 0.9, 1.O }.
 * <p>
 * Secondly, Ag, is computed so that the gray levels of either the dark regions, or the bright regions
 * (depending on the dominant population) of pYE, and qp?bl have the same intensity.
 * <p>
 * Thirdly, the one among the eight isometries { L }, <, which minimizes the distortion measure is selected.
 * <p>
 * The analysis of the pair ( p1R, qp1D ), and the related construction of a candidate massic transformation
 * q are done for every domain pD, in the domain pool.
 * The domain block p1 which minimize the distortion d( p1R0q( p1D))
 * are finally selected. The transformed domain block 7,( p1D ) is called the matching block for p1R.
 * <p>
 * Case 2. b: p1R, is an edge.
 * <p>
 * The analysis of the pair ( p1R, p1D) consists of a crude segmentation of the blocks p1R, and yp1D, based on
 * the computation of histograms of gray levels [5], [7]. We assume that image blocks of sufficiently small dimension
 * can be segmented into two uniform regions, one dark, one bright, separated by a transition region.
 * The segmented blocks are denoted pyi , and qpyfjl , respectively. After this segmentation is performed, we compute
 * the dynamic range of the segmented blocks as the gray level difference between the bright and dark regions.
 * We use massic transformations that are compositions of a contrast scaling, a luminance shift, and an isometry, of
 * the form:
 * and q ( YplDl ) = ltr, ( a! ( qplD ) + 'gi ).
 * 7,(Drn>C a m .
 * <p>
 * Firstly, a is computed so that pyi, and qpp, have the same dynamic range:
 * <p>
 * We now propose a procedure for the construction of a and for the directed search of the transformation pool
 * re
 */
public
class ImageBlockClassifier implements IClassifier {
    private final MappedByteBuffer buffer;
    protected Set <IClassifiable> categories;

    /**
     * @param level2Classes
     * @throws IOException
     */
    public
    ImageBlockClassifier ( SoftReference<ImageBlockInfo>[] level2Classes ) throws IOException {
        File file = File.createTempFile("mmap", null);
        file.deleteOnExit();
        FileChannel fileChannel;
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            for ( SoftReference<ImageBlockInfo> level2Class : level2Classes) {
                raf.writeInt(requireNonNull(level2Class.get()).getIndex());
                raf.writeDouble(requireNonNull(level2Class.get()).getPixelSum());
            }

            fileChannel = raf.getChannel();
        }
        buffer = fileChannel.map(READ_WRITE, 0, fileChannel.size());
    }

    /**
     * @return
     */
    @Override
    public
    Set <IClassifiable> classify () {
        ECategory category = UNCLASSIFIED;
        switch (category) {
            case SMOOTH:

                break;
            case MIDRANGE:

                break;
            case SIMPLE_EDGE:

                break;
            case COMPLEX_EDGE:

                break;
            case UNCLASSIFIED:

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + category);
        }

        return Collections.singleton(category);
    }

    /**
     * @return
     */
    public
    Set <IClassifiable> getCategories () {
        return categories;
    }

    /**
     * @return
     */
    public
    MappedByteBuffer getBuffer () {
        return buffer;
    }
}
