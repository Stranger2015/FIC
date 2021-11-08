
//#define QT_TRAVERSE(cell, nextLevel, xLocCode, yLocCode) {

//        while (cell->children) {

//        unsigned int childBranchBit = 1 << nextLevel;
//        unsigned int childIndex = ((xLocCode & childBranchBit) >> nextLevel) + ((yLocCode & childBranchBit) >> (--nextLevel));

//        cell = &((cell->children)[childIndex]);
//        }
//        }

/*
    typedef struct _qtCell {

    unsigned int xLocCode;
    unsigned int yLocCode;
    unsigned int level;

    struct _qtCell *parent;
    struct _qtCell *children;

    void *data; //generic data pointer
} qtCell;

#define QT_N_LEVELS 16 // Number of possible levels in the quadtree
#define QT_ROOT_LEVEL 15 // Level of root cell (QT_N_LEVELS - 1)
#define QT_MAX_VAL 32768.0f // For converting positions to locational codes
