package org.stranger2015.opencv.fic.core.geom;

import org.stranger2015.opencv.fic.core.geom.Dimension;
import org.stranger2015.opencv.fic.core.geom.GeometryComponentFilter;
import org.stranger2015.opencv.fic.core.geom.LineString;
import org.stranger2015.opencv.fic.core.operation.BoundaryOp;
import org.stranger2015.opencv.fic.core.algorithm.PrecisionModel;
import org.stranger2015.opencv.fic.core.operation.Lineal;

/**
 * Models a collection of {@link LineString}s.
 * <p>
 * Any collection of LineStrings is a valid MultiLineString.
 *
 *@version 1.7
 */
public class MultiLineString
        extends GeometryCollection
        implements Lineal
{
    private static final long serialVersionUID = 8166665132445433741L;
    /**
     *  Constructs a <code>MultiLineString</code>.
     *
     *@param  lineStrings     the <code>LineString</code>s for this <code>MultiLineString</code>
     *      , or <code>null</code> or an empty array to create the empty geometry.
     *      Elements may be empty <code>LineString</code>s, but not <code>null</code>
     *      s.
     *@param  precisionModel  the specification of the grid of allowable points
     *      for this <code>MultiLineString</code>
     *@param  SRID            the ID of the Spatial Reference System used by this
     *      <code>MultiLineString</code>
     * @deprecated Use GeometryFactory instead
     */
    public MultiLineString( LineString[] lineStrings, PrecisionModel precisionModel, int SRID) {
        super(lineStrings, new GeometryFactory(precisionModel, SRID));
    }



    /**
     * @param lineStrings
     *            the <code>LineString</code>s for this <code>MultiLineString</code>,
     *            or <code>null</code> or an empty array to create the empty
     *            geometry. Elements may be empty <code>LineString</code>s,
     *            but not <code>null</code>s.
     */
    public MultiLineString(LineString[] lineStrings, GeometryFactory factory) {
        super(lineStrings, factory);
    }

    public int getDimension() {
        return 1;
    }

    public int getBoundaryDimension() {
        if (isClosed()) {
            return Dimension.FALSE;
        }

        return 0;
    }

    public String getGeometryType() {
        return Geometry.TYPENAME_MULTILINESTRING;
    }

    public boolean isClosed() {
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
    Geometry getBoundary()
    {
        return (new BoundaryOp(this)).getBoundary();
    }

    /**
     * Creates a {@link org.stranger2015.opencv.fic.core.geom.MultiLineString} in the reverse
     * order to this object.
     * Both the order of the component LineStrings
     * and the order of their coordinate sequences
     * are reversed.
     *
     * @return a {@link org.stranger2015.opencv.fic.core.geom.MultiLineString} in the reverse order
     */
    public
    org.stranger2015.opencv.fic.core.geom.MultiLineString reverse() {
        return (org.stranger2015.opencv.fic.core.geom.MultiLineString) super.reverse();
    }

    protected
    org.stranger2015.opencv.fic.core.geom.MultiLineString reverseInternal() {
        LineString[] lineStrings = new LineString[this.geometries.length];
        for (int i = 0; i < lineStrings.length; i++) {
            lineStrings[i] = (LineString) this.geometries[i].reverse();
        }
        return new org.stranger2015.opencv.fic.core.geom.MultiLineString(lineStrings, factory);
    }

    protected
    MultiLineString copyInternal() {
        LineString[] lineStrings = new LineString[this.geometries.length];
        for (int i = 0; i < lineStrings.length; i++) {
            lineStrings[i] = (LineString) this.geometries[i].copy();
        }
        return new MultiLineString(lineStrings, factory);
    }

    public boolean equalsExact(Geometry other, double tolerance) {
        if (isEquivalentClass(other)) {
            return false;
        }
        return super.equalsExact(other, tolerance);
    }

    @Override
    public
    void apply ( GeometryFilter filter ) {

    }

    @Override
    public
    void apply ( GeometryComponentFilter filter ) {

    }

    protected int getTypeCode() {
        return Geometry.TYPECODE_MULTILINESTRING;
    }
}