package landing.abstractSyntaxTree;

import landing.scanner.TokenType;

public class MinusNode implements ASTnodeTwoChild{
    private ASTnode leftNode;
    private ASTnode rightNode;

    public MinusNode(ASTnode leftNode, ASTnode rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.MINUS_SIGN;
    }

    @Override
    public double doOperation() {
        return leftNode.doOperation() - rightNode.doOperation();
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
