/**
 * Abstract class describing binary Boolean operations.
 */
public abstract class BinaryOp extends Op {

    public BinaryOp(char op) {
        super(op);
    }

    /**
     * Return the result of this operation on its operands.
     *
     * @param left  the left operand.
     * @param right the right operand.
     * @return the result of the operation.
     */
    public abstract boolean operate(boolean left, boolean right);

}