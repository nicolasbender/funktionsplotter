package landing.abstractSyntaxTree;

import landing.scanner.TokenType;

public class NumberNode implements ASTnodeUnary {
    private ValueNode valueNode;

    public NumberNode(ValueNode valueNode) {
        this.valueNode = valueNode;
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.NUMBER;
    }

    @Override
    public double doOperation() {
        return valueNode.getValue();
    }

    @Override
    public ValueNode getValueNode() {
        return valueNode;
    }
}
