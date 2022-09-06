package org.stranger2015.opencv.fic.core.operation.distance;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.geom.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A ConnectedElementPointFilter extracts a single point
 * from each connected element in a Geometry
 * (e.g. a polygon, linestring or point)
 * and returns them in a list. The elements of the list are
 * {@link org.locationtech.jts.operation.distance.GeometryLocation}s.
 * Empty geometries do not provide a location item.
 *
 * @version 1.7
 */
public
class ConnectedElementLocationFilter
        implements IGeometryFilter {

    /**
     * Returns a list containing a point from each Polygon, LineString, and Point
     * found inside the specified geometry. Thus, if the specified geometry is
     * not a GeometryCollection, an empty list will be returned. The elements of the list
     * are {@link org.locationtech.jts.operation.distance.GeometryLocation}s.
     */
    public static
    List <GeometryLocation> getLocations ( Geometry geom ) {
        List <GeometryLocation> locations = Collections.unmodifiableList(new ArrayList <>());
        geom.apply(new ConnectedElementLocationFilter(locations));

        return locations;
    }

    private final List <GeometryLocation> locations;

    /**
     * @param locations
     */
    @Contract(pure = true)
    ConnectedElementLocationFilter ( List <GeometryLocation> locations ) {
        this.locations = locations;
    }

    public
    void filter ( Geometry geom ) {
        // empty geometries do not provide a location
        if (geom.isEmpty()) {
            return;
        }
        if (geom instanceof Point || geom instanceof LineString || geom instanceof Polygon) {
            locations.add(new GeometryLocation(geom, 0, geom.getCoordinate()));
        }
    }
}

