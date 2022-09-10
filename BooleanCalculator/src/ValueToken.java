class ValueToken implements CalcToken {
    private boolean val;

    public ValueToken(boolean val) {
        this.val = val;
    }

    /**
     * @return the val.
     **/
    public boolean getValue() {
        return val;
    }

    /**
     * @return string representation of val
     * T if true
     * F if false
     */
    public String toString() {
        if (val == true) {
            return "T";
        } else {
            return "F";
        }
    }
}
