
public class StackCalculator extends Calculator {
    private Object token;

    public StackCalculator(boolean[] assigner) {
        super(assigner);
    }

    /**
     * @param expr a valid infix expression.
     * @return a valid postfix expression
     */
    public String infixToPostfix(String expr) {
        String post_expr = "";
        StackAsArray stack = new StackAsArray();
        ExpTokenizer infix_expr = new ExpTokenizer(expr, assigner);


        while (infix_expr.hasMoreElements()) {
            token = infix_expr.nextElement();
            if (token instanceof OpenBracket) {
                stack.push(token);
            } else if (token instanceof CloseBracket) {
                while (!(stack.isEmpty())) {
                    Object elem = stack.pop();
                    if (elem instanceof OpenBracket == false) {
                        post_expr += elem.toString() + " ";
                    } else {
                        break;
                    }
                }
            } else if (token instanceof Op) {
                while (!(stack.isEmpty())) {
                    Object element = stack.pop();
                    if (element instanceof Op) {
                        double preced_of_element = ((Op) element).getPrecedence();
                        double preced_of_token = ((Op) token).getPrecedence();
                        if (preced_of_element > preced_of_token) {
                            post_expr += element.toString() + " ";
                        } else {
                            stack.push(element);
                            break;
                        }
                    } else {
                        stack.push(element);
                        break;
                    }
                }
                stack.push(token);
            } else {
                post_expr += token.toString() + " ";
            }
        }

        while (!(stack.isEmpty())) {
            post_expr += stack.pop().toString() + " ";
        }
        //return the postfix expr without space in the end.
        return post_expr.substring(0, post_expr.length() - 1);
    }

    /**
     * @param expr a string representing a valid postfix expression.
     * @return evaluates expr and returns its value
     */
    public boolean evaluate(String expr) {
        ExpTokenizer input_expr = new ExpTokenizer(expr, assigner);
        StackAsArray evaluate_stack = new StackAsArray();
        Object postoken;
        boolean final_res;
        Object result;

        while (input_expr.hasMoreElements()) {
            postoken = input_expr.nextElement();
            if (postoken instanceof Op) {

                if (postoken instanceof BinaryOp) {
                    Object first = evaluate_stack.pop();
                    Object second = evaluate_stack.pop();
                    result = (boolean) ((BinaryOp) postoken).operate(((ValueToken) first).getValue(), ((ValueToken) second).getValue());

                    if ((boolean) result) {
                        result = new ValueToken(true);
                    } else {
                        result = new ValueToken(false);
                    }
                } else {
                    Object top = evaluate_stack.pop();
                    result = ((NotOp) postoken).operate(((ValueToken) top).getValue());
                    if ((boolean) result) {
                        result = new ValueToken(true);
                    } else {
                        result = new ValueToken(false);
                    }
                }

                evaluate_stack.push(result);
            } else evaluate_stack.push(postoken);
        }

        final_res = ((ValueToken) evaluate_stack.pop()).getValue();
        return final_res;
    }
}




