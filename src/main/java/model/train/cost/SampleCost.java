package model.train.cost;

import model.train.StateTrain;
import model.train.OperatorTrain;

public class SampleCost implements CostFunction {

    public double getCost(StateTrain state, OperatorTrain operator) {
        int block = operator.block();
        int hely = operator.place();
        int[] allapot = state.getState();
        int blocksNum = (int)Math.ceil(allapot.length/4.0)-1;

        int osszeg = 0;

        for (int k = 1; k < allapot.length; k++) {
            if (allapot[k] == 1) {
                osszeg += Math.pow(2, cost(k, block, blocksNum, hely));
            }
        }
        return osszeg;
    }

    private static int cost(int seatOne, int groupTwo, int blocksNum, int j) {
        int groupOne = (int)Math.ceil(seatOne/4.0);

        if (j == 0 || groupOne != groupTwo) {
            if (groupOne % 2 == groupTwo % 2 || (groupOne < groupTwo && groupTwo % 2 == 0) || (groupTwo < groupOne && groupOne % 2 == 0)) {
                return (blocksNum - Math.abs(groupOne - groupTwo));
            } else {
                if (groupOne < groupTwo) {
                    return (blocksNum - (groupTwo - groupOne + 2));
                } else {
                    return (blocksNum - (groupOne - groupTwo + 2));
                }
            }
        } else {
            int seatTwo = 4*(groupTwo-1)+j;

            if(seatOne %2 == seatTwo %2){
                return blocksNum + 4;
            } else if((seatOne +1 == seatTwo && seatOne%2==1) || (seatOne -1 == seatTwo && seatTwo%2==1)){
                return blocksNum + 5;
            }else{
                return blocksNum + 3;
            }
        }
    }
    //}
}

