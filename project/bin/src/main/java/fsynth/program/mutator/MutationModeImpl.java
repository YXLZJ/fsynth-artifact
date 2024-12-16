package fsynth.program.mutator;

import java.lang.annotation.*;

/**
 * Marks a MutationMode implementation to be included in the mutator
 *
 * @author Lukas Kirschner
 * @since 2021-12-30
 **/
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
@Documented
public @interface MutationModeImpl {
}
