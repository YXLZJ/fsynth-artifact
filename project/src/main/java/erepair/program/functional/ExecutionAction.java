package erepair.program.functional;

import erepair.program.subject.ExecutionInfo;

/**
 **/
@FunctionalInterface
public interface ExecutionAction {
    /**
     * @param action Execution Info from the subject
     */
    void run(ExecutionInfo action);
}
