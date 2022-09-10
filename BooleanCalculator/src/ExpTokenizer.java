public class ExpTokenizer {

    private String[] result;
    private int index;
    private boolean[] assigner;

    public ExpTokenizer(String exp, boolean[] assigner) {
        this.result = exp.split(" ");
        this.index = 0;
        this.assigner = assigner;
    }

    public CalcToken nextElement() { //we create for each token the suitable object.
        CalcToken resultToken = null;
        String token = nextToken();

        if (token.equals("|"))
            resultToken = new OrOp();

        else if (token.equals("("))
            resultToken = new OpenBracket();

        else if (token.equals(")"))
            resultToken = new CloseBracket();

        else if (token.equals("&"))
            resultToken = new AndOp();

        else if (token.equals("^"))
            resultToken = new XorOp();

        else if (token.equals("!"))
            resultToken = new NotOp();

        else if ("TF".contains(token))
            resultToken = new ValueToken("T".equals(token));

        else //variableToken.
            resultToken = new VariableToken(token, assigner);

        return resultToken;
    }

    //@return true if there are more elements.
    public boolean hasMoreElements() {
        return (this.index != this.result.length);
    }

    //@return the next token.
    public String nextToken() {
        return this.result[index++];
    }

    //@return the number of tokens.
    public int countTokens() {
        return (this.result.length - this.index);
    }
}