package org.stranger2015.opencv.fic.core.io;


import org.stranger2015.opencv.fic.core.geom.CoordinateSequenceComparator;
import org.stranger2015.opencv.fic.core.geom.IGeometryComponentFilter;
import org.stranger2015.opencv.fic.core.geom.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Models a collection of {@link Polygon}s.
 * <p>
 * As per the OGC SFS specification,
 * the Polygons in a MultiPolygon may not overlap,
 * and may only touch at single points.
 * This allows the topological point-set semantics
 * to be well-defined.
 *
 * @version 1.7
 */
public
class MultiPolygon<T extends MultiPolygon<T>>//fixme
        extends GeometryCollection
        implements IPolygonal {

    private static final long serialVersionUID = -551033529766975875L;

    /**
     * Constructs a <code>MultiPolygon</code>.
     *
     * @param polygons       the <code>Polygon</code>s for this <code>MultiPolygon</code>
     *                       , or <code>null</code> or an empty array to create the empty geometry.
     *                       Elements may be empty <code>Polygon</code>s, but not <code>null</code>
     *                       s. The polygons must conform to the assertions specified in the <A
     *                       HREF="http://www.opengis.org/techno/specs.htm">OpenGIS Simple Features
     *                       Specification for SQL</A> .
     * @param precisionModel the specification of the grid of allowable points
     *                       for this <code>MultiPolygon</code>
     * @param SRID           the ID of the Spatial Reference System used by this
     *                       <code>MultiPolygon</code>
     * @deprecated Use GeometryFactory instead
     */
    public
    MultiPolygon ( Polygon <T>[] polygons, PrecisionModel precisionModel, int SRID ) {
        this(polygons, new GeometryFactory(precisionModel, SRID));
    }


    /**
     * @param polygons the <code>Polygon</code>s for this <code>MultiPolygon</code>,
     *                 or <code>null</code> or an empty array to create the empty
     *                 geometry. Elements may be empty <code>Polygon</code>s, but
     *                 not <code>null</code>s. The polygons must conform to the
     *                 assertions specified in the <A
     *                 HREF="http://www.opengis.org/techno/specs.htm">OpenGIS Simple
     *                 Features Specification for SQL</A>.
     */
    public
    MultiPolygon ( Polygon <T>[] polygons, GeometryFactory factory ) {
        super(polygons, factory);
    }

    public
    int getDimension () {
        return 2;
    }

    public
    int getBoundaryDimension () {
        return 1;
    }

    public
    String getGeometryType () {
        return Geometry.TYPENAME_MULTIPOLYGON;
    }

    /**
     * Computes the boundary of this geometry
     *
     * @return a lineal geometry (which may be empty)
     * @see Geometry#getBoundary
     */
    public
    Geometry getBoundary () {
        if (isEmpty()) {
            return getFactory().createMultiLineString();
        }
        List <LineString> allRings = new ArrayList <>();
        for (Geometry geometry : geometries) {
            Polygon <T> polygon = (Polygon <T>) geometry;
            Geometry rings = polygon.getBoundary();
            IntStream.range(0, rings.getNumGeometries()).
                    <LineString>mapToObj(rings::getGeometryN)
                    .forEachOrdered(allRings::add);
        }
        LineString[] allRingsArray = new LineString[allRings.size()];
        return getFactory().createMultiLineString(allRings.toArray(allRingsArray));
    }

    public
    boolean equalsExact ( Geometry other, double tolerance ) {
        if (!isEquivalentClass(other)) {
            return false;
        }
        return super.equalsExact(other, tolerance);
    }

    @Override
    public
    void apply ( IGeometryComponentFilter filter ) {

    }

    /**
     * Creates a {@link MultiPolygon} with
     * every component reversed.
     * The order of the components in the collection are not reversed.
     *
     * @return a MultiPolygon in the reverse order
     */
    public
    Geometry <?> reverse () {
        return (MultiPolygon) super.reverse();
    }

    @Override
    protected
    Geometry <?> reverseInternal () {
        Polygon <T>[] polygons = new Polygon <T>[this.geometries.length];
        Arrays.setAll(polygons, this::apply);
        return new MultiPolygon<>(polygons, factory);
    }

    protected
    MultiPolygon copyInternal () {
        Polygon <T>[] polygons = new Polygon <T>[this.geometries.length];
        for (int i = 0; i < polygons.length; i++) {
            polygons[i] = (Polygon <T>) this.geometries[i].copy();
        }
        return new MultiPolygon(polygons, factory);
    }

    @Override
    protected
    <T extends Geometry> int compareToSameClass ( T o ) {
        return 0;
    }

    @Override
    protected
    <T extends Geometry> int compareToSameClass ( T o, CoordinateSequenceComparator comp ) {
        return 0;
    }

    protected
    Geometry.EType getTypeCode () {
        return Geometry.TYPECODE_MULTIPOLYGON;
    }

    private
    Polygon <T> apply ( int i ) {
        return (Polygon <T>) this.geometries[i].reverse();
    }
}


