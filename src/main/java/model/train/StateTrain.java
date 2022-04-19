package model.train;

import java.util.ArrayList;
import java.util.Arrays;

public class StateTrain {

    private final int[] state;
    private final ArrayList<Integer> passengerList;

public StateTrain(int n, ArrayList<Integer> utaslista) {
    state = new int[n + 1];
    this.passengerList = utaslista;


    for (int i = 1; i <= n; i++) {
        state[i] = 0;
    }
}

    StateTrain(StateTrain state) {
    this.state = Arrays.copyOf(state.state,  state.state.length);
    passengerList = state.passengerList;
}

    public int[] getState() {
        return state;
    }

    public boolean isGoal() {
        int passenger = 0;
        int helyek = 0;
        for (int i = 1; i <= state.length - 1; i++) {
            passenger += state[i];
        }
        for(int group: passengerList){
            helyek += group;
        }
        return passenger == helyek;
    }

    @Override
    public String toString() {
    StringBuilder fogl = new StringBuilder();
    for (int i = 0; i< state.length; i++){
        if(state[i] == 1){
            fogl.append(i).append(". hely foglalt ");
        }
    }
        return "StateTrain{" +
                "a=" + Arrays.toString(state) +
                '}' + "\n" +fogl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateTrain state = (StateTrain) o;
        return Arrays.equals(this.state, state.state);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(state);
    }
}
