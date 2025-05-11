package erepair.program;

import java.nio.file.Path;

/**
 * This class is used to compare two paths, using the integer values of any numbers that are present in the file name.
 *
 */
public final class PathNumericComparator implements java.util.Comparator<Path> {
    @Override
    public int compare(Path o1, Path o2) {
        if (o1 == o2 || o1.equals(o2)) return 0;
        try {
            final String nm1 = o1.getFileName().normalize().toString().split("\\.")[0];
            final String nm2 = o2.getFileName().normalize().toString().split("\\.")[0];
            int n1 = Integer.parseInt(nm1);
            int n2 = Integer.parseInt(nm2);
            return Integer.compare(n1, n2);
        } catch (Exception e) {
        }

        return o1.compareTo(o2);
    }
}
