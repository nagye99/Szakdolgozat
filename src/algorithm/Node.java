package algorithm;

import train.cost.SampleTrain;
import representation.Successor;
import train.OperatorTrain;
import train.StateTrain;

import java.util.LinkedList;
import java.util.List;

public class Node {

    private final StateTrain state;

    private final OperatorTrain creator;

    private final Node parent;

    private final double pathCost;

    Node(StateTrain state, OperatorTrain creator, Node parent, SampleTrain cost) {
        this.state = state;
        this.creator = creator;
        this.parent = parent;
        this.pathCost = parent == null ? 0 : parent.pathCost + cost.getCost(parent.getState(), creator);
    }

    StateTrain getState() {
        return state;
    }

    OperatorTrain getCreator() {
        return creator;
    }

    double getPathCost(){
        return pathCost;
    }


    Node newChild(Successor<StateTrain,OperatorTrain> successor){
        return new Node(successor.getState(), successor.getCreator(), this, new SampleTrain());
    }

    List<OperatorTrain> getPath() {
        LinkedList<OperatorTrain> path = new LinkedList<>();
        for (Node n = this; n.parent != null; n = n.parent ) {
            path.addFirst(n.creator);
        }
        return path;
    }

}
