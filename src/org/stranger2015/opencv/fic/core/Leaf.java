package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.Collections;

public
class Leaf extends TreeNode <Leaf> {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    protected final Mat image;

    public
    Leaf ( Leaf parent, Mat image, Rect rect ) {
        super(parent, rect, Collections.emptyList());
        this.image = image.submat(rect);
    }

    public
    Leaf ( Leaf parent, Mat image, int x, int y, int width, int height ) {
        this(parent, image, new Rect(x, y, width, height));
    }

    public
    boolean isDomainBlock () {
        return false;
    }

    @Override
    public
    void draw ( Mat image, Rect rect ) {
        Imgproc.rectangle(image, rect, new Scalar(1.0 / 255, 1.0 / 255, 1.0 / 255, 0.0));
    }

    @Override
    public
    Leaf merge () {
        return this;
    }

    public
    Mat getImage () {
        return image;
    }
}
