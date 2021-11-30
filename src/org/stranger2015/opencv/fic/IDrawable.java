package org.stranger2015.opencv.fic;

import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.Square;
import org.stranger2015.opencv.fic.core.Triangle;


/**
 *
 */
public
interface IDrawable<M extends Image> {
    /**
     * @param image
     * @param rect
     */
    void draw ( M image, Rect rect );

    /**
     * @param image
     * @param rect
     * @param color
     */
    default
    void rectangle ( M image, Rect rect, Scalar color ) {
        Imgproc.rectangle(image, rect, color);
    }

    /**
     * @param image
     * @param rect
     * @param color
     */
    default
    void square ( M image, Square rect, Scalar color ) {
        Imgproc.rectangle(image, rect, color);
    }

    /**
     * @param image
     * @param triangle
     * @param color
     */
    default
    void triangle ( Image image, Triangle triangle, Scalar color ) {
        Point p0 = new Point(triangle.x, triangle.y);
        Point p1 = new Point(triangle.x + triangle.width, triangle.y);
        Point p2 = new Point(triangle.x, triangle.y + triangle.height);

        Imgproc.line(image, p0, p1, color);
        Imgproc.line(image, p1, p2, color);
        Imgproc.line(image, p2, p0, color);
    }
}
