package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.geom.Coordinate;
import org.stranger2015.opencv.fic.core.geom.Geometry;
import org.stranger2015.opencv.fic.core.geom.GeometryFactory;
import org.stranger2015.opencv.fic.core.shape.fractal.HilbertCode;
import org.stranger2015.opencv.fic.core.shape.fractal.PeanoCurveBuilder;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.QuadEdgeSubdivision;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Deque;

import static org.stranger2015.opencv.fic.core.IShape.EShape;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class DelaunayTriangularTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends TriangularTiler <N, A, G> {

    private int rangeW;
    private int rangeH;
    private QuadEdgeSubdivision subdiv;

    /**
     * @return
     */
    public
    DelaunayTriangulationBuilder getTriangulationBuilder () {
        return triangulationBuilder;
    }

    /**
     *
     */
    protected
    DelaunayTriangulationBuilder triangulationBuilder;

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    DelaunayTriangularTiler ( IImage <A> image,
                              IIntSize rangeSize,
                              IIntSize domainSize,
                              IEncoder <N, A, G> encoder,
                              ITreeNodeBuilder <N, A, G> builder
    ) {
        super(
                image,
                rangeSize,
                domainSize,
                encoder,
                builder
        );

        triangulationBuilder = new DelaunayTriangulationBuilder();
    }

    /**
     * @return
     */
    @Override
    public
    ITiler <N, A, G> instance () {
        return new DelaunayTriangularTiler <>(
                getImage(),
                getRangeSize(),
                getDomainSize(),
                getEncoder(),
                getBuilder()
        );
    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( TreeNode <N, A, G> node ) {

    }

    /**
     * @param imageBlockShape
     * @param imageBlock
     * @param minRangeSize
     * @param queue
     * @throws ValueError
     */
    @Override
    public
    void segmentShape ( EShape imageBlockShape,
                        IImageBlock <A> imageBlock,
                        IIntSize minRangeSize,
                        Deque <IImageBlock <A>> queue
    ) throws ValueError {

    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentTriangle ( IImageBlock <A> imageBlock ) throws ValueError {
        super.segmentTriangle(imageBlock);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( IImageBlock <A> imageBlock ) throws ValueError {
        rangeH = rangeSize.getWidth();
        rangeW = rangeSize.getHeight();
        GeometryFactory geometryFactory = new GeometryFactory();
        subdiv = triangulationBuilder.getSubdivision();
        Geometry triangles = triangulationBuilder.getTriangles(geometryFactory);
        Coordinate[] coords = triangulationBuilder.triangles.getCoordinates();

        PeanoCurveBuilder builder = new PeanoCurveBuilder(geometryFactory);
        builder.
        builder.setNumPoints(coords.length);
        builder.setLevel(HilbertCode.level(coords.length));

    }

    /**
     *
     */
    @Override
    protected
    void onFinish () {

    }

    public
    int getRangeW () {
        return rangeW;
    }

    public
    int getRangeH () {
        return rangeH;
    }

    public
    QuadEdgeSubdivision getSubdiv () {
        return subdiv;
    }
}
