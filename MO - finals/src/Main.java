import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            File file_input = new File("input.txt");
            FileReader reader = new FileReader(file_input);
            BufferedReader br = new BufferedReader(reader);

            FileWriter writer = new FileWriter("output.txt");
            BufferedWriter bw = new BufferedWriter(writer);

            Parser parser = new Parser();
            Scanner scanner = new Scanner();
            String text;
            StringBuilder content = new StringBuilder();

            if ((text = br.readLine()) != null) {
                do {
                    ArrayList<Tokens> tokenList = scanner.process(text);
                    content.append(text);
                    if (tokenList.size() > 0) {
                        content.append(parser.parseHandler(tokenList));
                    }
                    content.append("\n");
                } while ((text = br.readLine()) != null);
            }
            content = new StringBuilder(content.toString().trim());
            bw.write(content.toString());

            reader.close();
            br.close();
            bw.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
    }
}