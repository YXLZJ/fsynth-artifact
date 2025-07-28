package erepair.program.repairer;

import erepair.program.Parsing;
import erepair.program.deltadebugging.DD;
import erepair.program.deltadebugging.DDMax;
import erepair.program.deltadebugging.DeltaSet;
import erepair.program.subject.Subject;
import erepair.program.Algorithm;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.logging.Level;

/**
 **/
@ConcreteRepairer(algorithm = Algorithm.DDMAX, type = IsBinaryEnum.BINARY)
public class DDMaxBinaryRepairer extends Repairer {
    /**
     * Instantiate a DDMax-repairer that repairs the given binary file
     *
     * @param file File to repair
     */
    public DDMaxBinaryRepairer(Path file) {
        super(file);
    }


    byte[] runDeltaDebugging(Path file, Subject subject) {
        final byte[] origInput = Parsing.readBinaryFile(file);
        DD<byte[]> myDD = new BinaryDDMax(origInput, timeouts, this.format.getSuffix(), subject);
        byte[] ddmaxresult = myDD.run();
        super.incrementSubjectRunsBy(subject.getKind(), myDD.getNumberOfOracleRuns());
        myDD.tearDown();
        return ddmaxresult;
    }

    @Override
    protected Path repair(Path file, Subject subject) {
        byte[] ddmaxresult;

        //Check if the file is corrupted
        final boolean isCorrupted = !subject.run(file.normalize().toString(), null).wasSuccessful();
        super.incrementSubjectRuns(subject.getKind());
        if (isCorrupted) {
            ddmaxresult = this.runDeltaDebugging(file, subject);
        } else {
            ddmaxresult = Parsing.readBinaryFile(file);
        }
        final Path ddmaxRepairedFile = getResultPathFor(subject.getKind(), file);
        Parsing.writeBinaryFile(ddmaxRepairedFile, ddmaxresult);
        return ddmaxRepairedFile;
    }

    private final class BinaryDDMax extends DDMax<byte[]> {

        /**
         * Instantiates the Delta Debugging class
         *
         * @param input           Input to process
         * @param timeoutInMillis Timeout in milliseconds
         * @param fileSuffix      Suffix of the test files without dot, e.g. 'obj'
         * @param oracle          Oracle to test the files
         */
        public BinaryDDMax(byte[] input, long timeoutInMillis, String fileSuffix, Subject oracle) {
            super("DDMax", input, timeoutInMillis, fileSuffix, oracle);
        }

        @Override
        protected byte[] exclude(DeltaSet deltaInterval, byte[] input) {
            return DeltaSet.exclude(input, deltaInterval);
        }

        @Override
        protected DeltaSet runAlgorithm() {
            log(Level.INFO, "Running DDMax...");
            return this.run_recursive(new DeltaSet(0, this.getLength(this.input)), 2);
        }

        @Override
        protected int getLength(byte[] input) {
            return input.length;
        }

        @Override
        protected String toString(byte[] input) {
            return new String(input, StandardCharsets.UTF_8);
        }
    }
}
