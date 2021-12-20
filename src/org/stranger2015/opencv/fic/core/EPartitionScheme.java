package org.stranger2015.opencv.fic.core;

import org.stranger2015.org.enumus.Hierarchy;

/**
 *
 */
public
enum EPartitionScheme {
    
    FIXED_SIZE("org.stranger2015.opencv.fic.core.codec.Encoder"),
    BIN_TREE("org.stranger2015.opencv.fic.core.codec.Encoder"),
    CONST_SIZE_DOMAIN_POOL("org.stranger2015.opencv.fic.core.codec.ConstSizeDomainPoolEncoder"),
    ABP("org.stranger2015.opencv.fic.core.codec.Encoder", BIN_TREE),
    HV("org.stranger2015.opencv.fic.core.codec.HvEncoder"),
    IRREGULAR("org.stranger2015.opencv.fic.core.codec.Encoder"),
    QUADRILATERAL(""),
    QUAD_TREE("org.stranger2015.opencv.fic.core.codec.QuadTreeEncoder"),
    TRIANGULAR(""),
    SPLIT_AND_MERGE_0("", QUAD_TREE),
    SPLIT_AND_MERGE_1("", ABP),

    SEARCHLESS("org.stranger2015.opencv.fic.core.codec.SearchlessEncoder"),
    UNIFORM_SQUARE(""),
    TWO_LEVEL_SQUARE(""),
    VSA_0("org.stranger2015.opencv.fic.core.codec.VsaEncoder", null, QUAD_TREE),
    SABVR("org.stranger2015.opencv.fic.core.codec.SabvrEncoder", VSA_0),
    SQUIRAL("org.stranger2015.opencv.fic.core.codec.SipEncoder"),
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
