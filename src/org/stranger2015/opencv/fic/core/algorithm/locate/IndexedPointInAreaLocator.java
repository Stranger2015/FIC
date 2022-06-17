package org.stranger2015.opencv.fic.core.algorithm.locate;

import org.locationtech.jts.algorithm.RayCrossingCounter;
import org.locationtech.jts.algorithm.locate.PointOnGeometryLocator;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.util.LinearComponentExtracter;
import org.locationtech.jts.index.ArrayListVisitor;
import org.locationtech.jts.index.ItemVisitor;
import org.locationtech.jts.index.intervalrtree.SortedPackedIntervalRTree;
import org.stranger2015.opencv.fic.core.geom.IPolygonal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Determines the {@link Location} of {@link Coordinate}s relative to
 * an areal geometry, using indexing for efficiency.
 * This algorithm is suitable for use in cases where
 * many points will be tested against a given area.
 * <p>
 * The Location is computed precisely, in that points
 * located on the geometry boundary or segments will
 * return {@link Location#BOUNDARY}.
 * <p>
 * {@link Polygonal} and {@link LinearRing} geometries
 * are supported.
 * <p>
 * The index is lazy-loaded, which allows
 * creating instances even if they are not used.
 * <p>
 * Thread-safe and immutable.
 *
 * @author Martin Davis
 *
 */
public class IndexedPointInAreaLocator
        implements PointOnGeometryLocator
{

    private Geometry geom;
    private volatile IntervalIndexedGeometry index = null;

    /**
     * Creates a new locator for a given {@link Geometry}.
     * {@link Polygonal} and {@link LinearRing} geometries
     * are supported.
     *
     * @param g the Geometry to locate in
     */
    public IndexedPointInAreaLocator(Geometry g)
    {
        if (! (g instanceof IPolygonal || g instanceof LinearRing))
            throw new IllegalArgumentException("Argument must be IPolygonal or LinearRing");
        geom = g;
    }

    /**
     * Determines the {@link Location} of a point in an areal {@link Geometry}.
     *
     * @param p the point to test
     * @return the location of the point in the geometry
     */
    public int locate(Coordinate p)
    {
        // avoid calling synchronized method improves performance
        if (index == null) {
            createIndex();
        }

        RayCrossingCounter rcc = new RayCrossingCounter(p);

        SegmentVisitor visitor = new SegmentVisitor(rcc);
        index.query(p.y, p.y, visitor);

    /*
     // MD - slightly slower alternative
    List segs = index.query(p.y, p.y);
    countSegs(rcc, segs);
    */

        return rcc.getLocation();
    }

    /**
     * Creates the indexed geometry, creating it if necessary.
     */
    private synchronized void createIndex() {
        if (index == null) {
            index = new IntervalIndexedGeometry(geom);
            // no need to hold onto geom
            geom = null;
        }
    }

    private static class SegmentVisitor
            implements ItemVisitor
    {
        private final RayCrossingCounter counter;

        public SegmentVisitor(RayCrossingCounter counter)
        {
            this.counter = counter;
        }

        public void visitItem(Object item)
        {
            LineSegment seg = (LineSegment) item;
            counter.countSegment(seg.getCoordinate(0), seg.getCoordinate(1));
        }
    }

    private static class IntervalIndexedGeometry
    {
        private final boolean isEmpty;
        private final SortedPackedIntervalRTree index= new SortedPackedIntervalRTree();

        public IntervalIndexedGeometry(Geometry geom)
        {
            if (geom.isEmpty())
                isEmpty = true;
            else {
                isEmpty = false;
                init(geom);
            }
        }

        private void init(Geometry geom)
        {
            List lines = LinearComponentExtracter.getLines(geom);
            for (Iterator i = lines.iterator(); i.hasNext(); ) {
                LineString line = (LineString) i.next();
                Coordinate[] pts = line.getCoordinates();
                addLine(pts);
            }
        }

        private void addLine(Coordinate[] pts)
        {
            for (int i = 1; i < pts.length; i++) {
                LineSegment seg = new LineSegment(pts[i-1], pts[i]);
                double min = Math.min(seg.p0.y, seg.p1.y);
                double max = Math.max(seg.p0.y, seg.p1.y);
                index.insert(min, max, seg);
            }
        }

        public List query(double min, double max)
        {
            if (isEmpty)
                return new ArrayList();

            ArrayListVisitor visitor = new ArrayListVisitor();
            index.query(min, max, visitor);
            return visitor.getItems();
        }

        public void query(double min, double max, ItemVisitor visitor)
        {
            if (isEmpty)
                return;
            index.query(min, max, visitor);
        }
    }
}