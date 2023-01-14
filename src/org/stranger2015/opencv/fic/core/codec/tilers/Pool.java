package org.stranger2015.opencv.fic.core.codec.tilers;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.IImageBlock;
import org.stranger2015.opencv.fic.core.IIntSize;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @param <E>
 */
public
class Pool<E extends IImageBlock> extends ArrayList <E> {

    final List <E> filteredPool = new ArrayList <>(this.size());

    protected final IIntSize[] sizes;

    protected int currentSizeIdx;

    /**
     * @param sizes
     */
    public
    Pool ( IIntSize[] sizes ) {
        this.sizes = sizes;
        setCurrentSizeIdx(0);
    }

    /**
     * @return
     */
    public
    IIntSize[] getSizes () {
        return sizes;
    }

    /**
     * @return
     */
    public
    List <E> getFilteredPool ( IImageBlock rangeBlock ) {
        return filteredPool;
    }

    /**
     * @return
     */
    public
    int getCurrentSizeIdx () {
        return currentSizeIdx;
    }

    /**
     * @param currentSizeIdx
     */
    public
    void setCurrentSizeIdx ( int currentSizeIdx ) {
        this.currentSizeIdx = currentSizeIdx;
        filteredPool.clear();

        for (int i = 0; i < size(); i++) {
            E imageBlock = get(i);
            if (!Objects.equals(imageBlock.getSize(), sizes[currentSizeIdx])) {
                continue;
            }
            filteredPool.add(imageBlock);
        }
    }

    /**
     * @return
     */
    int incCurrentSizeIdx () {

        setCurrentSizeIdx(++currentSizeIdx);

        return currentSizeIdx;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * <p>The returned iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @NotNull
    @Override
    public
    Iterator <E> iterator () {
        return filteredPool.iterator();
    }

    /**
     * @return
     */
    public
    IIntSize getCurrentSize () {
        return Pool.this.getSizes()[currentSizeIdx];
    }

    /**
     * @param block
     */
    public static
    void incUsageCount ( List <IImageBlock> list, IImageBlock block ) {
        block.incUsageCount();

//        int size = list.size();
        for (int i = 0; i < list.size(); i++) {
            IImageBlock ib = list.get(i);
            if (block.getUsageCount() > ib.getUsageCount()) {
                swap(list, list.indexOf(ib), list.indexOf(block));
            }
        }
    }

    /**
     * @param index1
     * @param index2
     */
    public static
    void swap ( List <IImageBlock> list, int index1, int index2 ) {
        IImageBlock tmp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, tmp);
    }
}
