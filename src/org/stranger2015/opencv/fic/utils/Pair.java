package org.stranger2015.opencv.fic.utils;

import java.util.Objects;

/**
 * @param <T1>
 * @param <T2>
 */
public
class Pair<T1, T2> {
    private final T1 first;
    private final T2 second;

    public
    Pair (T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
    public
    T1 getFirst () {
        return first;
    }

    public
    T2 getSecond () {
        return second;
    }

    @Override
    public
    String toString () {
        return String.format("Pair{first=%s, second=%s}", first, second);
    }

    @Override
    public
    boolean equals ( Object o ) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair <?, ?> pair = (Pair <?, ?>) o;
        return getFirst().equals(pair.getFirst()) && getSecond().equals(pair.getSecond());
    }

    @Override
    public
    int hashCode () {
        return Objects.hash(getFirst(), getSecond());
    }
}
