package org.stranger2015.opencv.fic.core.noding;

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

import org.stranger2015.opencv.fic.core.geom.Coordinate;

import static java.lang.String.format;

/**
 * Methods for computing and working with octants of the Cartesian plane
 * Octants are numbered as follows:
 * <pre>
 *  \2|1/
 * 3 \|/ 0
 * ---+--
 * 4 /|\ 7
 *  /5|6\
 * </pre>
 * If line segments lie along a coordinate axis, the octant is the lower of the two
 * possible values.
 *
 * @version 1.7
 */
public
class Octant {

    /**
     * Returns the octant of a directed line segment (specified as x and y
     * displacements, which cannot both be 0).
     */
    public static
    int octant ( double dx, double dy ) {
        if (dx == 0.0 && dy == 0.0) {
            throw new IllegalArgumentException(format("Cannot compute the octant for point ( %s, %s )", dx, dy));
        }

        double adx = Math.abs(dx);
        double ady = Math.abs(dy);

        // dy < 0
        if (dx >= 0) {
            // dy < 0
            return dy >= 0 ? adx >= ady ? 0 : 1 : adx >= ady ? 7 : 6;
        }
        else // dx < 0
            return dy >= 0 ? adx >= ady ? 3 : 2 : adx >= ady ? 4 : 5;
    }

    /**
     * Returns the octant of a directed line segment from p0 to p1.
     */
    public static
    int octant ( Coordinate p0, Coordinate p1 ) {
        double dx = p1.x - p0.x;
        double dy = p1.y - p0.y;
        if (dx == 0.0 && dy == 0.0) {
            throw new IllegalArgumentException(format("Cannot compute the octant for two identical points %s", p0));
        }

        return octant(dx, dy);
    }

    private
    Octant () {
    }
}
