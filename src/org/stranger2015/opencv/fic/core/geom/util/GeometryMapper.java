package org.stranger2015.opencv.fic.core.geom.util;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.geom.Geometry;
import org.stranger2015.opencv.fic.core.geom.GeometryCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

/**
 * Methods to map various collections
 * of {@link Geometry}s
 * via defined mapping functions.
 *
 * @author Martin Davis
 */
public
class GeometryMapper {
    /**
     * Maps the members of a {@link Geometry}
     * (which may be atomic or composite)
     * into another <tt>Geometry</tt> of most specific type.
     * <tt>null</tt> results are skipped.
     * In the case of hierarchical {@link GeometryCollection}s,
     * only the first level of members are mapped.
     *
     * @param geom the input atomic or composite geometry
     * @param op   the mapping operation
     * @return a result collection or geometry of most specific type
     */
    public static
    Geometry <?> map ( Geometry <?> geom, GeometryMapper.MapOp op ) {
        List <Geometry <?>> mapped = range(0, geom.getNumGeometries())
                .mapToObj(i -> op.map(geom.getGeometryN(i))).filter(Objects::nonNull)
                .collect(Collectors.toList());

        return geom.getFactory().buildGeometry(mapped);
    }

    /**
     * @param geoms
     * @param op
     * @return
     */
    public static @NotNull
    Collection <Geometry <?>> map ( Collection <Geometry <?>> geoms, GeometryMapper.MapOp op ) {
        List <Geometry <?>> mapped = new ArrayList <>();
        for (Geometry <?> geom : geoms) {
            Geometry <?> gr = op.map(geom);
            if (gr != null) {
                mapped.add(gr);
            }
        }

        return mapped;
    }

    /**
     * An interface for geometry functions used for mapping.
     *
     * @author Martin Davis
     */
    public
    interface MapOp {
        /**
         * Computes a new geometry value.
         *
         * @param g the input geometry
         * @return a result geometry
         */
        Geometry<?> map ( Geometry<?> g );
    }
}
