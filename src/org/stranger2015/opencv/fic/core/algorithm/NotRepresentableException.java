package org.stranger2015.opencv.fic.core.algorithm;

/**
 * Indicates that a {@link HCoordinate} has been computed which is
 * not representable on the Cartesian plane.
 *
 * @version 1.7
 * @see HCoordinate
 */
public class NotRepresentableException extends Exception {

    /**
     *
     */
    public NotRepresentableException() {
        super("Projective point not representable on the Cartesian plane.");
    }

}
