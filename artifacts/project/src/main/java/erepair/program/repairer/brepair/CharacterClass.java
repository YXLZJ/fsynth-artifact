package erepair.program.repairer.erepair;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * This enum represents a character class, i.e., a set of similar characters that can usually be used interchangeably.
 *
 **/
@SuppressWarnings({"HardcodedLineSeparator", "HardcodedFileSeparator"})
enum CharacterClass implements Serializable, Iterable<Character> {
    WHITESPACE(' '),
    LINEBREAK('\n'),
    ASCII(33, 127), // All ASCII printable characters excl. whitespace
    TAB('\t'),
    ;

    private final int from, to;

    CharacterClass(int asciiFromInclusive, int asciiToExclusive) {
        if (asciiFromInclusive >= asciiToExclusive) {
            throw new IllegalArgumentException("Illegal Bounds");
        }
        this.from = asciiFromInclusive;
        this.to = asciiToExclusive;
    }

    CharacterClass(Character character) {
        this.from = (int) character;
        this.to = (int) character + 1;
    }

    /**
     * Check if the given character is a Whitespace
     *
     * @param c
     * @return
     */
    public static boolean isWhitespace(Character c) {
        return Character.isWhitespace(c);
    }

    /**
     * Check if this character class has alternatives
     *
     * @return true, if the character class has alternatives
     */
    public boolean hasAlternatives() {
        return this.size() > 1;
    }

    /**
     * Get the number of alternatives inside this character class
     *
     * @return the number of alternative characters
     */
    public int size() {
        return to - from;
    }

    /**
     * Get the i-th character in this character class
     *
     * @param i Index
     * @return the i-th character
     * @throws ArrayIndexOutOfBoundsException if the index is out of bounds
     */
    public Character get(int i) {
        if (i >= this.size() || i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index out of range: " + i);
        }
        return (char) (from + i);
    }

    @Override
    public Iterator<Character> iterator() {
        final int cur = this.from;
        final int endind = this.to;
        return new Iterator<Character>() {
            private int current = cur;
            private int end = endind;


            @Override
            public boolean hasNext() {
                return current < end;
            }

            @Override
            public Character next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException("Iterator out of bounds: " + current + " / " + end);
                }
                return (char) current++;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Removing items is not permitted");
            }

            @Override
            public void forEachRemaining(Consumer<? super Character> action) {
                IntStream.range(current, end).mapToObj(i -> (char) i).forEach(action);
            }
        };
    }

    /**
     * Check if the first character from this character class is a whitespace
     *
     * @return true, if the first character from this class is a whitespace
     */
    public boolean isWhitespace() {
        return isWhitespace(this.get(0));
    }
}
