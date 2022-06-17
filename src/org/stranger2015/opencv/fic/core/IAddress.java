package org.stranger2015.opencv.fic.core;

import jdk.internal.HotSpotIntrinsicCandidate;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.codec.PointAddress;
import org.stranger2015.opencv.fic.utils.Point;

import java.util.concurrent.atomic.AtomicLongArray;

import static org.stranger2015.opencv.fic.core.EAddressKind.ORDINARY;

/**
 * long address
 */
public
interface IAddress<A extends IAddress <A>> extends IAddressMath <A> {

    AtomicLongArray cache = new AtomicLongArray(128 + 127 + 1);

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
     * @param l a long value.
     * @return a {@code Long} instance representing {@code l}.
     * @since 1.5
     */
    @HotSpotIntrinsicCandidate
    static
    <A extends IAddress <A>>
    IAddress <A> valueOf ( long l ) throws ValueError {
        return valueOf(l, defaultAddressKind());
    }

    static
    <A extends IAddress <A>>
    @NotNull IAddress <A> valueOf ( long l, EAddressKind addressKind ) throws ValueError {
        long result;
        final int offset = 128;
        if (l >= -128 && l <= 127) { // will cache
            result = cache((int) l + offset).get();
        }
        else {
            result = l;
        }

        return create(result, addressKind);
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
     * @param <A>
     * @return
     * @throws ValueError
     */
    @Contract("_, _ -> new")
    static
    <A extends IAddress <A>>
    @NotNull IAddress <A> create ( long result, EAddressKind addressKind ) throws ValueError {
        switch (addressKind) {
            case POINT:
                return new PointAddress <>((int) result,0);
            case ORDINARY:
                return new DecAddress <>(result);
            case SPIRAL:
                return new SaAddress <>(result);
            case SQUIRAL:
                return new SipAddress <>(result);
            default:
                throw new IllegalStateException("Unexpected value: " + addressKind);
        }
    }

    /**
     * @return
     */
    EAddressKind getAddressKind ();

    static
    <A extends IAddress <A>>
    IAddress <A> cache ( int i ) throws ValueError {
        return  create(cache.get(i), defaultAddressKind());
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
    IAddress <A> newInstance ( long index ) throws ValueError;

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
    IAddress <A> newInstance ( long address, int offset ) throws ValueError {
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
}
