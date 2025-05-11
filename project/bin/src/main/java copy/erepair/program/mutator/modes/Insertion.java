package erepair.program.mutator.modes;

import erepair.program.mutator.MutationMode;
import erepair.program.mutator.MutationModeImpl;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * Perform a random insertion.
 *
 **/
@MutationModeImpl
public class Insertion extends MutationMode {
    public Insertion() {
        super("Insertion");
    }

    @Nonnull
    @Override
    public byte[] run(@Nonnull byte[] input, @Nonnull Random r) {
        final int pos = r.nextInt(input.length);
        byte[] b = new byte[input.length + 1];
        if (pos > 0) {
            System.arraycopy(input, 0, b, 0, pos);
        }
        b[pos] = (byte) (r.nextInt() & 0xff);
        System.arraycopy(input, pos, b, pos + 1, input.length - pos);
        return b;
    }

    @Override
    public boolean changesArrayInplace() {
        return false;
    }
}
