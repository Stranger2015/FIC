package org.stranger2015.opencv.fic.core.operation.union;

/*
 * Copyright (c) 2019 Martin Davis.
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
import org.stranger2015.opencv.fic.utils.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Extracts atomic elements from
 * input geometries or collections,
 * recording the dimension found.
 * Empty geometries are discarded since they
 * do not contribute to the result of {@link UnaryUnionOp}.
 *
 * @author Martin Davis
 *
 */
class InputExtracter implements IGeometryFilter
{
    /**
     * Extracts elements from a collection of geometries.
     *
     * @param geoms a collection of geometries
     * @return an extracter over the geometries
     */
    public static
    InputExtracter extract( Collection<Geometry> geoms) {
        InputExtracter extracter = new InputExtracter();
        extracter.add(geoms);

        return extracter;
    }

    /**
     * Extracts elements from a geometry.
     *
     * @param geoms a geometry to extract from
     * @return an extracter over the geometry
     */
    public static
    InputExtracter extract( Geometry geom) {
        InputExtracter extracter = new InputExtracter();
        extracter.add(geom);

        return extracter;
    }

    private GeometryFactory geomFactory = null;
    private List<Polygon <T>> polygons = new ArrayList<Polygon <T>>();
    private List<LineString> lines = new ArrayList<LineString>();
    private List<Point> points = new ArrayList<Point>();

    /**
     * The default dimension for an empty GeometryCollection
     */
    private int dimension = Dimension.FALSE;

    public InputExtracter() {

    }

    /**
     * Tests whether there were any non-empty geometries extracted.
     *
     * @return true if there is a non-empty geometry present
     */
    public boolean isEmpty() {
        return polygons.isEmpty()
                && lines.isEmpty()
                && points.isEmpty();
    }

    /**
     * Gets the maximum dimension extracted.
     *
     * @return the maximum extracted dimension
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * Gets the geometry factory from the extracted geometry,
     * if there is one.
     * If an empty collection was extracted, will return <code>null</code>.
     *
     * @return a geometry factory, or null if one could not be determined
     */
    public GeometryFactory getFactory() {
        return geomFactory;
    }

    /**
     * Gets the extracted atomic geometries of the given dimension <code>dim</code>.
     *
     * @param dim the dimension of geometry to return
     * @return a list of the extracted geometries of dimension dim.
     */
    public List getExtract(int dim) {
        switch (dim) {
            case 0: return points;
            case 1: return lines;
            case 2: return polygons;
        }
        Assert.shouldNeverReachHere("Invalid dimension: "  + dim);

        return null;
    }

    private void add(Collection<Geometry> geoms) {
        for (Geometry geom : geoms) {
            add(geom);
        }
    }

    private void add(Geometry geom) {
        if (geomFactory == null) {
            geomFactory = geom.getFactory();
        }

        geom.apply(this);
    }

    @Override
    public void filter( Geometry <?> geom) {
        recordDimension( geom.getDimension() );
        if (geom instanceof GeometryCollection) {
            return;
        }
        /*
         * Don't keep empty geometries
         */
        if (geom.isEmpty()) {
            return;
        }
        if (geom instanceof Polygon) {
            polygons.add((Polygon <T>) geom);
            return;
        }
        else if (geom instanceof LineString) {
            lines.add((LineString) geom);
            return;
        }
        else if (geom instanceof Point) {
            points.add((Point) geom);
            return;
        }

        Assert.shouldNeverReachHere("Unhandled geometry type: " + geom.getGeometryType());
    }

    private void recordDimension(int dim) {
        if (dim > dimension ) {
            dimension = dim;
        }
    }
}
