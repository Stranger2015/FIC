package org.stranger2015.opencv.fic.core.geomgraph.index;

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

import org.stranger2015.opencv.fic.core.geomgraph.index.MonotoneChainEdge;
import org.stranger2015.opencv.fic.core.noding.ISegmentIntersector;

/**
 * @version 1.7
 */
public class MonotoneChain {

    MonotoneChainEdge mce;
    int chainIndex;

    public MonotoneChain(MonotoneChainEdge mce, int chainIndex) {
        this.mce = mce;
        this.chainIndex = chainIndex;
    }

    public void computeIntersections( MonotoneChain mc, ISegmentIntersector si)
    {
        this.mce.computeIntersectsForChain(chainIndex, mc.mce, mc.chainIndex, si);
    }
}
