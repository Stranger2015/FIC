package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.org.enumus.Hierarchy;

/**
 *
 */
public
enum EPartitionScheme implements IEncoders {
    
    FIXED_SIZE(ENCODER),
    BIN_TREE(ENCODER),
    CONST_SIZE_DOMAIN_POOL(CSDP_ENCODER),
    ABP(ENCODER, BIN_TREE),
    HV(HV_ENCODER),
    IRREGULAR(ENCODER),
    QUADRILATERAL(""),
    QUAD_TREE(QUAD_TREE_ENCODER),
    TRIANGULAR(""),
    SPLIT_AND_MERGE_0("", QUAD_TREE),
    SPLIT_AND_MERGE_1("", ABP),

    SEARCHLESS(SEARCHLESS_ENCODER),
    UNIFORM_SQUARE(""),
    TWO_LEVEL_SQUARE(""),
    VSA_0(VSA_ENCODER, null, QUAD_TREE),
    SABVR(SABVR_ENCODER, VSA_0),
    SIP(SIP_ENCODER),
    ;

    private final EPartitionScheme parent;
    private final EPartitionScheme backend;

    private final String encoderClassName;

    /**
     *
     *
     * @param encoderClassName
     * @param parent
     * @param backend
     */
    @Contract(pure = true)
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
