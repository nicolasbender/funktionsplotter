package landing.abstractSyntaxTree;

public class BuildNodeContainer {
    private final int tokenPosition;

    public BuildNodeContainer() {
        tokenPosition = -1; // TODO: Richtig so?
    }

    private BuildNodeContainer(int tokenPosition) {
        this.tokenPosition = tokenPosition;
    }

    public BuildNodeContainer forPosition(int tokenPosition) {
        return new BuildNodeContainer(tokenPosition);
    }

    public NodeContainer andSetNode(ASTnode node) {
        return new NodeContainer(tokenPosition, node);
    }
}
