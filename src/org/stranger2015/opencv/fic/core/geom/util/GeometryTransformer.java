package org.stranger2015.opencv.fic.core.geom.util;
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

import org.stranger2015.opencv.fic.core.geom.*;
import org.stranger2015.opencv.fic.core.io.MultiPoint;
import org.stranger2015.opencv.fic.core.io.MultiPolygon;

import java.util.ArrayList;
import java.util.List;

/**
 * A framework for processes which transform an input {@link Geometry} into
 * an output {@link Geometry}, possibly changing its structure and type(s).
 * This class is a framework for implementing subclasses
 * which perform transformations on
 * various different Geometry subclasses.
 * It provides an easy way of applying specific transformations
 * to given geometry types, while allowing unhandled types to be simply copied.
 * Also, the framework ensures that if subcomponents change type
 * the parent geometries types change appropriately to maintain valid structure.
 * Subclasses will override whichever <code>transformX</code> methods
 * they need to to handle particular Geometry types.
 * <p>
 * A typically usage would be a transformation class that transforms <tt>Polygons</tt> into
 * <tt>Polygons</tt>, <tt>LineStrings</tt> or <tt>Points</tt>, depending on the geometry of the input
 * (For instance, a simplification operation).
 * This class would likely need to override the {@link #transformMultiPolygon(MultiPolygon, Geometry)}
 * method to ensure that if input Polygons change type the result is a <tt>GeometryCollection</tt>,
 * not a <tt>MultiPolygon</tt>.
 * <p>
 * The default behaviour of this class is simply to recursively transform
 * each Geometry component into an identical object by deep copying down
 * to the level of, but not including, coordinates.
 * <p>
 * All <code>transformX</code> methods may return <code>null</code>,
 * to avoid creating empty or invalid geometry objects. This will be handled correctly
 * by the transformer.   <code>transform<i>XXX</i></code> methods should always return valid
 * geometry - if they cannot do this they should return <code>null</code>
 * (for instance, it may not be possible for a transformLineString implementation
 * to return at least two points - in this case, it should return <code>null</code>).
 * The {@link #transform(Geometry)} method itself will always
 * return a non-null Geometry object (but this may be empty).
 *
 * @version 1.8
 *
 * @see GeometryEditor
 */
public class GeometryTransformer
{
    /**
     * Possible extensions:
     * getParent() method to return immediate parent e.g. of LinearRings in Polygons
     */

    private Geometry inputGeom;

    protected GeometryFactory factory = null;

    // these could eventually be exposed to clients
    /**
     * <code>true</code> if empty geometries should not be included in the result
     */
    private final boolean pruneEmptyGeometry = true;

    /**
     * <code>true</code> if a homogenous collection result
     * from a {@link GeometryCollection} should still
     * be a general GeometryCollection
     */
    private final boolean preserveGeometryCollectionType = true;

    /**
     * <code>true</code> if the output from a collection argument should still be a collection
     */
    private final boolean preserveCollections = false;

    /**
     * <code>true</code> if the type of the input should be preserved
     */
    private final boolean preserveType = false;

    public GeometryTransformer() {
    }

    /**
     * Utility function to make input geometry available
     *
     * @return the input geometry
     */
    public Geometry getInputGeometry() { return inputGeom; }

    public final Geometry transform(Geometry inputGeom)
    {
        this.inputGeom = inputGeom;
        this.factory = inputGeom.getFactory();

        if (inputGeom instanceof Point) {
            return transformPoint((Point) inputGeom, null);
        }
        if (inputGeom instanceof MultiPoint) {
            return transformMultiPoint((MultiPoint) inputGeom, null);
        }
        if (inputGeom instanceof LinearRing) {
            return transformLinearRing((LinearRing) inputGeom, null);
        }
        if (inputGeom instanceof LineString) {
            return transformLineString((LineString) inputGeom, null);
        }
        if (inputGeom instanceof MultiLineString) {
            return transformMultiLineString((MultiLineString) inputGeom, null);
        }
        if (inputGeom instanceof Polygon) {
            return transformPolygon((Polygon <T>) inputGeom, null);
        }
        if (inputGeom instanceof MultiPolygon) {
            return transformMultiPolygon((MultiPolygon) inputGeom, null);
        }
        if (inputGeom instanceof GeometryCollection) {
            return transformGeometryCollection((GeometryCollection) inputGeom, null);
        }

        throw new IllegalArgumentException("Unknown Geometry subtype: " + inputGeom.getClass().getName());
    }

    /**
     * Convenience method which provides standard way of
     * creating a {@link ICoordinateSequence}
     *
     * @param coords the coordinate array to copy
     * @return a coordinate sequence for the array
     */
    protected final
    ICoordinateSequence createCoordinateSequence( Coordinate[] coords)
    {
        return factory.getCoordinateSequenceFactory().create(coords);
    }

    /**
     * Convenience method which provides a standard way of copying {@link ICoordinateSequence}s
     * @param seq the sequence to copy
     * @return a deep copy of the sequence
     */
    protected final
    ICoordinateSequence copy( ICoordinateSequence seq)
    {
        return seq.copy();
    }

    /**
     * Transforms a {@link ICoordinateSequence}.
     * This method should always return a valid coordinate list for
     * the desired result type.  (E.g. a coordinate list for a LineString
     * must have 0 or at least 2 points).
     * If this is not possible, return an empty sequence -
     * this will be pruned out.
     *
     * @param coords the coordinates to transform
     * @param parent the parent geometry
     * @return the transformed coordinates
     */
    protected
    ICoordinateSequence transformCoordinates( ICoordinateSequence coords, Geometry parent)
    {
        return copy(coords);
    }

