package fsynth.program.repairer;

/**
 * A simple enum that signals whether an algorithm is capable of repairing binary or grammar-based file formats
 *
 * @author Lukas Kirschner
 * @since 2021-11-11
 */
public enum IsBinaryEnum {
    /**
     * The algorithm is capable of repairing binary formats
     */
    BINARY,
    /**
     * The algorithm is capable of repairing grammar-based formats
     */
    GRAMMARBASED,
    /**
     * The algorithm is capable of repairing both binary and grammar-based formats
     */
    BOTH
}
