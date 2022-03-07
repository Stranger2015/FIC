package org.stranger2015.opencv.fic.core.search.ga;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.core.codec.IAddress;
import org.stranger2015.opencv.fic.transform.EInterpolationType;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.utils.BitBuffer;


/**
 * @param <M>
 * @param <A>
 * @param <G>
 */
public
class BinChromosome<M extends IImage<A>, A extends Address <A>, G extends BitBuffer> extends Chromosome <M, A, G> {

    protected G genes;
    protected Address <A> address;

    /**
     * @param image
     * @param type
     * @param address
     */
    public
    BinChromosome ( M image, EInterpolationType type, BitBuffer genes, IAddress <A> address ) {
        super(image, type, address);

        this.genes = (G) genes;
    }

    /**
     * @param image
     * @param type
     * @param address
     * @param brightnessOffset
     * @param contrastScale
     * @param dihedralAffineTransformIndex
     */
    public
    BinChromosome ( M image,
                    EInterpolationType type,
                    IAddress <A> address,
                    int brightnessOffset,
                    double contrastScale,
                    int dihedralAffineTransformIndex ) {
        super(image, type, address, brightnessOffset, contrastScale, dihedralAffineTransformIndex);//todo genes
    }

    public
    BinChromosome ( @NotNull BitBuffer allocate ) {
        genes= (G) allocate;
    }

    /**
     * Get the genes as a string
     */
    @Override
    G getGenesAsG () {
        return genes;
    }

    /**
     * Copy the genes from the given chromosome over this chromosome's genes
     *
     * @param chromosome
     */
    @Override
    void copyChromGenes ( Chromosome <M, A, G> chromosome ) {

    }

    /**
     * Get the number of genes in common between this chromosome and the given chromosome
     *
     * @param chromosome
     */
    @Override
    int getNumGenesInCommon ( Chromosome <M, A, G> chromosome ) {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    Address <A> getAddress () {
        return address;
    }
}
