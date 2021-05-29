package landing.l3.domainCode.scanner;

import landing.l4.abstractionCode.TokenType;

public class Token {
    private final TokenType tokenType;
    private final int position;
    private final Double value;
    private final int length;
    private final int nestingLevel;

    public Token(TokenType tokenType, int position, Double value, int length, int nestingLevel) {
        this.tokenType = tokenType;
        this.position = position;
        this.length = length;
        this.value = value;
        this.nestingLevel = nestingLevel;
    }
    public Token(TokenType tokenType, int position, int length, int nestingLevel) {
        this(tokenType, position, null, length, nestingLevel);
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public int getPosition() {
        return position;
    }

    public Double getValue() {
        return value;
    }

    public int getLength() {
        return length;
    }

    public int getNestingLevel() {
        return nestingLevel;
    }
}
