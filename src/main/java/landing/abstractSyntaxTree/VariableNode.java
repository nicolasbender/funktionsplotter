package landing.abstractSyntaxTree;

import landing.scanner.TokenType;

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
