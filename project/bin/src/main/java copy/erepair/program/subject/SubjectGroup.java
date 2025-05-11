package erepair.program.subject;

import erepair.program.InputFormat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation automatically sorts the subjects into their respective group.
 *
 **/
@Retention(RetentionPolicy.RUNTIME)
public @interface SubjectGroup {
    /**
     * @return The subject group, i.e. which input format this subject is associated with
     */
    InputFormat group();
}
