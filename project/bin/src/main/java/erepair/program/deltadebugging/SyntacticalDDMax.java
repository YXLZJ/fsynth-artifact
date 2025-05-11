package erepair.program.deltadebugging;

import erepair.program.subject.Subject;
import erepair.program.visitor.SimpleTreeFlattener;

import java.util.List;
import java.util.logging.Level;

/**
 **/
public class SyntacticalDDMax extends DDMax<List<String>> {
    /**
     * Instantiates the Syntactical DDMax
     *
     * @param input           Input to process, as flattened by {@link SimpleTreeFlattener}, e.g. List&lt;String&gt; flattenedTree = input.accept(new SimpleTreeFlattener());
     * @param timeoutInMillis Timeout in milliseconds
     * @param fileSuffix      Suffix of the test files without dot, e.g. 'obj'
     * @param oracle          Oracle to test the files
     */
    public SyntacticalDDMax(List<String> input, long timeoutInMillis, String fileSuffix, Subject oracle) {
        super("DDMaxG", input, timeoutInMillis, fileSuffix, oracle);
    }

    @Override
    protected List<String> exclude(DeltaSet deltaInterval, List<String> flattenedTree) {
        return exclude(flattenedTree, deltaInterval);
    }

    @Override
    protected DeltaSet runAlgorithm() {
        log(Level.INFO, "Running DDMaxG...");
        return this.run_recursive(new DeltaSet(0, this.getLength(this.input)), 2);
    }

    @Override
    protected int getLength(List<String> input) {
        return input.size();
    }

    @Override
    protected String toString(List<String> input) {
        return String.join("", input);
    }
}
