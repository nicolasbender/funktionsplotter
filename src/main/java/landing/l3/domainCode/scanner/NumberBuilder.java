package landing.l3.domainCode.scanner;

public class NumberBuilder {
    public static boolean isNumerical(Character charAt) {
        return Character.isDigit(charAt) || charAt.equals('.');
    }

    public static Double convertValueToDoubleFrom(String partOfFunction) {
        try {
            return Double.valueOf(partOfFunction);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("Could not convert value from string");
        }
    }

    public static String determineStringForValueFrom(String partOfFunction) {
        if(partOfFunction.charAt(0) == '.') {
            return buildValueDirectlyAfterDecimalPoint(partOfFunction);
        } else {
            return buildValueBeforeDecimalPoint(partOfFunction);
        }
    }

    private static String buildValueBeforeDecimalPoint(String partOfFunction) {
        char currentSymbol = partOfFunction.charAt(0);
        if (currentSymbol == '.') {
            return buildValueDirectlyAfterDecimalPoint(partOfFunction);
        }
        if(isNumerical(currentSymbol)) {
            return currentSymbol + buildValueBeforeDecimalPoint(partOfFunction.substring(1));
        } else {
            return "";
        }
    }

    private static String buildValueDirectlyAfterDecimalPoint(String partOfFunction) {
        char currentSymbol = partOfFunction.charAt(0);
        if (isNumerical(currentSymbol)) {
            return "." + buildValueAfterDecimalPoint(partOfFunction.substring(1));
        } else {
            return ".0";
        }
    }

    private static String buildValueAfterDecimalPoint(String partOfFunction) {
        char currentSymbol = partOfFunction.charAt(0);
        if (currentSymbol == '.') {
            throw new NumberFormatException("There are too many decimal points");
        }
        if (isNumerical(currentSymbol)) {
            return currentSymbol + buildValueAfterDecimalPoint(partOfFunction.substring(1));
        } else {
            return "";
        }
    }
}
