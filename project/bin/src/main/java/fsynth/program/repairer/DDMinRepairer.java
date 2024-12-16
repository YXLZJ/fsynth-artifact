package fsynth.program.repairer;

import fsynth.program.Parsing;
import fsynth.program.deltadebugging.DD;
import fsynth.program.deltadebugging.DDMin;
import fsynth.program.subject.Subject;
import fsynth.program.Algorithm;

import java.nio.file.Path;

/**
 * @author Lukas Kirschner
 * @since 2021-03-22
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
