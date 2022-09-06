package org.stranger2015.opencv.fic.core.triangulation;

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

import org.opencv.core.MatOfInt;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.IImageBlock;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.LocateFailureException;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.QuadEdge;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.QuadEdgeSubdivision;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Collection;

/**
 * Computes a Delaunay Triangulation of a set of {@link Vertex}es, using an
 * incremental insertion algorithm.
 *
 * @author Martin Davis
 * @version 1.0
 */
public
class IncrementalDelaunayTriangulator<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        implements IDelaunayTriangulator {
    private final DelaunayTriangulation <N, A, G> subdiv;
    private final boolean isUsingTolerance;

    /**
     * Creates a new triangulator using the given {@link QuadEdgeSubdivision}.
     * The triangulator uses the tolerance of the supplied subdivision.
     *
     * @param subdiv1
     * @param subdiv
     */
    public
    IncrementalDelaunayTriangulator ( DelaunayTriangulation<N, A, G> subdiv ) {
        this.subdiv = subdiv;
        isUsingTolerance = subdiv.getTolerance() > 0.0;
    }

    /**
     * Inserts all sites in a collection. The inserted vertices <b>MUST</b> be
     * unique up to the provided tolerance value. (i.e. no two vertices should be
     * closer than the provided tolerance value). They do not have to be rounded
     * to the tolerance grid, however.
     *
     * @param vertices a Collection of Vertex
     * @throws LocateFailureException if the location algorithm fails to converge in a reasonable number of iterations
     */
    @Override
    public
    void insertSites ( Collection <Vertex> vertices ) {
        for (Vertex v : vertices) {
            insertSite(v);
        }
    }

    /**
     * Inserts a new point into a subdivision representing a Delaunay
     * triangulation, and fixes the affected edges so that the result is still a
     * Delaunay triangulation.
     * <p>
     *
     * @return a quadedge containing the inserted vertex
     */
    @Override
    public
    QuadEdge insertSite ( Vertex v ) {
        QuadEdge result;
        /*
         * This code is based on Guibas and Stolfi (1985), with minor modifications
         * and a bug fix from Dani Lischinski (Graphic Gems 1993). (The modification
         * I believe is the test for the inserted site falling exactly on an
         * existing edge. Without this test zero-width triangles have been observed
         * to be created)
         */
        QuadEdge e = subdiv.locate(v);

        if (subdiv.isVertexOfEdge(e, v)) {
            // point is already in subdivision.
            result = e;
        }
        else {
            if (subdiv.isOnEdge(e, v.getCoordinate())) {
                // the point lies exactly on an edge, so delete the edge
                // (it will be replaced by a pair of edges which have the point as a vertex)
                e = e.oPrev();
                subdiv.delete(e.oNext());
            }
            /*
             * Connect the new point to the vertices of the containing triangle
             * (or quadrilateral, if the new point fell on an existing edge.)
             */
            QuadEdge base = subdiv.makeEdge(e.orig(), v);
            QuadEdge.splice(base, e);
            QuadEdge startEdge = base;
            base = subdiv.connect(e, base.sym());
            e = base.oPrev();
            while (e.lNext() != startEdge) {
                base = subdiv.connect(e, base.sym());
                e = base.oPrev();
            }
            // Examine suspect edges to ensure that the Delaunay condition is satisfied.
            while (true) {
                QuadEdge t = e.oPrev();
                if (t.dest().rightOf(e) && v.isInCircle(e.orig(), t.dest(), e.dest())) {
                    QuadEdge.swap(e);
                    e = e.oPrev();
                }
                else if (e.oNext() == startEdge) {
                    result = base;
                    break;// no more suspect edges.
                }
                else {
                    e = e.oNext().lPrev();
                }
            }
        }

        return result;
    }

    /**
     * @return
     */
    public
    boolean isUsingTolerance () {
        return isUsingTolerance;
    }

    public
    IImageBlock <?> getImageBlock () {
        return (IImageBlock <?>) getImage();
    }

    public
    MatOfInt getMat () {
        return null;
    }

    public
    IImage <?> getImage () {
        return null;
    }
}