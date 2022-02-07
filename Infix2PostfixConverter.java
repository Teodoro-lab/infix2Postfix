import java.util.Stack;

/**
 * Infix2PostfixConverter
 */
public class Infix2PostfixConverter {

    private static int precedence(Character character) {
        if (character == '+' || character == '-') {
            return 1;
        } else if (character == '*' || character == '/') {
            return 2;
        } else if (character == '^') {
            return 3;
        } else {
            return 0;
        }
    }

    public String convert(String infixExpression) {
        String postfixExpression = "";
        Stack<Character> stack = new Stack<Character>();
        stack.push('#');

        String longNumbers = " ";
        char character;
        for (int i = 0; i < infixExpression.length(); i++) {
            character = infixExpression.charAt(i);

            if (Character.isLetterOrDigit(character)) {
                longNumbers += character;
                try {
                    if (!Character.isLetterOrDigit(infixExpression.charAt(i + 1))) {
                        if (longNumbers.length() == 2)
                            postfixExpression += longNumbers.strip();
                        else
                            postfixExpression += longNumbers + " ";
                        longNumbers = " ";
                    }

                } catch (IndexOutOfBoundsException e) {
                    if (longNumbers != " ") {
                        postfixExpression += longNumbers;
                    }
                }
            } else if (character == '(')
                stack.push('(');
            else if (character == '^')
                stack.push('^');
            else if (character == ')') {
                while (stack.peek() != '#' && stack.peek() != '(') {
                    postfixExpression += stack.pop();
                }
                stack.pop();
            } else {
                if (precedence(character) > precedence(stack.peek()))
                    stack.push(character);
                else {
                    while (stack.peek() != '#' && precedence(character) <= precedence(stack.peek())) {
                        postfixExpression += stack.pop();
                    }
                    stack.push(character);
                }

            }
        }
        while (stack.peek() != '#') {
            postfixExpression += stack.pop(); // store and pop until stack is not empty.
        }
        return postfixExpression;
    }

}
