package landing.abstractSyntaxTree;

public class ValueNode {
    private boolean isVariable;
    private Double value;

    private ValueNode(boolean isVariable, Double value) {
        this.isVariable = isVariable;
        this.value = value;
    }

    public ValueNode(boolean isVariable) {
        this(isVariable, null);
    }

    public ValueNode(double value) {
        this(false, value);
    }

    public boolean isVariable() {
        return isVariable;
    }

    public Double getValue() {
        return value;
    }

    public void setVariableValueForCalculation(double variableValue) {
        if(isVariable) {
            this.value = variableValue;
        }
    }

    public void resetVariableValueForCalculation() {
        if(isVariable) {
            this.value = null;
        }
    }
}
