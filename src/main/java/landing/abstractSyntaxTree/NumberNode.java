package landing.abstractSyntaxTree;

import landing.scanner.TokenType;

public class NumberNode extends ASTnodeUnary {

    public NumberNode(ValueNode valueNode) {
        super(valueNode);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.NUMBER;
    }
}
