package fsynth.program.repairer;

import fsynth.program.Parsing;
import fsynth.program.deltadebugging.DD;
import fsynth.program.deltadebugging.LexicalDDMax;
import fsynth.program.subject.Subject;
import fsynth.program.Algorithm;

import java.nio.file.Path;

/**
 * @author Lukas Kirschner
 * @since 2021-03-22
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
