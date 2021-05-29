package landing.l4.abstractionCode;

public abstract class ASTnodeBinary extends ASTnode{
    protected ASTnode leftNode;
    protected ASTnode rightNode;

    public ASTnodeBinary(ASTnode leftNode, ASTnode rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }
    public abstract double doOperation(double value);
}
