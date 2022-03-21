package train;

import train.cost.SampleTrain;
import representation.AbstractProblem;
import representation.State;

import java.util.ArrayList;
import java.util.List;

public class ProblemTrain extends AbstractProblem<StateTrain, OperatorTrain> {

    private final int n;
    private final SampleTrain costFunction;
    private final ArrayList<Integer> utaslista;

    protected ProblemTrain(int n, ArrayList<Integer> utaslista, SampleTrain costFunction) {
        super(operators(n));
        this.n = n;
        this.costFunction = costFunction;
        this.utaslista = utaslista;
    }

    private static List<OperatorTrain> operators(int n){
    List<OperatorTrain> operators = new ArrayList<>();
    for(int i=1; i<=n; i++){
        operators.add(new OperatorTrain(i));
    }
    return operators;
    }

    /*public <S extends State> int getCost(S State, OperatorTrain op) {
        int j = op.getI();
        int[] state = State.getState();

        int osszeg = 0;

        for (int i = 1; i <= 40; i++) {
            if (state[i] == 1) {
                osszeg += costFunction.cost(i, j);
            }
        }
        return osszeg;
    }*/


    @Override
    public StateTrain newStartState() {
        return  new StateTrain(n, utaslista);
    }
}
