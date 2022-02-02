package org.stranger2015.opencv.fic.core;

import java.util.List;

/**
 *
 */
public
class NormalizeImageShapeTask extends LoadSaveImageTask {

    /**
     * @param fileName
     * @param tasks
     */
    public
    NormalizeImageShapeTask ( String fileName, EPartitionScheme scheme, Task... tasks ) {
        super(fileName, scheme, tasks);
    }

    /**
     * @param fileName
     * @param tasks
     */
    public
    NormalizeImageShapeTask ( String fileName, EPartitionScheme scheme, List <Task> tasks ) {
        super(fileName, scheme, tasks);
    }
}
