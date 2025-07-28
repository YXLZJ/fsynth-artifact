package erepair.program.mutator.modes;

import erepair.program.mutator.MutationMode;
import erepair.program.mutator.MutationModeImpl;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * A random byte flip
 *
 **/
@MutationModeImpl
public class ByteFlip extends MutationMode {
    public ByteFlip() {
        super("ByteFlip");
    }

    @Nonnull
    @Override
    public byte[] run(@Nonnull byte[] input, @Nonnull Random r) {
        final int pos = r.nextInt(input.length);
        input[pos] = (byte) (r.nextInt() & 0xff);
        return input;
    }
}
