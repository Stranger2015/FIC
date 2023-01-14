package org.stranger2015.opencv.fic.utils;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.IIntSize;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.core.geom.Geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public
class ColorImage extends Image {
    /**
     * @param image
     */
    public
    ColorImage ( IImage image ) {
        super(image, image);

        List <Mat> list = new ArrayList <>(4);//channels e.g. rgba etc
        Core.split(image.getMat(), list);

        Collection <GrayScaleImage> gsiList = list.stream()
                .map(GrayScaleImage::new)
                .collect(Collectors.toList());

        components.addAll(gsiList);
    }

    /**
     * @param mat
     */
    public
    ColorImage ( Mat mat ) {
        super(actualImage, mat, size);
    }

    /**
     * @return
     */
    @Override
    public
    List <IImage> getComponents () {
        return components;
    }

    /**
     * @param x
     * @param y
     * @param pixelData
     * @throws ValueError
     */
    @Override
    public
    void putPixel ( int x, int y, double[] pixelData ) throws ValueError {
        super.putPixel(x, y, pixelData);
    }

    /**
     * @param o
     * @return
     */
    @Override
    public
    int compareTo ( IIntSize o ) {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    Size toSize () {
        return null;
    }

    /**
     * @param bb
     * @param polygonList
     * @return
     */
    @Override
    public
    Mat createMask ( IIntSize bb, List <Geometry <?>> polygonList ) {
        return super.createMask(bb, polygonList);
    }

    /**
     * @return
     */
    @Override
    public
    boolean isGrayScale () {
        return false;
    }
}
