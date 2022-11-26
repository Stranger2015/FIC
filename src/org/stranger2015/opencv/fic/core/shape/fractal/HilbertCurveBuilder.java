package org.stranger2015.opencv.fic.core.shape.fractal;

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

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.AbstractNode;
import org.stranger2015.opencv.fic.core.geom.Coordinate;
import org.stranger2015.opencv.fic.core.geom.Geometry;
import org.stranger2015.opencv.fic.core.geom.GeometryFactory;
import org.stranger2015.opencv.fic.core.geom.LineSegment;

import static org.stranger2015.opencv.fic.core.shape.fractal.HilbertCode.*;

/**
 * Generates a {@link LineString} representing the Hilbert Curve
 * at a given level.
 * <p>
 * @author Martin Davis
 * @see HilbertCode
 */
public
class HilbertCurveBuilder extends AbstractNode.GeometricShapeBuilder {

    /**
     * Creates a new instance using the provided {@link GeometryFactory}.
     *
     * @param geomFactory the geometry factory to use
     */
    public
    HilbertCurveBuilder ( GeometryFactory geomFactory ) {
        super(geomFactory);
        // use a null extent to indicate no transformation
        // (may be set by client)
        extent = null;
    }

    /**
     * Sets the level of curve to generate.
     * The level must be in the range [0 - 16].
     *
     * @param level the order of the curve
     */
    public
    void setLevel ( int level ) {
        this.numPts = size(level);
    }

    /**
     * @return
     */
    @Override
    public
    Geometry getGeometry () {
        int level = level(numPts);
        int nPts = size(level);

        double scale = 1;
        double baseX = 0;
        double baseY = 0;
        if (extent != null) {
            LineSegment baseLine = getSquareBaseLine();
            baseX = baseLine.minX();
            baseY = baseLine.minY();
            double width = baseLine.getLength();
            int maxOrdinate = maxOrdinate(level);
            scale = width / maxOrdinate;
        }

        Coordinate[] pts = new Coordinate[nPts];
        for (int i = 0; i < nPts; i++) {
            Coordinate pt = decode(level, i);
            double x = transform(pt.getX(), scale, baseX);
            double y = transform(pt.getY(), scale, baseY);
            pts[i] = new Coordinate(x, y);
        }

        return geomFactory.createLineString(pts);
    }

    /**
     * @param val
     * @param scale
     * @param offset
     * @return
     */
    @Contract(pure = true)
    private static
    double transform ( double val, double scale, double offset ) {
        return val * scale + offset;
    }

    /**
     * @return
     */
    public
    int getOrder () {
        return -1;
    }
}
