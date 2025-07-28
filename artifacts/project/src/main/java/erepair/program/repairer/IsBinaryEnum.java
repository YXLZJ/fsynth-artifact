package erepair.program.repairer;

/**
 * A simple enum that signals whether an algorithm is capable of repairing binary or grammar-based file formats
 *
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
