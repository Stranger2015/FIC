package org.stranger2015.opencv.fic.core.geom;

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

import org.jetbrains.annotations.Contract;

/**
 * Indicates an invalid or inconsistent topological situation encountered during processing
 *
 * @version 1.8
 */
public class TopologyException
        extends RuntimeException
{
    /**
     * @param msg
     * @param pt
     * @return
     */
    @Contract(pure = true)
    private static String msgWithCoord( String msg , Coordinate pt) {
        String result = msg;
        if (pt != null) {
            result = String.format("%s [ %s ]", msg, pt);
        }

        return result;
    }

    private Coordinate pt = null;

    /**
     * @param msg
     */
    public TopologyException(String msg)
    {
        super(msg);
    }

    /**
     * @param msg
     * @param pt
     */
    public TopologyException(String msg, Coordinate pt)
    {
        super(msgWithCoord(msg, pt));
        this.pt = new Coordinate(pt);
    }

    /**
     * @return
     */
    public
    Coordinate getCoordinate() { return pt; }
}
