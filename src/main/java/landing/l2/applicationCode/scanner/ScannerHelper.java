package landing.l2.applicationCode.scanner;

import landing.l3.domainCode.scanner.Token;
import landing.l3.domainCode.scanner.TokenType;
import landing.l3.domainCode.exceptions.ParenthesisException;
import landing.l3.domainCode.exceptions.SymbolException;

public class ScannerHelper {
    private String preprocessed;
    public ScannerHelper(String functionAsString) {
        this.preprocessed = preprocess(functionAsString);
    }

    private String preprocess(String functionAsString) {
        functionAsString += "$";
        return functionAsString.replace(" ", "");
    }

    public Token getToken(String partOfFunction, int position, int nestingLevel) throws ParenthesisException, SymbolException {
        char currentSymbol = partOfFunction.charAt(0);
        if(currentSymbol == 'x') {
            return new Token(TokenType.VARIABLE, position, 1, nestingLevel);
        } else if(currentSymbol == '*') {
            return new Token(TokenType.MULT_SIGN, position, 1, nestingLevel);
        } else if(currentSymbol == '+') {
            return new Token(TokenType.PLUS_SIGN, position, 1, nestingLevel);
        } else if(currentSymbol == '-') {
            return new Token(TokenType.MINUS_SIGN, position, 1, nestingLevel);
        } else if(currentSymbol == '/') {
            return new Token(TokenType.DIV_SIGN, position, 1, nestingLevel);
        } else if(currentSymbol == '^') {
            return new Token(TokenType.POWER_SIGN, position, 1, nestingLevel);
        } else if(currentSymbol == '$') {
            if(nestingLevel > 0) {
                throw new ParenthesisException("There is at least one open parenthesis");
            }
            return new Token(TokenType.END_OF_TERM, position, 1, nestingLevel);
        } else if(currentSymbol == '(') {
            return new Token(TokenType.LEFT_PARENTHESIS, position, 1, nestingLevel+1);
        } else if(currentSymbol == ')') {
            if(nestingLevel == 0) {
                throw new ParenthesisException("Right parenthesis without left one at position "+position);
            }
            return new Token(TokenType.RIGHT_PARENTHESIS, position, 1, nestingLevel-1);
        } else if(NumberBuilder.isNumerical(currentSymbol)) {
            String valueAsString = NumberBuilder.determineStringForValueFrom(partOfFunction);
            Double valueAsDouble = NumberBuilder.convertValueToDoubleFrom(valueAsString);
            return new Token(TokenType.NUMBER, position, valueAsDouble, valueAsString.length(), nestingLevel);
        }
        else {
            throw new SymbolException("Bad symbol at position: "+position+"; cannot generate token");
        }
    }

    public String getPreprocessed() {
        return preprocessed;
    }
}
