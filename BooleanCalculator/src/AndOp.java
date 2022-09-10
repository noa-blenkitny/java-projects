
public class AndOp extends BinaryOp implements CalcToken {
    char andOp;

    public AndOp() {
        super('&');
        this.andOp = '&';
    }

    /**
     * Return the precedence value of this operation
     *
     * @return the precedence of this operation.
     */
    public double getPrecedence() {
        return 2.0;
    }

    /**
     * Return the result of this operation on its operands.
     *
     * @param left  the left operand.
     * @param right the right operand.
     * @return the result of the operation.
     */
    public boolean operate(boolean left, boolean right) {
        return left && right;
    }
}
