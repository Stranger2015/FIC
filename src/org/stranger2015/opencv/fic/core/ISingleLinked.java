package org.stranger2015.opencv.fic.core;

/**
 * @param <T>
 */
public
interface ISingleLinked<T> {
    /**
     * @return
     */
    ISingleLinked<T> getNext();

    /**
     * @return
     */
    T getLinkedObject();

    /**
     * @param link
     */
    void setNext(ISingleLinked<T> link );
}
