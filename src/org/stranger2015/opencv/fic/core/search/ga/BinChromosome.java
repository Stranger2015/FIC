package org.stranger2015.opencv.fic.core.search.ga;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.transform.EInterpolationType;


/**
 * @param
 * @param <G>
 */
public
class BinChromosome extends Chromosome {

    protected C genes;
    protected IAddress address;

    /**
     * @param image
     * @param type
     * @param address
     */
    public
    BinChromosome ( IImage image, EInterpolationType type, IAddress address ) {
        super(image, type, address);

//        this.genes = ( ) genes;
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
    BinChromosome ( IImage image,
                    EInterpolationType type,
                    IAddress address,
                    int brightnessOffset,
                    double contrastScale,
                    int dihedralAffineTransformIndex ) {
        super(
                image,
                type,
                address,
                brightnessOffset,
                contrastScale,
                dihedralAffineTransformIndex);//todo genes
    }

    public
    BinChromosome () throws ValueError {
        super();
        genes = (C) allocate;
    }

    /**
     * Get the genes as a string
     */
    @Override
    Chromosome getGenesAsG () {
        return genes;
    }

    /**
     * Copy the genes from the given chromosome over this chromosome's genes
     *
     * @param chromosome
     */
    @Override
    void copyChromGenes ( Chromosome chromosome ) {

    }

    /**
     * Get the number of genes in common between this chromosome and the given chromosome
     *
     * @param chromosome
     */
    @Override
    int getNumGenesInCommon ( Chromosome chromosome ) {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    IAddress getAddress () {
        return address;
    }
}
