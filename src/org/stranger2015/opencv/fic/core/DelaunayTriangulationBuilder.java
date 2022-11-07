package org.stranger2015.opencv.fic.core;

/*
 * Copyright (c) 2016 Vivid Solutions.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * and Eclipse Distribution License v. 1.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v20.html
 * and the Eclipse Distribution License is available at
 *
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.tilers.DelaunayTriangularTopDownTiler;
import org.stranger2015.opencv.fic.core.geom.*;
import org.stranger2015.opencv.fic.core.triangulation.DelaunayTriangulation;
import org.stranger2015.opencv.fic.core.triangulation.IncrementalDelaunayTriangulator;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.QuadEdgeSubdivision;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.stranger2015.opencv.fic.core.geom.CoordinateArrays.BidirectionalComparator;

/**
 * A utility class which creates Delaunay Triangulations
 * from collections of points and extract the resulting
 * triangulation edges or triangles as geometries.
 *
 * @author Martin Davis
 */
public
class DelaunayTriangulationBuilder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer> {

    private final Class <? extends DelaunayTriangularTopDownTiler> clazz;

    public
    DelaunayTriangulationBuilder ( Class <? extends DelaunayTriangularTopDownTiler> clazz ) {
        this.clazz = clazz;
    }

    /**
     * Extracts the unique {@link Coordinate}s from the given {@link Geometry}.
     *
     * @param geom the geometry to extract from
     * @return a List of the unique Coordinates
     */
    public static
    CoordinateList extractUniqueCoordinates ( Geometry<?> geom ) {
        if (geom == null) {
            return new CoordinateList();
        }

        Coordinate[] coords = geom.getCoordinates();

        return unique(coords);
    }

    public static
    CoordinateList unique ( Coordinate[] coords ) {
        Coordinate[] coordsCopy = BidirectionalComparator.copyDeep(coords);
        Arrays.sort(coordsCopy);

        return new CoordinateList(coordsCopy, false);
    }

    /**
     * Converts all {@link Coordinate}s in a collection to {@link Vertex}es.
     *
     * @param coords the coordinates to convert
     * @return a List of Vertex objects
     */
    public static
    List <Vertex> toVertices ( Collection <Coordinate> coords ) {
        List <Vertex> verts = new ArrayList <>();
        for (Coordinate coord : coords) {
            verts.add(new Vertex(coord));
        }

        return verts;
    }

    /**
     * Computes the {@link Envelope} of a collection of {@link Coordinate}s.
     *
     * @param coords a List of Coordinates
     * @return the envelope of the set of coordinates
     */
    public static
    Envelope envelope ( Collection <Coordinate> coords ) {
        Envelope env = new Envelope();
        for (Coordinate coord : coords) {
            env.expandToInclude(coord);
        }

        return env;
    }

    private Collection <Coordinate> siteCoords;
    private double tolerance = 0.0;
    private DelaunayTriangulation <N, A, G> subdiv = new DelaunayTriangulation <>(
            new Envelope(), tolerance);

//    /**
//     * Creates a new triangulation builder.
//     */
//    public
//    DelaunayTriangulationBuilder ( Class <? extends DelaunayTriangularTopDownTiler> clazz ) {
//        this.clazz = clazz;
//    }

    /**
     * Sets the sites (vertices) which will be triangulated.
     * All vertices of the given geometry will be used as sites.
     *
     * @param geom the geometry from which the sites will be extracted.
     */
    public
    void setSites ( Geometry<?> geom ) {
        // remove any duplicate points (they will cause the triangulation to fail)
        siteCoords = extractUniqueCoordinates(geom);
    }

    /**
     * Sets the sites (vertices) which will be triangulated
     * from a collection of {@link Coordinate}s.
     *
     * @param coords a collection of Coordinates.
     */
    public
    void setSites ( Collection <Coordinate> coords ) {
        // remove any duplicate points (they will cause the triangulation to fail)
        siteCoords = unique(BidirectionalComparator.toCoordinateArray(coords));
    }

    /**
     * Sets the snapping tolerance which will be used
     * to improve the robustness of the triangulation computation.
     * A tolerance of 0.0 specifies that no snapping will take place.
     *
     * @param tolerance the tolerance distance to use
     */
    public
    void setTolerance ( double tolerance ) {
        this.tolerance = tolerance;
    }

    /**
     *
     */
    private
    void create () {
        if (subdiv == null) {
            Envelope siteEnv = envelope(siteCoords);
            List <Vertex> vertices = toVertices(siteCoords);
            subdiv = new DelaunayTriangulation <>(siteEnv, tolerance);
            IncrementalDelaunayTriangulator <N, A, G> triangulator = new IncrementalDelaunayTriangulator <>(subdiv);
            triangulator.insertSites(vertices);
        }
    }

    /**
     * Gets the {@link QuadEdgeSubdivision} which models the computed triangulation.
     *
     * @return the subdivision containing the triangulation
     */
    public
    QuadEdgeSubdivision getSubdivision () {
        create();

        return subdiv;
    }

    /**
     * Gets the edges of the computed triangulation as a {@link MultiLineString}.
     *
     * @param geomFact the geometry factory to use to create the output
     * @return the edges of the triangulation
     */
    public
    Geometry<?> getEdges ( GeometryFactory geomFact ) {
        create();
        return subdiv.getEdges(geomFact);
    }

    /**
     * Gets the faces of the computed triangulation as a {@link GeometryCollection}
     * of {@link Polygon}.
     *
     * @param geomFact the geometry factory to use to create the output
     * @return the faces of the triangulation
     */
    public
    Geometry<?>getTriangles ( GeometryFactory geomFact ) {
        create();

        return subdiv.getTriangles(geomFact);
    }

    List <Triangle <?>> getTriangles( CoordinateList coordinates){
        return subdiv.getTriangles(coordinates);
    }
//    public
//    Class <? extends TriangularTiler <N, A, G>> getClazz () {
//        return clazz;
//    }
}
