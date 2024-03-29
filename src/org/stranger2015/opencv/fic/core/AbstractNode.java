package org.stranger2015.opencv.fic.core;

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

import  org.locationtech.jts.index.strtree.Boundable;
import org.locationtech.jts.util.Assert;
import org.stranger2015.opencv.fic.core.geom.*;
import org.stranger2015.opencv.fic.core.shape.fractal.SpaceFillingCurveCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A node of an {@link AbstractSTRtree}. A node is one of:
 * <ul>
 * <li>empty
 * <li>an <i>interior node</i> containing child {@link org.locationtech.jts.index.strtree.AbstractNode}s
 * <li>a <i>leaf node</i> containing data items ({@link ItemBoundable}s).
 * </ul>
 * A node stores the bounds of its children, and its level within the index tree.
 *
 * @version 1.8
 */
public abstract class AbstractNode implements Boundable, Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6493722185909573708L;

    private List<Boundable> childBoundables = new ArrayList<>();
    private Object bounds = null;
    private int level;

    /**
     * Default constructor required for serialization.
     */
    public AbstractNode() {
    }

    /**
     * Constructs an AbstractNode at the given level in the tree
     * @param level 0 if this node is a leaf, 1 if a parent of a leaf, and so on; the
     * root node will have the highest level
     */
    public AbstractNode(int level) {
        this.level = level;
    }

    /**
     * Returns either child {@link AbstractNode}s, or if this is a leaf node, real data (wrapped
     * in {@link ItemBoundable}s).
     *
     * @return a list of the children
     */
    public List<Boundable> getChildBoundables() {
        return childBoundables;
    }

    /**
     * Returns a representation of space that encloses this Boundable,
     * preferably not much bigger than this Boundable's boundary yet fast to
     * test for intersection with the bounds of other Boundables. The class of
     * object returned depends on the subclass of AbstractSTRtree.
     *
     * @return an Envelope (for STRtrees), an Interval (for SIRtrees), or other
     *         object (for other subclasses of AbstractSTRtree)
     * @see AbstractSTRtree.IntersectsOp
     */
    protected abstract Object computeBounds();

    /**
     * Gets the bounds of this node
     *
     * @return the object representing bounds in this index
     */
    public Object getBounds() {
        if (bounds == null) {
            bounds = computeBounds();
        }
        return bounds;
    }

    /**
     * Returns 0 if this node is a leaf, 1 if a parent of a leaf, and so on; the
     * root node will have the highest level
     *
     * @return the node level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the count of the {@link Boundable}s at this node.
     *
     * @return the count of boundables at this node
     */
    public int size()
    {
        return childBoundables.size();
    }

    /**
     * Tests whether there are any {@link Boundable}s at this node.
     *
     * @return true if there are boundables at this node
     */
    public boolean isEmpty()
    {
        return childBoundables.isEmpty();
    }

    /**
     * Adds either an AbstractNode, or if this is a leaf node, a data object
     * (wrapped in an ItemBoundable)
     *
     * @param childBoundable the child to add
     */
    public void addChildBoundable(Boundable childBoundable) {
        Assert.isTrue(bounds == null);
        childBoundables.add(childBoundable);
    }

    void setChildBoundables(List<Boundable> childBoundables)
    {
        this.childBoundables = childBoundables;
    }

    /**
     *
     */
    public abstract static
    class GeometricShapeBuilder {

        protected Envelope extent = new Envelope(0, 1, 0, 1);
        protected int numPts = 0;
        protected GeometryFactory geomFactory;
        protected
        SpaceFillingCurveCode spaceFillingCurveCode;

        /**
         * @param geomFactory
         */
        public
        GeometricShapeBuilder ( GeometryFactory geomFactory, SpaceFillingCurveCode spaceFillingCurveCode ) {
            this.geomFactory = geomFactory;
            this.spaceFillingCurveCode = spaceFillingCurveCode;
        }

        /**
         * @param extent
         */
        public
        void setExtent ( Envelope extent ) {
            this.extent = extent;
        }

        /**
         * @return
         */
        public
        Envelope getExtent () {
            return extent;
        }

        /**
         * @return
         */
        public
        Coordinate getCentre () {
            return extent.centre();
        }

        /**
         * @return
         */
        public
        double getDiameter () {
            return Math.min(extent.getHeight(), extent.getWidth());
        }

        /**
         * @return
         */
        public
        double getRadius () {
            return getDiameter() / 2;
        }

        /**
         *
         * @return
         */
        public
        LineSegment getSquareBaseLine () {
            double radius = getRadius();

            Coordinate centre = getCentre();
            Coordinate p0 = new Coordinate(centre.x - radius, centre.y - radius);
            Coordinate p1 = new Coordinate(centre.x + radius, centre.y - radius);

            return new LineSegment(p0, p1);
        }

        /**
         * @return
         */
        public
        Envelope getSquareExtent () {
            double radius = getRadius();

            Coordinate centre = getCentre();

            return new Envelope(centre.x - radius, centre.x + radius,
                    centre.y - radius, centre.y + radius);
        }

        /**
         * Sets the total number of points in the created {@link Geometry}.
         * The created geometry will have no more than this number of points,
         * unless more are needed to create a valid geometry.
         */
        public
        void setNumPoints ( int numPts ) {
            this.numPts = numPts;
        }

        /**
         * @return
         */
        public abstract
        Geometry getGeometry ();

        /**
         * @param x
         * @param y
         * @return
         */
        protected
        Coordinate createCoord ( double x, double y ) {
            Coordinate pt = new Coordinate(x, y);
            geomFactory.getPrecisionModel().makePrecise(pt);

            return pt;
        }
    }
}
