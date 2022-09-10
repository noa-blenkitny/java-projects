

public class VariableToken extends ValueToken {
    private String var;
    private boolean[] assigner;

    public VariableToken(String var, boolean[] assigner) {
        super(assigner[var.charAt(0) - 'a']);
        this.var = var;
        this.assigner = assigner;
    }

    /**
     * @return char representation of var.
     **/
    public char getVariable() {
        return var.charAt(0);
    }

    /**
     * @return string representation of var.
     **/
    public String toString() {
        return var;
    }
}


