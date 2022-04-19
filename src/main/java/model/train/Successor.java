package model.train;

public class Successor {

    OperatorTrain creator;

    StateTrain state;

    public Successor(StateTrain state, OperatorTrain creator) {
        this.state = state;
        this.creator = creator;
    }

    public OperatorTrain getCreator() {
        return creator;
    }

    public StateTrain getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Successor{" +
                "creator=" + creator +
                ", state=" + state +
                '}';
    }
}
