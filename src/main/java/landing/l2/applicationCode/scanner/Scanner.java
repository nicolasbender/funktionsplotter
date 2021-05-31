package landing.l2.applicationCode.scanner;

import landing.l3.domainCode.scanner.Tokenizer;
import landing.l3.domainCode.scanner.Token;
import landing.l4.abstractionCode.TokenType;
import landing.l3.domainCode.exceptions.ParenthesisException;
import landing.l3.domainCode.exceptions.SymbolException;

import java.util.ArrayList;
import java.util.List;

public class Scanner {
    private final List<Token> tokenList;
    private final Tokenizer tokenizer;

    public Scanner(String functionAsString) throws SymbolException, ParenthesisException {
        tokenizer = new Tokenizer(functionAsString);
        String preProcessedfunctionAsString = tokenizer.getPreprocessed();
        this.tokenList = buildTokenList(preProcessedfunctionAsString);
    }

    public List<Token> getTokenList() {
        return tokenList;
    }

    public String getFunctionAsString() {
        return tokenizer.getPreprocessed();
    }

    public List<Token> buildTokenList(String partOfFunction) throws SymbolException, ParenthesisException {
        List<Token> tokenList = new ArrayList<>();
        Token currentToken;
        int position = 0;
        int nestingLevel = 0;
        do{
            currentToken = tokenizer.getToken(partOfFunction.substring(position), position, nestingLevel);
            position += currentToken.getLength();
            nestingLevel = currentToken.getNestingLevel();
            tokenList.add(currentToken);
        } while(currentToken.getTokenType() != TokenType.END_OF_TERM);
        return tokenList;
    }


}
