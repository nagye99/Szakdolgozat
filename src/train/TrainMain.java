package train;

import algorithm.UniformCostSearch;
import train.cost.SampleTrain;

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
        ProblemTrain problem = new ProblemTrain(40, foglalas, new SampleTrain());
        //SampleTrain train.cost = new SampleTrain();
        UniformCostSearch method = new UniformCostSearch();
        Optional<List<OperatorTrain>> goalState = method.run(problem, new SampleTrain());
        if (goalState.isPresent()) {
            System.out.println("Goal:  " + goalState.get());
        } else {
            System.out.println("No goal state.");
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(TimeUnit.NANOSECONDS.toSeconds(totalTime));
    }
}
