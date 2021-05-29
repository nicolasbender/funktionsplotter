package landing.l3.domainCode.ast;

import landing.l3.domainCode.scanner.TokenType;
import landing.l4.abstractionCode.ASTnodeUnary;

public class VariableNode extends ASTnodeUnary {
    public VariableNode() {
        super(null);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.VARIABLE;
    }

    @Override
    public double doOperation(double value) {
        return value;
    }
}
