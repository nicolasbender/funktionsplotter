package landing.abstractSyntaxTree;

import landing.scanner.TokenType;

public class MultNode implements ASTnodeBinary {
    private ASTnode leftNode;
    private ASTnode rightNode;

    public MultNode(ASTnode leftNode, ASTnode rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.MULT_SIGN;
    }

    @Override
    public double doOperation() {
        return leftNode.doOperation() * rightNode.doOperation();
    }

    @Override
    public ASTnode getLeftNode() {
        return leftNode;
    }

    @Override
    public ASTnode getRightNode() {
        return rightNode;
    }
}
