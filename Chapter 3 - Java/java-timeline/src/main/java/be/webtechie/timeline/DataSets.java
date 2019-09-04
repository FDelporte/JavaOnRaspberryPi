package be.webtechie.timeline;

import static java.util.Map.entry;

import java.util.Map;

public class DataSets {

    public static final Map<Integer, String> JAVA_HISTORY = Map.ofEntries(
            entry(1995, "Sun Microsystems\n "),
            entry(2007, "GNU General Public License\n "),
            entry(2014, "Oracle releases Java 8 LTS,\nthe latest free public update"),
            entry(2018, "First stable fully open JDK available\nthrough multiple providers")
    );

    public static final Map<Integer, String> JAVA_RELEASES = Map.ofEntries(
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
            entry(2019, "Java SE 12 + 13 (RC) + 14 (EA)\n ")
    );

    public static final Map<Integer, String> JAVAFX_HISTORY = Map.ofEntries(
            // TODO
            // entry(1995, "Sun Microsystems\n "),
            // entry(2007, "GNU General Public License\n "),
            // entry(2014, "Oracle releases Java 8 LTS,\nthe latest free public update"),
            // entry(2018, "First stable fully open JDK available\nthrough multiple providers")
    );

}
