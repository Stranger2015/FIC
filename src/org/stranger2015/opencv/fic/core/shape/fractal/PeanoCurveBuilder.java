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

import org.stranger2015.opencv.fic.core.AbstractNode;
import org.stranger2015.opencv.fic.core.geom.Coordinate;
import org.stranger2015.opencv.fic.core.geom.Geometry;
import org.stranger2015.opencv.fic.core.geom.GeometryFactory;
import org.stranger2015.opencv.fic.core.geom.LineSegment;

/**
 * Generates a {@link LineString} representing the Peano Curve
 * at a given level.
 *
 * peano(ctx, x + (2 * i1 * lg), y + (2 * i1 * lg), lg, i1, i2)
 * peano(ctx, x + ((i1 - i2 + 1) * lg), y + ((i1 + i2) * lg), lg, i1, 1 - i2)
 * peano(ctx, x + lg, y + lg, lg, i1, 1 - i2)
 * peano(ctx, x + ((i1 + i2) * lg), y + ((i1 - i2 + 1) * lg), lg, 1 - i1, 1 - i2)
 * peano(ctx, x + (2 * i2 * lg), y + ( 2 * (1-i2) * lg), lg, i1, i2)
 * peano(ctx, x + ((1 + i2 - i1) * lg), y + ((2 - i1 - i2) * lg), lg, i1, i2)
 * peano(ctx, x + (2 * (1 - i1) * lg), y + (2 * (1 - i1) * lg), lg, i1, i2)
 * peano(ctx, x + ((2 - i1 - i2) * lg), y + ((1 + i2 - i1) * lg), lg, 1 - i1, i2)
 * peano(ctx, x + (2 * (1 - i2) * lg), y + (2 * i2 * lg), lg, 1 - i1, i2)
 *
 * @author Martin Davis
 * @see PeanoCode
 */
public
class PeanoCurveBuilder extends AbstractNode.GeometricShapeBuilder {
    /**
     * Creates a new instance using the provided {@link GeometryFactory}.
     *
     * @param geomFactory the geometry factory to use
     */
    public
    PeanoCurveBuilder ( GeometryFactory geomFactory ) {
        super(geomFactory, spaceFillingCurveCode);
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
        this.numPts = spaceFillingCurveCode.size(level);
    }

    /**
     * @return
     */
    @Override
    public
    Geometry getGeometry () {
        int level = spaceFillingCurveCode.level(numPts);
        int nPts = spaceFillingCurveCode.size(level);

        double scale = 1;
        double baseX = 0;
        double baseY = 0;
        if (extent != null) {
            LineSegment baseLine = getSquareBaseLine();
            baseX = baseLine.minX();
            baseY = baseLine.minY();
            double width = baseLine.getLength();
            int maxOrdinate = spaceFillingCurveCode.maxOrdinate(level);
            scale = width / maxOrdinate;
        }

        Coordinate[] pts = new Coordinate[nPts];
        for (int i = 0; i < nPts; i++) {
            Coordinate pt = spaceFillingCurveCode.decode(level, i);
            double x = transform(pt.getX(), scale, baseX);
            double y = transform(pt.getY(), scale, baseY);
            pts[i] = new Coordinate(x, y);
        }

        return geomFactory.createLineString(pts);
    }

    private static
    double transform ( double val, double scale, double offset ) {
        return val * scale + offset;
    }

    public
    int getOrder () {
        return -1;
    }
}
