package erepair.program.repairer.erepair;

import erepair.program.Parsing;
import erepair.program.repairer.ConcreteRepairer;
import erepair.program.repairer.IsBinaryEnum;
import erepair.program.subject.Oracle;
import erepair.program.subject.SubjectStatus;
import erepair.program.Algorithm;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 **/
@ConcreteRepairer(algorithm = Algorithm.erepair, type = IsBinaryEnum.BINARY)

public final class erepairRepairerBinaryImpl extends erepairRepairer<List<Byte>> {
    public erepairRepairerBinaryImpl(Path file) {
        super(file);
        if (super.format.isGrammarBased){
            throw new AssertionError("Tried to instantiate Binary erepair for Grammar-Based Format!");
        }
    }

    @Override
    List<Byte> readFile(Path file) {
        byte[] dat = Parsing.readBinaryFile(file);
        List<Byte> ret = new ArrayList<>(dat.length);
        for (byte b : dat) {
            ret.add(b);
        }
        return ret;
    }

    @Override
    int length(List<Byte> bytes) {
        return bytes.size();
    }

    @Override
    String DebugPrintPrefix(List<Byte> prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        if (prefix.size() <= 16) {
            sb.append(prefix.stream().map(Byte::toUnsignedInt).map(i -> Integer.toString(i, 16)).collect(Collectors.joining(", ")));
        } else {
            sb.append(IntStream.range(0, 8).mapToObj(prefix::get).map(Byte::toUnsignedInt).map(i -> Integer.toString(i, 16)).collect(Collectors.joining(", ")));
            sb.append(", ..., ");
            sb.append(IntStream.range(prefix.size() - 8, prefix.size()).mapToObj(prefix::get).map(Byte::toUnsignedInt).map(i -> Integer.toString(i, 16)).collect(Collectors.joining(", ")));
        }
        sb.append(" ]");
        return sb.toString();
    }

    @Override
    void writeFile(Path file, List<Byte> bytes) {
        byte[] dat = new byte[bytes.size()];
        int i = 0;
        for (Byte b : bytes) {
            dat[i++] = b;
        }
        Parsing.writeBinaryFile(file, dat);
    }

    @Override
    SubjectStatus runTestOracle(List<Byte> bytes) {
        return Oracle.runOracleWithTemporaryBinaryFile(bytes, this.subject, this.format.getSuffix());
    }

    @Override
    List<Byte> substring(List<Byte> bytes, int a, int b) {
        return new ArrayList<>(bytes.subList(a, b));
    }

    @Override
    List<Byte> append(List<Byte> a, List<Byte> b) {
        List<Byte> ret = new ArrayList<Byte>(a);
        ret.addAll(b);
        return ret;
    }

    @Override
    List<Byte> charAt(List<Byte> bytes, int index) {
        return new ArrayList<Byte>(bytes.subList(index, index + 1));
    }

    /**
     * For the binary implementation, this completely ignores the character classes and iterates over all possible byte values.
     * @return all possible bytes
     */
    @Override
    Iterable<erepairRepairer<List<Byte>>.Tuple<List<Byte>, Optional<CharacterClass>>> replacementsIteratorFactory() {
        return new Iterable<erepairRepairer<List<Byte>>.Tuple<List<Byte>, Optional<CharacterClass>>>() {
            @Override
            public Iterator<erepairRepairer<List<Byte>>.Tuple<List<Byte>, Optional<CharacterClass>>> iterator() {
                return new Iterator<erepairRepairer<List<Byte>>.Tuple<List<Byte>, Optional<CharacterClass>>>() {
                    int cur = 0;

                    @Override
                    public boolean hasNext() {
                        return cur <= 255; // Works because cur is an integer
                    }

                    @Override
                    public erepairRepairer<List<Byte>>.Tuple<List<Byte>, Optional<CharacterClass>> next() {
                        return new Tuple<List<Byte>, Optional<CharacterClass>>(List.of((byte) cur++), Optional.empty());
                    }
                };
            }
        };
    }

    @Override
    boolean replacementSkip(erepairRepairer<List<Byte>>.Tuple<List<Byte>, Optional<CharacterClass>> replacement, List<Byte> currentPrefix) {
        return false;
    }

    @Override
    String debugPrintChar(List<Byte> c) {
        // Print the unsigned value of the byte
        return Integer.toString(Byte.toUnsignedInt(c.get(0)), 16);
    }
}
