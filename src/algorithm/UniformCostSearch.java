package algorithm;

import train.cost.SampleTrain;
import representation.Problem;
import representation.Successor;
import train.OperatorTrain;
import train.StateTrain;

import java.util.*;

public class UniformCostSearch {

    public Optional<List<OperatorTrain>> run(Problem<StateTrain, OperatorTrain> p, SampleTrain cost) {

        LinkedList<Node> frontier = new LinkedList<>();
        frontier.add(new Node(p.newStartState(), null, null, cost));
        Node goal;
        Frontier<Node> fr = new Frontier<>() {
            @Override
            public boolean isEmpty() {
                return frontier.isEmpty();
            }

            @Override
            public void add(Node node) {
                frontier.add(node);
            }

            @Override
            public Node removeNext() {
                Node min = frontier.stream().min((a, b) -> Double.compare(a.getPathCost(), b.getPathCost())).get();
                frontier.remove(min);
                return min;
            }
        };//);
        Set<StateTrain> explored = new HashSet<StateTrain>();
        // CONTROLLER:
        // Supposing that the frontier has initialized with the help of the start state
        while (true) {
            // 2.
            if (frontier.isEmpty()) {
                goal = null;
                break;
            }
            // 3.
            Node selected = fr.removeNext();
            // 4.
            if (selected.getState().isGoal()) {
                goal = selected;
                break;
            }
            // 5.
            if (!explored.contains(selected.getState())) {
                explored.add(selected.getState());
                for (Successor<StateTrain, OperatorTrain> successor : p.getSuccessors(selected.getState())) {
                    fr.add(selected.newChild(successor));
                }
            }
        }

        return goal == null ? Optional.empty() : Optional.of(goal.getPath());
    }

}
