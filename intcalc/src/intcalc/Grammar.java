package intcalc;

public class Grammar extends edu.hendrix.grambler.Grammar {
    public Grammar() {
        super();
        addProduction("lines", new String[]{"lines", "line"}, new String[]{"line"});
        addProduction("line", new String[]{"expr", "newline"}, new String[]{"expr"});
        addProduction("expr", new String[]{"expr", "op", "expr2"}, new String[]{"expr2"}, new String[]{"sy", "op", "expr2"}, new String[]{"expr", "op1", "expr2"}, new String[]{"sy", "op1", "expr2"}, new String[]{"sy", "op2", "sy"}, new String[]{"sy", "op3", "sy"});
        addProduction("op", new String[]{"'+'"});
        addProduction("op1", new String[]{"'-'"});
        addProduction("expr2", new String[]{"expr2", "op2", "paren"}, new String[]{"paren"}, new String[]{"equality"}, new String[]{"sy", "op2", "expr3"}, new String[]{"expr2", "op3", "paren"}, new String[]{"sy", "op3", "expr3"});
        addProduction("paren", new String[]{"'('", "expr", "')'"}, new String[]{"num"});
        addProduction("equality", new String[]{"sy", "'='", "expr3"});
        addProduction("expr3", new String[]{"num"}, new String[]{"sy", "op", "num"}, new String[]{"sy", "op1", "sy"}, new String[]{"sy", "op3", "sy"}, new String[]{"sy", "op3", "sy"});
        addProduction("op2", new String[]{"'*'"});
        addProduction("op3", new String[]{"'/'"});
        addProduction("num", new String[]{"\"\\d+\""});
        addProduction("newline", new String[]{"'\r\n'"}, new String[]{"'\n'"});
        addProduction("sy", new String[]{"'x'"}, new String[]{"'y'"});
    }
}

