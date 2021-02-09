import java.util.HashMap;

public class LookupTable {
    public HashMap<String, HashMap> lookupTable;

    public LookupTable() {
        this.lookupTable = new HashMap<>();
        generateTable();
    }

    public void generateTable(){
        HashMap<String, String> MAP = new HashMap<>();
        MAP.put("epsilon", "A B");
        MAP.put("lparen", "A B");
        MAP.put("terminals", "A B");
        lookupTable.put("S", MAP);

        HashMap<String, String> A = new HashMap<>();
        A.put("epsilon", "epsilon");
        A.put("lparen", "C");
        A.put("terminals", "C");
        lookupTable.put("A", A);

        HashMap<String, String> B = new HashMap<>();
        B.put("end", "");
        B.put("lparen", "C B");
        B.put("rparen", "");
        B.put("terminals", "C B");
        B.put("union", "union A B");
        B.put("$", "");
        lookupTable.put("B", B);

        HashMap<String, String> C = new HashMap<>();
        C.put("lparen", "D F");
        C.put("terminals", "D F");
        lookupTable.put("C", C);

        HashMap<String, String> D = new HashMap<>();
        D.put("lparen", "lparen S rparen");
        D.put("terminals", "terminals");
        lookupTable.put("D", D);

        HashMap<String, String> F = new HashMap<>();
        F.put("$", "");
        F.put("end", "");
        F.put("lparen", "");
        F.put("plus", "plus");
        F.put("question", "question");
        F.put("rparen", "");
        F.put("star", "star");
        F.put("terminals", "");
        F.put("union", "");
        lookupTable.put("F", F);
    }

    public String getKind(String nonTerminal, String Terminal){
        var nt = lookupTable.get(nonTerminal);
        return (String) nt.get(Terminal);
    }

    public Boolean hasKey(String nonTerminal, String Terminal){
        var nt = lookupTable.get(nonTerminal);
        return nt.containsKey(Terminal);
    }

    public Boolean isNT(String nonTerminal){
        if (lookupTable.containsKey(nonTerminal)) return true;
        else return false;
    }
}
