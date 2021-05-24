package landing.parser;

import landing.abstractSyntaxTree.ASTnode;
import landing.exceptions.ParenthesisException;
import landing.exceptions.SyntaxException;
import landing.scanner.Token;

import java.util.List;

public class Parser {
    ASTnode rootNode;

    public Parser(List<Token> tokenList) throws ParenthesisException, SyntaxException {
        RecursiveDescent recursiveDescent = new RecursiveDescent(tokenList);
        rootNode = recursiveDescent.buildAST();
    }

    public ASTnode getRoot() {
        return this.rootNode;
    }
}
