

public class OrOp extends BinaryOp implements CalcToken {
    char orOp;

    public OrOp() {
        super('|');
        this.orOp = '|';
    }

    /**
     * Return the precedence value of this operation
     *
     * @return the precedence of this operation.
     */
    public double getPrecedence() {
        return 1.0;
    }

    /**
     * Return the result of this operation on its operands.
     *
     * @param left  the left operand.
     * @param right the right operand.
     * @return the result of the operation.
     */
    public boolean operate(boolean left, boolean right) {
        return left || right;
    }
}
