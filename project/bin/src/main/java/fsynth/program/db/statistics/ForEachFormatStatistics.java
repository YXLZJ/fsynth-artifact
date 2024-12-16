package fsynth.program.db.statistics;

import fsynth.program.InputFormat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation annotates a statistic class that should be instantiated for each file format.
 * The constructor MUST be a single-element constructor that takes a {@link InputFormat} as argument
 *
 * @author Lukas Kirschner
 * @since 2020-05-26
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ForEachFormatStatistics {
}
