package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.stranger2015.org.enumus.Hierarchy;

/**
 *
 */
public
enum  EPartitionScheme implements ICodecs {
    FIXED_SIZE(CODEC, "", "", null, null),
    BIN_TREE(BIN_TREE_CODEC, BIN_TREE_ENCODER, BIN_TREE_DECODER, null, null),
    DCT(DCT_CODEC, DCT_ENCODER, DCT_DECODER, null, null),
    CONST_SIZE_DOMAIN_POOL(CSDP_CODEC, CSDP_ENCODER, CSDP_DECODER, null, null),
    ABP(CODEC, "", "", BIN_TREE, null),
    HV(HV_CODEC, HV_ENCODER, HV_DECODER, null, null),
    IRREGULAR(CODEC, "", "", null, null),
    QUADRILATERAL("", "", "", null, null),
    QUAD_TREE(QUAD_TREE_CODEC, QUAD_TREE_ENCODER, QUAD_TREE_DECODER, null, null),
    TRIANGULAR("", "", "", null, null),
    SPLIT_AND_MERGE_0(SAM0_CODEC,SAM0_ENCODER ,SAM0_DECODER, BIN_TREE),
    SPLIT_AND_MERGE_1("", "", "", null, null),//ABP

    SEARCHLESS(SEARCHLESS_CODEC, SEARCHLESS_ENCODER, SEARCHLESS_DECODER, null, null),
    UNIFORM_SQUARE("", "", "", null, null),
    TWO_LEVEL_SQUARE("", "", "", null, null),
    SA_0(SA_CODEC, "", "", null, null),
    SA_BVR(SA_BVR_CODEC, "", "", SA_0),

    SIP_BVR(SIP_BVR_CODEC, "", "", null),
    SIP(SIP_CODEC, SIP_ENCODER, SIP_DECODER, null, null),
    FA_FE_EV(FA_FE_EV_CODEC, FA_FE_EV_ENCODER, FA_FE_EV_DECODER, null, null),
    ;

    private
    static
    final Hierarchy <EPartitionScheme> hierarchy = new Hierarchy <>(EPartitionScheme.class, e -> e.parent);

    private final String decoderClassName;

    private final EPartitionScheme parent;
    private final EPartitionScheme backend;

    private final String codecClassName;
    private final String encoderClassName;

    /**
     * @param codecClassName
     * @param parent
     * @param backend
     */
    @Contract(pure = true)
    EPartitionScheme ( String codecClassName,
                       String encoderClassName,
                       String decoderClassName,
                       EPartitionScheme parent,
                       EPartitionScheme backend
    ) {
        this.codecClassName = codecClassName;
        this.encoderClassName = encoderClassName;
        this.decoderClassName = decoderClassName;
        this.parent = parent;
        this.backend = backend;
    }

    /**
     * @param codecClassName
     * @param parent
     */
    @Contract(pure = true)
    EPartitionScheme ( String codecClassName,
                       String encoderClassName,
                       String decoderClassName,
                       EPartitionScheme parent) {

        this(codecClassName, encoderClassName, decoderClassName, parent, null);
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
    @Contract(pure = true)
    public
    EPartitionScheme getParent () {
        return parent;
    }

    /**
     * @return
     */
    @Contract(pure = true)
    public
    EPartitionScheme getBackend () {
        return backend;
    }

    /**
     * @return
     */
    @Contract(pure = true)
    public @NotNull
    String getCodecClassName () {
        return PATH + codecClassName;
    }

    /**
     * @return
     */
    @Contract(pure = true)
    public @NotNull
    String getEncoderClassName () {
        return PATH + encoderClassName;
    }

//    /**
//     * @return
//     */
//    @Contract(pure = true)
//    public @NotNull
//    String getDecoderClassName () {
//        return PATH + decoderClassName;
//    }
//    @Contract(value = "_ -> !null", pure = true)
//    String getTilerClassName ( String encoderClassName) {
//        Map<String, String> mapper=new HashMap <>();
//        mapper.put(BIN_TREE_ENCODER, );
//
//        return PATH + encoderClassName;
//    }
}
