package org.stranger2015.opencv.fic;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.osgi.OpenCVNativeLoader;
import org.stranger2015.opencv.fic.core.Node;

import static org.stranger2015.opencv.fic.ImagePartitionProcessor.getNearestGreaterPow2;
import static org.stranger2015.opencv.fic.core.PartitionScheme.QUADTREE;

/**
 *
 */
public
class Main {

    public static int row;
    public static int col;

    public static Node currNode;
//    public final static List <Node> currQuads = new ArrayList <>(4);

//    private

    public static
    void main ( String[] args ) {
        if (args.length != 1) {
            System.exit(-1);
        }
        init();

        compressImage(args[0]);
    }

    private static
    void init () {
        new OpenCVNativeLoader().init();
    }

    private static
    void compressImage ( String fn ) {
        Mat image = Imgcodecs.imread(fn, Imgcodecs.IMREAD_GRAYSCALE);
        if (image.empty()) {
            System.out.println("Cannot read the image, `" + fn + "'");
            return;
        }

        Mat image2 = new Mat();
//        Imgproc.cvtColor(image, image2, Imgproc.COLOR_BGR2RGBA);
//        Imgproc.cvtColor(image, image2, Imgproc.COLOR_BGR2GRAY);
        double wh = getNearestGreaterPow2(Math.max(image.width(), image.height()));
        double fx = wh / image.width();
        double fy = wh / image.height();
        Size size = new Size();

        Imgproc.resize(image, image2, size, fx, fy, Imgproc.INTER_CUBIC);

        HighGui.namedWindow("OpenCV");

//        HighGui.imshow("OpenCV", image2);
        System.out.println(image2.dump());

       ImagePartitionProcessor processor = new ImagePartitionProcessor(image2, QUADTREE, rangeBlocks, tree);
       processor.process();
       processor.draw();

        HighGui.destroyAllWindows();

        image.release();
        image2.release();
    }

}
