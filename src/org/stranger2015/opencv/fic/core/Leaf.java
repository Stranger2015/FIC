package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public
class Leaf <N extends Leaf <N>> extends TreeNode <N> {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    protected final Image image;

    public
    Leaf ( TreeNode <N> parent, Image image, Rect rect ) {
        super(parent, null, rect);
        this.image = image.submat(rect);
    }

    /**
     * @param parent
     * @param image
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public
    Leaf ( Leaf <N> parent, Mat image, int x, int y, int width, int height ) {
        this(parent, image, new Rect(x, y, width, height));
    }

    public
    boolean isDomainBlock () {
        return false;
    }

    @Override
    public
    void draw ( Image image, Rect rect ) {
        Imgproc.rectangle(image, rect, new Scalar(1.0 / 255, 1.0 / 255, 1.0 / 255, 0.0));
    }

    /**
     * @param quadrant
     * @param boundingBox
     * @return
     */
    @Override
    public
    TreeNode <N> createChild ( Direction quadrant, Rect boundingBox ) {
        throw new IllegalStateException("createChild() in Leaf");
    }

    @Override
    public
    TreeNode <N> createNode ( TreeNode <N> parent, Direction quadrant, Rect boundingBox ) {
        return new Leaf <>(parent, null, boundingBox);
    }

    public
   Image getImage () {
        return image;
    }
}
