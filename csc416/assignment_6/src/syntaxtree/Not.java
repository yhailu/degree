package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

public class Not extends Exp {

    public Exp exp;

    public Not(Exp exp) {
        this.exp = exp;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Type accept(TypeVisitor visitor) {
        return visitor.visit(this);
    }

}
