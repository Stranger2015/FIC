package org.stranger2015.opencv.fic.core;

/**
 *
 */
public
interface ICodecs {
    String CODEC = "DefaultCodec";
    String HV_CODEC = "HvCodec";
    String CSDP_CODEC = "CsDpCodec";
    String BIN_TREE_CODEC = "BinTreeCodec";//graph
    String QUAD_TREE_CODEC = "QuadTreeCodec";
    String SEARCHLESS_CODEC = "SearchlessCodec";
    String SA_CODEC = "SaCodec";
    String SAM0_CODEC = "SplitAndMergeCodec";
    String SA_BVR_CODEC = "SabVrCodec";
    String SIP_CODEC = "SipCodec";
    String SIP_BVR_CODEC = "SipbVrCodec";
    String DCT_CODEC = "DctCodec";
    String FA_FE_EV_CODEC = "FaFeEvCodec";

    String SAM0_ENCODER = "DtSplitAndMergeEncoder";
    String SAM0_DECODER = "DtSplitAndMergeDecoder";
    String SIP_ENCODER = "SipEncoder";
    String SIP_DECODER = "SipDecoder";
    String HV_ENCODER = "HvEncoder";
    String HV_DECODER = "HvDecoder";
    String CSDP_ENCODER = "CsDpEncoder";
    String CSDP_DECODER = "CsDpDecoder";
    String QUAD_TREE_ENCODER = "QuadTreeEncoder";
    String QUAD_TREE_DECODER = "QuadTreeDecoder";
    String DCT_ENCODER = "DctEncoder";
    String DCT_DECODER = "DctDecoder";
    String BIN_TREE_ENCODER = "BinTreeEncoder";
    String BIN_TREE_DECODER = "BinTreeDecoder";
    String SEARCHLESS_ENCODER = "SearchlessEncoder";
    String SEARCHLESS_DECODER = "SearchlessDecoder";
    String FA_FE_EV_ENCODER = "FaFeEvEncoder";
    String FA_FE_EV_DECODER = "FaFeEvDecoder";

//==================

    String PATH = "org.stranger2015.opencv.fic.core.codec.";
}
