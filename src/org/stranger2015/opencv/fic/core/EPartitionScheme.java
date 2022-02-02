package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.stranger2015.org.enumus.Hierarchy;

/**
 *
 */
public
enum EPartitionScheme implements ICodecs {

    FIXED_SIZE(CODEC),
    BIN_TREE(CODEC),
    DCT(DCT_CODEC),
    CONST_SIZE_DOMAIN_POOL(CSDP_CODEC),
    ABP(CODEC, BIN_TREE),
    HV(HV_CODEC),
    IRREGULAR(CODEC),
    QUADRILATERAL(""),
    QUAD_TREE(QUAD_TREE_CODEC),
    TRIANGULAR(""),
    SPLIT_AND_MERGE_0("", QUAD_TREE),
    SPLIT_AND_MERGE_1("", ABP),

    SEARCHLESS(SEARCHLESS_CODEC),
    UNIFORM_SQUARE(""),
    TWO_LEVEL_SQUARE(""),
    SA_0(SA_CODEC, null, QUAD_TREE),
    SABVR(SABVR_CODEC, SA_0),
    SIP(SIP_CODEC),
    ;

    private static final Hierarchy <EPartitionScheme> hierarchy =
            new Hierarchy <>(EPartitionScheme.class, e -> e.parent);
    private final EPartitionScheme parent;
    private final EPartitionScheme backend;
    private final String codecClassName;

    /**
     * @param codecClassName
     * @param parent
     * @param backend
     */
    @Contract(pure = true)
    EPartitionScheme ( String codecClassName, EPartitionScheme parent, EPartitionScheme backend ) {
        this.codecClassName = codecClassName;
        this.parent = parent;
        this.backend = backend;
    }

    EPartitionScheme ( String codecClassName, EPartitionScheme parent ) {
        this(codecClassName, parent, null);
    }

    EPartitionScheme ( String codecClassName ) {
        this(codecClassName, null);
    }

    /**
     * @return
     */
    public
    EPartitionScheme[] children () {
        return hierarchy.getChildren(this);
    }

    /**
     * @param other
     * @return
     */
    public
    boolean isA ( EPartitionScheme other ) {
        return hierarchy.relate(other, this);
    }

    /**
     * @return
     */
    public
    EPartitionScheme getParent () {
        return parent;
    }

    /**
     * @return
     */
    public
    EPartitionScheme getBackend () {
        return backend;
    }

    /**
     * @return
     */
    public
    String getCodecClassName () {
        return codecClassName;
    }
}
