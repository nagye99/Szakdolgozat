package model.train;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public record ProblemTrain(int seatsNumber, ArrayList<Integer> passengerList) {
    private static List<OperatorTrain> operators;

    public ProblemTrain(int seatsNumber, ArrayList<Integer> passengerList){
        this.seatsNumber = seatsNumber;
        this.passengerList = passengerList;
        operators = operators((int)Math.ceil(seatsNumber /4.0));
    }

    private static List<OperatorTrain> operators(int n) {
    List<OperatorTrain> operators = new ArrayList<>();
        for(int i=1; i<=n; i++) {
            for (int j = 0; j <= 4; j++) {
                operators.add(new OperatorTrain(i, j));
            }
        }
    return operators;
    }

    public Iterable<Successor> getSuccessors(StateTrain state) {
        LinkedList<Successor> successors = new LinkedList<>();

        int maxFog = 0;
        int j = state.getState().length - 1;
        while(j >= 0 && state.getState()[j] != 1){
            j--;
        } if(j >= 0){
            maxFog = j;
        }
        for (OperatorTrain operator : operators) {
            if (operator.isApplicable(state, maxFog)) {
                successors.addFirst( new Successor(
                        operator.apply(state), operator));
            }
        }
        return successors;
    }

    public StateTrain newStartState() {
        return new StateTrain(seatsNumber, passengerList);
    }
}
