package org.stranger2015.opencv.fic.utils;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IAddress;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public
class ColorImage<A extends IAddress <A>> extends GrayScaleImage <A> {
    /**
     * @param image
     */
    public
    ColorImage ( GrayScaleImage <A> image ) {
        super(image);

        List <Mat> list = new ArrayList <>(4);//channels e.g. rgba etc
        Core.split(image.getMat(), list);

        Collection <GrayScaleImage <A>> gsiList = list.stream()
                .map((Function <Mat, GrayScaleImage <A>>) GrayScaleImage::new)
                .collect(Collectors.toList());

        components.addAll(gsiList);
    }

    /**
     * @param mat
     */
    public
    ColorImage ( Mat mat ) {
        super(mat);
    }

    /**
     * @return
     */
    @Override
    public
    List <GrayScaleImage <A>> getComponents () {
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
