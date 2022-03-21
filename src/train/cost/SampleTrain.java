package train.cost;

import representation.State;
import train.OperatorTrain;

public class SampleTrain {
    public <S extends State> double getCost(S State, OperatorTrain op) {
        int j = op.getI();
        int[] state = State.getState();

        int osszeg = 0;
        int magassag = 0;

        for (int i = 1; i <= 40; i++) {
            if (state[i] == 1) {
                osszeg += Math.pow(2,cost(i, j));
                magassag++;
            }
        }
        return osszeg;
    }

    public static int cost(int seatOne, int seatTwo) {
        double groupOne = (seatOne -1)/4+1;
        double groupTwo = (seatTwo -1)/4+1;

        if(groupOne == groupTwo){
            if(seatOne %2 == seatTwo %2){
                return 14;
            } else if(seatOne +1 == seatTwo || seatOne -1 == seatTwo){
                return 15;
            }else{
                return 13;
            }
        }else{
            if(groupOne%2 == groupTwo%2 || (groupOne<groupTwo && groupTwo%2==0) || (groupTwo<groupOne && groupOne%2==0)){
                return (int) (10 - Math.abs(groupOne - groupTwo));
            }else{
                if(groupOne<groupTwo){
                    return (int) (10-(groupTwo-groupOne+2));
                }else{
                    return (int) (10-(groupOne-groupTwo+2));
                }
            }
        }
    }
}

