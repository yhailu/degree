package visitor;import symboltable.RamMethod;import syntaxtree.*;public class TypeCheckExpVisitor extends TypeDepthFirstVisitor {    @Override    public Type visit(Println n) {        return null;    }    // Exp lhs,rhs;    public Type visit(And n) {        if (!(n.lhs.accept(this) instanceof BooleanType)) {            System.out.printf("Left side of %s must be of type %s.", "And", BooleanType.name);            System.exit(-1);        }        if (!(n.rhs.accept(this) instanceof BooleanType)) {            System.out.printf("Right side of %s must be of type %s.", "And", BooleanType.name);            System.exit(-1);        }        return new BooleanType();    }    @Override    public Type visit(Or n) {        return null;    }    public Type visit(LessThan n) {        if (!(n.lhs.accept(this) instanceof IntegerType)) {            System.out.printf("Left side of %s must be of type %s.", "LessThan", IntegerType.name);            System.exit(-1);        }        if (!(n.rhs.accept(this) instanceof IntegerType)) {            System.out.printf("Right side of %s must be of type %s.", "LessThan", IntegerType.name);            System.exit(-1);        }        return new BooleanType();    }    @Override    public Type visit(Equals n) {        return null;    }    // Exp lhs,rhs;    public Type visit(Plus n) {        if (!(n.lhs.accept(this) instanceof IntegerType)) {            System.out.println("Left side of LessThan must be of type integer");            System.exit(-1);        }        if (!(n.rhs.accept(this) instanceof IntegerType)) {            System.out.println("Right side of LessThan must be of type integer");            System.exit(-1);        }        return new IntegerType();    }    @Override    public Type visit(PlusEquals n) {        return null;    }    // Exp lhs,rhs;    public Type visit(Minus n) {        if (!(n.lhs.accept(this) instanceof IntegerType)) {            System.out.println("Left side of LessThan must be of type integer");            System.exit(-1);        }        if (!(n.rhs.accept(this) instanceof IntegerType)) {            System.out.println("Right side of LessThan must be of type integer");            System.exit(-1);        }        return new IntegerType();    }    // Exp lhs,rhs;    public Type visit(Times n) {        if (!(n.lhs.accept(this) instanceof IntegerType)) {            System.out.println("Left side of LessThan must be of type integer");            System.exit(-1);        }        if (!(n.rhs.accept(this) instanceof IntegerType)) {            System.out.println("Right side of LessThan must be of type integer");            System.exit(-1);        }        return new IntegerType();    }    // Exp lhs,rhs;    public Type visit(ArrayLookup n) {        if (!(n.lhs.accept(this) instanceof IntArrayType)) {            System.out.println("Left side of LessThan must be of type integer");            System.exit(-1);        }        if (!(n.rhs.accept(this) instanceof IntegerType)) {            System.out.println("Right side of LessThan must be of type integer");            System.exit(-1);        }        return new IntegerType();    }    // Exp lhs;    public Type visit(ArrayLength n) {        if (!(n.exp.accept(this) instanceof IntArrayType)) {            System.out.println("Left side of LessThan must be of type integer");            System.exit(-1);        }        return new IntegerType();    }    // Exp lhs;    // Identifier identifier;    // ExpList expList;    public Type visit(Call n) {        if (!(n.exp.accept(this) instanceof IdentifierType)) {            System.out.println("method " + n.identifier.toString() + "called  on something that is not a" + " class or Object.");            System.exit(-1);        }        String mname = n.identifier.toString();        String cname = ((IdentifierType) n.exp.accept(this)).string;        RamMethod calledMethod = TypeCheckVisitor.symbolTable.getMethod(mname, cname);        for (int i = 0; i < n.expList.size(); i++) {            Type t1 = null;            Type t2 = null;            if (calledMethod.getParameterAt(i) != null) {                t1 = calledMethod.getParameterAt(i).type();            }            t2 = n.expList.elementAt(i).accept(this);            if (!TypeCheckVisitor.symbolTable.compareTypes(t1, t2)) {                System.out.println("Type Error in arguments passed to " + cname + "." + mname);                System.exit(-1);            }        }        return TypeCheckVisitor.symbolTable.getMethodType(mname, cname);    }    // int identifier;    public Type visit(IntegerLiteral n) {        return new IntegerType();    }    public Type visit(True n) {        return new BooleanType();    }    public Type visit(False n) {        return new BooleanType();    }    // String string;    public Type visit(IdentifierExp n) {        return TypeCheckVisitor.symbolTable.getVarType(TypeCheckVisitor.currentMethod, TypeCheckVisitor.currentClass, n.string);    }    public Type visit(This n) {        return TypeCheckVisitor.currentClass.type();    }    // Exp lhs;    public Type visit(NewArray n) {        if (!(n.exp.accept(this) instanceof IntegerType)) {            System.out.println("Left side of LessThan must be of type integer");            System.exit(-1);        }        return new IntArrayType();    }    // Identifier identifier;    public Type visit(NewObject n) {        return new IdentifierType(n.identifier.string);    }    // Exp lhs;    public Type visit(Not n) {        if (!(n.exp.accept(this) instanceof BooleanType)) {            System.out.println("Left side of LessThan must be of type integer");            System.exit(-1);        }        return new BooleanType();    }}