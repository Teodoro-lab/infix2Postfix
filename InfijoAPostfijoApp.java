import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class InfijoAPostfijoApp {
    public static void main(String[] args) {

        Infix2PostfixConverter converter = new Infix2PostfixConverter();
        PostfixEvaluator evaluator = new PostfixEvaluator();

        String infix;
        String fileOutput = "";

        File file = new File("exp_infijas.txt");
        BufferedReader br;

        // read the file
        try {
            br = new BufferedReader(new FileReader(file));
            while ((infix = br.readLine()) != null) {
                String postfix = converter.convert(infix);
                float result = evaluator.eval(postfix);
                fileOutput += "Exp: " + postfix + "; Eval: " + result + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // write the file
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter("exp_postfijas.txt"))) {
            writer.write(fileOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
