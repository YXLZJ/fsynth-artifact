package fsynth.program.repairer;

import fsynth.program.subject.Subject;
import fsynth.program.Algorithm;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @author Lukas Kirschner
 * @since 2021-03-22
 **/
@ConcreteRepairer(algorithm = Algorithm.PRETTYPRINT, type = IsBinaryEnum.GRAMMARBASED)
public class AntlrRepairer extends Repairer {
    /**
     * Instantiate a repairer that repairs the given file
     *
     * @param file File to repair
     */
    public AntlrRepairer(Path file) {
        super(file);
    }

    @Override
    protected Path repair(Path file, Subject subject) {
        Path prettyPrintedFile;
        try {
            prettyPrintedFile = prettyPrintFile(file);
        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return prettyPrintedFile;
    }
}
