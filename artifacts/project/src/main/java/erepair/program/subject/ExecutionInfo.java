package erepair.program.subject;

import erepair.program.InputFormat;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 **/
public class ExecutionInfo {
    private final SubjectStatus status;
    @Nullable
    private Subjects subject;
    private InputFormat format;

    /**
     * Instantiate a new Execution Info Descriptor
     *
     * @param status  Status the subject returned
     * @param subject Subject
     * @param format  Input Format
     */
    public ExecutionInfo(@Nonnull SubjectStatus status, @Nonnull Subjects subject, @Nonnull InputFormat format) {
        this.status = status;
        this.subject = subject;
        this.format = format;
    }

    /**
     * Get the failed reason or an empty String if the oracle succeeded
     *
     * @return the failed reason
     */
    @Nonnull
    public String getFailedReason() {
        return this.status.getStatusReport();
    }

    public boolean isIncorrect() {
        return this.status.wasIncorrect();
    }

    public boolean isIncomplete() {
        return this.status.wasIncomplete();
    }

    public String getSubjectName() {
        return this.subject.toString();

    }

    public InputFormat getFormat() {
        return this.format;
    }

    public Subjects getSubject() {
        return this.subject;
    }

    @Override
    public String toString() {
        return "ExecutionInfo of " + this.subject.toString() + ", " + (this.isIncorrect() ? "failed because of " + this.getFailedReason() : "succeeded");
    }
}
