package landing.scanner;

public enum TokenType {
    END_OF_TERM,
    VARIABLE,
    NUMBER,
    MULT_SIGN,
    DIV_SIGN,
    PLUS_SIGN,
    MINUS_SIGN,
    POWER_SIGN,
    LEFT_PARENTHESIS,
    RIGHT_PARENTHESIS;

    TokenType() {
    }
}
