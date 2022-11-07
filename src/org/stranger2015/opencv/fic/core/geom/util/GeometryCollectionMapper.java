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

import org.stranger2015.opencv.fic.core.geom.GeometryFactory;
import org.stranger2015.opencv.fic.core.geom.util.GeometryMapper.MapOp;
import org.stranger2015.opencv.fic.core.geom.Geometry;
import org.stranger2015.opencv.fic.core.geom.GeometryCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Maps the members of a {@link GeometryCollection}
 * into another <tt>GeometryCollection</tt> via a defined
 * mapping function.
 *
 * @author Martin Davis
 *
 */
public class GeometryCollectionMapper
{
    public static
    GeometryCollection map( GeometryCollection gc, MapOp op)
    {
        var mapper = new GeometryCollectionMapper(op);
        return mapper.map(gc);
    }

    private MapOp mapOp = null;

    public GeometryCollectionMapper(MapOp mapOp) {
        this.mapOp = mapOp;
    }

    public GeometryCollection map(GeometryCollection gc)
    {
        List <Geometry <?>> mapped = new ArrayList <Object>();
        for (int i = 0; i < gc.getNumGeometries(); i++) {
            Geometry g = mapOp.map(gc.getGeometryN(i));
            if (!g.isEmpty())
                mapped.add(g);
        }
        return gc.getFactory().createGeometryCollection(
                GeometryFactory.toGeometryArray(mapped));
    }
}
