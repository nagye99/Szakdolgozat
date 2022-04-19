package model.algorithm;

import model.train.StateTrain;
import model.train.cost.CostFunction;
import model.train.cost.SampleCost;
import model.train.Successor;
import model.train.OperatorTrain;

import java.util.LinkedList;
import java.util.List;

public class Node {

    private final StateTrain state;

    private final OperatorTrain creator;

    private final Node parent;

    private final double pathCost;

    private int melyseg;

    Node(StateTrain state, OperatorTrain creator, Node parent, CostFunction cost) {
        this.state = state;
        this.creator = creator;
        this.parent = parent;
        this.pathCost = parent == null ? 0 : parent.pathCost + cost.getCost(parent.getState(), creator);
        this.melyseg = parent == null ? 0 : parent.melyseg + 1;
    }

    public int getMelyseg() {
        return melyseg;
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

    public Node getParent() {
        return parent;
    }

    Node newChild(Successor successor){
        return new Node(successor.getState(), successor.getCreator(), this, new SampleCost());
    }

    List<OperatorTrain> getPath() {
        LinkedList<OperatorTrain> path = new LinkedList<>();
        for (Node n = this; n.parent != null; n = n.parent ) {
            path.addFirst(n.creator);
        }
        return path;
    }

}
