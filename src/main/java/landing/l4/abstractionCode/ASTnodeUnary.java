package landing.l4.abstractionCode;

public abstract class ASTnodeUnary extends ASTnode {
    protected ASTnode node;
    public ASTnodeUnary(ASTnode node) {
        this.node = node;
    }

    public abstract double doOperation(double value);
}
