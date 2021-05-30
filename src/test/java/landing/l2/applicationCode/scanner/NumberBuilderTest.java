package landing.l2.applicationCode.scanner;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class NumberBuilderTest {
    @Test
    public void testDetermineStringForValueFromInteger() {
        String correctInteger = "12345$";
        String answerToCorrectInteger = NumberBuilder.determineStringForValueFrom(correctInteger) + "$";
        assertEquals(correctInteger, answerToCorrectInteger);
    }

    @Test
    public void testDetermineStringForValueFromDouble() {
        String correctDouble = "123.45$";
        String answerToCorrectDouble = NumberBuilder.determineStringForValueFrom(correctDouble) + "$";
        assertEquals(correctDouble, answerToCorrectDouble);
    }

    @Test
    public void testDetermineStringForValueFromDecimalPoint() {
        String correctDecimalPoint = ".$";
        String answerToCorrectDecimalPoint = NumberBuilder.determineStringForValueFrom(correctDecimalPoint) + "$";
        assertEquals(correctDecimalPoint, answerToCorrectDecimalPoint);
    }

    @Test
    public void testDetermineStringForValueFromDecimalPointAfter() {
        String correctZeroBefore = "0.$";
        String answerToCorrectZeroBefore = NumberBuilder.determineStringForValueFrom(correctZeroBefore) + "$";
        assertEquals(correctZeroBefore, answerToCorrectZeroBefore);
    }

    @Test
    public void testDetermineStringForValueFromDecimalPointBefore() {
        String correctZeroAfter = ".0$";
        String answerToCorrectZeroAfter = NumberBuilder.determineStringForValueFrom(correctZeroAfter) + "$";
        assertEquals(correctZeroAfter, answerToCorrectZeroAfter);
    }

    @Test
    public void testDetermineStringForValueFromWrongDecimalPoint0() {
        String wrongDecimalPoint0 = "1.2.3$";
        assertThrows(NumberFormatException.class, () -> NumberBuilder.determineStringForValueFrom(wrongDecimalPoint0));
    }

    @Test
    public void testDetermineStringForValueFromWrongDecimalPoint1() {
        String wrongDecimalPoint1 = "1..2$";
        assertThrows(NumberFormatException.class, () -> NumberBuilder.determineStringForValueFrom(wrongDecimalPoint1));
    }

    @Test
    public void testDetermineStringForValueFromWrongDecimalPoint2() {
        String wrongDecimalPoint2 = "12..34$";
        assertThrows(NumberFormatException.class, () -> NumberBuilder.determineStringForValueFrom(wrongDecimalPoint2));
    }

    @Test
    public void testDetermineStringForValueFromWrongDecimalPoint3() {
        String wrongDecimalPoint3 = "..$";
        assertThrows(NumberFormatException.class, () -> NumberBuilder.determineStringForValueFrom(wrongDecimalPoint3));
    }

    @Test
    public void testConvertValueToDoubleFromInteger() {
        double correctInteger = 12345;
        double answerToCorrectInteger = NumberBuilder.convertValueToDoubleFrom("12345");
        assertEquals(correctInteger, answerToCorrectInteger, 1E-6);
    }

    @Test
    public void testConvertValueToDoubleFromDouble() {
        double correctDouble = 123.45;
        double answerToCorrectDouble = NumberBuilder.convertValueToDoubleFrom("123.45");
        assertEquals(correctDouble, answerToCorrectDouble, 1E-6);
    }

    @Test
    public void testConvertValueToDoubleFromDecimalPointAfter() {
        double correctZeroBefore = 0.;
        double answerToCorrectZeroBefore = NumberBuilder.convertValueToDoubleFrom("0.");
        assertEquals(correctZeroBefore, answerToCorrectZeroBefore, 1E-6);
    }

    @Test
    public void testConvertValueToDoubleFromDecimalPointBefore() {
        double correctZeroAfter = .0;
        double answerToCorrectZeroAfter = NumberBuilder.convertValueToDoubleFrom(".0");
        assertEquals(correctZeroAfter, answerToCorrectZeroAfter, 1E-6);
    }
}
