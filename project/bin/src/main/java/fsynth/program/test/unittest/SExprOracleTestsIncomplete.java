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
@DisplayName("SExpr Oracle Tests - Incomplete")
public class SExprOracleTestsIncomplete extends SExprOracleTests {

    @SuppressWarnings("unused")
    static Stream<Arguments> localParameters() {
        return SExprOracleTests.localParameters();
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Default Test Case from sexp-parser with missing quote and parenthesis")
    public void testIncompleteSExpr1(Subjects subjects) {
        this.testIncomplete(
                " (ab\\(c 123 de(f g) \"hi\\\"j", subjects
        );
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Default Test Case from sexp-parser with missing parenthesis")
    public void testIncompleteSExpr2(Subjects subjects) {
        this.testIncomplete(
                " (ab\\(c 123 de(f g) \"hi\\\"j\"", subjects
        );
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Default Test Case from sexp-parser with missing parenthesis, quote and j")
    public void testIncompleteSExpr3(Subjects subjects) {
        this.testIncomplete(
                " (ab\\(c 123 de(f g) \"hi\\\"", subjects
        );
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Default Test Case from sexp-parser with trailing backslash")
    public void testIncompleteSExpr4(Subjects subjects) {
        this.testIncomplete(
                " (ab\\(c 123 de(f g) \"hi\\", subjects
        );
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Incomplete Real-World LISP Source File")
    @Disabled("Comments are currently not supported by all subjects")
    public void testIncompleteSExpr5(Subjects subjects) {
        this.testIncomplete(
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
                        "        (ia (* 2 seg)))\n"
                , subjects
        );
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Incomplete Real-World LISP Source File")
    public void testIncompleteSExpr6(Subjects subjects) {
        this.testIncomplete(
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
                        "        (ia (* 2 seg)))\n"
                , subjects
        );
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Incomplete Real-World LISP Source File with code line commented out")
    @Disabled("Comments are currently not supported by all subjects")
    public void testIncompleteSExpr7(Subjects subjects) {
        this.testIncomplete(
                "\n" +
                        "(in-package :bzspl)\n" +
                        "\n" +
                        "; http://graphics.cs.ucdavis.edu/~joy/ecs178/Unit-7-Notes/MatrixBSpline.pdf\n" +
                        "\n" +
                        "(defstruct bzspl\n" +
                        "  (n nil :type pos-int :read-only t)\n" +
                        "  (ns nil :type pos-int :read-only t)\n" +
                        "  (closed nil :type boolean :read-only t)\n" +
                        ";  (vpts nil :type veq:fvec :read-only t))\n"
                , subjects
        );
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Incomplete Real-World LISP Source File with deleted code line")
    public void testIncompleteSExpr8(Subjects subjects) {
        this.testIncomplete(
                "\n" +
                        "(in-package :bzspl)\n" +
                        "\n" +
                        "\n" +
                        "(defstruct bzspl\n" +
                        "  (n nil :type pos-int :read-only t)\n" +
                        "  (ns nil :type pos-int :read-only t)\n" +
                        "  (closed nil :type boolean :read-only t)\n"
                , subjects
        );
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Real-World File with empty Parentheses at the end")
    public void testIncompleteSExpr9(Subjects subjects) {
        this.testIncomplete(
                "(defmethod get-value ((self poly-triangle) (index integer))\n" +
                        "  (assert (>= 3 index 1))\n" +
                        "  (_wrap_Poly_Triangle_value (ff-pointer self) index()\n"
                , subjects
        );
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Real-World File with empty Parentheses at the end, finished inner object")
    public void testIncompleteSExpr10(Subjects subjects) {
        this.testIncomplete(
                "(defmethod get-value ((self poly-triangle) (index integer))\n" +
                        "  (assert (>= 3 index 1))\n" +
                        "  (_wrap_Poly_Triangle_value (ff-pointer self) index())\n"
                , subjects
        );
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Real-World File with empty Parentheses at the end")
    public void testIncompleteSExpr11(Subjects subjects) {
        this.testSuccessful(
                "(defmethod get-value ((self poly-triangle) (index integer))\n" +
                        "  (assert (>= 3 index 1))\n" +
                        "  (_wrap_Poly_Triangle_value (ff-pointer self) index()))\n"
                , subjects
        );
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Real-World File with unfinished String and non-printable characters")
    public void testIncompleteSExpr12(Subjects subjects) {
        //noinspection SpellCheckingInspection
        this.testIncomplete(
                "(defun solve (n)\n" +
                        "  (let ((\u009D (loop repeat n collect (read))))\n" +
                        "    (- (length a) (len\u009Fth (removû-duplicates a)))))\n" +
                        "\n" +
                        "(defun main ()\n" +
                        "  (format t e~a~%\" (solve (read))))\n" +
                        "(main)\n"
                , subjects
        );
    }

    @SuppressWarnings("HardcodedFileSeparator")
    @ParameterizedTest(name = "{index}: ({0})")
    @MethodSource("localParameters")
    @DisplayName("Real-World File with finished String and non-printable characters")
    public void testIncompleteSExpr13(Subjects subjects) {
        //noinspection SpellCheckingInspection
        this.testIncomplete(
                "(defun solve (n)\n" +
                        "  (let ((\u009D (loop repeat n collect (read))))\n" +
                        "    (- (length a) (len\u009Fth (removû-duplicates a)))))\n" +
                        "\n" +
                        "(defun main ()\n" +
                        "  (format t e~a~%\" (solve (read))))\n" +
                        "(main)\"\n"
                , subjects
        );
    }

}