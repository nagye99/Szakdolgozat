package representation;

import train.StateTrain;

public interface Operator<S extends State> {

    boolean isApplicable(StateTrain s, int maxFog);

    S apply(StateTrain s);

}
