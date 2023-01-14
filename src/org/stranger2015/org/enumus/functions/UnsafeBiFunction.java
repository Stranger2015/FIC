package org.stranger2015.opencv.fic.core.enumus.functions;

/**
 * @param <T>
 * @param <U>
 * @param <R>
 * @param <E>
 */
public interface UnsafeBiFunction<T, U, R, E extends Throwable> {
    R apply(T t, U u) throws E;
}
