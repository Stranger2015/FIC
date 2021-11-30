package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 * fractal compressor instance. combines the tiler and
 * comparator classes to create a fractal image model
 */
public
class Compressor extends Observable {

    private final ScaleTransform <Image, CompressedImage> scaleTransform;
    private final Tiler <BufferedImage> tiler;
    private final ThreadLocal <Distanceator <BufferedImage>> comparator = new ThreadLocal <>();
    private final Set <ImageTransform <Image, CompressedImage>> transforms;
    private final Set <BufferedImageOp> filters;

    /**
     * @param scaleTransform the scale difference between the ranges and the domains
     * @param tiler          the tiler used to tile the image
     * @param comparator     the comparator used to compare the tiles of the image
     * @param transforms     a list of transform to apply to the tiles of the image
     * @param observer       an observer receiving progress results for the compression - allowed to be null
     * @throws NullPointerException if any field is null
     * @see Observable
     * @see Observer#update(java.util.Observable, java.lang.Object)
     */
    public
    Compressor ( final ScaleTransform <Image, CompressedImage> scaleTransform,
                 final Tiler <BufferedImage> tiler,
                 final Distanceator <BufferedImage> comparator,
                 final Set <ImageTransform <Image, CompressedImage>> transforms,
                 final Observer observer )
            throws NullPointerException {
        this(scaleTransform, tiler, comparator, transforms,
                new HashSet <>(0), observer);
    }

    /**
     * @param scaleTransform the scale difference between the ranges and the domains
     * @param tiler          the tiler used to tile the image
     * @param comparator     the comparator used to compare the tiles of the image
     * @param transforms     a set of transform to apply to the tiles of the image
     * @param filters        a set of filters to apply to the image for normalization
     * @param observer       an observer receiving progress results from {@code compress} - allowed to be null
     * @throws NullPointerException if any field is null
     * @see Observable
     * @see Observer#update(java.util.Observable, java.lang.Object)
     */
    public
    Compressor ( final ScaleTransform <Image, CompressedImage> scaleTransform,
                 final Tiler <BufferedImage> tiler,
                 final Distanceator <BufferedImage> comparator,
                 final Set <ImageTransform <Image, CompressedImage>> transforms,
                 final Set <BufferedImageOp> filters,
                 Observer observer ) throws NullPointerException {
        assert (comparator != null) && (transforms != null) && (filters != null)
                && (tiler != null) && (scaleTransform != null) : "Null elements now allowed";

        this.comparator.set(comparator);
        this.tiler = tiler;
        this.filters = filters;
        this.transforms = transforms;
        this.scaleTransform = scaleTransform;

        if (observer != null) {
            this.addObserver(observer);
        }
    }

    public
    ScaleTransform <Image, CompressedImage> getScaleTransform () {
        return scaleTransform;
    }

    public
    Tiler <BufferedImage> getTiler () {
        return tiler;
    }

    public
    Set <ImageTransform <Image, CompressedImage>> getTransforms () {
        return transforms;
    }

    public
    Set <BufferedImageOp> getFilters () {
        return filters;
    }
}
