package landing.abstractSyntaxTree;

import landing.scanner.TokenType;

public class VariableNode implements ASTnodeOneChild{
    private ValueNode valueNode;

    public VariableNode(ValueNode valueNode) {
        this.valueNode = valueNode;
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.VARIABLE;
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
