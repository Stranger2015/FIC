package org.stranger2015.opencv.fic.core.geom;

import org.stranger2015.opencv.fic.core.geom.util.GeometryCollectionMapper;
import org.stranger2015.opencv.fic.core.geom.util.GeometryMapper;
import org.stranger2015.opencv.fic.core.operation.overlay.OverlayOp;
import org.stranger2015.opencv.fic.core.operation.overlay.snap.SnapIfNeededOverlayOp;
import org.stranger2015.opencv.fic.core.operation.overlayng.OverlayNGRobust;
import org.stranger2015.opencv.fic.core.operation.union.UnaryUnionOp;

/**
 * Internal class which encapsulates the runtime switch to use OverlayNG,
 * and some additional extensions for optimization and GeometryCollection handling.
 * <p>
 * This class allows the {@link org.stranger2015.opencv.fic.core.geom.Geometry} overlay methods to be
 * switched between the original algorithm and the modern OverlayNG codebase
 * via a system property <code>jts.overlay</code>.
 * <ul>
 * <li><code>jts.overlay=old</code> - (default) use original overlay algorithm
 * <li><code>jts.overlay=ng</code> - use OverlayNG
 * </ul>
 *
 * @author mdavis
 *
 */
class GeometryOverlay
{
    public static String OVERLAY_PROPERTY_NAME = "jts.overlay";

    public static String OVERLAY_PROPERTY_VALUE_NG = "ng";
    public static String OVERLAY_PROPERTY_VALUE_OLD = "old";

    /**
     * Currently the original JTS overlay implementation is the default
     */
    public static boolean OVERLAY_NG_DEFAULT = false;

    private static boolean isOverlayNG = OVERLAY_NG_DEFAULT;

    static {
        setOverlayImpl(System.getProperty(OVERLAY_PROPERTY_NAME));
    }

    /**
     * This function is provided primarily for unit testing.
     * It is not recommended to use it dynamically, since
     * that may result in inconsistent overlay behaviour.
     *
     * @param overlayImplCode the code for the overlay method (may be null)
     */
    static void setOverlayImpl(String overlayImplCode) {
        if (overlayImplCode == null)
            return;
        // set flag explicitly since current value may not be default
        isOverlayNG = OVERLAY_NG_DEFAULT;

        if (OVERLAY_PROPERTY_VALUE_NG.equalsIgnoreCase(overlayImplCode) )
            isOverlayNG = true;
    }

    private static
    org.stranger2015.opencv.fic.core.geom.Geometry overlay( org.stranger2015.opencv.fic.core.geom.Geometry a, org.stranger2015.opencv.fic.core.geom.Geometry b, int opCode) {
        if (isOverlayNG) {
            return OverlayNGRobust.overlay(a, b, opCode);
        }
        else {
            return SnapIfNeededOverlayOp.overlayOp(a, b, opCode);
        }
    }

    static
    org.stranger2015.opencv.fic.core.geom.Geometry difference( org.stranger2015.opencv.fic.core.geom.Geometry a, org.stranger2015.opencv.fic.core.geom.Geometry b)
    {
        // special case: if A.isEmpty ==> empty; if B.isEmpty ==> A
        if (a.isEmpty()) return OverlayOp.createEmptyResult(OverlayOp.DIFFERENCE, a, b, a.getFactory());
        if (b.isEmpty()) return a.copy();

        org.stranger2015.opencv.fic.core.geom.Geometry.checkNotGeometryCollection(a);
        org.stranger2015.opencv.fic.core.geom.Geometry.checkNotGeometryCollection(b);

        return overlay(a, b, OverlayOp.DIFFERENCE);
    }

    static
    org.stranger2015.opencv.fic.core.geom.Geometry intersection( org.stranger2015.opencv.fic.core.geom.Geometry a, org.stranger2015.opencv.fic.core.geom.Geometry b)
    {
        /**
         * TODO: MD - add optimization for P-A case using Point-In-Polygon
         */
        // special case: if one input is empty ==> empty
        if (a.isEmpty() || b.isEmpty())
            return OverlayOp.createEmptyResult(OverlayOp.INTERSECTION, a, b, a.getFactory());

        // compute for GCs
        // (An inefficient algorithm, but will work)
        // TODO: improve efficiency of computation for GCs
        if (a.isGeometryCollection()) {
            final org.stranger2015.opencv.fic.core.geom.Geometry g2 = b;
            return GeometryCollectionMapper.map(
                    (GeometryCollection) a,
                    new GeometryMapper.MapOp() {
                        public
                        org.stranger2015.opencv.fic.core.geom.Geometry map( org.stranger2015.opencv.fic.core.geom.Geometry g) {
                            return g.intersection(g2);
                        }
                    });
        }

        // No longer needed since GCs are handled by previous code
        //checkNotGeometryCollection(this);
        //checkNotGeometryCollection(other);

        return overlay(a, b, OverlayOp.INTERSECTION);
    }

    static
    org.stranger2015.opencv.fic.core.geom.Geometry symDifference( org.stranger2015.opencv.fic.core.geom.Geometry a, org.stranger2015.opencv.fic.core.geom.Geometry b)
    {
        // handle empty geometry cases
        if (a.isEmpty() || b.isEmpty()) {
            // both empty - check dimensions
            if (a.isEmpty() && b.isEmpty())
                return OverlayOp.createEmptyResult(OverlayOp.SYMDIFFERENCE, a, b, a.getFactory());

            // special case: if either input is empty ==> result = other arg
            if (a.isEmpty()) return b.copy();
            if (b.isEmpty()) return a.copy();
        }

        org.stranger2015.opencv.fic.core.geom.Geometry.checkNotGeometryCollection(a);
        org.stranger2015.opencv.fic.core.geom.Geometry.checkNotGeometryCollection(b);
        return overlay(a, b, OverlayOp.SYMDIFFERENCE);
    }

    static
    org.stranger2015.opencv.fic.core.geom.Geometry union( org.stranger2015.opencv.fic.core.geom.Geometry a, org.stranger2015.opencv.fic.core.geom.Geometry b)
    {
        // handle empty geometry cases
        if (a.isEmpty() || b.isEmpty()) {
            if (a.isEmpty() && b.isEmpty())
                return OverlayOp.createEmptyResult(OverlayOp.UNION, a, b, a.getFactory());

            // special case: if either input is empty ==> other input
            if (a.isEmpty()) return b.copy();
            if (b.isEmpty()) return a.copy();
        }

        // TODO: optimize if envelopes of geometries do not intersect
        Geometry.checkNotGeometryCollection(a);
        Geometry.checkNotGeometryCollection(b);

        return overlay(a, b, OverlayOp.UNION);
    }

    static
    Geometry union( Geometry a) {
        return isOverlayNG ? OverlayNGRobust.union(a) : UnaryUnionOp.union(a);
    }
}
