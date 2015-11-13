package visitor;import symboltable.RamClass;import symboltable.Table;import syntaxtree.*;public class BuildSymbolTableVisitor extends TypeDepthFirstVisitor {    private Table symbolTable;    private RamClass currClass;    private RamMethod currMethod;    public BuildSymbolTableVisitor() {        symbolTable = new Table();    }    public Table getSymTab() {        return symbolTable;    }    // MainClass m;    // ClassDeclList cl;    public Type visit(Program n) {        n.m.accept(this);        for (int i = 0; i < n.cl.size(); i++) {            n.cl.elementAt(i).accept(this);        }        return null;    }    // Identifier i1,i2;    // Statement s;    public Type visit(MainClass n) {        symbolTable.addClass(n.i1.toString());        currClass = symbolTable.getClass(n.i1.toString());        // Ugly hack -- creating        // new IdentifierType("void") and new IdentifierType("String[]");        // not worth defining a VoidType and StringAryType        // for just a few occurrences        symbolTable.getClass(n.i1.s).addMethod("main", new IdentifierType("void"));        currMethod = symbolTable.getClass(n.i1.toString()).getMethod("main");        symbolTable.getMethod("main",                currClass.getId()).addParam(n.i2.toString(), new IdentifierType("String[]"));        n.s.accept(this);        currMethod = null;        return null;    }    // Identifier i;    // VarDeclList vl;    // MethodDeclList ml;    public Type visit(ClassDeclSimple n) {        if (!symbolTable.addClass(n.i.toString())) {            System.out.println("Class " + n.i.toString()                    + "is already defined");            System.exit(-1);        }        currClass = symbolTable.getClass(n.i.toString());        for (int i = 0; i < n.vl.size(); i++) {            n.vl.elementAt(i).accept(this);        }        for (int i = 0; i < n.ml.size(); i++) {            n.ml.elementAt(i).accept(this);        }        return null;    }    // Type t;    // Identifier i;    public Type visit(VarDecl n) {        Type t = n.t.accept(this);        String id = n.i.toString();        if (currMethod == null) {            if (!currClass.addVar(id, t)) {                System.out.println(id + "is already defined in "                        + currClass.getId());                System.exit(-1);            }        } else {            if (!currMethod.addVar(id, t)) {                System.out.println(id + "is already defined in "                        + currClass.getId() + "."                        + currMethod.getId());                System.exit(-1);            }        }        return null;    }    // Type t;    // Identifier i;    // FormalList fl;    // VarDeclList vl;    // StatementList sl;    // Exp e;    public Type visit(MethodDecl n) {        Type t = n.t.accept(this);        String id = n.i.toString();        if (!currClass.addMethod(id, t)) {            System.out.println("Method " + id                    + "is already defined in "                    + currClass.getId());            System.exit(-1);        }        currMethod = currClass.getMethod(id);        for (int i = 0; i < n.fl.size(); i++) {            n.fl.elementAt(i).accept(this);        }        for (int i = 0; i < n.vl.size(); i++) {            n.vl.elementAt(i).accept(this);        }        for (int i = 0; i < n.sl.size(); i++) {            n.sl.elementAt(i).accept(this);        }        n.e.accept(this);        currMethod = null;        return null;    }    // Type t;    // Identifier i;    public Type visit(Formal n) {        Type t = n.t.accept(this);        String id = n.i.toString();        if (!currMethod.addParam(id, t)) {            System.out.println("Formal" + id + "is already defined in "                    + currClass.getId() + "."                    + currMethod.getId());            System.exit(-1);        }        return null;    }    public Type visit(IntArrayType n) {        return n;    }    public Type visit(BooleanType n) {        return n;    }    public Type visit(IntegerType n) {        return n;    }    // String s;    public Type visit(IdentifierType n) {        return n;    }    // StatementList sl;    public Type visit(Block n) {        for (int i = 0; i < n.sl.size(); i++) {            n.sl.elementAt(i).accept(this);        }        return null;    }}