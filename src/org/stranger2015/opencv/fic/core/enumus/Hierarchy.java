package org.stranger2015.opencv.fic.core.enumus;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;

/**
 * @param <T>
 */
public
class Hierarchy<T extends Enum <T>> {
    private final Function <T, T> parentAccessor;
    private final Map <T, T[]> children = new HashMap <>();


    /**
     * @param type
     * @param parentAccessor
     */
    public
    Hierarchy ( Class <T> type, Function <T, T> parentAccessor ) {
        this.parentAccessor = parentAccessor;

        Map <T, Collection <T>> hm = new HashMap <>();
        Arrays.stream(type.getEnumConstants()).forEach(e -> {
            T parent = parentAccessor.apply(e);
            Collection <T> l = hm.getOrDefault(parentAccessor.apply(e), new ArrayList());
            l.add(e);
            hm.put(parent, l);
            hm.putIfAbsent(e, new ArrayList());
        });

        populateChildren(type, hm);
    }

    /**
     * @param type
     * @param hm
     */
    @SuppressWarnings("unchecked")
    private
    void populateChildren ( Class <T> type, Map <T, Collection <T>> hm ) {
        hm.forEach(( key, value ) -> children.put(key, value.toArray((T[]) Array.newInstance(type, value.size()))));
    }

    @SuppressWarnings("WeakerAccess") // can be called from outside this class if needed.
    public
    T getParent ( T element ) {
        return parentAccessor.apply(element);
    }

    /**
     * @param element
     * @return
     */
    public
    T[] getChildren ( T element ) {
        return children.get(element);
    }

    /**
     * @param ascendant
     * @param descendant
     * @return
     */
    // ascendant -- parent
    // descendant -- child
    public
    boolean relate ( T ascendant, T descendant ) {
        boolean result = false;
        for (T elem = descendant; elem != null; elem = parentAccessor.apply(elem)) {
            if (elem == ascendant) {
                result = true;
                break;
            }
        }
        return result;
    }

    public
    <R> R invoke ( T element, Function <Object[], R> defaultImpl, Object... args ) {
        String methodName = new Throwable().getStackTrace()[1].getMethodName();
        for (T elem = element; elem != null; elem = getParent(elem)) {
            Optional <R> result = invokeMethod(elem, methodName, args);
            if (result.isPresent()) {
                return result.get();
            }
        }

        return defaultImpl.apply(args);
    }


    private
    <R> Optional <R> invokeMethod ( T object, String methodName, Object... args ) {
        @SuppressWarnings("unchecked")
        Class <T> clazz = (Class <T>) object.getClass();
        Class <T> enumClass = object.getDeclaringClass();


        if (enumClass.equals(clazz)) {
            return Optional.empty();
        }

        Optional <Method> method = Arrays.stream(clazz.getMethods()).filter(m -> m.getName().equals(methodName)).findFirst();
        if (method.isPresent()) {
            if (method.get().getDeclaringClass().equals(enumClass)) {
                return Optional.empty();
            }
            try {
                Method IImage = method.get();
                m.setAccessible(true);
                @SuppressWarnings("unchecked")
                R result = (R) m.invoke(object, args);
                return Optional.ofNullable(result);
            } catch (ReflectiveOperationException e) {
                throw new IllegalStateException(e);
            }
        }
        return Optional.empty();
    }
}
