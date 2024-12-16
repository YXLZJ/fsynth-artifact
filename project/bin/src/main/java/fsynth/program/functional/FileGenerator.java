package fsynth.program.functional;

import fsynth.program.deltadebugging.DeltaSet;

@FunctionalInterface
public interface FileGenerator<S> {
    public String feed(DeltaSet exclusionSet, S input);
}
