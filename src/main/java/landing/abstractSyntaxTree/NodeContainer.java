package landing.abstractSyntaxTree;

public class NodeContainer {
    private final int position;
    private final ASTnode node;

    public NodeContainer(int position, ASTnode node) {
        this.position = position;
        this.node = node;
    }

    public int getPosition() {
        return position;
    }

    public ASTnode getNode() {
        return node;
    }
}
