package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;


public class Block extends Statement {
    public StatementList statementList;

    public Block(StatementList statementList) {
        this.statementList = statementList;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Type accept(TypeVisitor visitor) {
        return visitor.visit(this);
    }


}

