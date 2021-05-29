package landing.l3.domainCode.ast;

import landing.l3.domainCode.scanner.TokenType;
import landing.l4.abstractionCode.ASTnode;
import landing.l4.abstractionCode.ASTnodeBinary;

public class PlusNode extends ASTnodeBinary {

    public PlusNode(ASTnode leftNode, ASTnode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.PLUS_SIGN;
    }

    @Override
    public double doOperation(double value) {
        return leftNode.doOperation(value) + rightNode.doOperation(value);
    }
}
