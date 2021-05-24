package landing.parser;

import landing.abstractSyntaxTree.*;
import landing.exceptions.ParenthesisException;
import landing.exceptions.SyntaxException;
import landing.scanner.Token;
import landing.scanner.TokenType;

import java.util.List;

public class RecursiveDescent {
    private final List<Token> tokenList;

    public RecursiveDescent(List<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public ASTnode buildAST() throws ParenthesisException, SyntaxException {
        NodeContainer nodeAndPosition = expressionDeduction(0);
        ASTnode rootNode = nodeAndPosition.getNode();
        int tokenPosition = nodeAndPosition.getPosition();
        Token currentToken = tokenList.get(tokenPosition);
        if(currentToken.getTokenType() == TokenType.END_OF_TERM) {
            return rootNode;
        }
        throw new SyntaxException("Parser expects end of term, but there is: "+ currentToken.getTokenType());
    }

    public double calculateValue(ASTnode node, double xValue) {
        return node.doOperation(xValue);
    }

    public Token expectToken(int tokenPosition) throws SyntaxException {
        Token currentToken = tokenList.get(tokenPosition);
        if(currentToken.getTokenType() == TokenType.END_OF_TERM) {
            throw new SyntaxException("Unexpected end of expression at position: "+currentToken.getPosition());
        }
        return currentToken;
    }

    public NodeContainer atomicDeduction(int tokenPosition) throws SyntaxException, ParenthesisException {
        Token currentToken = expectToken(tokenPosition);
        if(currentToken.getTokenType() == TokenType.NUMBER) {
            ASTnode newNumberNode = new NumberNode(currentToken.getValue());
            return new BuildNodeContainer().forPosition(tokenPosition+1).andSetNode(newNumberNode);
        } else if(currentToken.getTokenType() == TokenType.VARIABLE) {
            ASTnode newVariableNode = new VariableNode();
            return new BuildNodeContainer().forPosition(tokenPosition+1).andSetNode(newVariableNode);
        }
        if(currentToken.getTokenType() == TokenType.LEFT_PARENTHESIS) {
            NodeContainer nodeAndPosition = expressionDeduction(tokenPosition+1);
            tokenPosition = nodeAndPosition.getPosition();
            ASTnode currentNode = nodeAndPosition.getNode();
            if(expectToken(tokenPosition).getTokenType() == TokenType.RIGHT_PARENTHESIS) {
                throw new ParenthesisException("Parser expects closing parenthesis, but got: "+ tokenList.get(tokenPosition).getTokenType());
            }
            return new BuildNodeContainer().forPosition(tokenPosition+1).andSetNode(currentNode);
        }
        throw new SyntaxException("Parser does not expect: "+currentToken.getTokenType());
    }

    public NodeContainer powerDeduction(int tokenPosition) throws ParenthesisException, SyntaxException {
        NodeContainer nodeAndPosition = atomicDeduction(tokenPosition);
        tokenPosition = nodeAndPosition.getPosition();
        ASTnode currentNode = nodeAndPosition.getNode();
        Token currentToken = tokenList.get(tokenPosition);
        if(currentToken.getTokenType() == TokenType.POWER_SIGN) {
            NodeContainer newNodeAndPosition = negationDeduction(tokenPosition+1);
            ASTnode newNode = newNodeAndPosition.getNode();
            tokenPosition = newNodeAndPosition.getPosition();
            ASTnode newPowerNode = new PowerNode(currentNode, newNode);
            return new BuildNodeContainer().forPosition(tokenPosition).andSetNode(newPowerNode);
        }
        return new BuildNodeContainer().forPosition(tokenPosition).andSetNode(currentNode);
    }

    public NodeContainer negationDeduction(int tokenPosition) throws ParenthesisException, SyntaxException {
        Token currentToken = tokenList.get(tokenPosition);
        if(currentToken.getTokenType() == TokenType.MINUS_SIGN) {
            NodeContainer nodeAndPosition = powerDeduction(tokenPosition+1);
            tokenPosition = nodeAndPosition.getPosition();
            ASTnode currentNode = nodeAndPosition.getNode();
            ASTnode newNegateNode = new NegateNode(currentNode);
            return new BuildNodeContainer().forPosition(tokenPosition).andSetNode(newNegateNode);
        }
        return powerDeduction(tokenPosition);
    }

    public NodeContainer multilpicationDeduction(int tokenPosition) throws ParenthesisException, SyntaxException {
        NodeContainer nodeAndPosition = negationDeduction(tokenPosition);
        ASTnode node = nodeAndPosition.getNode();
        tokenPosition = nodeAndPosition.getPosition();
        Token currentToken = tokenList.get(tokenPosition);
        while(currentToken.getTokenType() == TokenType.MULT_SIGN || currentToken.getTokenType() == TokenType.DIV_SIGN) {
            NodeContainer newNodeAndPosition = negationDeduction(tokenPosition+1);
            ASTnode newNode = newNodeAndPosition.getNode();
            tokenPosition = newNodeAndPosition.getPosition();
            if(currentToken.getTokenType() == TokenType.MULT_SIGN) {
                node = new MultNode(node, newNode);
            } else {
                node = new DivisionNode(node, newNode);
            }
            currentToken = tokenList.get(tokenPosition);
        }
        return new BuildNodeContainer().forPosition(tokenPosition).andSetNode(node);
    }

    public NodeContainer additionDeduction(int tokenPosition) throws ParenthesisException, SyntaxException {
        NodeContainer nodeAndPosition = multilpicationDeduction(tokenPosition);
        ASTnode node = nodeAndPosition.getNode();
        tokenPosition = nodeAndPosition.getPosition();
        Token currentToken = tokenList.get(tokenPosition);
        while(currentToken.getTokenType() == TokenType.PLUS_SIGN || currentToken.getTokenType() == TokenType.MINUS_SIGN) {
            NodeContainer newNodeAndPosition = multilpicationDeduction(tokenPosition+1);
            ASTnode newNode = newNodeAndPosition.getNode();
            tokenPosition = newNodeAndPosition.getPosition();
            if(currentToken.getTokenType() == TokenType.PLUS_SIGN) {
                node = new PlusNode(node, newNode);
            } else {
                node = new MinusNode(node, newNode);
            }
            currentToken = tokenList.get(tokenPosition);
        }
        return new BuildNodeContainer().forPosition(tokenPosition).andSetNode(node);
    }

    public NodeContainer expressionDeduction(int tokenPosition) throws ParenthesisException, SyntaxException {
        return additionDeduction(tokenPosition);
    }
}
