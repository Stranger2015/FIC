package org.stranger2015.opencv.fic.core.codec.tilers;

import org.slf4j.Logger;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.geom.Coordinate;
import org.stranger2015.opencv.fic.core.geom.Geometry;
import org.stranger2015.opencv.fic.core.geom.GeometryFactory;
import org.stranger2015.opencv.fic.core.shape.fractal.HilbertCode;
import org.stranger2015.opencv.fic.core.shape.fractal.HilbertCurveBuilder;
import org.stranger2015.opencv.fic.core.triangulation.DelaunayTriangulation;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class DelaunayTriangularTopDownTiler
        extends TriangularTiler
        implements ITopDownTiler {

    private DelaunayTriangulation subdivTriangulation;

    /**
     * @return
     */
    public
    DelaunayTriangulationBuilder <N> getTriangulationBuilder ( Class <?> clazz ) {
        return triangulationBuilder;
    }

    /**
     *
     */
    protected
    DelaunayTriangulationBuilder <N> triangulationBuilder;

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    DelaunayTriangularTopDownTiler ( IImage image,
                                     IIntSize rangeSize,
                                     IIntSize domainSize,
                                     IEncoder <N> encoder,
                                     ITreeNodeBuilder <N> builder ) {
        super(
                image,
                rangeSize,
                domainSize,
                encoder,
                builder
        );

        triangulationBuilder = new DelaunayTriangulationBuilder <N>(getClass());
    }

    /**
     * @return
     */
    @Override
    public
    ITiler <N> instance () {
        return new DelaunayTriangularTopDownTiler <>(
                getImage(),
                getCurrentRangeSize(),
                this.getCurrentDomainSize(),
                getEncoder(),
                getBuilder()
        );
    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( LeafNode <N> node ) {
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
    void segmentGeometry ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {
        segmentSquare(node, imageBlock);
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentRectangle ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {

    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentTriangle ( TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError {

    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentPolygon ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {

    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentQuadrilateral ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError {
        super.segmentSquare(node, imageBlock);

        GeometryFactory geometryFactory = new GeometryFactory();
        subdivTriangulation = (DelaunayTriangulation <N>) triangulationBuilder.getSubdivision();
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
    DelaunayTriangulation <N> getSubdivTriangulation () {
        return subdivTriangulation;
    }

    /**
     * @param node
     * @param imageBlock
     */
    @Override
    public
    void onSuccessors ( TreeNodeBase <N> node, IImageBlock  imageBlock ) {
        throw new UnsupportedOperationException();
    }

    /**
     * @param node
     * @param imageBlock
     */
    @Override
    public
    void onSuccessor ( TreeNodeBase <N> node, IImageBlock  imageBlock ) {
        throw new UnsupportedOperationException();    }
}
