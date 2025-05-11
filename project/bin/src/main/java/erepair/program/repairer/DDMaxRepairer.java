package erepair.program.repairer;

import erepair.program.Parsing;
import erepair.program.deltadebugging.DD;
import erepair.program.deltadebugging.LexicalDDMax;
import erepair.program.subject.Subject;
import erepair.program.Algorithm;

import java.nio.file.Path;

/**
 **/
@ConcreteRepairer(algorithm = Algorithm.DDMAX, type = IsBinaryEnum.GRAMMARBASED)
public class DDMaxRepairer extends DDRepairer {
    /**
     * Instantiate a DDMax-repairer that repairs the given file
     *
     * @param file File to repair
     */
    public DDMaxRepairer(Path file) {
        super(file);
    }

    @Override
    String runDeltaDebugging(Path file, Subject subject) {
        final String origInput = Parsing.readStringFromFile(file);
        DD<String> myDD = new LexicalDDMax(origInput, timeouts, this.format.getSuffix(), subject);
        String ddmaxresult = myDD.run();
        super.incrementSubjectRunsBy(subject.getKind(),myDD.getNumberOfOracleRuns());
        myDD.tearDown();
        return ddmaxresult;
    }
}
