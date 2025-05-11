package erepair.program.repairer;

import erepair.program.subject.Subject;
import erepair.program.Algorithm;

import java.nio.file.Path;

/**
 **/
@ConcreteRepairer(algorithm = Algorithm.BASELINE, type = IsBinaryEnum.BOTH)
public class BaselineRepairer extends Repairer {
    /**
     * Instantiate a repairer that "repairs" the given file
     *
     * @param file File to repair
     */
    public BaselineRepairer(Path file) {
        super(file);
    }

    /**
     * Just return the file, since baseline does not change the file at all
     *
     * @param file    File to repair
     * @param subject Subject to repair the file for
     * @return
     */
    @Override
    protected Path repair(Path file, Subject subject) {
        return file;
    }
}
