package org.stranger2015.opencv.fic.utils;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.Image;

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
        super(image.actualImage, image, image.size);

        List <Mat> list = new ArrayList <>(4);//channels e.g. rgba etc
        Core.split(image.getMat(), list);

        Collection <GrayScaleImage> gsiList = list.stream()
                .map((Function <Mat, GrayScaleImage>) GrayScaleImage::new)
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
     * @return
     */
    @Override
    public
    boolean isGrayScale () {
        return false;
    }
}
