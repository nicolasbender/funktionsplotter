package landing.scanner;

import landing.exceptions.BadParenthesisException;
import landing.exceptions.BadSymbolException;

import java.util.ArrayList;
import java.util.List;

public class Scanner {
    private final List<Token> tokenList;
    private final ScannerHelper scannerHelper;

    public Scanner(String functionAsString) throws BadSymbolException, BadParenthesisException {
        scannerHelper = new ScannerHelper(functionAsString);
        String preProcessedfunctionAsString = scannerHelper.getPreprocessed();
        this.tokenList = buildTokenList(preProcessedfunctionAsString);
    }

    public List<Token> getTokenList() {
        return tokenList;
    }

    public List<Token> buildTokenList(String partOfFunction) throws BadSymbolException, BadParenthesisException {
        List<Token> tokenList = new ArrayList<>();
        Token currentToken;
        int position = 0;
        int nestingLevel = 0;
        do{
            currentToken = scannerHelper.getToken(partOfFunction.substring(position), position, nestingLevel);
            position += currentToken.getLength();
            nestingLevel = currentToken.getNestingLevel();
            tokenList.add(currentToken);
        } while(currentToken.getTokenType() != TokenType.END_OF_TERM);
        return tokenList;
    }


}
