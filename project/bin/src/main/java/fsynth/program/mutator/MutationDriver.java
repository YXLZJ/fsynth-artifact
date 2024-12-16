package fsynth.program.mutator;

import fsynth.program.InputFormat;
import fsynth.program.Parsing;
import fsynth.program.subject.Oracle;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class represents a mutation driver that aims to corrupt a given input file using different kinds of mutations.
 */
public class MutationDriver {
    /**
     * The maximum number of retries
     */
    public static final int maxretries = 10000;
    private static Random r = new Random();
    private static MutationMode[] availableMutationMethods;

    static {
        final Reflections reflections = new Reflections("fsynth.program.mutator.modes", new TypeAnnotationsScanner(), new SubTypesScanner());
        final Set<Class<?>> classes = reflections.getTypesAnnotatedWith(MutationModeImpl.class, true);
        availableMutationMethods = classes.stream()
                .map(mode -> {
                    try {
                        return (MutationMode) (mode.getDeclaredConstructor().newInstance());
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                })
                .toArray(MutationMode[]::new)
        ;
    }

    /**
     * MutationDriver is a static class
     */
    private MutationDriver() {
    }

    /**
     * Sets the seed of the random number generator used in the mutation driver
     *
     * @param seed Seed to use
     */
    public static void setSeed(long seed) {
        r = new Random(seed);
    }

    /**
     * Sets the random object used to generate randomness to the given object
     *
     * @param random Random to use
     */
    public static void setRandom(@Nonnull Random random) {
        r = random;
    }

    /**
     * Performs a random mutation on every file in the given input directory using the given algorithm
     * and saves the mutated files into the given output directory.
     * If no algorithm is specified, an arbitrary algorithm is used.
     * All methods that start with "random_" inside this class are considered an algorithm.
     * <p>
     * This method automatically determines the correct oracle to run based on the file suffix. (see {@link InputFormat#fromFileType}
     * </p>
     *
     * @param inputdirectory  Input Directory
     * @param outputdirectory Output Directory
     * @param algorithm       Algorithm to use. If null, an arbitrary one is chosen in every run
     * @param maxMutations    Maximum number of mutations per file
     */
    @SuppressWarnings("ObjectAllocationInLoop")
    public static void performMutations(String inputdirectory, String outputdirectory, String algorithm, int maxMutations) {
        final String preferredAlgorithm = algorithm == null ? "" : algorithm;
        ArrayList<MutationReportDatabaseObject> statistics = new ArrayList<>(512);
        Path[] files;
        try (Stream<Path> s = Files.walk(Paths.get(inputdirectory))) {
            files = s.filter(Files::isRegularFile).limit(300).toArray(Path[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Oracle.createDirectoryIfNotExists(Paths.get(outputdirectory));
        for (Path file : files) {
            Path outfile = Paths.get(outputdirectory, file.getFileName().normalize().toString()).toAbsolutePath();
            int retrynum = 0;
            while (retrynum < maxretries) {
                final int numOfRuns = maxMutations <= 1 ? 1 : r.nextInt(maxMutations) + 1;//nextInt takes an exclusive upper bound
                System.out.println("Mutating " + numOfRuns + " times.");
                MutationMode m;
                if ("".equals(preferredAlgorithm)) {
                    m = availableMutationMethods[r.nextInt(availableMutationMethods.length)];
                } else {
                    m = Arrays.stream(availableMutationMethods)
                            .filter(x -> (preferredAlgorithm)
                                    .equalsIgnoreCase(x.name()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("The mutation mode " + preferredAlgorithm + " was not found!"));
                }
                System.out.printf("Mutating %s using %s, Retry %d%n",
                        file.getFileName().normalize().toString(),
                        m.name(),
                        retrynum);
                try {
                    byte[] filecontents = Files.readAllBytes(file); // TODO unnecessary for many cases (MutationMode#changesArrayInplace())
                    if (filecontents.length < 1) throw new IOException("The file was empty!");
                    for (int i = 0; i < numOfRuns; i++) {
                        filecontents = m.run(filecontents, r);
                    }
                    Parsing.writeBinaryFile(outfile, filecontents);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                retrynum++;
                boolean allOraclesSucceeded = true;
                final String filename = outfile.toString();
                final InputFormat form = InputFormat.fromFileType(outfile);
                allOraclesSucceeded = Oracle.getSubjectsFor(form).stream().allMatch(
                        oracle -> oracle.run(outfile.normalize().toString(), null, null, null).wasSuccessful()
                );
                if (!allOraclesSucceeded) {
                    System.out.println("Oracle failed for one case. Aborting Loop");
                    statistics.add(new MutationReportDatabaseObject(retrynum - 1, m.name()));
                    break;
                }

                if (retrynum >= maxretries) {
                    statistics.add(new MutationReportDatabaseObject(maxretries, m.name()));
                    try {
                        Files.delete(outfile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("Mutation Driver finished. Detailled statistics:");
        Map<Integer, List<MutationReportDatabaseObject>> x = statistics.stream().collect(Collectors.groupingBy(MutationReportDatabaseObject::getMethodIndex));
        for (Integer meth : x.keySet()) {
            System.out.printf("Method: %15s Executions: %3d, Average %2.2f Retries%n",
                    x.get(meth).get(0).getMethodName(),
                    x.get(meth).size(),
                    x.get(meth).stream()
                            .mapToInt(MutationReportDatabaseObject::getNumOfRetries)
                            .average()
                            .orElse(Double.NaN));
        }
    }
}

class MutationReportDatabaseObject implements Serializable {
    protected static final HashMap<String, Integer> methods = new HashMap<>();
    private static int nextFreeIndex = 0;
    private final int numOfRetries, method;

    MutationReportDatabaseObject(int numOfRetries, @Nonnull String method) {
        this.numOfRetries = numOfRetries;
        if (methods.containsKey(method)) {
            this.method = methods.get(method);
        } else {
            this.method = nextFreeIndex;
            methods.put(method, nextFreeIndex++);
        }
    }

    public int getNumOfRetries() {
        return numOfRetries;
    }

    public int getMethodIndex() {
        return method;
    }

    public String getMethodName() {//Extremely poor performance - Call responsibly
        return methods.entrySet().stream().filter(x -> x.getValue() == method).findFirst().get().getKey();
    }
}