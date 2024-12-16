package fsynth.program.visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Joining Collector that joins a stream of strings with a given separator. Ignores any whitespaces
 */
public class TreeFlattenerJoiner<T> implements Collector<ArrayList<T>, ArrayList<T>, ArrayList<T>> {
    private final Supplier<ArrayList<T>> mySupplier = ArrayList::new;
    private final BiConsumer<ArrayList<T>, ArrayList<T>> myAccumulator;
    private final BinaryOperator<ArrayList<T>> myOperator;
    private final Function<ArrayList<T>, ArrayList<T>> myFinisher = x -> x;
    private final T delimiter;

    public TreeFlattenerJoiner(T delimiter) {
        this.delimiter = delimiter;
        myOperator = (s1, s2) -> {
            if (((s2.size() > 0) &&
                    (s1.size() > 0)) &&
                    (s1.size() != 1 || (s1.get(s1.size() - 1).toString().length() > 0 && s1.get(s1.size() - 1).toString().charAt(0) != '\n')) &&
                    (s2.size() != 1 || (s2.get(0).toString().length() > 0 && s2.get(0).toString().charAt(0) != '\n'))) {
                s1.add(this.delimiter);
            } else {
            }
            s1.addAll(s2);
            return s1;
        };
        myAccumulator = myOperator::apply;
    }

    /**
     * A function that creates and returns a new mutable result container.
     *
     * @return a function which returns a new, mutable result container
     */
    @Override
    public Supplier<ArrayList<T>> supplier() {
        return mySupplier;
    }

    /**
     * A function that folds a value into a mutable result container.
     *
     * @return a function which folds a value into a mutable result container
     */
    @Override
    public BiConsumer<ArrayList<T>, ArrayList<T>> accumulator() {
        return myAccumulator;
    }

    /**
     * A function that accepts two partial results and merges them.  The
     * combiner function may fold state from one argument into the other and
     * return that, or may return a new result container.
     *
     * @return a function which combines two partial results into a combined
     * result
     */
    @Override
    public BinaryOperator<ArrayList<T>> combiner() {
        return myOperator;
    }

    /**
     * Perform the final transformation from the intermediate accumulation type
     * {@code A} to the final result type {@code R}.
     *
     * <p>If the characteristic {@code IDENTITY_FINISH} is
     * set, this function may be presumed to be an identity transform with an
     * unchecked cast from {@code A} to {@code R}.
     *
     * @return a function which transforms the intermediate result to the final
     * result
     */
    @Override
    public Function<ArrayList<T>, ArrayList<T>> finisher() {
        return myFinisher;
    }

    /**
     * Returns a {@code Set} of {@code Collector.Characteristics} indicating
     * the characteristics of this Collector.  This set should be immutable.
     *
     * @return an immutable set of collector characteristics
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
