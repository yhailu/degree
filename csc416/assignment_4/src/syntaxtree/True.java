package syntaxtree;

import visitor.Visitor;

public class True extends Exp {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
