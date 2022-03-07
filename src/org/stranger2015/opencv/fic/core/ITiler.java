package org.stranger2015.opencv.fic.core;

import java.util.List;

public
interface ITiler<M extends IImage<A>, A extends Address<A>> {

    /**
     *
     *
     * @param t
     * @return
     */
    List <ImageBlock<A>> tile ( final M t );
}
