package landing.abstractSyntaxTree;

import landing.scanner.TokenType;

public abstract class ASTnode {
    public abstract TokenType getTokenType();
    public abstract double doOperation();
}
