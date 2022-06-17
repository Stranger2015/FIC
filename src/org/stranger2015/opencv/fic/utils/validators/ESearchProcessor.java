package org.stranger2015.opencv.fic.utils.validators;

import org.jetbrains.annotations.Contract;

/**
 *
 */
public
enum ESearchProcessor {
    EXHAUSTIVE_SEARCH("ExhaustiveSearchProcessor"),
    GA_SEARCH("GaSearchProcessor"),
    ANN_SEARCH("AnnSearchProcessor");

    private final String className;
    private final static String PATH = "org.stranger2015.opencv.";

    /**
     * @param className
     */
    @Contract(pure = true)
    ESearchProcessor ( String className ) {
        this.className = className;
    }

    /**
     * @return
     */
    @Contract(pure = true)
    public
    String getClassName () {
        return PATH + className;
    }
}
