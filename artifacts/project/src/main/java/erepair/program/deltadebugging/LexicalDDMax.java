package erepair.program.deltadebugging;

import erepair.program.subject.Subject;
import erepair.program.visitor.SimpleTreeFlattener;

import java.util.logging.Level;

/**
 **/
public class LexicalDDMax extends DDMax<String> {
    /**
     * Instantiates the Lexical DDMax
     *
     * @param input           Input to process, as flattened by {@link SimpleTreeFlattener}, e.g. List&lt;String&gt; flattenedTree = input.accept(new SimpleTreeFlattener());
     * @param timeoutInMillis Timeout in milliseconds
     * @param fileSuffix      Suffix of the test files without dot, e.g. 'obj'
     * @param oracle          Oracle to test the files
     */
    public LexicalDDMax(String input, long timeoutInMillis, String fileSuffix, Subject oracle) {
        super("DDMax", input, timeoutInMillis, fileSuffix, oracle);
    }

    @Override
    protected String exclude(DeltaSet deltaInterval, String flattenedTree) {
        return DeltaSet.exclude(flattenedTree, deltaInterval);
    }

    @Override
    protected DeltaSet runAlgorithm() {
        log(Level.INFO, "Running DDMax...");
        return this.run_recursive(new DeltaSet(0, this.getLength(this.input)), 2);
    }

    @Override
    protected int getLength(String input) {
        return input.length();
    }

    @Override
    protected String toString(String input) {
        return input;
    }
}
