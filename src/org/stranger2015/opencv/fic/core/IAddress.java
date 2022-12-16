package org.stranger2015.opencv.fic.core;

import jdk.internal.HotSpotIntrinsicCandidate;
import org.jetbrains.annotations.Contract;

import java.util.concurrent.atomic.AtomicIntegerArray;

import static org.stranger2015.opencv.fic.core.EAddressKind.ORDINARY;

/**
 * long address
 */
public
interface IAddress extends IAddressMath  {
    AtomicIntegerArray cache = new AtomicIntegerArray(128 + 127 + 1);

    /**
     * Returns a {@code AtomicLong} instance representing the specified
     * {@code long} value.
     * If a new {@code AtomicLong} instance is not required, this method
     * should generally be used in preference to the constructoR, as this
     * method is likely to yield significantly better space and time performance
     * by caching frequently requested values.
     * <p>
     * This method will always cache values in the range -128 to 127,
     * inclusive, and may cache other values outside this range.
     *
     * @param l     a long value.
     * @param width
     * @param i2
     * @return a {@code Long} instance representing {@code l}.
     * @since 1.5
     */
    @HotSpotIntrinsicCandidate
    static

    IAddress valueOf ( int l, int stride, int i2 ) throws ValueError {
        return valueOf(l * stride + i2, defaultAddressKind());
    }

    int getAddr();

    /**
     * @param l
     * @param addressKind
     * @param
     * @return
     * @throws ValueError
     */
    static
    IAddress valueOf ( int l, EAddressKind addressKind ) throws ValueError {
        int result;
        final int offset = 128;
        if (l >= -128 && l <= 127) { // will cache
            result = Math.toIntExact(cache( l + offset).get());
        }
        else {
            result = l;
        }

        return  create(result,1,0,addressKind);//fixme
    }

    /**
     * @return
     */
    static
    EAddressKind defaultAddressKind () {
        return ORDINARY;
    }

    /**
     * @param result
     * @param addressKind
     * @param
     * @return
     * @throws ValueError
     */
    @Contract("_, _ -> new")
    static
    IAddress create ( int row, int stride, int col, EAddressKind addressKind ) throws ValueError {
        switch (addressKind) {
            case ORDINARY:
                return new DecAddress (row, stride, col);
            case SPIRAL:
                return new SaAddress (row, stride, col);
            case SQUIRAL:
                return new SipAddress (row, stride, col);
            default:
                throw new IllegalStateException("Unexpected value: " + addressKind);
        }
    }

    /**
     * @return
     */
    EAddressKind getAddressKind ();

    static

    IAddress cache ( int i ) throws ValueError {
        return  create(cache.get(i), 1, 0,defaultAddressKind());//fixme
    }

    /**
     * @return
     */
    @Override
    long getIndex ();

    /**
     * @param index
     * @return
     */
    IAddress  newInstance ( long index ) throws ValueError;

    /**
     * @return
     */
    long get ();

    /**
     * @param address
     * @param offset
     * @return
     * @throws ValueError
     */
    default
    IAddress  newInstance ( long address, int offset ) throws ValueError {
        return newInstance(address + offset);
    }

    /**
     * @return
     */
    int radix ();

    /**
     *
     */
    long incrementAndGet ();

    /**
     * @return
     */
    long getAndIncrement ();

    /**
     * @return
     */
    long longValue ();

    /**
     * @return
     */
    int getX();

    /**
     * @return
     */
    int getY();
}
