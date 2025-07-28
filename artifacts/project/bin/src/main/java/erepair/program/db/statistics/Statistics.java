package erepair.program.db.statistics;

import erepair.program.InputFormat;
import erepair.program.Loggable;
import erepair.program.Main;
import erepair.program.db.FileDatabase;
import erepair.program.subject.Subjects;
import erepair.program.Algorithm;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 **/
public abstract class Statistics extends Loggable {
    /**
     * A set of all statistics that should be built when all statistics are built.
     */
    public static final Set<Statistics> STATISTICS;

    static {
        final Reflections reflections = new Reflections("erepair.program.db.statistics", new TypeAnnotationsScanner());
        final Set<Class<?>> classes = reflections.getTypesAnnotatedWith(BuildStatistics.class, true);
        STATISTICS = new HashSet<>();
        classes.forEach(statistic -> {
            try {
                if (statistic.isAnnotationPresent(ForEachAlgorithmStatistics.class)) { // Instantiate for each algorithm
                    Arrays.stream(Algorithm.values()).filter(x -> x != Algorithm.INVALID)
                            .forEach(algorithm -> {
                                try {
                                    STATISTICS.add((Statistics) statistic.getConstructor(Algorithm.class).newInstance(algorithm));
                                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                                    e.printStackTrace();
                                    System.exit(1);
                                }
                            });
                } else if (statistic.isAnnotationPresent(ForEachFormatStatistics.class)) { // Instantiate for each format
                    Arrays.stream(InputFormat.values()).filter(x -> x != InputFormat.INVALID)
                            .forEach(format -> {
                                try {
                                    STATISTICS.add((Statistics) statistic.getConstructor(InputFormat.class).newInstance(format));
                                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                                    e.printStackTrace();
                                    System.exit(1);
                                }
                            });
                } else if (statistic.isAnnotationPresent(ForEachSubjectStatistics.class)) { // Instantiate for each subject
                    Arrays.stream(Subjects.values()).filter(x -> x != Subjects.INVALID)
                            .forEach(subject -> {
                                try {
                                    STATISTICS.add((Statistics) statistic.getConstructor(Subjects.class).newInstance(subject));
                                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                                    e.printStackTrace();
                                    System.exit(1);
                                }
                            });
                } else {
                    STATISTICS.add((Statistics) statistic.getDeclaredConstructor().newInstance());
                }
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                System.exit(1);
            }
        });
    }

    String name;
    String key;

    public Statistics(String key, String name) {
        super(key);
        this.key = key;
        this.name = name;
    }

    /**
     * Get the location of this Statistics object as human-readable String, e.g. "/home/MaxMustermann/out.csv"
     * or "https://sheets.google.com/example"
     *
     * @return a human-readable String representation of the location
     */
    public abstract String getLocation();

    final FileDatabase getDatabase() {
        return Main.GLOBAL_DATABASE;
    }

    public final boolean build() {
//        log(Level.INFO, "Building statistics: " + this.name);
        return this.buildStatistics();
    }

    abstract boolean buildStatistics();

    /**
     * Checks if this object is a google sheet, i.e. should be included in the JSON file for google sheets
     *
     * @return
     */
    boolean isGoogleSheet() {
        return false;
    }

    /**
     * Get the unique key of a statistics object
     *
     * @return the unique key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Get the printable name of this Statistics
     *
     * @return the printable name
     */
    public String getDisplayName() {
        return this.name;
    }
}
