package landing.abstractSyntaxTree;

import landing.scanner.TokenType;

public class DivisionNode extends ASTnodeBinary {

    public DivisionNode(ASTnode leftNode, ASTnode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.DIV_SIGN;
    }

    @Override
    public double doOperation(double value) {
        double rightNodeValue = rightNode.doOperation(value);
        if(Math.abs(rightNodeValue)<1E-8) {
            throw new ArithmeticException("Divison by zero");
        }
        return leftNode.doOperation(value) / rightNodeValue;
    }
}
