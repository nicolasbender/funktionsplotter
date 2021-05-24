package landing.abstractSyntaxTree;

import landing.scanner.TokenType;

public class DivisionNode implements ASTnodeTwoChild{
    private ASTnode leftNode;
    private ASTnode rightNode;

    public DivisionNode(ASTnode leftNode, ASTnode rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.DIV_SIGN;
    }

    @Override
    public double doOperation() {
        double rightNodeValue = rightNode.doOperation();
        if(Math.abs(rightNodeValue)<1E-8) {
            throw new ArithmeticException("Divison by zero");
        }
        return leftNode.doOperation() / rightNodeValue;
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
