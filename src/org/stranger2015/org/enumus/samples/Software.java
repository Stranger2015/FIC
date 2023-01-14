package org.stranger2015.opencv.fic.core.enumus.enumus.samples;

import org.stranger2015.opencv.fic.core.enumus.enumus.initializer.Initializable;
import org.stranger2015.opencv.fic.core.enumus.enumus.initializer.Value;

import static org.stranger2015.opencv.fic.core.enumus.enumus.samples.OsType.Unix;
import static org.stranger2015.opencv.fic.core.enumus.enumus.samples.OsType.Windows;

public enum Software implements Initializable {
    MS_WORD("Microsoft Word", "Microsoft Office", "HKLM/Microsoft/Office/Word", "/Program Files/Microsoft Office/Microsoft Word", new OsType[] {Windows}, null),

    //INTELLIJ("IntelliJ IDEA", null, null, null, new OsType[] {OsType.Windows, OsType.Unix}, RuntimeEnvironment.JAVA),

    @Value(name = "title", value = "IntelliJ IDEA")
    @Platform(name = "operatingSystem", os = {Windows, Unix})
    @Environment(name = "environemnt", value = RuntimeEnvironment.JAVA)
    INTELLIJ(){},

    ECLIPSE(),
    ;


    private final String title;
    private final String parentTitle;
    private final String registryPath;
    private final String path;
    private final OsType[] operatingSystems;
    private final RuntimeEnvironment environment;

    Software() {
        this.title = $();
        this.parentTitle = $();
        this.registryPath = $();
        this.path = $();
        this.operatingSystems = $();
        this.environment = $();

    }


    Software(String title, String parentTitle, String registryPath, String path, OsType[] operatingSystems, RuntimeEnvironment environment) {
        this.title = title;
        this.parentTitle = parentTitle;
        this.registryPath = registryPath;
        this.path = path;
        this.operatingSystems = operatingSystems;
        this.environment = environment;
    }
}
