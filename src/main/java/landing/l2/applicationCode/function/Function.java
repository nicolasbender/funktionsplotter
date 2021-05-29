package landing.l2.applicationCode.function;

import landing.l2.applicationCode.scanner.Scanner;
import landing.l4.abstractionCode.ASTnode;
import landing.l3.domainCode.exceptions.ParenthesisException;
import landing.l3.domainCode.exceptions.SymbolException;
import landing.l3.domainCode.exceptions.SyntaxException;
import landing.l2.applicationCode.parser.Parser;
import landing.l3.domainCode.scanner.Token;

import java.util.List;
import java.util.Objects;

public class Function {
    private final ASTnode rootNode;
    private String functionAsString;

    public Function(String functionAsString) throws ParenthesisException, SymbolException, SyntaxException {
        this.rootNode = compile(functionAsString);
    }

    public double calculateValueOf(double currentX) throws ArithmeticException{
        return rootNode.doOperation(currentX);
    }

    private ASTnode compile(String functionAsString) throws ParenthesisException, SymbolException, SyntaxException {
        Scanner scanner = new Scanner(functionAsString);
        this.functionAsString = scanner.getFunctionAsString();
        List<Token> tokenList = scanner.getTokenList();
        Parser parser = new Parser(tokenList);
        return parser.getRoot();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Function function = (Function) o;
        return functionAsString.equals(function.functionAsString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(functionAsString);
    }
}
