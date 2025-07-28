package erepair.program.mutator;

import java.lang.annotation.*;

/**
 * Marks a MutationMode implementation to be included in the mutator
 *
 **/
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
@Documented
public @interface MutationModeImpl {
}
