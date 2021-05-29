package landing.l3.domainCode.ast;

import landing.l4.abstractionCode.TokenType;
import landing.l4.abstractionCode.ASTnode;
import landing.l4.abstractionCode.ASTnodeBinary;

public class MultNode extends ASTnodeBinary {

    public MultNode(ASTnode leftNode, ASTnode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.MULT_SIGN;
    }

    @Override
    public double doOperation(double value) {
        return leftNode.doOperation(value) * rightNode.doOperation(value);
    }
}
