package representation;

public class Successor<S extends State, O extends Operator> {

    O creator;

    S state;

    public Successor(S state, O creator) {
        this.state = state;
        this.creator = creator;
    }

    public O getCreator() {
        return creator;
    }

    public S getState() {
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
