package org.stranger2015.opencv.fic.core;

import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.PrecisionModel;
import org.opencv.core.Rect;

/**
 *
 */
public
class Hexagon extends Polygon implements IShape {
    public final static double THREE_ROOT = 1.732f;
//    private LinearRing side;
    double side;
    int radius;
    /**
     * Constructs a <code>Polygon</code> with the given exterior boundary.
     *
     * @param shell          the outer boundary of the new <code>Polygon</code>,
     *                       or <code>null</code> or an empty <code>LinearRing</code> if the empty
     * @param precisionModel the specification of the grid of allowable points
     * @param SRID           the ID of the Spatial Reference System used by this
     *                       <code>Polygon</code>
     * @deprecated Use GeometryFactory instead
     */
    public
    Hexagon ( LinearRing shell, PrecisionModel precisionModel, int SRID ) {
        super(shell, precisionModel, SRID);
    }

    /**
     * @return
     */
    @Override
    public
    double area () {
//        return 3 * THREE_ROOT * shellenvelope.getWidth() / 2f;
        return 0;
    }

    /**
     * @param image
     * @param rect
     */
    @Override
    public
    void draw ( Image image, Rect rect ) {
            Polygon polygon=new Polygon(side);
    }
}
