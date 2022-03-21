package representation;

import train.StateTrain;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractProblem<S extends State, O extends Operator<S>> implements Problem<S, O> {

    final List<O> operators;

    protected AbstractProblem(List<O> operators) {
        this.operators = operators;
    }

    @Override
    public Iterable<Successor<S, O>> getSuccessors(StateTrain state) {
        ArrayList<Successor<S, O>> successors = new ArrayList<>();

        int maxFog=0;
        int j=state.getState().length-1;
        while(j>=0 && state.getState()[j] != 1){
            j--;
        } if(j>=0){
            maxFog = j;
        }
        for (O op : operators) {
            if (op.isApplicable(state, maxFog)) {
                successors.add( new Successor<S, O>(
                        op.apply(state), op));
            }
        }
        return successors;
    }

}
