package landing.abstractSyntaxTree;

public interface ASTnodeBinary extends ASTnode{
    ASTnode getLeftNode();
    ASTnode getRightNode();
}
