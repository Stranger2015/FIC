package org.stranger2015.opencv.fic.core.triangulation.quadedge;

import org.stranger2015.opencv.fic.core.geom.LineSegment;

public class LocateFailureException
        extends RuntimeException
{
    private static String msgWithSpatial(String msg, LineSegment seg) {
        if (seg != null)
            return msg + " [ " + seg + " ]";
        return msg;
    }

    private LineSegment seg = null;

    public LocateFailureException(String msg) {
        super(msg);
    }

    public LocateFailureException(String msg, LineSegment seg) {
        super(msgWithSpatial(msg, seg));
        this.seg = new LineSegment(seg);
    }

    public LocateFailureException(LineSegment seg) {
        super(
                "Locate failed to converge (at edge: "
                        + seg
                        + ").  Possible causes include invalid Subdivision topology or very close sites");
        this.seg = new LineSegment(seg);
    }

    public LineSegment getSegment() {
        return seg;
    }

}
