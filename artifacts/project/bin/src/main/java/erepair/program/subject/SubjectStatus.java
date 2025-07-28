package erepair.program.subject;

import javax.annotation.Nonnull;

/**
 * This class represents the status of a subject program run
 **/
public class SubjectStatus {
    public static SubjectStatus SUCCESS = new SubjectStatus();
    public static SubjectStatus TIMEOUT = new SubjectStatus(true);
    public static SubjectStatus INCOMPLETE = new SubjectStatus(false);
    private final boolean incorrect;
    private final boolean incomplete;
    private final boolean timedout;
    @Nonnull
    private final String status;

    /**
     * Initialize a subject status that was a success
     */
    private SubjectStatus() {
        this.incorrect = false;
        this.incomplete = false;
        this.timedout = false;
        this.status = "";
    }

    /**
     * Instantiate a SubjectStatus completely manually. Can be used to deep-copy a SubjectStatus
     *
     * @param incorrect  Incorrect Status
     * @param incomplete Incomplete Status
     * @param timedout   Timeout Status
     * @param status     Message
     */
    private SubjectStatus(boolean incorrect, boolean incomplete, boolean timedout, String status) {
        this.incorrect = incorrect;
        this.incomplete = incomplete;
        this.timedout = timedout;
        this.status = status;
    }

    /**
     * Initialize a subject status that was either a timeout or reported as incomplete
     *
     * @param timedout True, if the status was a timeout
     */
    private SubjectStatus(boolean timedout) {
        this.timedout = timedout;
        if (timedout) {
            this.incorrect = true;
            this.incomplete = false;
            this.status = "Reported Timeout";
        } else {
            this.incorrect = false;
            this.incomplete = true;
            this.status = "Reported Incomplete";
        }
    }

    /**
     * Initialize a subject status that reported a failure
     *
     * @param failReason Reason for the failure, must not be empty!
     */
    public SubjectStatus(@Nonnull String failReason) {
        if (failReason.isEmpty()) {
            throw new IllegalStateException("The Fail Reason must not be empty!");
        }
        this.status = failReason;
        this.incomplete = false;
        this.incorrect = true;
        this.timedout = false;
    }

    /**
     * Checks if the run returned the input file to be incorrect.
     *
     * @return true, if the input file was reported to be incorrect
     */
    public boolean wasIncorrect() {
        return this.incorrect;
    }

    /**
     * Checks if the run returned the input file to be incomplete.
     *
     * @return true, if the input file was reported to be incomplete
     */
    public boolean wasIncomplete() {
        return this.incomplete;
    }

    /**
     * Checks if the run was successful, i.e. did not time out and neither reported incorrect nor incomplete
     *
     * @return true, if the run was successful
     */
    public boolean wasSuccessful() {
        return !this.incomplete && !this.incorrect && !this.timedout;
    }

    public String enumtext() {
        if (this.wasIncomplete()) {
            return "INCOMPLETE";
        }
        if (this.wasIncorrect()) {
            return "INCORRECT";
        }
        if (this.wasSuccessful()) {
            return "SUCCESSFUL";
        }
        return "<?Internal Error?>";
    }

    /**
     * Get the status report of the subject run. Returns an empty String if the run was successful
     *
     * @return the status report or an empty string
     */
    @Nonnull
    public String getStatusReport() {
        return this.status;
    }

    /**
     * Append a message to the status report of this SubjectReturnStatus and return a copy.
     * Can be used for debugging purposes.
     * <p>
     * Messages will always be appended separated by a line break.
     *
     * @param s Message to append
     * @return a copy with the appended message
     */
    public SubjectStatus append(CharSequence s) {
        return new SubjectStatus(this.incorrect, this.incomplete, this.timedout,
                this.status + "\n" + s);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SubjectStatus: ")
                .append(this.enumtext())
                .append(" - reason: " + this.status);
        return sb.toString();
    }
}
