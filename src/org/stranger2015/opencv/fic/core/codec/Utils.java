package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.EPartitionScheme;
import org.stranger2015.opencv.fic.utils.Point;

import java.lang.reflect.Constructor;

/**
 * useful generic utility tools
 */
public
class Utils {

    /**
     * @param index
     * @param width
     * @return
     */
    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull
    Point indexToPoint ( final int index, final int width ) {
        assert (index >= 0) && (width >= 0);

        return new Point(index / width, index % width);
    }

    /**
     * @param point
     * @param width
     * @return
     */
    @Contract(pure = true)
    public static
    int pointToIndex ( final Point point, final int width ) {
        assert (point != null) && (point.getX() >= 0) && (point.getY() >= 0) && (width >= 0);

        return (int) (point.getX() * width + point.getY());
    }

//    /**
//     * @param className
//     * @param listeners
//     * @param parameterTypes
//     * @param parameters
//     * @return
//     * @throws ReflectiveOperationException
//     */
//    public static @NotNull
//    Object create ( String className,
//                    @Unmodifiable List <IListener <?>> listeners,
//                    ImageBlockClassifier[] parameterTypes,
//                    Object[] parameters )
//            throws ReflectiveOperationException {
//
//       Class <?> clazz = ImageBlockClassifier.class.forName(className);
//       Constructor <?> ctor = clazz.getConstructor(parameterTypes);
//
//        return ctor.newInstance(parameters);
//    }

    /**
     * @param runnable
     */
    public static
    void invoke ( Runnable runnable ) {
        runnable.run();
    }

    public static @NotNull
    Object invoke ( EPartitionScheme scheme, Constructor <?> constructor, Object... objects )
            throws ReflectiveOperationException {

        return constructor.newInstance(scheme, objects);
    }
}
