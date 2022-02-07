import java.util.Stack;
import java.lang.Math;

public class PostfixEvaluator {
    public float eval(String postfixExpression) {

        Stack<String> stack = new Stack<String>();
        float first, second, result = 0;
        Character character;
        String longNumber = "";
        boolean readingLongNumber = false;

        for (int i = 0; i < postfixExpression.length(); i++) {
            character = postfixExpression.charAt(i);


            if (character == ' '){
                if(readingLongNumber){
                    stack.push(longNumber);
                }
                readingLongNumber = !readingLongNumber;
            }
            else if (Character.isLetterOrDigit(character)){
                if (readingLongNumber){
                    longNumber += character;
                } else 
                    stack.push(String.valueOf(character));
            } else {
                if(stack.size() < 2)
                    continue;

                second = Float.valueOf(stack.pop());
                first = Float.valueOf(stack.pop());

                switch (character) {
                    case '+':
                        result = first + second;
                        break;
                    case '-':
                        result = first - second;
                        break;
                    case '*':
                        result = first * second;
                        break;
                    case '/':
                        result = first / second;
                        break;
                    case '^':
                        result = (float) Math.pow(first,second);
                        break;
                }
                stack.push(String.valueOf(result));
            }
        }
        return result;
    }
}
