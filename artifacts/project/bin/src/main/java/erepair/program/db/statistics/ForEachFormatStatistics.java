package erepair.program.db.statistics;

import erepair.program.InputFormat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation annotates a statistic class that should be instantiated for each file format.
 * The constructor MUST be a single-element constructor that takes a {@link InputFormat} as argument
 *
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ForEachFormatStatistics {
}
