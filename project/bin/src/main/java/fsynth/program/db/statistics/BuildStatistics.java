package fsynth.program.db.statistics;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation marks a class inheriting from {@link Statistics} to be executed when statistics are built.
 * The constructor of the annotated class MUST be a default constructor, unless {@link ForEachAlgorithmStatistics} is annotated.
 *
 * @author Lukas Kirschner
 * @since 2020-05-14
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BuildStatistics {
}
