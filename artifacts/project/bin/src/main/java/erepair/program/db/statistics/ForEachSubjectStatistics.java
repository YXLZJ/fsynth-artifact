package erepair.program.db.statistics;

import erepair.program.subject.Subjects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation annotates a statistic class that should be instantiated for each subject.
 * The constructor MUST be a single-element constructor that takes a {@link Subjects} as argument
 *
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ForEachSubjectStatistics {
}
