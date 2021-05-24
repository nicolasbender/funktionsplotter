package landing.parser;

import landing.exceptions.ParenthesisException;
import landing.exceptions.SymbolException;
import landing.exceptions.SyntaxException;
import landing.scanner.Scanner;
import landing.scanner.Token;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ParserTest {
    @Test
    public void testcalculateValue() throws ParenthesisException, SymbolException, SyntaxException {
        Scanner scanner = new Scanner("-3*(x+1)^(x+2-1)/6");
        List<Token> tokenList = scanner.getTokenList();
        Parser parser = new Parser(tokenList);
        double result = parser.getRoot().doOperation(2);
        assertEquals(-13.5, result, 1E-6);
    }

    @Test
    public void testcalculateValueDivisionZero() throws ParenthesisException, SymbolException, SyntaxException {
        Scanner scanner = new Scanner("1/x");
        List<Token> tokenList = scanner.getTokenList();
        Parser parser = new Parser(tokenList);
        assertThrows(ArithmeticException.class, () -> parser.getRoot().doOperation(0));
    }

    @Test
    public void testcalculateValueWrongSyntax() throws ParenthesisException, SymbolException {
        Scanner scanner = new Scanner("*1");
        List<Token> tokenList = scanner.getTokenList();
        assertThrows(SyntaxException.class, () -> new Parser(tokenList));
    }

    @Test
    public void testcalculateValueWrongSyntaxAtEnd() throws ParenthesisException, SymbolException {
        Scanner scanner = new Scanner("1+");
        List<Token> tokenList = scanner.getTokenList();
        assertThrows(SyntaxException.class, () -> new Parser(tokenList));
    }
}
