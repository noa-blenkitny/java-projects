public abstract class Op implements CalcToken {
    char op;

    public Op(char op) {
        this.op = op;
    }

    /**
     * Return the precedence value of this operation
     * (note that actual values do not matter, only their
     * relative values to each other).
     *
     * @return the precedence of this operation.
     */
    public abstract double getPrecedence();

    public String toString() {
        return String.valueOf(op);
    }
}
