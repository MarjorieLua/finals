import java.util.ArrayList;

public class Scanner{
    ArrayList<Tokens> process (String text) {
        String[] input = text.split("");
        ArrayList<String> w = new ArrayList<String>();

        for (int j = 0, inputLength = input.length; j < inputLength; j++) {
            String i = input[j];
            if (i.contains(" ")) {
                continue;
            }
            w.add(i);
        }

        ArrayList<Tokens> tokenList = new ArrayList();
        for (int i = 0, wordsSize = w.size(); i < wordsSize; i++) {
            String words = w.get(i);
            switch (words) {
                case "":
                    break;
                default:
                    Tokens t = new Tokens(words);
                    tokenList.add(t);
                    break;
            }
        }
        return tokenList;
    }
}