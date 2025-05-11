package erepair.program.repairer;

import erepair.program.Algorithm;

import java.lang.annotation.*;

/**
 * Marks a class as Repairer Class to be run in the evaluation.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
@Documented
public @interface ConcreteRepairer {
    /**
     * @return The Algorithm this class implements. May exist only once for binary and grammar-based formats
     */
    Algorithm algorithm();

    /**
     * @return The kind of file this algorithm is meant to repair, i.e. {@link IsBinaryEnum#BINARY} for binary files,
     *         {@link IsBinaryEnum#GRAMMARBASED} for grammar-based files, {@link IsBinaryEnum#BOTH} if the algorithm
     *         supports both.
     */
    IsBinaryEnum type();
}

