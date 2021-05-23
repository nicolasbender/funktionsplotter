package landing.function;

public class FunctionManager {
    private Function function;
    public FunctionManager(String functionAsString) {
        this.function = compile(functionAsString);
    }

    public double calculateValueOf(double currentX) {
         return function.calculateValueOf(currentX);
    }

    private Function compile(String functionAsString) {
        // ScannedFunction scannedFunction = new Scanner(functionAsString);
        // ParsedFunction parsedFunction = new Parser(scannedFunction);
        return null;
    }


}
