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

/**
 * An interface for classes which represent a sequence of contiguous line segments.
 * SegmentStrings can carry a context object, which is useful
 * for preserving topological or parentage information.
 *
 * @version 1.7
 */
public
interface ISegmentString {
    /**
     * Gets the user-defined data for this segment string.
     *
     * @return the user-defined data
     */
    Object getData ();

    /**
     * Sets the user-defined data for this segment string.
     *
     * @param data an Object containing user-defined data
     */
    void setData ( Object data );

    int size ();

    Coordinate getCoordinate ( int i );

    Coordinate[] getCoordinates ();

    boolean isClosed ();
}
