package landing.abstractSyntaxTree;

import landing.scanner.TokenType;

public class PlusNode implements ASTnodeTwoChild{
    private ASTnode leftNode;
    private ASTnode rightNode;

    public PlusNode(ASTnode leftNode, ASTnode rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.PLUS_SIGN;
    }

    @Override
    public double doOperation() {
        return leftNode.doOperation() + rightNode.doOperation();
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
