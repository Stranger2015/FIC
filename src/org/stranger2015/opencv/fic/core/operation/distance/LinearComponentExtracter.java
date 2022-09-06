package org.stranger2015.opencv.fic.core.operation.distance;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.geom.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Extracts all the 1-dimensional ({@link LineString}) components from a {@link Geometry}.
 * For polygonal geometries, this will extract all the component {@link LinearRing}s.
 * If desired, <code>LinearRing</code>s can be forced to be returned as <code>LineString</code>s.
 *
 * @version 1.7
 */
public
class LinearComponentExtracter implements IGeometryComponentFilter {
    /**
     * Extracts the linear components from a single {@link Geometry}
     * and adds them to the provided {@link Collection}.
     *
     * @param geoms the collection of geometries from which to extract linear components
     * @param lines the collection to add the extracted linear components to
     * @return the collection of linear components (LineStrings or LinearRings)
     */
    public static
    Collection <LineString> getLines ( Collection <Geometry> geoms, Collection <LineString> lines ) {
        for (Geometry g : geoms) {
            getLines(g, lines);
        }

        return lines;
    }

    /**
     * Extracts the linear components from a single {@link Geometry}
     * and adds them to the provided {@link Collection}.
     *
     * @param geoms             the Collection of geometries from which to extract linear components
     * @param lines             the collection to add the extracted linear components to
     * @param forceToLineString true if LinearRings should be converted to LineStrings
     * @return the collection of linear components (LineStrings or LinearRings)
     */
    public static
    Collection <LineString> getLines ( Collection <Geometry> geoms,
                                       Collection <LineString> lines,
                                       boolean forceToLineString ) {
        for (Geometry g : geoms) {
            getLines(g, lines, forceToLineString);
        }

        return lines;
    }

    /**
     * Extracts the linear components from a single {@link Geometry}
     * and adds them to the provided {@link Collection}.
     *
     * @param geom  the geometry from which to extract linear components
     * @param lines the Collection to add the extracted linear components to
     * @return the Collection of linear components (LineStrings or LinearRings)
     */
    @Contract("_, _ -> param2")
    public static
    Collection <LineString> getLines ( Geometry geom, Collection <LineString> lines ) {
        if (geom instanceof LineString) {
            lines.add((LineString) geom);
        }
        else {
            geom.apply(new LinearComponentExtracter(lines));
        }

        return lines;
    }

    /**
     * Extracts the linear components from a single {@link Geometry}
     * and adds them to the provided {@link Collection}.
     *
     * @param geom              the geometry from which to extract linear components
     * @param lines             the Collection to add the extracted linear components to
     * @param forceToLineString true if LinearRings should be converted to LineStrings
     * @return the Collection of linear components (LineStrings or LinearRings)
     */
    @Contract("_, _, _ -> param2")
    public static
    Collection<LineString> getLines ( Geometry geom, Collection<LineString> lines, boolean forceToLineString ) {
        geom.apply(new LinearComponentExtracter(lines, forceToLineString));

        return lines;
    }

    /**
     * Extracts the linear components from a single geometry.
     * If more than one geometry is to be processed, it is more
     * efficient to create a single {@link org.locationtech.jts.geom.util.LinearComponentExtracter} instance
     * and pass it to multiple geometries.
     *
     * @param geom the geometry from which to extract linear components
     * @return the list of linear components
     */
    public static
    List<LineString> getLines ( Geometry geom ) {
        return getLines(geom, false);
    }

    /**
     * Extracts the linear components from a single geometry.
     * If more than one geometry is to be processed, it is more
     * efficient to create a single {@link org.locationtech.jts.geom.util.LinearComponentExtracter} instance
     * and pass it to multiple geometries.
     *
     * @param geom              the geometry from which to extract linear components
     * @param forceToLineString true if LinearRings should be converted to LineStrings
     * @return the list of linear components
     */
    public static
    List<LineString> getLines ( Geometry geom, boolean forceToLineString ) {
        List<LineString> lines = new ArrayList<>();
        geom.apply(new LinearComponentExtracter(lines, forceToLineString));

        return lines;
    }

    /**
     * Extracts the linear components from a single {@link Geometry}
     * and returns them as either a {@link LineString} or {@link MultiLineString}.
     *
     * @param geom the geometry from which to extract
     * @return a linear geometry
     */
    public static
    Geometry getGeometry ( Geometry geom ) {
        return geom.getFactory().buildGeometry(getLines(geom));
    }

    /**
     * Extracts the linear components from a single {@link Geometry}
     * and returns them as either a {@link LineString} or {@link MultiLineString}.
     *
     * @param geom              the geometry from which to extract
     * @param forceToLineString true if LinearRings should be converted to LineStrings
     * @return a linear geometry
     */
    public static
    Geometry getGeometry ( Geometry geom, boolean forceToLineString ) {
        return geom.getFactory().buildGeometry(getLines(geom, forceToLineString));
    }

    private final Collection<LineString> lines;
    private boolean isForcedToLineString = false;

    /**
     * Constructs a LineExtracterFilter with a list in which to store LineStrings found.
     */
    public
    LinearComponentExtracter ( Collection<LineString> lines ) {
        this.lines = lines;
    }

    /**
     * Constructs a LineExtracterFilter with a list in which to store LineStrings found.
     */
    public
    LinearComponentExtracter ( Collection<LineString> lines, boolean isForcedToLineString ) {
        this.lines = lines;
        this.isForcedToLineString = isForcedToLineString;
    }

    /**
     * Indicates that LinearRing components should be
     * converted to pure LineStrings.
     *
     * @param isForcedToLineString true if LinearRings should be converted to LineStrings
     */
    public
    void setForceToLineString ( boolean isForcedToLineString ) {
        this.isForcedToLineString = isForcedToLineString;
    }

    /**
     * @param geom
     */
    @Override
    public
    void filter ( Geometry geom ) {
        if (isForcedToLineString && geom instanceof LinearRing) {
            LineString line = geom.getFactory().createLineString(((LinearRing) geom).getCoordinateSequence());
            lines.add(line);

        }
        else {// if not being forced, and this is a linear component
            if (geom instanceof LineString)
                lines.add((LineString) geom);// else this is not a linear component, so skip it
        }

    }
}