package org.stranger2015.opencv.fic.core.operation;

import org.stranger2015.opencv.fic.core.algorithm.PointLocator;
import org.stranger2015.opencv.fic.core.geom.IsSimpleOp;
import org.stranger2015.opencv.fic.core.geom.LineString;
import org.stranger2015.opencv.fic.core.geom.LinearRing;
import org.stranger2015.opencv.fic.core.geom.MultiLineString;
import org.stranger2015.opencv.fic.core.operation.relate.RelateOp;

/**
 * An interface for rules which determine whether node points
 * which are in boundaries of {@link ILineal} geometry components
 * are in the boundary of the parent geometry collection.
 * The SFS specifies a single kind of boundary node rule,
 * the {@link IBoundaryNodeRule.Mod2BoundaryNodeRule} rule.
 * However, other kinds of Boundary Node Rules are appropriate
 * in specific situations (for instance, linear network topology
 * usually follows the {@link IBoundaryNodeRule.EndPointBoundaryNodeRule}.)
 * Some JTS operations
 * (such as {@link RelateOp}, {@link BoundaryOp} and {@link IsSimpleOp})
 * allow the IBoundaryNodeRule to be specified,
 * and respect the supplied rule when computing the results of the operation.
 * <p>
 * An example use case for a non-SFS-standard Boundary Node Rule is
 * that of checking that a set of {@link LineString}s have
 * valid linear network topology, when turn-arounds are represented
 * as closed rings.  In this situation, the entry road to the
 * turn-around is only valid when it touches the turn-around ring
 * at the single (common) endpoint.  This is equivalent
 * to requiring the set of <tt>LineString</tt>s to be
 * <b>simple</b> under the {@link IBoundaryNodeRule.EndPointBoundaryNodeRule}.
 * The SFS-standard {@link IBoundaryNodeRule.Mod2BoundaryNodeRule} is not
 * sufficient to perform this test, since it
 * states that closed rings have <b>no</b> boundary points.
 * <p>
 * This interface and its subclasses follow the <tt>Strategy</tt> design pattern.
 *
 * @author Martin Davis
 * @version 1.8
 * @see RelateOp
 * @see BoundaryOp
 * @see IsSimpleOp
 * @see PointLocator
 */
public
interface IBoundaryNodeRule {
    /**
     * Tests whether a point that lies in <tt>boundaryCount</tt>
     * geometry component boundaries is considered to form part of the boundary
     * of the parent geometry.
     *
     * @param boundaryCount the number of component boundaries that this point occurs in
     * @return true if points in this number of boundaries lie in the parent boundary
     */
    boolean isInBoundary ( int boundaryCount );

    /**
     * The Mod-2 Boundary Node Rule (which is the rule specified in the OGC SFS).
     *
     * @see IBoundaryNodeRule.Mod2BoundaryNodeRule
     */
    IBoundaryNodeRule MOD2_BOUNDARY_RULE = new IBoundaryNodeRule.Mod2BoundaryNodeRule();

    /**
     * The Endpoint Boundary Node Rule.
     *
     * @see IBoundaryNodeRule.EndPointBoundaryNodeRule
     */
    IBoundaryNodeRule ENDPOINT_BOUNDARY_RULE = new IBoundaryNodeRule.EndPointBoundaryNodeRule();

    /**
     * The MultiValent Endpoint Boundary Node Rule.
     *
     * @see IBoundaryNodeRule.MultiValentEndPointBoundaryNodeRule
     */
    IBoundaryNodeRule MULTIVALENT_ENDPOINT_BOUNDARY_RULE = new IBoundaryNodeRule.MultiValentEndPointBoundaryNodeRule();

    /**
     * The Monovalent Endpoint Boundary Node Rule.
     *
     * @see IBoundaryNodeRule.MonoValentEndPointBoundaryNodeRule
     */
    IBoundaryNodeRule MONOVALENT_ENDPOINT_BOUNDARY_RULE = new IBoundaryNodeRule.MonoValentEndPointBoundaryNodeRule();

    /**
     * The Boundary Node Rule specified by the OGC Simple Features Specification,
     * which is the same as the Mod-2 rule.
     *
     * @see IBoundaryNodeRule.Mod2BoundaryNodeRule
     */
    IBoundaryNodeRule OGC_SFS_BOUNDARY_RULE = MOD2_BOUNDARY_RULE;

    /**
     * A {@link IBoundaryNodeRule} specifies that points are in the
     * boundary of a lineal geometry iff
     * the point lies on the boundary of an odd number
     * of components.
     * Under this rule {@link LinearRing}s and closed
     * {@link LineString}s have an empty boundary.
     * <p>
     * This is the rule specified by the <i>OGC SFS</i>,
     * and is the default rule used in JTS.
     *
     * @author Martin Davis
     * @version 1.7
     */
    class Mod2BoundaryNodeRule
            implements IBoundaryNodeRule {
        public
        boolean isInBoundary ( int boundaryCount ) {
            // the "Mod-2 Rule"
            return boundaryCount % 2 == 1;
        }
    }

    /**
     * A {@link IBoundaryNodeRule} which specifies that any points which are endpoints
     * of lineal components are in the boundary of the
     * parent geometry.
     * This corresponds to the "intuitive" topological definition
     * of boundary.
     * Under this rule {@link LinearRing}s have a non-empty boundary
     * (the common endpoint of the underlying LineString).
     * <p>
     * This rule is useful when dealing with linear networks.
     * For example, it can be used to check
     * whether linear networks are correctly noded.
     * The usual network topology constraint is that linear segments may touch only at endpoints.
     * In the case of a segment touching a closed segment (ring) at one point,
     * the Mod2 rule cannot distinguish between the permitted case of touching at the
     * node point and the invalid case of touching at some other interior (non-node) point.
     * The EndPoint rule does distinguish between these cases,
     * so is more appropriate for use.
     *
     * @author Martin Davis
     * @version 1.7
     */
    class EndPointBoundaryNodeRule
            implements IBoundaryNodeRule {
        public
        boolean isInBoundary ( int boundaryCount ) {
            return boundaryCount > 0;
        }
    }

    /**
     * A {@link IBoundaryNodeRule} which determines that only
     * endpoints with valency greater than 1 are on the boundary.
     * This corresponds to the boundary of a {@link MultiLineString}
     * being all the "attached" endpoints, but not
     * the "unattached" ones.
     *
     * @author Martin Davis
     * @version 1.7
     */
    class MultiValentEndPointBoundaryNodeRule
            implements IBoundaryNodeRule {
        public
        boolean isInBoundary ( int boundaryCount ) {
            return boundaryCount > 1;
        }
    }

    /**
     * A {@link IBoundaryNodeRule} which determines that only
     * endpoints with valency of exactly 1 are on the boundary.
     * This corresponds to the boundary of a {@link MultiLineString}
     * being all the "unattached" endpoints.
     *
     * @author Martin Davis
     * @version 1.7
     */
    class MonoValentEndPointBoundaryNodeRule
            implements IBoundaryNodeRule {
        public
        boolean isInBoundary ( int boundaryCount ) {
            return boundaryCount == 1;
        }
    }
}
