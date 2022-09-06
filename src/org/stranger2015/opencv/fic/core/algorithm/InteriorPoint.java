package org.stranger2015.opencv.fic.core.algorithm;


import org.locationtech.jts.geom.GeometryFilter;
import org.stranger2015.opencv.fic.core.algorithm.InteriorPointArea;
import org.stranger2015.opencv.fic.core.algorithm.InteriorPointLine;
import org.stranger2015.opencv.fic.core.algorithm.InteriorPointPoint;
import org.stranger2015.opencv.fic.core.algorithm.construct.LargestEmptyCircle;
import org.stranger2015.opencv.fic.core.algorithm.construct.MaximumInscribedCircle;
import org.stranger2015.opencv.fic.core.geom.Coordinate;
import org.stranger2015.opencv.fic.core.geom.Geometry;
import org.stranger2015.opencv.fic.core.geom.GeometryCollection;

/**
 * Computes an interior point of a <code>{@link Geometry}</code>.
 * An interior point is guaranteed to lie in the interior of the Geometry,
 * if it possible to calculate such a point exactly.
 * Otherwise, the point may lie on the boundary of the geometry.
 * For collections the interior point is computed for the collection of
 * non-empty elements of highest dimension.
 * The interior point of an empty geometry is <code>null</code>.
 *
 * <h2>Algorithm</h2>
 * The point is chosen to be "close to the center" of the geometry.
 * The location depends on the dimension of the input:
 *
 * <ul>
 * <li><b>Dimension 2</b> - the interior point is constructed in the middle of the longest interior segment
 * of a line bisecting the area.
 *
 * <li><b>Dimension 1</b> - the interior point is the interior or boundary vertex closest to the centroid.
 *
 * <li><b>Dimension 0</b> - the point is the point closest to the centroid.
 * </ul>
 *
 * @see Centroid
 * @see MaximumInscribedCircle
 * @see LargestEmptyCircle
 */
public
class InteriorPoint {

    /**
     * Computes a location of an interior point in a {@link Geometry}.
     * Handles all geometry types.
     *
     * @param geom a geometry in which to find an interior point
     * @return the location of an interior point,
     * or <code>null</code> if the input is empty
     */
    public static
    Coordinate getInteriorPoint ( Geometry geom ) {
        if (geom.isEmpty()) {
            return null;
        }

        Coordinate interiorPt = null;
        //int dim = geom.getDimension();
        int dim = effectiveDimension(geom);
        // this should not happen, but just in case...
        if (dim < 0) {

            return null;
        }
        if (dim == 0) {
            interiorPt = InteriorPointPoint.getInteriorPoint(geom);
        }
        else if (dim == 1) {
            interiorPt = InteriorPointLine.getInteriorPoint(geom);
        }
        else {
            interiorPt = InteriorPointArea.getInteriorPoint(geom);
        }

        return interiorPt;
    }

    private static
    int effectiveDimension ( Geometry geom ) {
        InteriorPoint.EffectiveDimensionFilter dimFilter = new InteriorPoint.EffectiveDimensionFilter();
        geom.apply(dimFilter);

        return dimFilter.getDimension();
    }

    private static
    class EffectiveDimensionFilter implements GeometryFilter {
        private int dim = -1;

        public
        int getDimension () {
            return dim;
        }

        public
        void filter ( Geometry elem ) {
            if (elem instanceof GeometryCollection)
                return;
            if (!elem.isEmpty()) {
                int elemDim = elem.getDimension();
                if (elemDim > dim) dim = elemDim;
            }
        }
    }
}
