package landing.abstractSyntaxTree;

import landing.scanner.TokenType;

public class NegateNode extends ASTnodeUnary {
    public NegateNode(ASTnode node) {
        super(node);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.MINUS_SIGN;
    }

    @Override
    public double doOperation(double value) {
        return -node.doOperation(value);
    }
}
