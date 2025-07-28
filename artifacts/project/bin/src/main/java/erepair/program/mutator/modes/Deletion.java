package erepair.program.mutator.modes;

import erepair.program.mutator.MutationMode;
import erepair.program.mutator.MutationModeImpl;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * Performs a random deletion of a single byte.
 *
 **/
@MutationModeImpl
public class Deletion extends MutationMode {
    public Deletion() {
        super("Deletion");
    }

    @Nonnull
    @Override
    public byte[] run(@Nonnull byte[] input, @Nonnull Random r) {
        final int l = input.length;
        if (l < 1) throw new IllegalArgumentException("The given data was empty!");
        final int pos = r.nextInt(l);
        byte[] b = new byte[input.length - 1];
        if (pos > 0) {
            System.arraycopy(input, 0, b, 0, pos);
        }
        if (pos < (input.length - 1)) {
            System.arraycopy(input, pos + 1, b, pos, input.length - (pos + 1));
        }
        return b;
    }

    @Override
    public boolean changesArrayInplace() {
        return false;
    }
}
