package landing.abstractSyntaxTree;

public abstract class ASTnodeBinary extends ASTnode{
    protected ASTnode leftNode;
    protected ASTnode rightNode;
    public ASTnodeBinary(ASTnode leftNode, ASTnode rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public ASTnode getLeftNode() {
        return leftNode;
    }
    public ASTnode getRightNode(){ return rightNode; }
    public double doOperation() {
        return leftNode.doOperation() * rightNode.doOperation();
    }
}
