package org.stranger2015.opencv.fic.core;

import org.stranger2015.org.enumus.Hierarchy;

public
enum EPartitionScheme {
    FIXED_SIZE(""),
    BIN_TREE(""),
    ABP("", BIN_TREE),
    HV(""),
    IRREGULAR(""),
    QUADRILATERAL(""),
    QUAD_TREE(""),
    TRIANGULAR(""),
    SPLIT_AND_MERGE_0("", QUAD_TREE),
    SPLIT_AND_MERGE_1("", ABP),

    UNIFORM_SQUARE(""),
    TWO_LEVEL_SQUARE(""),
    VSA_0("", null, QUAD_TREE)
    ;

    private final EPartitionScheme parent;
    private final EPartitionScheme backend;

    private final String encoderClassName;

    EPartitionScheme ( String encoderClassName, EPartitionScheme parent, EPartitionScheme backend ) {
        this.encoderClassName= encoderClassName;
        this.parent = parent;
        this.backend = backend;
    }

    EPartitionScheme (String encoderClassName, EPartitionScheme parent ) {
        this(encoderClassName, parent, null);
    }

    EPartitionScheme (String encoderClassName) {
        this(encoderClassName, null);
    }

    private static final Hierarchy <EPartitionScheme> hierarchy = new Hierarchy <>(EPartitionScheme.class, e -> e.parent);

    public
    EPartitionScheme[] children () {
        return hierarchy.getChildren(this);
    }

    public
    boolean isA ( EPartitionScheme other ) {
        return hierarchy.relate(other, this);
    }

    public
    EPartitionScheme getParent () {
        return parent;
    }

    public
    EPartitionScheme getBackend () {
        return backend;
    }

    public
    String getEncoderClassName () {
        return encoderClassName;
    }
}
