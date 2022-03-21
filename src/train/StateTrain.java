package train;

import representation.State;

import java.util.ArrayList;
import java.util.Arrays;

public class StateTrain implements State {

    final int[] a;
    ArrayList<Integer> utaslista;

public StateTrain(int n, ArrayList<Integer> utaslista) {
    a = new int[n + 1];
    this.utaslista = utaslista;


    for (int i = 1; i <= n; i++) {
        a[i] = 0;
    }
}

    StateTrain(StateTrain state){a = Arrays.copyOf(state.a,  state.a.length); utaslista= state.utaslista;}

    @Override
    public int[] getState() {
        return a;
    }

    public boolean isGoal() {
        int passenger = 0;
        int helyek = 0;
        for (int i = 1; i <= a.length - 1; i++) {
            passenger += a[i];
        }
        for(int i=0; i<utaslista.size(); i++){
            helyek += utaslista.get(i);
        }
        return passenger == helyek;
    }

    @Override
    public String toString() {
        return "StateTrain{" +
                "a=" + Arrays.toString(a) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateTrain state = (StateTrain) o;
        return Arrays.equals(a, state.a);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(a);
    }
}
