package landing.l1.adapters;

import landing.l3.domainCode.representation.ValueCoordinate;

import java.util.ArrayList;
import java.util.List;

public class DerivativeCalculator {
    private final double resolutionOfxValues;

    public DerivativeCalculator(double resolutionOfxValues) {
        this.resolutionOfxValues = resolutionOfxValues;
    }

    public List<ValueCoordinate> calculateDerivativeValuesFor(List<ValueCoordinate> valueTable) {
        List<ValueCoordinate> derivativeValueTable = new ArrayList<>();

        double yBefore;
        double xCurrent;
        double yCurrent;
        double yAfter;
        double derivativeValue;

        int startIndex = 0;
        int lastIndex = valueTable.size() - 1;
        int secondLastIndex = lastIndex - 1;

        xCurrent = valueTable.get(startIndex).getX();
        yCurrent = valueTable.get(startIndex).getY();
        yAfter = valueTable.get(startIndex + 1).getY();
        derivativeValue = firstOrderDerivativeForward(yCurrent, yAfter);
        derivativeValueTable.add(new ValueCoordinate(xCurrent, derivativeValue));

        for(int tableIndex = 1; tableIndex < lastIndex; tableIndex++) {
            xCurrent = valueTable.get(tableIndex).getX();
            yBefore = valueTable.get(tableIndex - 1).getY();
            yAfter = valueTable.get(tableIndex + 1).getY();
            derivativeValue = firstOrderDerivativeCentral(yBefore, yAfter);
            derivativeValueTable.add(new ValueCoordinate(xCurrent, derivativeValue));
        }

        yBefore = valueTable.get(secondLastIndex).getY();
        xCurrent = valueTable.get(lastIndex).getX();
        yCurrent = valueTable.get(lastIndex).getY();
        derivativeValue = firstOrderDerivativeBackward(yBefore, yCurrent);
        derivativeValueTable.add(new ValueCoordinate(xCurrent, derivativeValue));

        return derivativeValueTable;
    }

    private double firstOrderDerivativeCentral(double yBefore, double yAfter) {
        return (yAfter - yBefore) / (2 * resolutionOfxValues);
    }
    private double firstOrderDerivativeForward(double yCurrent, double yAfter) {
        return (yAfter - yCurrent)/resolutionOfxValues;
    }
    private double firstOrderDerivativeBackward(double yBefore, double yCurrent) {
        return (yCurrent - yBefore)/resolutionOfxValues;
    }

}
