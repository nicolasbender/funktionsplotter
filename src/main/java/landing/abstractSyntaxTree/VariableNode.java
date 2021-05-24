package landing.abstractSyntaxTree;

import landing.scanner.TokenType;

public class VariableNode extends ASTnodeUnary {

    public VariableNode(ValueNode valueNode) {
        super(valueNode);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.VARIABLE;
    }
}
