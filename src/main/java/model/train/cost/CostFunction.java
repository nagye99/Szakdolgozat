package model.train.cost;

import model.train.OperatorTrain;
import model.train.StateTrain;

public interface CostFunction {
    public double getCost(StateTrain state, OperatorTrain operator);
}
