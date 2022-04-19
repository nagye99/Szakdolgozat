package model.train;

import java.util.Random;

public record OperatorTrain (int block, int place) {
    private static Random rand = new Random();

    public boolean isApplicable(StateTrain state, int maxFog){
        int blockMaxFog = (int) Math.ceil(maxFog / 4.0);
        int[] a = state.getState();

        if (this.block >= blockMaxFog) {
            if (this.place == 0) {
                for (int k = 1; k <= 4; k++) {
                    if (a[4 * (this.block - 1) + k] == 1) {
                        return false;
                    }
                }
                return true;
            } else {
                for (int k = 1; k <= 4; k++) {
                    if (a[4 * (this.block - 1) + k] != 0) {
                        return a[4 * (this.block - 1) + place] != 1;
                    }
                }
                return false;
            }
        }
        return false;
    }

    public StateTrain apply(StateTrain state) {
        StateTrain newState = new StateTrain(state);
        int[] b = newState.getState();

        if (this.place == 0) {
            int r = rand.nextInt(4)+1;

            b[4*(this.block - 1) + r] = 1;
        } else {
            b[4*(this.block - 1) + place] = 1;
        }


        return newState;
    }

    public String toString(){
        return block + ". blokkban hely lefoglalva " + place;
    }
}
