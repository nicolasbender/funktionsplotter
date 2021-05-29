package landing.l3.domainCode.ast;

import landing.l3.domainCode.scanner.TokenType;
import landing.l4.abstractionCode.ASTnodeUnary;

public class NumberNode extends ASTnodeUnary {
    private final double value;

    public NumberNode(double value) {
        super(null);
        this.value = value;
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.NUMBER;
    }

    @Override
    public double doOperation(double value) {
        return this.value;
    }
}
