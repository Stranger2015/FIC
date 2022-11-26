package org.stranger2015.opencv.fic.core.codec.tilers;

import org.slf4j.Logger;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.geom.Coordinate;
import org.stranger2015.opencv.fic.core.geom.Geometry;
import org.stranger2015.opencv.fic.core.geom.GeometryFactory;
import org.stranger2015.opencv.fic.core.shape.fractal.HilbertCode;
import org.stranger2015.opencv.fic.core.shape.fractal.HilbertCurveBuilder;
import org.stranger2015.opencv.fic.core.triangulation.DelaunayTriangulation;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class DelaunayTriangularTopDownTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends TriangularTiler <N, A, G>
        implements ITopDownTiler <N, A, G> {

    private DelaunayTriangulation <N, A, G> subdivTriangulation;

    /**
     * @return
     */
    public
    DelaunayTriangulationBuilder <N, A, G> getTriangulationBuilder ( Class <?> clazz ) {
        return triangulationBuilder;
    }

    /**
     *
     */
    protected
    DelaunayTriangulationBuilder <N, A, G> triangulationBuilder;

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    DelaunayTriangularTopDownTiler ( IImage <A> image,
                                     IIntSize rangeSize,
                                     IIntSize domainSize,
                                     IEncoder <N, A, G> encoder,
                                     ITreeNodeBuilder <N, A, G> builder ) {
        super(
                image,
                rangeSize,
                domainSize,
                encoder,
                builder
        );

        triangulationBuilder = new DelaunayTriangulationBuilder <N, A, G>(getClass());
    }

    /**
     * @return
     */
    @Override
    public
    ITiler <N, A, G> instance () {
        return new DelaunayTriangularTopDownTiler <>(
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
    void addLeafNode ( LeafNode <N, A, G> node ) {
    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 0;
    }

    /**
     * @param imageBlockShape
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentGeometry ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
        segmentSquare(node, imageBlock);
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentRectangle ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {

    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentTriangle ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {

    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentPolygon ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {

    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentQuadrilateral ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
        super.segmentSquare(node, imageBlock);

        GeometryFactory geometryFactory = new GeometryFactory();
        subdivTriangulation = (DelaunayTriangulation <N, A, G>) triangulationBuilder.getSubdivision();
        Geometry <?> triangles = triangulationBuilder.getTriangles(geometryFactory);
        Coordinate[] coords = triangles.getCoordinates();

        HilbertCurveBuilder builder = new HilbertCurveBuilder(geometryFactory);
        builder.setLevel(HilbertCode.level(coords.length));
    }

    /**
     * @return
     */
    @Override
    public
    Logger getLogger () {
        return logger;
    }

    /**
     * @return
     */
    public
    DelaunayTriangulation <N, A, G> getSubdivTriangulation () {
        return subdivTriangulation;
    }

    /**
     * @param node
     * @param imageBlock
     */
    @Override
    public
    void onSuccessors ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) {
        throw new UnsupportedOperationException();
    }

    /**
     * @param node
     * @param imageBlock
     */
    @Override
    public
    void onSuccessor ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) {
        throw new UnsupportedOperationException();    }
}
