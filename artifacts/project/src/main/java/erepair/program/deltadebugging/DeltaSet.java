package erepair.program.deltadebugging;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class represents a set of intervals
 *
 */
public class DeltaSet {
    private final TreeSet<DeltaInterval> intervalSet;

    /**
     * Instantiates a new empty DeltaSet
     */
    public DeltaSet() {
        this.intervalSet = new TreeSet<>();
    }

    /**
     * Deep-copies an existing DeltaSet
     *
     * @param other DeltaSet to copy
     */
    public DeltaSet(DeltaSet other) {
        this.intervalSet = new TreeSet<>(other.intervalSet);
    }

    /**
     * Instantiates a new DeltaSet containing the given DeltaIntervals
     *
     * @param intervals Intervals to include in the DeltaSet
     */
    public DeltaSet(DeltaInterval... intervals) {
        this.intervalSet = new TreeSet<>();
        this.intervalSet.addAll(Arrays.asList(intervals));
    }

    /**
     * Instantiates a new DeltaSet with the given bounds
     *
     * @param inclusiveLowerBound Inclusive Lower Bound
     * @param exclusiveUpperBound Exclusive Upper Bound
     */
    public DeltaSet(int inclusiveLowerBound, int exclusiveUpperBound) {
        this.intervalSet = new TreeSet<>();
        this.intervalSet.add(new DeltaInterval(inclusiveLowerBound, exclusiveUpperBound));
    }

    /**
     * Excludes the given DeltaSet from a string.
     *
     * @param input    String to use for exclusion
     * @param deltaSet DeltaSet to exclude
     * @return non-excluded part of the string
     */
    public static String exclude(String input, DeltaSet deltaSet) {
        StringBuilder ret = new StringBuilder(input.length() - deltaSet.length());
        for (int i = 0; i < input.length(); i++) {
            if (!deltaSet.inside(i)) {
                ret.append(input.charAt(i));
            }
        }
        return ret.toString();
    }

    /**
     * Excludes the given DeltaSet from a binary file.
     *
     * @param input    String to use for exclusion
     * @param deltaSet DeltaSet to exclude
     * @return non-excluded part of the string
     */
    public static byte[] exclude(byte[] input, DeltaSet deltaSet) {
        byte[] ret = new byte[input.length - deltaSet.length()];
        int ind = 0;
        for (int i = 0; i < input.length; i++) {
            if (!deltaSet.inside(i)) {
                ret[ind++] = input[i];
            }
        }
        return ret;
    }

    /**
     * Gets the length of the DeltaInterval, which is the count of integers that are inside the interval
     *
     * @return Length
     */
    public int length() {
        return this.intervalSet.stream().mapToInt(DeltaInterval::getLength).sum();
    }

    /**
     * Gets the n-th index that is included in the DeltaInterval.
     * If n is a negative value, return the n-th index in reversed direction.
     *
     * @param n n
     * @return the n-th index
     */
    public int getNthIndex(int n) {
        if (n < 0) { // For performance reasons, treat the last element differently to catch n==-1 which is called very frequently
            final DeltaInterval tail = this.intervalSet.last();
            if ((-n) < tail.getLength()) {
                return tail.getExclusiveUpperBound() + n;
            }
            n = this.length() + n;
            if (n < 0) {
                throw new IndexOutOfBoundsException(this.toString() + " " + (n - this.length()));
            }
        }

        int nn = n;
        for (DeltaInterval i : this.intervalSet) {
            final int len = i.getLength();
            if (len > nn) {
                return i.getInclusiveLowerBound() + nn;
            } else {
                nn -= len;
            }
        }
        throw new IndexOutOfBoundsException(this.toString() + " " + n);
    }

    /**
     * Joins a given DeltaInterval with this DeltaSet
     *
     * @param i Interval to join
     */
    public void joinInterval(DeltaInterval i) {
        int lowerBound = i.getInclusiveLowerBound();
        int upperBound = i.getExclusiveUpperBound();
//        System.out.println("Inserting " + i);
        //Check if there is an overlapping interval at the bottom
        var ls = intervalSet.headSet(i);
        if (!ls.isEmpty()) {
            var lb = ls.last();
            if (lb.getExclusiveUpperBound() >= lowerBound) {
                lowerBound = lb.getInclusiveLowerBound();
                intervalSet.remove(lb);
//                System.out.println("Removed " + lb);
            }
        }
        //Check if there are overlapping intervals at the top and delete all, updating the current interval
        var removeCandidates = intervalSet.tailSet(i);
        var it = removeCandidates.iterator();
        while (it.hasNext()) {
            final DeltaInterval candidate = it.next();
            if (candidate.getInclusiveLowerBound() > upperBound) {
                break;
            }
            if (candidate.getInclusiveLowerBound() >= lowerBound &&
                    candidate.getInclusiveLowerBound() <= upperBound) {//We use only discrete intervals
                upperBound = Math.max(upperBound, candidate.getExclusiveUpperBound());
//                System.out.println("Removing " + candidate);
                it.remove();
            }
        }
        intervalSet.add(new DeltaInterval(lowerBound, upperBound));
    }

