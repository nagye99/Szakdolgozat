package train;

import representation.Operator;

public class OperatorTrain implements Operator<StateTrain> {
    private final int i;

    public OperatorTrain(int i){
        this.i = i;
    }



    public int getI(){
        return i;
    }

    public boolean isApplicable(StateTrain state, int maxFog){
        int a[] = state.a;
        return a[this.i] != 1 && this.i > maxFog;
    }

    public StateTrain apply(StateTrain state){
        StateTrain newState = new StateTrain(state);
        int b[] = newState.a;

        b[this.i] = 1;

        return newState;
    }

    public String toString(){
        return i + ". hely lefoglalva";
    }
}
