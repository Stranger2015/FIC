package org.stranger2015.opencv.fic.core.triangulation;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.opencv.core.Core;
import org.opencv.core.MatOfDouble;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.Triangulator;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.core.codec.ImageBlockGenerator;
import org.stranger2015.opencv.fic.core.codec.RegionOfInterest;
import org.stranger2015.opencv.fic.core.geom.Coordinate;
import org.stranger2015.opencv.fic.core.geom.Geometry;
import org.stranger2015.opencv.fic.core.geom.GeometryFactory;
import org.stranger2015.opencv.fic.core.geom.Triangle;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.QuadEdge;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.QuadEdgeSubdivision;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Iterator;

/**
 * function Bowyer Watson (pointList)
 * // pointList is a set of coordinates defining the points to be triangulated
 * triangulation := empty triangle mesh data structure.
 * Add super-triangle to triangulation // must be large enough to completely contain all the points in pointList
 * for each point in pointList do // add all the points one at a time to the triangulation
 * badTriangles := empty set
 * for each triangle in triangulation do // first find all the triangles that are no longer valid due to the insertion
 * if point is inside circumcircle of triangle
 * add triangle to badTriangles
 * polygon := empty set
 * for each triangle in badTriangles do // find the boundary of the polygonal hole
 * for each edge in triangle do
 * if edge is not shared by any other triangles in badTriangles
 * add edge to polygon
 * for each triangle in badTriangles do // remove them from the data structure
 * remove triangle from triangulation
 * for each edge in polygon do // re-triangulate the polygonal hole
 * newTri := form a triangle from edge to point
 * add newTri to triangulation
 * for each triangle in triangulation // done inserting points, now clean up
 * if triangle contains a vertex from original super-triangle
 * remove triangle from triangulation
 * return triangulation
 * <p>
 * parameters of split-and-merge algorithm:
 * a: grey level variance and
 * b: the mean value.
 *
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class IncrementalDelaunayTriangulator<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Triangulator <N, A, G> {

    private final ImageBlockGenerator <N, A, G> imageBlockGenerator;
    private final RegionOfInterest <A> roi;
    private final QuadEdgeSubdivision subdiv;

    public final GeometryFactory geometryFact = new GeometryFactory();
    private boolean converges = true;
    private int grayLevel;

    /**
     * @return
     */
    public
    double getThresholdE () {
        return thresholdE;
    }

    /**
     * T threshold
     * <p>
     * gray level mean of a pixel
     *
     * @return
     */
    public
    double getThresholdT () {
        return thresholdT;
    }

    protected final double thresholdE;
    protected final double thresholdT;
    protected final double triangleSize;

    /**
     *
     */
    @Contract(pure = true)
    public
    IncrementalDelaunayTriangulator (
            ImageBlockGenerator <N, A, G> imageBlockGenerator,
            RegionOfInterest <A> roi,
            @NotNull QuadEdgeSubdivision subdiv,
            double thresholdE,
            double thresholdT,
            double triangleSize ) {

        this.imageBlockGenerator = imageBlockGenerator;
        this.roi = roi;

        this.subdiv = subdiv;
        this.thresholdE = thresholdE;
        this.thresholdT = thresholdT;
        this.triangleSize = triangleSize;

        initialize();
    }

    /**
     * @param triangle
     * @return
     */
    public
    boolean isHomogeneous ( Triangle triangle ) throws ValueError {
        IImage <?> image = triangle.getImage();
        double[][] result = meanStdDev(image.getPixels());
        triangle.setStdDev(result[0][0]);
        triangle.setSize(triangle.getSize());
        triangle.setGrayLevelMean(result[1][0]);

        return result[0][0] < thresholdE && triangle.getSize() < thresholdT;
    }

    /**
     *
     */
    public
    void createLattice () {
        Vertex[] pointSet = imageBlockGenerator.generatePointSet(roi, 4, 4);
        for (Vertex vertex : pointSet) {
            subdiv.insertSite(vertex);
        }
    }

    /**
     *
     */
    public
    void initialize () {
        createLattice();
    }

    /**
     *
     */
    public
    void split () throws ValueError {
        Iterator <Triangle> iterator = subdiv.getTriangleIterator();
        do {
            splitStep1();
            splitStep2(iterator);

        } while (!converges());
    }

    /**
     *
     */
    public
    void mergeStep3 () {

        for (; ; ) {
            Geometry t = subdiv.getTriangles(geometryFact);

        }
    }

    /**
     * @return
     */
    private
    boolean converges () {

        return converges;
    }

    /**
     *
     */
    public
    void splitStep1 () {
        this.calculate();
    }

    /**
     *
     */
    @Contract(pure = true)
    private
    void calculate () {

    }

    /**
     *
     * @param src
     * @return
     */
    public static
    double[][] meanStdDev ( double src ) {
        MatOfDouble mean = new MatOfDouble();
        MatOfDouble stdDev = new MatOfDouble();

        Core.meanStdDev(new MatOfDouble(src), mean, stdDev);
        double[][] result = new double[2][1];

        result[0] = mean.toArray();
        result[1] = stdDev.toArray();

        return result;
    }


    /**
     *
     * @param src
     * @return
     */
    public static
    double[][] meanStdDev ( double[] src ) {
        MatOfDouble mean = new MatOfDouble();
        MatOfDouble mStdDev = new MatOfDouble();

        Core.meanStdDev(new MatOfDouble(src), mean, mStdDev);
        double[][] result = new double[2][src.length];

        result[0] = mean.toArray();
        result[1] = mStdDev.toArray();

        return result;
    }

    /**
     * @param gray
     */
    public
    void setGrayLevel ( int gray ) {
        grayLevel = gray * 255 / 100;
    }

    /**
     * @return
     */
    public
    int getGrayLevel () {
        return grayLevel;
    }

    /**
     *
     */
    public
    void splitStep2 ( Iterator <Triangle> iterator ) throws ValueError {
        while (iterator.hasNext()) {
            Triangle nextTriangle = iterator.next();
            double[][] msd = meanStdDev(roi.getPixels());
            if (!nextTriangle.isHomogeneous()) {
                insert(new Vertex(nextTriangle.centroid()));
                converges = false;
            }
            else {
                nextTriangle.setStdDev(msd[0][0]);
                nextTriangle.setGrayLevelMean(msd[1][0]);
                nextTriangle.setSize(nextTriangle.perimeter());
            }
        }
    }

    /**
     *
     */
    public
    void mergeStep () {

    }

    /**
     * @param c
     * @return
     */
    public
    QuadEdge insert ( Coordinate c ) {
        return insert(new Vertex(c));
    }

    /**
     * Inserts a new point into a subdivision representing a Delaunay triangulation,
     * and fixes the affected edges so that the result is still a Delaunay triangulation.
     *
     * @return a quad edge containing the inserted vertex
     */
    public
    QuadEdge insert ( Vertex v ) {

        /**

         */
        QuadEdge e = subdiv.locate(v);

        if (subdiv.isVertexOfEdge(e, v)) {
            // point is already in subdivision.
            return e;
        }
        else if (subdiv.isOnEdge(e, v.getCoordinate())) {
            // the point lies exactly on an edge, so delete the edge
            // (it will be replaced by a pair of edges which have the point as a vertex)
            e = e.oPrev();
            subdiv.delete(e.oNext());
        }

        /**
         * Connect the new point to the vertices of the containing triangle
         * (or quadrilateral, if the new point fell on an existing edge.)
         */
        QuadEdge base = subdiv.makeEdge(e.orig(), v);
        QuadEdge.splice(base, e);
        QuadEdge startEdge = base;
        do {
            base = subdiv.connect(e, base.sym());
            e = base.oPrev();

        } while (e.lNext() != startEdge);

        // Examine suspect edges to ensure that the Delaunay condition
        // is satisfied.
        do {
            QuadEdge t = e.oPrev();
            if (t.dest().rightOf(e) && v.isInCircle(e.orig(), t.dest(), e.dest())) {
                QuadEdge.swap(e);
                e = e.oPrev();
            }
            else if (e.oNext() == startEdge) {
                return base; // no more suspect edges.
            }
            else {
                e = e.oNext().lPrev();
            }
        } while (true);
    }

    /**
     * @return
     */
    public
    double getTriangleSize () {
        return triangleSize;
    }
}