package be.webtechie.timeline;

import static java.util.Map.entry;

import be.webtechie.piheaders.definition.PiBoardVersion;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public enum DataSet {
    LANGUAGE_BIRTHDATES(1990, 2024, Map.ofEntries(
            entry(1990, "Ruby\n "),
            entry(1991, "Python\n "),
            entry(1995, "Java, JavaScript, PHP, Qt"),
            entry(2000, "C#"),
            entry(2009, "Go"),
            entry(2014, "Swift"))),
    JAVA_HISTORY(1990, 2024, Map.ofEntries(
            entry(1995, "Initial Java platform released by\nSun Microsystems"),
            entry(2007, "Sun relinces under the\nGNU General Public License"),
            entry(2010, "Oracle acquires the Java ecosystem\nas part of Sun Microsystems deal"),
            entry(2014, "Oracle releases Java 8 LTS,\nthe latest free public update"),
            entry(2018, "Latest free public release\nof Java 8 by Oracle"))),
    JAVA_RELEASES(1990, 2024, Map.ofEntries(
            entry(1995, "JDK Beta\n "),
            entry(1996, "JDK 1.0\n "),
            entry(1997, "JDK 1.1\n "),
            entry(1998, "J2SE 1.2\n "),
            entry(2000, "J2SE 1.3\n "),
            entry(2002, "J2SE 1.4\n "),
            entry(2004, "J2SE 1.5\n "),
            entry(2006, "Java SE 6\n "),
            entry(2011, "Java SE 7\n "),
            entry(2014, "Java SE 8 (LTS)\n "),
            entry(2017, "Java SE 9\n "),
            entry(2018, "Java SE 10 + 11 (LTS)\n "),
            entry(2019, "Java SE 12 + 13 (RC) + 14 (EA)\n "))),
    JAVAFX_HISTORY(2005, 2024, Map.ofEntries(
            entry(2008, "JavaFX 1.0 released by Sun\n "),
            entry(2009, "JavaFX 1.1 & 1.2 mobile development, css, charts...\n "),
            entry(2010, "JavaFX 1.3 better performance, additional platforms\n "),
            entry(2011, "JavaFX 2.0 platform specific runtime, open-sourced\n "),
            entry(2012, "JavaFX 2.1 & 2.2 OSX, audio, video, touch events...\n "),
            entry(2014, "JavaFX 8 part of Java8, 3D, sensor support\n "),
            entry(2017, "JavaFX 9 controls and css modularized\n "),
            entry(2018, "JavaFX 11\n "),
            entry(2019, "JavaFX 12 and JavaFX 13\n "),
            entry(2020, "JavaFX 14\n "))),
    RASPBERRYPI_BOARDS(2005, 2024, getPiBoards());

    private final int startYear;
    private final int endYear;
    private final Map<Integer, String> entries;

    DataSet(int startYear, int endYear, Map <Integer, String> entries) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.entries = entries;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public Map<Integer, String> getEntries() {
        return this.entries;
    }

    private static Map<Integer, String> getPiBoards() {
        Map<Integer, String> entries = new HashMap<>();

        // Add entry for every unique year
        for (PiBoardVersion pi : Arrays.stream(PiBoardVersion.values())
                .sorted(Comparator.comparing(PiBoardVersion::getReleaseDate))
                .collect(Collectors.toList())) {
            String alreadyAdded = entries.get(pi.getReleaseDate().getYear());
            String name = pi.getLabel().replace("Module", "M.");
            entries.put(
                    pi.getReleaseDate().getYear(),
                    alreadyAdded == null ? name : alreadyAdded + ", " + name
            );
        }

        // Add new line to better align on the drawing
        for (Entry<Integer, String> entry : entries.entrySet()) {
            entry.setValue(entry.getValue() + "\n ");
        }

        return entries;
    }
}
