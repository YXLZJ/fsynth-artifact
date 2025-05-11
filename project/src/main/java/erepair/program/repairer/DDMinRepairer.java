package erepair.program.repairer;

import erepair.program.Parsing;
import erepair.program.deltadebugging.DD;
import erepair.program.deltadebugging.DDMin;
import erepair.program.subject.Subject;
import erepair.program.Algorithm;

import java.nio.file.Path;

/**
 **/
@ConcreteRepairer(algorithm = Algorithm.DDMIN, type = IsBinaryEnum.GRAMMARBASED)
public class DDMinRepairer extends DDRepairer {
    /**
     * Instantiate a DDMin-Diagnoser that diangnoses the given file
     *
     * @param file File to repair
     */
    public DDMinRepairer(Path file) {
        super(file);
    }

    @Override
    String runDeltaDebugging(Path file, Subject subject) {
        final String origInput = Parsing.readStringFromFile(file);
        DD<String> myDD = new DDMin(origInput, timeouts, format.getSuffix(), subject);
        String ddminresult = myDD.run();
        super.incrementSubjectRunsBy(subject.getKind(), myDD.getNumberOfOracleRuns());
        myDD.tearDown();
        return ddminresult;
    }
}
