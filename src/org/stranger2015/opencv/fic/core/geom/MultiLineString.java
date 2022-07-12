package org.stranger2015.opencv.fic.core.geom;

import org.locationtech.jts.geom.Lineal;
import org.stranger2015.opencv.fic.core.operation.BoundaryOp;

import java.util.Arrays;

/**
 * Models a collection of {@link LineString}s.
 * <p>
 * Any collection of LineStrings is a valid MultiLineString.
 *
 * @version 1.7
 */
public
class MultiLineString
        extends GeometryCollection
        implements Lineal {
    private static final long serialVersionUID = 8166665132445433741L;

    /**
     * Constructs a <code>MultiLineString</code>.
     *
     * @param lineStrings    the <code>LineString</code>s for this <code>MultiLineString</code>
     *                       , or <code>null</code> or an empty array to create the empty geometry.
     *                       Elements may be empty <code>LineString</code>s, but not <code>null</code>
     *                       s.
     * @param precisionModel the specification of the grid of allowable points
     *                       for this <code>MultiLineString</code>
     * @param SRID           the ID of the Spatial Reference System used by this
     *                       <code>MultiLineString</code>
     * @deprecated Use GeometryFactory instead
     */
    public
    MultiLineString ( LineString[] lineStrings, PrecisionModel precisionModel, int SRID ) {
        super(lineStrings, new GeometryFactory(precisionModel, SRID));
    }


    /**
     * @param lineStrings the <code>LineString</code>s for this <code>MultiLineString</code>,
     *                    or <code>null</code> or an empty array to create the empty
     *                    geometry. Elements may be empty <code>LineString</code>s,
     *                    but not <code>null</code>s.
     */
    public
    MultiLineString ( Geometry[] lineStrings, GeometryFactory factory ) {
        super(lineStrings, factory);
    }

    public
    int getDimension () {
        return 1;
    }

    public
    int getBoundaryDimension () {
        if (isClosed()) {
            return Dimension.FALSE;
        }

        return 0;
    }

    public
    String getGeometryType () {
        return TYPENAME_MULTI_LINESTRING;
    }

    public
    boolean isClosed () {
        if (isEmpty()) {
            return false;
        }
        for (Geometry geometry : geometries) {
            if (!((LineString) geometry).isClosed()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the boundary of this geometry.
     * The boundary of a lineal geometry is always a zero-dimensional geometry (which may be empty).
     *
     * @return the boundary geometry
     * @see Geometry#getBoundary
     */
    public
    Geometry getBoundary () {
        return (new BoundaryOp(this)).getBoundary();
    }

    /**
     * MultiLineString in the reverse
     * order to this object.
     * Both the order of the component LineStrings
     * and the order of their coordinate sequences
     * are reversed.
     *
     * @return a {@link MultiLineString} in the reverse order
     */
    public
    MultiLineString reverse () {
        return (MultiLineString) super.reverse();
    }

    /**
     * @return
     */
    @Override
    protected
    MultiLineString reverseInternal () {
        LineString[] lineStrings = Arrays.stream(this.geometries).map(geometry -> (LineString) geometry.reverse())
                .toArray(LineString[]::new);

        return new MultiLineString(lineStrings, factory);
    }

    protected
    MultiLineString copyInternal () {
        LineString[] lineStrings = new LineString[this.geometries.length];
        Arrays.setAll(lineStrings, i -> (LineString) this.geometries[i].copy());

        return new MultiLineString(lineStrings, factory);
    }

    public
    boolean equalsExact ( Geometry other, double tolerance ) {
        if (isEquivalentClass(other)) {
            return false;
        }
        return super.equalsExact(other, tolerance);
    }

    @Override
    public
    void apply ( IGeometryFilter filter ) {

    }

    @Override
    public
    void apply ( IGeometryComponentFilter filter ) {

    }

    protected
    int getTypeCode () {
        return TYPECODE_MULTILINESTRING;
    }
}