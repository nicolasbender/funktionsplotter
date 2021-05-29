package landing.l2.applicationCode.scanner;

import landing.l3.domainCode.scanner.Token;
import landing.l3.domainCode.exceptions.ParenthesisException;
import landing.l3.domainCode.exceptions.SymbolException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ScannerTest {
    @Test
    public void testBuildTokenListLength() throws SymbolException, ParenthesisException {
        String functionAsString = "1+2.0  *5 ";
        Scanner scanner = new Scanner(functionAsString);
        int tokenListLength = scanner.getTokenList().size();
        assertEquals(6, tokenListLength);
    }

    @Test
    public void testBuildTokenListNumberLength() throws SymbolException, ParenthesisException {
        String functionAsString = "1+ 123.4567*5";
        Scanner scanner = new Scanner(functionAsString);
        int tokenLength = scanner.getTokenList().get(2).getLength();
        assertEquals(8, tokenLength);
    }

    @Test
    public void testBuildTokenListPosition() throws SymbolException, ParenthesisException {
        String functionAsString = "x^(2*x) + 123.4567*x-5";
        Scanner scanner = new Scanner(functionAsString);
        Token multToken = scanner.getTokenList().get(9);
        int tokenPosition = multToken.getPosition();
        assertEquals(16, tokenPosition);
    }

    @Test
    public void testBuildTokenListNestingLevel() throws SymbolException, ParenthesisException {
        String functionAsString = "(((()())(()(x))()))";
        Scanner scanner = new Scanner(functionAsString);
        Token xToken = scanner.getTokenList().get(12);
        int xTokenNestingLevel= xToken.getNestingLevel();
        assertEquals(4, xTokenNestingLevel);
    }

    @Test
    public void testBuildTokenListWrongSymbol() {
        String functionAsString = "x^(2*x)+123.4a567*x-5";
        assertThrows(SymbolException.class, () -> new Scanner(functionAsString));
    }

    @Test
    public void testBuildTokenListClosingParenthesisWithoutOpen() {
        String functionAsString = "x^())2*x)+123.4567*x-5";
        assertThrows(ParenthesisException.class, () -> new Scanner(functionAsString));
    }

    @Test
    public void testBuildTokenListOpenParenthesisWithoutClose() {
        String functionAsString = "(x^)()2*x(()+123.4567*x-5";
        assertThrows(ParenthesisException.class, () -> new Scanner(functionAsString));
    }
}
