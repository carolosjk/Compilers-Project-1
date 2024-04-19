import java.io.IOException;
import java.io.InputStream;

public class Calculator {

    private final InputStream in;

    private int lookahead;

    public Calculator(InputStream in) throws IOException {
        this.in = in;
        lookahead = in.read();
    }

    private void consume(int symbol) throws IOException, ParseException{
        if (lookahead == symbol){
            lookahead = in.read();
            if ((char)lookahead == ' '){
                consume(' ');
            }
        }
        else
            throw new ParseException("Expected symbol: '" + (char)symbol + "' but instead got: '" + (char)lookahead + "'");
        
    }

    private boolean isDigit(int c){
        return '0' <= c && c <= '9';
    }

    private int evalDigit(int c) {
        return  c - '0';
    }

    public int Expr() throws IOException, ParseException {
        if (isDigit(lookahead)) {
            int term = Term();
            return Expr1(term);
        }else if (lookahead == '('){
            return Factor();
        }

        throw new ParseException("Expected number or '(' at Expr() but instead got " + (char)lookahead);
    }

    public int Term() throws IOException, ParseException {
        if (isDigit(lookahead)) {
            int factor = Factor();
            return Term1(factor);
        }else if (lookahead == '('){
            return Factor();
        }

        throw new ParseException("Expected number or '(' at Term() but instead got " + (char)lookahead);
    }

    public int Factor() throws IOException, ParseException {
        if (isDigit(lookahead)) {
            int digit = evalDigit(lookahead);
            consume(lookahead);
            return digit;
        }else if (lookahead == '('){
            consume('(');
            int num = Expr();
            consume(')');
            return num;
        }

        throw new ParseException("Expected number or '(' at Factor() but instead got " + (char)lookahead);
    }

    public int Expr1(int num) throws IOException, ParseException {
        switch (lookahead) {
            case ')':
                return num;
            case '^':
                consume('^');
                int term = Term();
                num = num ^ term;
                return Expr1(num);
            case -1:
                return num; 
            case '\n':
                return num; 
        }

        throw new ParseException("Expected EOF or ')' or '^' at Expr1() but instead got " + (char)lookahead);
    }

    public int Term1(int num) throws IOException, ParseException {
        switch (lookahead) {
            case ')':
                return num;
            case '&':
                consume('&');
                int factor = Factor();
                num = num & factor;
                return Term1(num);
            case '^':
                return num;
            case -1:
                return num; 
            case '\n':
                return num; 
        }

        throw new ParseException("Expected EOF or ')' or '^' or '&' at Term1() but instead got " + (char)lookahead);
    }
}
