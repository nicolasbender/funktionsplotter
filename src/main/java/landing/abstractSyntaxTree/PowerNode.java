package landing.abstractSyntaxTree;

import landing.scanner.TokenType;

public class PowerNode extends ASTnodeBinary {

    public PowerNode(ASTnode leftNode, ASTnode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.POWER_SIGN;
    }
}
