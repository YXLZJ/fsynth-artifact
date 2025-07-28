package erepair.program.repairer;

import erepair.program.Main;
import erepair.program.db.FileDatabase;
import erepair.program.differencing.DifferencingAlgorithms;
import erepair.program.subject.Subjects;
import erepair.program.Algorithm;

import java.nio.file.Path;

/**
 **/
public abstract class DatabaseConnection {
    /**
     * Report a test run result to the database.
     *
     * @param file               File that was tested
     * @param algorithm          Algorithm that was used for rectification
     * @param subject            Subject that tested the file
     * @param time               Run time that the algorithm took to repair the file, without the validating subject runs (except for algorithms that include subject runs, e.g. DDMax) in ms
     * @param success            True, if the test was a success
     * @param numberOfOracleRuns The number of oracle runs that were required to repair the file
     * @param rectifiedFile      The new path of the rectified file that resulted in the test run
     * @param rectifiedFileSize  The size of the rectified file in Bytes
     */
    void reportToDatabase(Path file, Algorithm algorithm, Subjects subject, long time, boolean success, long numberOfOracleRuns, Path rectifiedFile, long rectifiedFileSize) {
        this.getDatabase().getFileRecord(file).setTestResult(subject, algorithm, time, success, numberOfOracleRuns, rectifiedFile, rectifiedFileSize);
    }

    /**
     * Reports a differencing property to the database.
     * May be overridden by subclasses to modify the database behavior
     *
     * @param file                  File that was tested
     * @param algorithm             Algorithm that was used to rectify the file
     * @param subject               Subject that ran the file
     * @param differencingAlgorithm Differencing Algorithm in use
     * @param property              Property as result from the algorithm run
     */
    void reportToDatabase(Path file, Algorithm algorithm, Subjects subject, DifferencingAlgorithms differencingAlgorithm, long property) {
        this.getDatabase().getFileRecord(file).setDistancingResult(subject, algorithm, differencingAlgorithm, property);
    }

    /**
     * Reports the file size of the file that should be repaired to the database
     *
     * @param file        File under test
     * @param sizeInBytes Size in bytes
     */
    void reportToDatabase(Path file, long sizeInBytes) {
        this.getDatabase().getFileRecord(file).setSize(sizeInBytes);
    }

    /**
     * Reports the number of deletions and insertions to the database
     *
     * @param file          File under test
     * @param algorithm     Algorithm that was evaluated
     * @param subject       Subject that was evaluated
     * @param numInsertions Number of insertions
     * @param numDeletions  Number of deletions
     */
    protected void reportToDatabase(Path file, Algorithm algorithm, Subjects subject, long numInsertions, long numDeletions) {
        this.getDatabase().getFileRecord(file).setbFuzzerRepairStats(subject, algorithm, numInsertions, numDeletions);
    }

    /**
     * Reports a miscellaneous info field to the database.
     *
     * @param file              File under test
     * @param algorithm         Algorithm that has been run on the file
     * @param subject           Subject that was tested
     * @param additionalInfoKey Key of the additional info
     * @param additionalInfo    Content of the additional info
     */
    protected void reportToDatabase(Path file, Algorithm algorithm, Subjects subject, String additionalInfoKey, String additionalInfo) {
        //TODO implement me
        throw new IllegalStateException("Not implemented!");
    }

    /**
     * Get the database that should be used to store results
     *
     * @return the database
     */
    @SuppressWarnings("MethodMayBeStatic")
    FileDatabase getDatabase() {
        return Main.GLOBAL_DATABASE;
    }

}
