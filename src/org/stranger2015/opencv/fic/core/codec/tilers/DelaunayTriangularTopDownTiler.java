package org.stranger2015.opencv.fic.core.codec.tilers;

import org.slf4j.Logger;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.geom.Coordinate;
import org.stranger2015.opencv.fic.core.geom.Geometry;
import org.stranger2015.opencv.fic.core.geom.GeometryFactory;
import org.stranger2015.opencv.fic.core.shape.fractal.HilbertCode;
import org.stranger2015.opencv.fic.core.shape.fractal.HilbertCurveBuilder;
import org.stranger2015.opencv.fic.core.triangulation.DelaunayTriangulation;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Deque;
import java.util.List;

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
                                     ITreeNodeBuilder <N, A, G> builder
    ) {
        super(
                image,
                rangeSize,
                domainSize,
                encoder,
                builder
        );

        triangulationBuilder = new DelaunayTriangulationBuilder <>(getClass());
    }

    /**
     * @return
     */
    @Override
    public
    TriangularTiler <N, A, G> instance () {
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
    void addLeafNode ( TreeNode <N, A, G> node ) {

    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 1;//FIXME 2 ??
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
    List <IImageBlock <A>> segmentGeometry ( IImageBlock <A> imageBlock,
                                             IIntSize minRangeSize,
                                             Deque <IImageBlock <A>> queue
    ) throws ValueError {
        return List.of(imageBlock);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    List <IImageBlock <A>> segmentTriangle ( IImageBlock <A> imageBlock ) throws ValueError {
        return super.segmentTriangle(imageBlock);
    }

    @Override
    public
    List <IImageBlock <A>> segmentPolygon ( IImageBlock <A> imageBlock ) throws ValueError {
        return List.of(imageBlock);
    }

    @Override
    public
    List <IImageBlock <A>> segmentQuadrilateral ( IImageBlock <A> imageBlock ) throws ValueError {
        return List.of(imageBlock);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    List <IImageBlock <A>> segmentSquare ( IImageBlock <A> imageBlock ) throws ValueError {
        int w;

        GeometryFactory geometryFactory = new GeometryFactory();
        subdivTriangulation = (DelaunayTriangulation <N, A, G>) triangulationBuilder.getSubdivision();
        Geometry<?> triangles = triangulationBuilder.getTriangles(geometryFactory);
        Coordinate[] coords = triangles.getCoordinates();

        HilbertCurveBuilder builder = new HilbertCurveBuilder(geometryFactory);
        builder.setLevel(HilbertCode.level(coords.length));

        return List.of(imageBlock);
    }

    /**
     *
     */
    @Override
    public
    void onFinish () {

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
}
