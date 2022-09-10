
public class Tester {

    private static boolean testPassed = true;
    private static int testNum = 0;

    /**
     * @param args command line arguments
     */
    public static void main(String[] args) {

        testStackAsArray();
        testExpTokenizer();
        testOrOp();
        testAndOp();
        testXorOp();
        testNotOp();
        testVariableToken();
        testStackCalculator();


        // Notifying the user that the code have passed all tests.
        if (testPassed) {
            System.out.println("All " + testNum + " tests passed!");
        }
    }

    /**
     * This utility function will count the number of times it was invoked.
     * In addition, if a test fails the function will print the error message.
     *
     * @param exp The actual test condition
     * @param msg An error message, will be printed to the screen in case the test fails.
     */
    private static void test(boolean exp, String msg) {
        testNum++;

        if (!exp) {
            testPassed = false;
            System.out.println("Test " + testNum + " failed: " + msg);
        }
    }

    /**
     * Checks the StackAsArray class.
     */
    private static void testStackAsArray() {
        StackAsArray test_stack = new StackAsArray();
        boolean test_res = true;

        test(test_stack.isEmpty(), "The Stack should be empty");

        try {
            test_stack.pop();
        } catch (RuntimeException e) {
            test_res = true;
        }

        test(test_res, "RuntimeException should be thrown");

        test_stack.push(5);
        test_stack.push("a");
        test_stack.push("&");
        test_stack.push(7);
        test_stack.push("f");
        test(!(test_stack.isEmpty()), "The Stack should not be empty");
        test(test_stack.pop().equals("f"), "pop should return the last element - f");
    }

    /**
     * Checks the ExpTokenizer class.
     */
    private static void testExpTokenizer() {
        boolean[] assigner = {true, false, false, true, true, false, true, false, false};
        ExpTokenizer toknizer = new ExpTokenizer("a b g i F", assigner);
        boolean testAns = true;

        test(toknizer.countTokens() == 5, "the toknizer should contain 5 tokens");

        while (toknizer.hasMoreElements()) {
            if (!(toknizer.nextElement() instanceof ValueToken))
                testAns = false;
        }

        test(testAns, "All Tokens should be of type ValueToken");
        test(!toknizer.hasMoreElements(), "All Tokens should be of type ValueToken");

        ExpTokenizer tokn = new ExpTokenizer("& (", assigner);
        boolean testRes = true;

        while (tokn.hasMoreElements()) {
            if (!(tokn.nextElement() instanceof AndOp)) {
                testRes = false;
            }

            test(testRes, "the token should be of type AndOp");

            if (!(tokn.nextElement() instanceof OpenBracket)) {
                testRes = false;
            }

            test(testRes, "the token should be of type OpenBracket");
        }
    }

    /**
     * Checks the OrOp class.
     */
    private static void testOrOp() {
        OrOp op = new OrOp();

        test(op.toString().equals("|"), "The string | should be printed.");
        test(op.operate(true, true), "true | true = true");
        test(op.operate(true, false), "true | false= true");
        test(op.operate(false, true), "false | true = true");
        test(!op.operate(false, false), "false | false = false");
        test(op.getPrecedence() == 1.0, "The precedence should be 1.0");
    }

    /**
     * Checks the AndOp class.
     */
    private static void testAndOp() {
        AndOp op = new AndOp();

        test(op.toString().equals("&"), "The string & should be printed.");
        test(op.operate(true, true), "true & true = true");
        test(!op.operate(true, false), "true & false= false");
        test(!op.operate(false, true), "false & true = false");
        test(!op.operate(false, false), "false & false = false");
        test(op.getPrecedence() == 2.0, "The presedence should be 2.0");
    }

    /**
     * Checks the XorOp class.
     */
    private static void testXorOp() {
        XorOp op = new XorOp();

        test(op.toString().equals("^"), "The string ^ should be printed.");
        test(!op.operate(true, true), "true ^ true = false");
        test(op.operate(true, false), "true ^ false= true");
        test(op.operate(false, true), "false ^ true = true");
        test(!op.operate(false, false), "false ^ false = false");
        test(op.getPrecedence() == 3.0, "The presedence should be 3.0");
    }

    /**
     * Checks the NotOp class.
     */
    private static void testNotOp() {
        NotOp op = new NotOp();

        test(op.toString().equals("!"), "The string ! should be printed.");
        test(!op.operate(true), "!true = false");
        test(op.operate(false), "!false = true");
        test(op.getPrecedence() == 4.0, "The presedence should be 4.0");
    }

    /**
     * Checks the ValueToken class.
     */
    private static void testVariableToken() {
        boolean[] assigner = {true, false, false, true, true, false, true, false, false};
        ValueToken t = new VariableToken("a", assigner);
        ValueToken c = new VariableToken("c", assigner);
        ValueToken s = new ValueToken(true);
        ValueToken f = new ValueToken(false);

        test(t.getValue(), "Value of 'a' should be true");
        test(!(c.getValue()), "Value of 'c' should be false");
        test(t.toString().equals("a"), "String of VariableToken should be a");
        test(s.toString().equals("T"), "String of true should be T");
        test(f.toString().equals("F"), "String of false should be F");
    }

    /**
     * Helper function to check the StackCalculator class
     *
     * @param pc            a StackCalculator instance
     * @param infix         a string representing an infix expression
     * @param expectedPost  the expected post expression of infix
     * @param expectedValue the expected boolean value of the expression
     */
    private static void testPostAndEvaluate(StackCalculator pc, String infix, String expectedPost, boolean expectedValue) {
        String postExp = pc.infixToPostfix(infix);
        String msg = String.format("Postfix of \"%s\" is \"%s\" (got: \"%s\")", infix, expectedPost, postExp);
        test(postExp.equals(expectedPost), msg);
        msg = String.format("The output of \"%s\" should be %s", expectedPost, expectedValue);
        test(pc.evaluate(postExp) == expectedValue, msg);
    }

    /**
     * Checks the StackCalculator class.
     */
    private static void testStackCalculator() {
        boolean[] assigner = {true, false, false, true, true, false, true, false, false};
        StackCalculator pc = new StackCalculator(assigner);

        testPostAndEvaluate(pc, "a & F", "a F &", false);
        testPostAndEvaluate(pc, "a ^ d", "a d ^", false);
        testPostAndEvaluate(pc, "T & F | T", "T F & T |", true);
        testPostAndEvaluate(pc, "a | b & c | d", "a b c & d | |", true);
        testPostAndEvaluate(pc, "( a ^ h ) & ( h ^ h ) & ( h | e | ( c | h ) )", "a h ^ h h ^ h e c h | | | & &", false);
        testPostAndEvaluate(pc, "b & i & b & h & ( e | d ) ^ d ^ d", "b i b h e d | d d ^ ^ & & & &", false);
        testPostAndEvaluate(pc, "( ( f | c ^ h | i ) | ( ( g ^ b ) ^ ( g & b ) ) )", "f c h ^ i | | g b ^ g b & ^ |", true);
        testPostAndEvaluate(pc, "a ^ ! b", "a b ! ^", false);
    }
}