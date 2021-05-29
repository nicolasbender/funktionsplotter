package landing.l3.domainCode.ast;

import landing.l4.abstractionCode.TokenType;
import landing.l4.abstractionCode.ASTnode;
import landing.l4.abstractionCode.ASTnodeUnary;

public class NegateNode extends ASTnodeUnary {
    public NegateNode(ASTnode node) {
        super(node);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.MINUS_SIGN;
    }

    @Override
    public double doOperation(double value) {
        return -node.doOperation(value);
    }
}
