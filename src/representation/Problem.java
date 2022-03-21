package representation;

import train.StateTrain;

public interface Problem<S extends State, O extends Operator> {

    S newStartState();

    Iterable<Successor<S, O>> getSuccessors(StateTrain state);


}
