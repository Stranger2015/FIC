package org.stranger2015.opencv.fic;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.Leaf;
import org.stranger2015.opencv.fic.core.TreeNode;

/**
 * 8 X 8
 */
public
class DomainBlock extends Leaf {

    public final static int W = 8;
    public final static int H = 8;

    public
    DomainBlock ( TreeNode <?> parent, Mat image, Rect rect ) {
        super(parent, image, rect);
    }
}
