package org.stranger2015.opencv.fic.core;

import java.util.List;

/**
 *
 */
public
class BidiImageColorModelTask extends LoadSaveImageTask {
    /**
     * @param tasks
     */
    public
    BidiImageColorModelTask ( String filename, EPartitionScheme scheme, List <Task> tasks ) {
        super(filename, scheme, tasks);
    }

    /**
     * @param task
     * @param inverseTask
     */
    public
    BidiImageColorModelTask ( String filename,
EPartitionScheme scheme,
                              RgbToYuvImageColorModelTask task,
                              YuvToRgbImageColorModelTask inverseTask ) {
        super(filename, scheme, task, inverseTask);
    }
}

