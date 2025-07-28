package erepair.program.functional;

import erepair.program.deltadebugging.DeltaSet;

@FunctionalInterface
public interface FileGenerator<S> {
    public String feed(DeltaSet exclusionSet, S input);
}
