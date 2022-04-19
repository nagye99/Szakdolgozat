package model.algorithm;

import model.train.ProblemTrain;
import model.train.StateTrain;
import model.train.cost.CostFunction;
import model.train.Successor;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class DepthFirst {

    public Optional<List<StateTrain>> run(ProblemTrain problemTrain, CostFunction costFunction) {
        LinkedList<Node> frontier = new LinkedList<>();
        GenerateInit method = new GenerateInit();
        LinkedList<StateTrain> solution = new LinkedList<>();
        Node init = method.run(problemTrain, costFunction);
        solution.add(init.getState());
        double limit = init.getPathCost();

        long startTime = System.nanoTime();
        long totalTime = 0;
        frontier.add(new Node(problemTrain.newStartState(), null, null, costFunction));
        while (TimeUnit.NANOSECONDS.toSeconds(totalTime) < 60) {
            if (frontier.isEmpty()) {
                return solution.size() == 0 ? Optional.empty() : Optional.of(solution);
            }
            Node selected = frontier.removeLast();
            if (selected.getPathCost() < limit) {
                if (selected.getState().isGoal()) {
                    limit = selected.getPathCost();
                    solution.add(selected.getState());
                }

                for (Successor successor : problemTrain.getSuccessors(selected.getState())) {
                    frontier.add(new Node(successor.getState(), successor.getCreator(), selected, costFunction));
                }
            }
            long endTime = System.nanoTime();
            totalTime = endTime - startTime;
        }
        return solution.size() == 0 ? Optional.empty() : Optional.of(solution);
    }
}