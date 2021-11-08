package org.stranger2015.opencv.fic;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.stranger2015.opencv.fic.core.Square;
import org.stranger2015.opencv.fic.core.Triangle;

/**
 *
 */
public
interface IDrawable {
    void draw ( Mat image, Rect rect );

    default
    void rectangle ( Mat image, Rect rect, Scalar color ) {
        Imgproc.rectangle(image, rect, color);
    }

    default
    void square ( Mat image, Square rect, Scalar color ) {
        Imgproc.rectangle(image, rect, color);
    }

    default
    void triangle ( Mat image, Triangle triangle, Scalar color ) {
        Point p0 = new Point(triangle.x, triangle.y);
        Point p1 = new Point(triangle.x + triangle.width, triangle.y);
        Point p2 = new Point(triangle.x, triangle.y + triangle.height);

        Imgproc.line(image, p0, p1, color);
        Imgproc.line(image, p1, p2, color);
        Imgproc.line(image, p2, p0, color);
    }
}
