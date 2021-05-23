package landing.scanner;

import landing.exceptions.BadParenthesisException;
import landing.exceptions.BadSymbolException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ScannerTest {
    @Test
    public void testBuildTokenListLength() throws BadSymbolException, BadParenthesisException {
        String functionAsString = "1+2.0  *5 ";
        Scanner scanner = new Scanner(functionAsString);
        int tokenListLength = scanner.getTokenList().size();
        assertEquals(6, tokenListLength);
    }

    @Test
    public void testBuildTokenListNumberLength() throws BadSymbolException, BadParenthesisException {
        String functionAsString = "1+ 123.4567*5";
        Scanner scanner = new Scanner(functionAsString);
        int tokenLength = scanner.getTokenList().get(2).getLength();
        assertEquals(8, tokenLength);
    }

    @Test
    public void testBuildTokenListPosition() throws BadSymbolException, BadParenthesisException {
        String functionAsString = "x^(2*x) + 123.4567*x-5";
        Scanner scanner = new Scanner(functionAsString);
        Token multToken = scanner.getTokenList().get(9);
        int tokenPosition = multToken.getPosition();
        assertEquals(16, tokenPosition);
    }

    @Test
    public void testBuildTokenListNestingLevel() throws BadSymbolException, BadParenthesisException {
        String functionAsString = "(((()())(()(x))()))";
        Scanner scanner = new Scanner(functionAsString);
        Token xToken = scanner.getTokenList().get(12);
        int xTokenNestingLevel= xToken.getNestingLevel();
        assertEquals(4, xTokenNestingLevel);
    }

    @Test
    public void testBuildTokenListWrongSymbol() {
        String functionAsString = "x^(2*x)+123.4a567*x-5";
        assertThrows(BadSymbolException.class, () -> new Scanner(functionAsString));
    }

    @Test
    public void testBuildTokenListClosingParenthesisWithoutOpen() {
        String functionAsString = "x^())2*x)+123.4567*x-5";
        assertThrows(BadParenthesisException.class, () -> new Scanner(functionAsString));
    }

    @Test
    public void testBuildTokenListOpenParenthesisWithoutClose() {
        String functionAsString = "(x^)()2*x(()+123.4567*x-5";
        assertThrows(BadParenthesisException.class, () -> new Scanner(functionAsString));
    }
}
