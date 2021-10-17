package org.stranger2015.opencv.fic;

import org.opencv.core.Mat;
import org.opencv.core.Range;
import org.stranger2015.opencv.fic.core.PartitionScheme;

public
class ImagePartitionSegmenter extends ImagePartitionProcessor {

    public
    ImagePartitionSegmenter ( Mat image, PartitionScheme scheme ) {
        super(image, scheme, rangeBlocks, tree);
    }

    public
    void segment ( ) {
        double fx = image.width() / 8.0;
        double fy = image.height() / 8.0;
        Range range = new Range();
        Mat subimage = new Mat();

//        Imgproc.resize(image, subimage, new Size(), fx, fy, Imgproc.INTER_CUBIC);
    }
}
