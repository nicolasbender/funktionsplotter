package landing.l4.abstractionCode;

public abstract class ASTnode {
    public abstract TokenType getTokenType();
    public abstract double doOperation(double value);
}
