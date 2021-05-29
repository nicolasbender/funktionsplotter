package landing.l2.applicationCode.scanner;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class NumberBuilderTest {
    @Test
    public void testDetermineStringForValueFrom() {
        String correctInteger = "12345$";
        String correctDouble = "123.45$";
        String correctDecimalPoint = ".$";
        String correctZeroBefore = "0.$";
        String correctZeroAfter = ".0$";
        String wrongDecimalPoint0 = "1.2.3$";
        String wrongDecimalPoint1 = "1..2$";
        String wrongDecimalPoint2 = "12..34$";
        String wrongDecimalPoint3 = "..$";

        String answerToCorrectInteger = NumberBuilder.determineStringForValueFrom(correctInteger) + "$";
        String answerToCorrectDouble = NumberBuilder.determineStringForValueFrom(correctDouble) + "$";
        String answerToCorrectDecimalPoint = NumberBuilder.determineStringForValueFrom(correctDecimalPoint) + "$";
        String answerToCorrectZeroBefore = NumberBuilder.determineStringForValueFrom(correctZeroBefore) + "$";
        String answerToCorrectZeroAfter = NumberBuilder.determineStringForValueFrom(correctZeroAfter) + "$";

        assertEquals(correctInteger, answerToCorrectInteger);
        assertEquals(correctDouble, answerToCorrectDouble);
        assertEquals(correctDecimalPoint, answerToCorrectDecimalPoint);
        assertEquals(correctZeroBefore, answerToCorrectZeroBefore);
        assertEquals(correctZeroAfter, answerToCorrectZeroAfter);
        assertThrows(NumberFormatException.class, () -> NumberBuilder.determineStringForValueFrom(wrongDecimalPoint0));
        assertThrows(NumberFormatException.class, () -> NumberBuilder.determineStringForValueFrom(wrongDecimalPoint1));
        assertThrows(NumberFormatException.class, () -> NumberBuilder.determineStringForValueFrom(wrongDecimalPoint2));
        assertThrows(NumberFormatException.class, () -> NumberBuilder.determineStringForValueFrom(wrongDecimalPoint3));
    }

    @Test
    public void testConvertValueToDoubleFrom() {
        double correctInteger = 12345;
        double correctDouble = 123.45;
        double correctZeroBefore = 0.;
        double correctZeroAfter = .0;

        double answerToCorrectInteger = NumberBuilder.convertValueToDoubleFrom("12345");
        double answerToCorrectDouble = NumberBuilder.convertValueToDoubleFrom("123.45");
        double answerToCorrectZeroBefore = NumberBuilder.convertValueToDoubleFrom("0.");
        double answerToCorrectZeroAfter = NumberBuilder.convertValueToDoubleFrom(".0");

        assertEquals(correctInteger, answerToCorrectInteger, 1E-6);
        assertEquals(correctDouble, answerToCorrectDouble, 1E-6);
        assertEquals(correctZeroBefore, answerToCorrectZeroBefore, 1E-6);
        assertEquals(correctZeroAfter, answerToCorrectZeroAfter, 1E-6);
    }
}
