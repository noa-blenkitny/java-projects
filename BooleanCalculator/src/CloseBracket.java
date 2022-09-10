public class CloseBracket implements CalcToken {

    private char closeBracket;

    public CloseBracket() {
        super();
        this.closeBracket = ')';
    }

    public String toString() {
        return ")";
    }
}

