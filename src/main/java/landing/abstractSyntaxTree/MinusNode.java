package landing.abstractSyntaxTree;

import landing.scanner.TokenType;

public class MinusNode extends ASTnodeBinary {

    public MinusNode(ASTnode leftNode, ASTnode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.MINUS_SIGN;
    }
}
