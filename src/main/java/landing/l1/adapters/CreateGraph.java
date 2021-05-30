package landing.l1.adapters;

import landing.l2.applicationCode.function.Function;
import landing.l3.domainCode.representation.ValueCoordinate;

public final class CreateGraph {
    private Function function;
    private ValueCoordinate valueOfPixelMostLeft;
    private ValueCoordinate valueOfPixelMostRight;

    private CreateGraph(Function function) {
        this.function = function;
    }

    public static CreateGraph accordingTo(Function function) {
        return new CreateGraph(function);
    }

    public CreatableGraph from(ValueCoordinate valueOfPixelMostLeft) {
        this.valueOfPixelMostLeft = valueOfPixelMostLeft;
        return new CreatableGraph();
    }

    private Graph build() {
        return new Graph(function, valueOfPixelMostLeft, valueOfPixelMostRight);
    }

    public class CreatableGraph {
        private CreatableGraph() {}

        public Graph to(ValueCoordinate valueOfPixelMostRight) {
            CreateGraph.this.valueOfPixelMostRight = valueOfPixelMostRight;
            return CreateGraph.this.build();
        }
    }
}
