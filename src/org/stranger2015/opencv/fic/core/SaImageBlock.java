package org.stranger2015.opencv.fic.core;

/**
 * @param <A>
 */
public
class SaImageBlock<A> extends ImageBlock<A>{
    public
    <A extends Address <A>>
    SaImageBlock ( IImage <A> image, int x, int y, int w, int h ) {
        super(image, x,y,w,h);
    }
}
