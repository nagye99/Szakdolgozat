package model.train;

import model.algorithm.DepthFirst;
import model.train.cost.SampleCost;

import java.util.List;
import java.util.Optional;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class TrainMain {

    public static void main(String[] args) {

        ArrayList<Integer> foglalas = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                foglalas.add(sc.nextInt());
            } catch (InputMismatchException e) {
                break;
            }
        }
        //System.out.println(foglalas);


        long startTime = System.nanoTime();
        ProblemTrain problem = new ProblemTrain(40, foglalas);
        SampleCost cost = new SampleCost();
        DepthFirst method = new DepthFirst();
        Optional<List<StateTrain>> goalState = method.run(problem, cost);
        if (goalState.isPresent()) {
            System.out.println("Goal:  " + goalState.get().get(goalState.get().size()-1));
        } else {
            System.out.println("No goal state.");
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(TimeUnit.NANOSECONDS.toSeconds(totalTime));
    }
}
