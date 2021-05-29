package landing.l4.abstractionCode;

import landing.l3.domainCode.scanner.TokenType;

public abstract class ASTnode {
    public abstract TokenType getTokenType();
    public abstract double doOperation(double value);
}
