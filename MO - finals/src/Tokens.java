public class Tokens{
    private int state;

    public enum Type{
        end, epsilon, error, lparen, plus,
        question, rparen, star, terminals, union
    }

    private static final int q0 = 0;
    private static final int q1 = 1;
    private static final int q2 = 2;
    private static final int q3 = 3;
    private static final int q4 = 4;
    private static final int q5 = 5;
    private static final int q6 = 6;
    private static final int q7 = 7;
    private static final int q8 = 8;
    private static final int qDead = -1;

    public Type type;
    public String lexeme;

    public Tokens (String token) {
        this.lexeme = token;
        this.type = tokenIdentifier(token);
    }

    public String getTokenType() {return type.toString();}

    public Type tokenIdentifier(String temp){
        int i = 0;
        while (i < temp.length()) {
            char c = temp.charAt(i);
            state = stateTransition(state, c);
            i++;
        }
        return switch (state) {
            case 1 -> Type.terminals;
            case 2 -> Type.star;
            case 3 -> Type.plus;
            case 4 -> Type.question;
            case 5 -> Type.lparen;
            case 6 -> Type.rparen;
            case 7 -> Type.union;
            case 8 -> Type.epsilon;
            case 9 -> Type.end;
            default -> Type.error;
        };
    }

    static private int stateTransition (int state, char c) {
        if (state == q0) {
            return switch (c) {
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' -> q1;
                case '*' -> q2;
                case '+' -> q3;
                case '?' -> q4;
                case '(' -> q5;
                case ')' -> q6;
                case 'U' -> q7;
                case 'E' -> q8;
                default -> qDead;
            };
        }
        else if (state == q1) {
            return qDead;
        }
        else if (state == q2) {
            return qDead;
        }
        else if (state == q3) {
            return qDead;
        }
        else if (state == q4) {
            return qDead;
        }
        else if (state == q5) {
            return qDead;
        }
        else if (state == q6) {
            return qDead;
        }
        else if (state == q7) {
            return qDead;
        }
        else if (state == q8) {
            return qDead;
        }
        return qDead;
    }

    @Override
    public String toString(){
        return this.type.toString();
    }
}