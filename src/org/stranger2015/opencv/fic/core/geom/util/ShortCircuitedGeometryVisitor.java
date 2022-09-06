package org.stranger2015.opencv.fic.core.geom.util;

import org.stranger2015.opencv.fic.core.geom.Geometry;
import org.stranger2015.opencv.fic.core.geom.GeometryCollection;

/**
 * A visitor to {@link Geometry} components, which
 * allows short-circuiting when a defined condition holds.
 *
 * @version 1.7
 */
public abstract class ShortCircuitedGeometryVisitor
{
    private boolean isDone = false;

    public ShortCircuitedGeometryVisitor() {
    }

    /**
     * @param geom
     */
    public void applyTo( Geometry geom) {
        for (int i = 0; i < geom.getNumGeometries() && !isDone; i++) {
            Geometry element = geom.getGeometryN(i);
            if (!(element instanceof GeometryCollection)) {
                visit(element);
                if (isDone()) {
                    isDone = true;
                    break;
                }
            }
            else {
                applyTo(element);
            }
        }
    }

    protected abstract void visit( Geometry element);

    /**
     * Reports whether visiting components can be terminated.
     * Once this method returns <tt>true</tt>, it must
     * continue to return <tt>true</tt> on every subsequent call.
     *
     * @return true if visiting can be terminated.
     */
    protected abstract boolean isDone();
}
