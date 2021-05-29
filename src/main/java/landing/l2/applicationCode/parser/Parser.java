package landing.l2.applicationCode.parser;

import landing.l4.abstractionCode.ASTnode;
import landing.l3.domainCode.parser.RecursiveDescent;
import landing.l3.domainCode.exceptions.ParenthesisException;
import landing.l3.domainCode.exceptions.SyntaxException;
import landing.l3.domainCode.scanner.Token;

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
