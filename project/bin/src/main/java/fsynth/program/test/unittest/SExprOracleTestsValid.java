package fsynth.program.test.unittest;

import fsynth.program.subject.Subjects;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * System tests for the SExpr Oracles Return Status
 */
@SuppressWarnings({"JavaDoc", "HardcodedLineSeparator", "HardcodedFileSeparator"})
@DisplayName("SExpr Oracle Tests - Valid")
public class SExprOracleTestsValid extends SExprOracleTests {

    @SuppressWarnings("unused")
    static Stream<Arguments> localParameters() {
        return SExprOracleTests.localParameters();
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Test SExpr - Default Test Case from sexp-parser")
    public void testValidSExpr1(Subjects subjects) {
        this.testSuccessful(
                " (ab\\(c 123 de(f g) \"hi\\\"j\")", subjects
        );
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Valid Real-World LISP Source File with line comment")
    @Disabled("Line Comments, hashes and ticks not supported by sexp-parser")
    public void testValidSExpr2(Subjects subjects) {
        this.testSuccessful(
                "\n" +
                        "(in-package :bzspl)\n" +
                        "\n" +
                        "; http://graphics.cs.ucdavis.edu/~joy/ecs178/Unit-7-Notes/MatrixBSpline.pdf\n" +
                        "\n" +
                        "(defstruct bzspl\n" +
                        "  (n nil :type pos-int :read-only t)\n" +
                        "  (ns nil :type pos-int :read-only t)\n" +
                        "  (closed nil :type boolean :read-only t)\n" +
                        "  (vpts nil :type veq:fvec :read-only t))\n" +
                        "\n" +
                        "\n" +
                        "(declaim (inline -do-calc))\n" +
                        "(veq:vdef -do-calc (vpts x seg)\n" +
                        "  (declare #.*opt* (veq:fvec vpts) (veq:ff x) (pos-int seg))\n" +
                        "  (let ((2x (+ x x))\n" +
                        "        (xe2 (* x x))\n" +
                        "        (ia (* 2 seg)))\n" +
                        "    (declare (veq:ff 2x xe2) (pos-int ia))\n" +
                        "    (labels ((fx ((veq:varg 2 va vb vc))\n" +
                        "               (veq:f2+ (veq:f2scale vc xe2)\n" +
                        "                        (veq:f2+ (veq:f2scale va (+ 1f0 (- 2x) xe2))\n" +
                        "                                 (veq:f2scale vb (+ 2x (* -2f0 xe2)))))))\n" +
                        "      (weird:mvc #'fx (veq:f2$ vpts ia (+ ia 1) (+ ia 2))))))\n" +
                        "\n", subjects
        );
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Valid Real-World LISP Source File")
    public void testValidSExpr4(Subjects subjects) {
        this.testSuccessful(
                "\n" +
                        "(in-package :bzspl)\n" +
                        "\n" +
                        "\n" +
                        "(defstruct bzspl\n" +
                        "  (n nil :type pos-int :read-only t)\n" +
                        "  (ns nil :type pos-int :read-only t)\n" +
                        "  (closed nil :type boolean :read-only t)\n" +
                        "  (vpts nil :type veq:fvec :read-only t))\n" +
                        "\n" +
                        "\n" +
                        "(declaim (inline -do-calc))\n" +
                        "(veq:vdef -do-calc (vpts x seg)\n" +
                        "  (declare *opt* (veq:fvec vpts) (veq:ff x) (pos-int seg))\n" +
                        "  (let ((2x (+ x x))\n" +
                        "        (xe2 (* x x))\n" +
                        "        (ia (* 2 seg)))\n" +
                        "    (declare (veq:ff 2x xe2) (pos-int ia))\n" +
                        "    (labels ((fx ((veq:varg 2 va vb vc))\n" +
                        "               (veq:f2+ (veq:f2scale vc xe2)\n" +
                        "                        (veq:f2+ (veq:f2scale va (+ 1f0 (- 2x) xe2))\n" +
                        "                                 (veq:f2scale vb (+ 2x (* -2f0 xe2)))))))\n" +
                        "      (weird:mvc fx (veq:f2$ vpts ia (+ ia 1) (+ ia 2))))))\n" +
                        "\n", subjects
        );
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Valid Real-World LISP Source File Part")
    public void testValidSExpr3(Subjects subjects) {
        this.testSuccessful(
                "(defstruct bzspl\n" +
                        "  (n nil :type pos-int :read-only t)\n" +
                        "  (ns nil :type pos-int :read-only t)\n" +
                        "  (closed nil :type boolean :read-only t)\n" +
                        "  (vpts nil :type veq:fvec :read-only t))\n", subjects
        );
    }
}