package erepair.program.deltadebugging;

import java.util.Objects;

/**
 * This class represents an immutable discrete interval.
 * We use it to obtain substrings from an input.
 *
 */
public class DeltaInterval implements Comparable<DeltaInterval> {
    private final int inclusiveLowerBound;
    private final int exclusiveUpperBound;

    /**
     * Create a new DeltaInterval with given bounds.
     *
     * @param inclusiveLowerBound Inclusive lower bound
     * @param exclusiveUpperBound Exclusive upper bound
     */
    public DeltaInterval(int inclusiveLowerBound, int exclusiveUpperBound) {
        this.inclusiveLowerBound = inclusiveLowerBound;
        this.exclusiveUpperBound = exclusiveUpperBound;
    }

    /**
     * Check if a given integer is inside the DeltaInterval
     *
     * @param x Integer to check
     * @return true, if x is inside DeltaInterval
     */
    public boolean inside(int x) {
        return x >= inclusiveLowerBound && x < exclusiveUpperBound;
    }

    /**
     * Gets the inclusive lower bound of the DeltaInterval
     *
     * @return inclusive lower bound
     */
    public int getInclusiveLowerBound() {
        return inclusiveLowerBound;
    }

    /**
     * Gets the exclusive upper bound of the DeltaInterval
     *
     * @return exclusive upper bound
     */
    public int getExclusiveUpperBound() {
        return exclusiveUpperBound;
    }

    /**
     * Gets the count of numbers that are inside the DeltaInterval
     *
     * @return {@code |DeltaInterval|}
     */
    public int getLength() {
        return exclusiveUpperBound - inclusiveLowerBound;
    }

    /**
     * Check if the DeltaInterval is equal to given object
     *
     * @param o Object
     * @return true, if Object is equal to the DeltaInterval
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeltaInterval other = (DeltaInterval) o;
        return inclusiveLowerBound == other.inclusiveLowerBound &&
                exclusiveUpperBound == other.exclusiveUpperBound;
    }

    /**
     * Get hash code of the DeltaInterval
     *
     * @return Hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(inclusiveLowerBound, exclusiveUpperBound);
    }


    @Override
    public int compareTo(DeltaInterval o) {
        if (o == null) return -1;
        return Integer.compare(this.inclusiveLowerBound, o.inclusiveLowerBound);
    }

    /**
     * Convert the DeltaInterval to a human-readable form, e.g. {@code [0,1)}
     *
     * @return human-readable form as String
     */
    @Override
    public String toString() {
        return "[" + getInclusiveLowerBound() + "," + getExclusiveUpperBound() + ")";
    }
}
