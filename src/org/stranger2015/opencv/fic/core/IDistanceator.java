package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.search.EMetrics;

/**
 * This interface should be implemented by classes that implement
 * a comparison between two object and return their distance-difference.
 * Several distance metrics may be supported.
 */
public
interface IDistanceator {

    /**
     * Distance between two given objects
     *
     * @param obj1 the first object to compare to
     * @param obj2 the second object to compare with
     * @return the distance as defined by a metric between the objects
     * @see EMetrics
     */
    double distance ( final IImage image1, final IImage image2 );
}
