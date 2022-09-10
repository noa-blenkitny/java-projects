
public class NotOp extends Op implements CalcToken {
    char notOp;

    public NotOp() {
        super('!');
        this.notOp = '!';
    }

    /**
     * Return the precedence value of this operation
     *
     * @return the precedence of this operation.
     */
    public double getPrecedence() {
        return 4.0;
    }

    /**
     * Return the result of this operation on its operand.
     *
     * @param operand the operand.
     * @return the result of the operation.
     */
    public boolean operate(boolean operand) {
        return !operand;
    }
}
