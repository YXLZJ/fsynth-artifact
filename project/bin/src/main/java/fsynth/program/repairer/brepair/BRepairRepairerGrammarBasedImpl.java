package fsynth.program.repairer.brepair;

import fsynth.program.Parsing;
import fsynth.program.repairer.ConcreteRepairer;
import fsynth.program.repairer.IsBinaryEnum;
import fsynth.program.subject.Oracle;
import fsynth.program.subject.SubjectStatus;
import fsynth.program.Algorithm;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;

/**
 * @author Lukas Kirschner
 * @since 2021-11-12
 **/
@ConcreteRepairer(algorithm = Algorithm.BREPAIR, type = IsBinaryEnum.GRAMMARBASED)

public final class BRepairRepairerGrammarBasedImpl extends BRepairRepairer<String> {
    public BRepairRepairerGrammarBasedImpl(Path file) {
        super(file);
        if (!super.format.isGrammarBased) {
            throw new AssertionError("Tried to instantiate Grammar-Based bRepair for Binary Format!");
        }
    }

    @Override
    String readFile(Path file) {
        return Parsing.readStringFromFile(file);
    }

    @Override
    int length(String s) {
        return s.length();
    }

    @Override
    String DebugPrintPrefix(String prefix) {
        return prefix;
    }

    @Override
    void writeFile(Path file, String s) {
        Parsing.writeStringToFile(file, s);
    }

    @Override
    SubjectStatus runTestOracle(String s) {
        return Oracle.runOracleWithTemporaryFile(s, this.subject, this.format.getSuffix());
    }

    @Override
    String substring(String s, int a, int b) {
        return s.substring(a, b);
    }

    @Override
    String append(String a, String b) {
        return a + b;
    }

    @Override
    String charAt(String s, int index) {
        return Character.toString(s.charAt(index));
    }

    /**
     * This returns all characters in all character classes that are contained in {@link CharacterClass#values()}
     *
     * @return all characters in all character classes
     */
    @SuppressWarnings({"Convert2Diamond", "Convert2Lambda"})
    @Override
    Iterable<BRepairRepairer<String>.Tuple<String, Optional<CharacterClass>>> replacementsIteratorFactory() {
        return new Iterable<BRepairRepairer<java.lang.String>.Tuple<String, Optional<CharacterClass>>>() {
            @Override
            public Iterator<BRepairRepairer<String>.Tuple<String, Optional<CharacterClass>>> iterator() {
                return new Iterator<BRepairRepairer<java.lang.String>.Tuple<String, Optional<CharacterClass>>>() {
                    final Iterator<CharacterClass> myIt = Arrays.asList(CharacterClass.values()).iterator();
                    Iterator<Character> myCharIt = Collections.emptyIterator();
                    CharacterClass cur = null;

                    @Override
                    public boolean hasNext() {
                        return myCharIt.hasNext() || myIt.hasNext();
                    }

                    @Override
                    public BRepairRepairer<String>.Tuple<String, Optional<CharacterClass>> next() {
                        if (!myCharIt.hasNext()) {
                            cur = myIt.next();
                            myCharIt = cur.iterator();

                        }
                        return new Tuple<String, Optional<CharacterClass>>(Character.toString(myCharIt.next()), Optional.of(cur));
                    }
                };
            }
        };
    }

    @Override
    boolean replacementSkip(BRepairRepairer<String>.Tuple<String, Optional<CharacterClass>> replacement, String currentPrefix) {
        return false;
    }

    @SuppressWarnings({"HardcodedFileSeparator", "HardcodedLineSeparator"})
    @Override
    String debugPrintChar(String c) {
        switch (c.charAt(0)) {
            case '\n':
                return "\\n";
            case '\r':
                return "\\r";
            default:
                return Character.toString(c.charAt(0));
        }
    }
}
