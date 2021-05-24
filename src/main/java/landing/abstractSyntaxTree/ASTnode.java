package landing.abstractSyntaxTree;

import landing.scanner.TokenType;

public interface ASTnode {
    TokenType getTokenType();
    double doOperation();
}
