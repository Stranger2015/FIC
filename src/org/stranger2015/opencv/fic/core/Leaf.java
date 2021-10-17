package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public
class Leaf extends Node {

    public
    Mat getDomainBlock () {
        return domainBlock;
    }

    //    private final Color color;
    protected Mat domainBlock;

    public
    Leaf ( Mat domainBlock ) {
        this.domainBlock = domainBlock;
    }

    @Override
    public
    void draw ( Mat image, Rect rect, boolean drawQuads ) {
        Imgproc.rectangle(image, rect, new Scalar());
    }

//    public
//    Color getColor () {
//        return domainBlock.;
//    }

    @Override
    public
    Node instance ( Object... objects ) {
        return this;
    }

    @Override
    public
    Node merge () {
        return this;
    }
}
