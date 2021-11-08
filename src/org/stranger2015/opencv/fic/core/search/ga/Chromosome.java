package org.stranger2015.opencv.fic.core.search.ga;

import io.jenetics.BitChromosome;

public
class Chromosome extends BitChromosome {

    public
    Chromosome ( byte[] bits, int start, int end ) {
        super(bits, start, end);
    }
}
