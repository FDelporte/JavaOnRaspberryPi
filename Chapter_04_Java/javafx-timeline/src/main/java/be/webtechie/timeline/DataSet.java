package be.webtechie.timeline;

import com.pi4j.boardinfo.definition.BoardModel;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum DataSet {
    LANGUAGE_BIRTHDAYS(1990, 2016, true, List.of(
            new Release(1991, 2, "Python"),
            new Release(1995, 2, "Ruby"),
            new Release(1995, 5, "Java, Qt", true),
            new Release(1995, 6, "PHP"),
            new Release(1995, 9, "JavaScript"),
            new Release(2000, 6, "C#"),
            new Release(2009, 11, "Go"),
            new Release(2014, 6, "Swift"))),
    JAVA_HISTORY(1990, 2024, false, List.of(
            new Release(1995, 5, "Initial Java platform released by\nSun Microsystems"),
            new Release(2007, 5, "Sun - releases Java under the\nGNU General Public License"),
            new Release(2010, 1, "Oracle acquires the Java ecosystem\nas part of Sun Microsystems deal"),
            new Release(2014, 3, "Oracle releases Java 8 LTS"),
            new Release(2018, 1, "Latest free public release\nof Java 8 by Oracle"))),
    // https://www.java.com/releases/
    JAVA_RELEASES(1994, 2030, true, List.of(
            new Release(1995, 5, "JDK Beta"),
            new Release(1996, 1, "JDK 1.0"),
            new Release(1997, 2, "JDK 1.1"),
            new Release(1998, 12, "J2SE 1.2"),
            new Release(2000, 5, "J2SE 1.3"),
            new Release(2002, 2, "J2SE 1.4"),
            new Release(2004, 9, "J2SE 1.5"),
            new Release(2006, 12, "Java SE 6"),
            new Release(2011, 7, "Java SE 7"),
            new Release(2014, 3, "Java SE 8 (LTS)", true),
            new Release(2017, 9, "Java SE 9"),
            new Release(2018, 3, "Java SE 10"),
            new Release(2018, 9, "Java SE 11 (LTS)", true),
            new Release(2019, 3, "Java SE 12"),
            new Release(2019, 9, "Java SE 13"),
            new Release(2020, 3, "Java SE 14"),
            new Release(2020, 9, "Java SE 15"),
            new Release(2021, 3, "Java SE 16"),
            new Release(2021, 9, "Java SE 17 (LTS)", true),
            new Release(2022, 3, "Java SE 18"),
            new Release(2022, 9, "Java SE 19"),
            new Release(2023, 3, "Java SE 20"),
            new Release(2023, 9, "Java SE 21 (LTS)", true),
            new Release(2024, 3, "Java SE 22"),
            new Release(2024, 9, "Java SE 23"),
            new Release(2025, 3, "Java SE 24"),
            new Release(2025, 9, "Java SE 25 (LTS)", true),
            new Release(2026, 3, "Java SE 26"),
            new Release(2026, 9, "Java SE 27"),
            new Release(2027, 3, "Java SE 28"),
            new Release(2027, 9, "Java SE 29 (LTS)", true),
            new Release(2028, 3, "Java SE 30"),
            new Release(2028, 9, "Java SE 31"),
            new Release(2029, 3, "..."))),
    JAVAFX_RELEASES(2005, 2030, true, List.of(
            new Release(2008, 12, "JavaFX 1.0 released by Sun"),
            new Release(2009, 2, "JavaFX 1.1 mobile development, css"),
            new Release(2009, 6, "JavaFX 1.2 charts and additional features"),
            new Release(2010, 4, "JavaFX 1.3 better performance, additional platforms"),
            new Release(2011, 10, "JavaFX 2.0 platform specific runtime, open-sourced"),
            new Release(2012, 2, "JavaFX 2.1 OSX support"),
            new Release(2012, 8, "JavaFX 2.2 audio, video, touch events"),
            new Release(2014, 3, "JavaFX 8 part of Java 8, 3D, sensor support"),
            new Release(2017, 9, "JavaFX 9 controls and css modularized"),
            new Release(2018, 9, "JavaFX 11"),
            new Release(2019, 3, "JavaFX 12"),
            new Release(2019, 9, "JavaFX 13"),
            new Release(2020, 3, "JavaFX 14"),
            new Release(2020, 9, "JavaFX 15"),
            new Release(2021, 3, "JavaFX 16"),
            new Release(2021, 9, "JavaFX 17"),
            new Release(2022, 3, "JavaFX 18"),
            new Release(2022, 9, "JavaFX 19"),
            new Release(2023, 3, "JavaFX 20"),
            new Release(2023, 9, "JavaFX 21"),
            new Release(2024, 3, "JavaFX 22"),
            new Release(2024, 9, "JavaFX 23"),
            new Release(2025, 3, "JavaFX 24"),
            new Release(2025, 9, "JavaFX 25"),
            new Release(2026, 3, "JavaFX 26"),
            new Release(2026, 9, "JavaFX 27"),
            new Release(2027, 3, "JavaFX 28"),
            new Release(2027, 9, "JavaFX 29"),
            new Release(2028, 3, "JavaFX 30"),
            new Release(2028, 9, "JavaFX 31"),
            new Release(2029, 3, "..."))),
    RASPBERRYPI_BOARDS(2010, getPiBoardsEndYear() + 1, true, getPiBoards()),
    PI4J_RELEASES(2010, 2027, true, List.of(
            new Release(2012, 1, "Project started"),
            new Release(2015, 4, "V1.0 - Java 8", true),
            new Release(2016, 7, "V1.1"),
            new Release(2019, 2, "V1.2"),
            new Release(2021, 1, "V1.3"),
            new Release(2021, 3, "V1.4 - Java 11"),
            new Release(2021, 8, "V2.0.0 - Java 11", true),
            new Release(2021, 10, "V2.1.0"),
            new Release(2021, 12, "V2.1.1"),
            new Release(2022, 8, "V2.2.0"),
            new Release(2023, 2, "V2.3.0"),
            new Release(2023, 10, "V2.4.0"),
            new Release(2024, 3, "V2.5.0 - RPi 5"),
            new Release(2024, 4, "V2.6.0"),
            new Release(2024, 7, "V2.6.1"),
            new Release(2024, 10, "V2.7.0"),
            new Release(2025, 1, "V2.8.0"),
            new Release(2025, 3, "V3.0.1 - Java 21", true),
            new Release(2025, 5, "V3.0.2"),
            new Release(2025, 12, "V4.0.0 - Java 25 + FFM API", true)));

    private final int startYear;
    private final int endYear;
    private final boolean alternate;
    private final List<Release> releases;

    DataSet(int startYear, int endYear, boolean alternate, List<Release> releases) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.alternate = alternate;
        this.releases = releases;
    }

    private static List<Release> getPiBoards() {
        return Arrays.stream(BoardModel.values())
                .filter(bm -> bm.getReleaseDate() != null
                        && !bm.getName().contains("GENERIC")
                        && !bm.getName().contains("UNKNOWN"))
                .sorted(Comparator.comparing(BoardModel::getReleaseDate))
                .map(bm ->
                        new Release(bm.getReleaseDate().getYear(),
                                bm.getReleaseDate().getMonthValue(),
                                bm.getLabel().replace("Module", "M.")))
                .toList();
    }

    private static int getPiBoardsEndYear() {
        return getPiBoards().getLast().year();
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public boolean isAlternate() {
        return alternate;
    }

    public List<Release> getReleases() {
        return releases;
    }

    public record Release(int year, int month, String description, boolean highlight) {
        Release(int year, int month, String description) {
            this(year, month, description, false);
        }
    }
}
