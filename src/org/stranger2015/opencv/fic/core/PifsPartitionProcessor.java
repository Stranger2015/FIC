package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.ImagePartitionProcessor;

/**
 * Whose are you?
 * Excuse me, but what meant m-m "whose"?
 * Whose slave, I asked?!
 * Excuse me, comrade artist, but I don't understand you ...
 * Oh, really stupid slave.
 * Why do you say all the time "slave" and "slave", what is this word?
 * This is from the role, the word is from the role ...
 * This role is abusive, and I ask you not to apply it to me!
 *
 * Well, we live in the wonderful house! First you were robbed, then cursed.
 * And at the same time, we are struggling for the honorary title of
 * "The house of high level culture of everyday life".
 * It's a nightmare, the nightmare indeed!
 */
public
class PifsPartitionProcessor extends ImagePartitionProcessor {

    public
    PifsPartitionProcessor ( Mat image, PartitionScheme scheme ) {
        super(image,  scheme, rangeBlocks, tree);
    }
}
