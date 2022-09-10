
public abstract class Calculator {
    protected boolean[] assigner;

    public Calculator(boolean[] assigner) {
        this.assigner = assigner;
    }

    /**
     * @param expr a string representing a valid postfix expression.
     * @return evaluates expr and returns its value
     */
    public abstract boolean evaluate(String expr);
}
