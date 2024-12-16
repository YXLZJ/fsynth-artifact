package fsynth.program.repairer;

import fsynth.program.deltadebugging.DD;
import fsynth.program.deltadebugging.SyntacticalDDMax;
import fsynth.program.subject.Subject;
import fsynth.program.Algorithm;
import fsynth.program.visitor.SimpleTreeFlattener;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Lukas Kirschner
 * @since 2021-03-22
 **/
@ConcreteRepairer(algorithm = Algorithm.DDMAXG, type = IsBinaryEnum.GRAMMARBASED)
public class DDMaxGRepairer extends DDRepairer {
    /**
     * Instantiate a DDMaxG-repairer that repairs the given file
     *
     * @param file File to repair
     */
    public DDMaxGRepairer(Path file) {
        super(file);
    }

    /**
     * Runs the syntactical DDMax algorithm
     */
    @Override
    String runDeltaDebugging(Path file, Subject subject) {
        ParseTree pt;
        try {
            pt = this.parse(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DD<List<String>> myDD = new SyntacticalDDMax(pt.accept(new SimpleTreeFlattener()), timeouts, format.getSuffix(), subject);
        String ddmaxresult = String.join("", myDD.run());
        super.incrementSubjectRunsBy(subject.getKind(), myDD.getNumberOfOracleRuns());
        myDD.tearDown();
        return ddmaxresult;
    }
}
