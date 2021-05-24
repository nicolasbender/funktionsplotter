package landing.abstractSyntaxTree;

import landing.scanner.TokenType;

public class PlusNode extends ASTnodeBinary {

    public PlusNode(ASTnode leftNode, ASTnode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.PLUS_SIGN;
    }
}
