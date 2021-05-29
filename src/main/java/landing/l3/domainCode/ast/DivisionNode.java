package landing.l3.domainCode.ast;

import landing.l3.domainCode.scanner.TokenType;
import landing.l4.abstractionCode.ASTnode;
import landing.l4.abstractionCode.ASTnodeBinary;

public class DivisionNode extends ASTnodeBinary {

    public DivisionNode(ASTnode leftNode, ASTnode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.DIV_SIGN;
    }

    @Override
    public double doOperation(double value) throws ArithmeticException{
        double rightNodeValue = rightNode.doOperation(value);
        if(Math.abs(rightNodeValue)<1E-8) {
            throw new ArithmeticException("Divison by zero");
        }
        return leftNode.doOperation(value) / rightNodeValue;
    }
}
