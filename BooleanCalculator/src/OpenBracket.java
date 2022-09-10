public class OpenBracket implements CalcToken {

    private char openBracket;

    public OpenBracket() {
        super();
        this.openBracket = '(';
    }

    public String toString() {
        return "(";
    }
}