    /**
     * Excludes a DeltaInterval from this DeltaSet
     *
     * @param i Interval to exclude
     */
    public void excludeInterval(DeltaInterval i) {
        int lowerBound = i.getInclusiveLowerBound();
        int upperBound = i.getExclusiveUpperBound();
        //Check if there is an overlapping interval at the bottom and cut if it exists
        var hs = intervalSet.headSet(i);
        if (!hs.isEmpty()) {
            var lb = hs.last();
            if (lb.getExclusiveUpperBound() > lowerBound) {
                intervalSet.remove(lb);
                intervalSet.add(new DeltaInterval(lb.getInclusiveLowerBound(), lowerBound));
                if (lb.getExclusiveUpperBound() > upperBound) {
                    intervalSet.add(new DeltaInterval(upperBound, lb.getExclusiveUpperBound()));
                }
            }
        }
        //Check if there are overlapping intervals at the top and delete all, updating the current interval
        var removeCandidates = intervalSet.tailSet(i);
        var it = removeCandidates.iterator();
        ArrayList<DeltaInterval> toadd = new ArrayList<>();
        while (it.hasNext()) {
            final DeltaInterval candidate = it.next();
            if (candidate.getInclusiveLowerBound() > upperBound) {
                break;
            }
            if (candidate.getInclusiveLowerBound() >= lowerBound &&
                    candidate.getInclusiveLowerBound() < upperBound) {
                it.remove();
                if (candidate.getExclusiveUpperBound() > upperBound) {
                    toadd.add(new DeltaInterval(upperBound, candidate.getExclusiveUpperBound()));
                }
            }
        }
        intervalSet.addAll(toadd);
    }

    /**
     * Gets an Index Iterator that iterates over all indices that are inside the DeltaInterval
     *
     * @return Iterator
     */
    public Iterator<Integer> getIndexIterator() {
        return new Iterator<>() {
            private final Iterator<DeltaInterval> it = intervalSet.iterator();
            private DeltaInterval currentInterval = null;
            private int currentIndex = 0;
            private boolean nextAvailable = it.hasNext();

            @Override
            public boolean hasNext() {
                return nextAvailable;
            }

            @Override
            public Integer next() {
                if (currentInterval == null) {
                    this.currentInterval = it.next();
                    this.currentIndex = this.currentInterval.getInclusiveLowerBound();
                }

                final int ret = currentIndex++;

                if (!(currentInterval.inside(currentIndex))) {
                    this.nextAvailable = it.hasNext();
                    if (this.nextAvailable) {
                        this.currentInterval = it.next();
                        this.currentIndex = this.currentInterval.getInclusiveLowerBound();
                    }
                }
                return ret;
            }
        };
    }

    /**
     * Returns true, if the DeltaInterval is empty
     *
     * @return true, if this DeltaInterval is empty
     */
    public boolean isEmptyset() {
        return intervalSet.isEmpty();
    }

    /**
     * Check, if the given integer is contained in this DeltaSet.
     *
     * @param i Integer to check
     * @return true, if it is contained, false otherwise
     */
    public boolean inside(int i) {
        final DeltaInterval arti = new DeltaInterval(i, i + 1);
        final SortedSet<DeltaInterval> ts = this.intervalSet.tailSet(arti);
        final SortedSet<DeltaInterval> hs = this.intervalSet.headSet(arti);
        return (!ts.isEmpty() && ts.first().inside(i)) || (!hs.isEmpty() && hs.last().inside(i));
    }

    /**
     * Gets a human-readable representation of this DetaSet
     *
     * @return String
     */
    @Override
    public String toString() {
        return "{" + this.intervalSet.stream().map(DeltaInterval::toString).collect(Collectors.joining(",")) + "}";
    }
}