package org.stranger2015.opencv.fic.utils;

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
import org.stranger2015.opencv.fic.core.geom.ICoordinateFilter;

import java.util.*;

/**
 *  A {@link CoordinateFilter} that extracts a unique array of <code>Coordinate</code>s.
 *  The array of coordinates contains no duplicate points.
 *  It preserves the order of the input points.
 *
 *@version 1.7
 */
public class
UniqueCoordinateArrayFilter implements ICoordinateFilter
{
    /**
     * Convenience method which allows running the filter over an array of {@link Coordinate}s.
     *
     * @param coords an array of coordinates
     * @return an array of the unique coordinates
     */
    public static Coordinate[] filterCoordinates(Coordinate[] coords)
    {
        UniqueCoordinateArrayFilter filter = new UniqueCoordinateArrayFilter();
        Arrays.stream(coords).forEachOrdered(filter::filter);

        return filter.getCoordinates();
    }

    private Set<Coordinate> coordSet = new HashSet<Coordinate>();
    // Use an auxiliary list as well in order to preserve coordinate order
    private List<Coordinate> list = new ArrayList<Coordinate>();

    public UniqueCoordinateArrayFilter() { }

    /**
     *  Returns the gathered <code>Coordinate</code>s.
     *
     *@return    the <code>Coordinate</code>s collected by this <code>CoordinateArrayFilter</code>
     */
    public Coordinate[] getCoordinates() {
        Coordinate[] coordinates = new Coordinate[list.size()];
        return (Coordinate[]) list.toArray(coordinates);
    }

    /**
     * @see CoordinateFilter#filter(Coordinate)
     */
    public void filter(Coordinate coord) {
        if (coordSet.add(coord)) {
            list.add(coord);
        }
    }
}
