package model.algorithm;

import model.train.ProblemTrain;
import model.train.StateTrain;
import model.train.cost.CostFunction;
import model.train.OperatorTrain;

import java.util.*;

public class GenerateInit {
    public Node run(ProblemTrain problem, CostFunction cost) {
        Random rand = new Random();
        Set<Integer> s = new HashSet<>();
        ArrayList<Integer> passengerList = problem.passengerList();
        int helyek = 0;

        for(int i=0; i<passengerList.size(); i++){
            helyek += passengerList.get(i);
        }

        while(s.size() != helyek){
            s.add(rand.nextInt(problem.seatsNumber()) + 1);
        }

        Node currentState = new Node(problem.newStartState(), null, null, cost);
        for(int i : s){
            int block = (int)Math.ceil(i/4.0);
            OperatorTrain operator =  new OperatorTrain(block, i-4*(block-1));
            StateTrain selected = operator.apply(currentState.getState());
            currentState = new Node(selected, operator, currentState, cost);
        }

        System.out.println(currentState.getState());
        return currentState;
    }
}
