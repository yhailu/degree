package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

public class Plus extends Exp {

    public Exp lhs;
    public Exp rhs;

    public Plus(Exp lhs, Exp rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Type accept(TypeVisitor visitor) {
        return visitor.visit(this);
    }

}
