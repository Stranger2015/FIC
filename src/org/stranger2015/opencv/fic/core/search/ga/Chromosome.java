package org.stranger2015.opencv.fic.core.search.ga;

import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.transform.EInterpolationType;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * Chromosome is the abstract base class for all chromosomes. It defines each chromosome's
 * genes, fitness, fitness rank, and provides simple methods for copying and returning
 * chromosome values as strings.
 * <p>
 * ChromChars, ChromStrings, and ChromFloat both extend Chromosome and model individual candidate
 * solutions. You will probably never need to subclass these classes.
 *
 * @author Jeff Smith jeff@SoftTechDesign.com
 */
public abstract
class Chromosome</*M extends IImage<A>,*/ A extends IAddress <A>, G extends BitBuffer>
        extends ImageTransform <A, G> {

    /**
     * absolute (not relative) fitness value
     */
    protected double fitness;

    /**
     * 0 = worst fit, PopDim = best fit
     */
    protected int fitnessRank;

    /**
     * @param image
     * @param type
     * @param address
     */
    public
    Chromosome ( M image, EInterpolationType type, IAddress <A> address ) {
        super(image, type, address);
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
    Chromosome ( M image,
                 EInterpolationType type,
                 IAddress <A> address,
                 int brightnessOffset,
                 double contrastScale,
                 int dihedralAffineTransformIndex ) {

        super(image,
                type,
                address,
                brightnessOffset,
                contrastScale,
                dihedralAffineTransformIndex);
    }

    protected
    Chromosome () throws ValueError {
        super();
    }

    /**
     * Get the genes as a string
     */
    abstract
    G getGenesAsG ();

    /**
     * Copy the genes from the given chromosome over this chromosome's genes
     */
    abstract
    void copyChromGenes ( Chromosome <A, G> chromosome );

    /**
     * Get the number of genes in common between this chromosome and the given chromosome
     */
    abstract
    int getNumGenesInCommon ( Chromosome <A, G> chromosome );
}

