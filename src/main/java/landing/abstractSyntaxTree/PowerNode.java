package landing.abstractSyntaxTree;

import landing.scanner.TokenType;

public class PowerNode implements ASTnodeBinary {
    private ASTnode leftNode;
    private ASTnode rightNode;

    public PowerNode(ASTnode leftNode, ASTnode rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.POWER_SIGN;
    }

    @Override
    public double doOperation() {
        return Math.pow(leftNode.doOperation(), rightNode.doOperation());
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
