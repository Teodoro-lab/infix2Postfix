class main {
    public static void main(String[] args) {

        Infix2PostfixConverter converter = new Infix2PostfixConverter();

        String infix = "2*(3+75/(4+9))";
        System.out.println("infix: " + infix);

        String postfix = converter.convert(infix);
        System.out.println("postfix: " + postfix);

        PostfixEvaluator evaluator = new PostfixEvaluator();
        float result = evaluator.eval(postfix);
        System.out.println("result: " + result);
    }
}
