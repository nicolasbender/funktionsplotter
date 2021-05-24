package landing.abstractSyntaxTree;

public abstract class ASTnodeUnary extends ASTnode {
    protected ValueNode valueNode;
    public ASTnodeUnary(ValueNode valueNode) {
        this.valueNode = valueNode;
    }

    public double doOperation() {
        return valueNode.getValue();
    }
    public ValueNode getValueNode() {
        return valueNode;
    }
}
