package landing.parser;

import landing.abstractSyntaxTree.ASTnode;
import landing.exceptions.ParenthesisException;
import landing.exceptions.SyntaxException;
import landing.scanner.Token;

import java.util.List;

public class Parser {
    RecursiveDescent recursiveDescent;
    ASTnode rootNode;

    public Parser(List<Token> tokenList) throws ParenthesisException, SyntaxException {
        recursiveDescent = new RecursiveDescent(tokenList);
        rootNode = recursiveDescent.buildAST();
    }

    public double calculateValueFor(double xValue) {
        return rootNode.doOperation(xValue);
    }
}
