package erepair.program.repairer.erepair;

import erepair.program.Parsing;
import erepair.program.repairer.ConcreteRepairer;
import erepair.program.repairer.IsBinaryEnum;
import erepair.program.subject.Oracle;
import erepair.program.subject.SubjectStatus;
import erepair.program.Algorithm;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;

/**
 **/
@ConcreteRepairer(algorithm = Algorithm.erepair, type = IsBinaryEnum.GRAMMARBASED)

public final class erepairRepairerGrammarBasedImpl extends erepairRepairer<String> {
    public erepairRepairerGrammarBasedImpl(Path file) {
        super(file);
        if (!super.format.isGrammarBased) {
            throw new AssertionError("Tried to instantiate Grammar-Based erepair for Binary Format!");
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
    Iterable<erepairRepairer<String>.Tuple<String, Optional<CharacterClass>>> replacementsIteratorFactory() {
        return new Iterable<erepairRepairer<java.lang.String>.Tuple<String, Optional<CharacterClass>>>() {
            @Override
            public Iterator<erepairRepairer<String>.Tuple<String, Optional<CharacterClass>>> iterator() {
                return new Iterator<erepairRepairer<java.lang.String>.Tuple<String, Optional<CharacterClass>>>() {
                    final Iterator<CharacterClass> myIt = Arrays.asList(CharacterClass.values()).iterator();
                    Iterator<Character> myCharIt = Collections.emptyIterator();
                    CharacterClass cur = null;

                    @Override
                    public boolean hasNext() {
                        return myCharIt.hasNext() || myIt.hasNext();
                    }

                    @Override
                    public erepairRepairer<String>.Tuple<String, Optional<CharacterClass>> next() {
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
    boolean replacementSkip(erepairRepairer<String>.Tuple<String, Optional<CharacterClass>> replacement, String currentPrefix) {
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