    protected Geometry transformPoint(Point geom, Geometry parent) {
        return factory.createPoint(
                transformCoordinates(geom.getCoordinateSequence(), geom));
    }

    protected Geometry transformMultiPoint(MultiPoint geom, Geometry parent) {
        List transGeomList = new ArrayList();
        for (int i = 0; i < geom.getNumGeometries(); i++) {
            Geometry transformGeom = transformPoint((Point) geom.getGeometryN(i), geom);
            if (transformGeom == null) continue;
            if (transformGeom.isEmpty()) continue;
            transGeomList.add(transformGeom);
        }
        if (transGeomList.isEmpty()) {
            return factory.createMultiPoint();
        }
        return factory.buildGeometry(transGeomList);
    }

    /**
     * Transforms a LinearRing.
     * The transformation of a LinearRing may result in a coordinate sequence
     * which does not form a structurally valid ring (i.e. a degenerate ring of 3 or fewer points).
     * In this case a LineString is returned.
     * Subclasses may wish to override this method and check for this situation
     * (e.g. a subclass may choose to eliminate degenerate linear rings)
     *
     * @param geom the ring to simplify
     * @param parent the parent geometry
     * @return a LinearRing if the transformation resulted in a structurally valid ring
     * @return a LineString if the transformation caused the LinearRing to collapse to 3 or fewer points
     */
    protected Geometry transformLinearRing(LinearRing geom, Geometry parent) {
        ICoordinateSequence seq = transformCoordinates(geom.getCoordinateSequence(), geom);
        if (seq == null)
            return factory.createLinearRing((ICoordinateSequence) null);
        int seqSize = seq.size();
        // ensure a valid LinearRing
        if (seqSize > 0 && seqSize < 4 && ! preserveType)
            return factory.createLineString(seq);
        return factory.createLinearRing(seq);
    }

    /**
     * Transforms a {@link LineString} geometry.
     *
     * @param geom
     * @param parent
     * @return
     */
    protected Geometry transformLineString(LineString geom, Geometry parent) {
        // should check for 1-point sequences and downgrade them to points
        return factory.createLineString(
                transformCoordinates(geom.getCoordinateSequence(), geom));
    }

    protected Geometry transformMultiLineString(MultiLineString geom, Geometry parent) {
        List transGeomList = new ArrayList();
        for (int i = 0; i < geom.getNumGeometries(); i++) {
            Geometry transformGeom = transformLineString((LineString) geom.getGeometryN(i), geom);
            if (transformGeom == null) continue;
            if (transformGeom.isEmpty()) continue;
            transGeomList.add(transformGeom);
        }
        if (transGeomList.isEmpty()) {
            return factory.createMultiLineString();
        }
        return factory.buildGeometry(transGeomList);
    }

    protected Geometry transformPolygon( Polygon <T> geom, Geometry parent) {
        boolean isAllValidLinearRings = true;
        Geometry shell = transformLinearRing(geom.getExteriorRing(), geom);

        // handle empty inputs, or inputs which are made empty
        boolean shellIsNullOrEmpty = shell == null || shell.isEmpty();
        if (geom.isEmpty() && shellIsNullOrEmpty ) {
            return factory.createPolygon();
        }

        if (shellIsNullOrEmpty || ! (shell instanceof LinearRing))
            isAllValidLinearRings = false;

        ArrayList holes = new ArrayList();
        for (int i = 0; i < geom.getNumInteriorRing(); i++) {
            Geometry hole = transformLinearRing(geom.getInteriorRingN(i), geom);
            if (hole == null || hole.isEmpty()) {
                continue;
            }
            if (! (hole instanceof LinearRing))
                isAllValidLinearRings = false;

            holes.add(hole);
        }

        if (isAllValidLinearRings)
            return factory.createPolygon((LinearRing) shell,
                    (LinearRing[]) holes.toArray(new LinearRing[] {  }));
        else {
            List components = new ArrayList();
            if (shell != null) components.add(shell);
            components.addAll(holes);
            return factory.buildGeometry(components);
        }
    }

    protected Geometry transformMultiPolygon(MultiPolygon geom, Geometry parent) {
        List transGeomList = new ArrayList();
        for (int i = 0; i < geom.getNumGeometries(); i++) {
            Geometry transformGeom = transformPolygon((Polygon <T>) geom.getGeometryN(i), geom);
            if (transformGeom == null) continue;
            if (transformGeom.isEmpty()) continue;
            transGeomList.add(transformGeom);
        }
        if (transGeomList.isEmpty()) {
            return factory.createMultiPolygon();
        }
        return factory.buildGeometry(transGeomList);
    }

    protected Geometry transformGeometryCollection(GeometryCollection geom, Geometry parent) {
        List transGeomList = new ArrayList();
        for (int i = 0; i < geom.getNumGeometries(); i++) {
            Geometry transformGeom = transform(geom.getGeometryN(i));
            if (transformGeom == null) continue;
            if (pruneEmptyGeometry && transformGeom.isEmpty()) continue;
            transGeomList.add(transformGeom);
        }
        if (preserveGeometryCollectionType)
            return factory.createGeometryCollection(GeometryFactory.toGeometryArray(transGeomList));
        return factory.buildGeometry(transGeomList);
    }

    public
    boolean isPreserveCollections () {
        return preserveCollections;
    }
}
