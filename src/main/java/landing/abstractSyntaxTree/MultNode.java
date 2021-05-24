package landing.abstractSyntaxTree;

import landing.scanner.TokenType;

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
