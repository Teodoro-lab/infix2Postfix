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

    public String convert(String infixStr) {
        Stack<Character> stk = new Stack<>();
        String postfixStr = "";
        String longNumbers = " ";

        for (int i = 0; i < infixStr.length(); i++) {
            char character = infixStr.charAt(i);

            if (Character.isLetterOrDigit(character)) {
                longNumbers += character;
                try {
                    if (!Character.isLetterOrDigit(infixStr.charAt(i + 1))) {
                        if (longNumbers.length() == 2)
                            postfixStr += longNumbers.strip();
                        else
                            postfixStr += longNumbers + " ";
                        longNumbers = " ";
                    }

                } catch (IndexOutOfBoundsException e) {
                    if (longNumbers != " ") {
                        postfixStr += longNumbers;
                    }
                }
            } else if (character == '(') {
                stk.push('(');
            } else if (character == ')') {

                while (!stk.isEmpty() && stk.peek() != '(') {
                    postfixStr += stk.pop();
                }
                if (!stk.isEmpty()) {
                    stk.pop();
                }

            } else {
                if (!stk.isEmpty() && precedence(character) > precedence(stk.peek())) {
                    stk.push(character);
                } else {
                    while (!stk.isEmpty() && precedence(stk.peek()) >= precedence(character)) { 
                                                                                                
                        Character pop = stk.pop();
                        if (character != '(') {
                            postfixStr += pop;
                        } else {
                            character = pop;
                        }
                    }
                    stk.push(character);
                }

            }
        }
        while (!stk.isEmpty()) {
            Character c = stk.pop();
            if (c != ';')
                postfixStr += stk.pop();
        }
        return postfixStr;
    }
}
