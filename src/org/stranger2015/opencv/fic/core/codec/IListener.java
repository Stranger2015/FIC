package org.stranger2015.opencv.fic.core.codec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;

/**
 * @param <T>
 */
public
interface IListener<T> {
    Logger logger = LoggerFactory.getLogger(IListener.class);

    /**
     * @param instance
     */
    default
    void onCreated ( T instance ) {
        logger.info(format("On codec created '%s'\n", instance));
    }
}
