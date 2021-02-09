import java.util.ArrayList;
import java.util.Stack;

public class Parser {
    public LookupTable parseTable;
    Stack<String> tokenStack = new Stack<>();
    ArrayList<String> tokensList = new ArrayList<>();

    public Parser() {
        this.parseTable = new LookupTable();
    }

    public void expandGrammar(Stack<String> s, String production) {
        if (!s.isEmpty()) {
            s.pop();
            String[] prod = production.split(" ");
            int i = prod.length - 1;
            while (i >= 0) {
                s.push(prod[i]);
                i--;
            }
        } else {
            return;
        }
    }

    public String parseHandler(ArrayList<Tokens> tokenList) {
        int trackToken = 0;
        boolean limit = false;

        for (int i = 0, tokenListSize = tokenList.size(); i < tokenListSize; i++) {
            Tokens token = tokenList.get(i);
            tokensList.add(token.toString());
        }

        tokenStack.clear();
        tokenStack.push("$");
        tokenStack.push("S"); //initialize

        if (tokenList.contains("ERROR")) {
            return " - REJECT";
        }
        else {
            tokenStack.peek();
            do {
                Tokens token = null;
                if (trackToken >= tokenList.size()) {
                    limit = true;
                    if (!parseTable.isNT(tokenStack.peek())) {
                        return " - REJECT";
                    }
                    else {
                        expandGrammar(tokenStack, parseTable.getKind(tokenStack.peek(), "$"));
                        switch (tokenStack.peek()) {
                            case "" -> tokenStack.pop();
                        }
                    }
                }
                else {
                    token = tokenList.get(trackToken);
                    if (limit || !tokenStack.peek().equals(token.getTokenType())) {
                        if (limit || !tokenStack.peek().equals("$")) {
                            if (!limit && parseTable.hasKey(tokenStack.peek(), token.toString())) {
                                expandGrammar(tokenStack, parseTable.getKind(tokenStack.peek(), token.toString()));
                            }
                            else {
                                return " - REJECT";
                            }
                        }
                        else {
                            return "- REJECT";
                        }
                        switch (tokenStack.peek()) {
                            case "" -> tokenStack.pop();
                        }
                    }
                    else {
                        tokenStack.pop();
                        trackToken++;
                        switch (tokenStack.peek()) {
                            case "" -> tokenStack.pop();
                        }
                    }
                }
            } while (!tokenStack.peek().equals("$") || !limit);
            return " - ACCEPT";
        }
    }
}